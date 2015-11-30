package uzuzjmd.competence.mapper.gui.write

import scala.collection.JavaConverters._
import javax.ws.rs.WebApplicationException
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.owl.dao.Catchword
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import uzuzjmd.competence.owl.dao.SelectedLearningProjectTemplate
import uzuzjmd.competence.owl.dao.TeacherRole
import uzuzjmd.competence.owl.dao.User
import uzuzjmd.competence.service.rest.model.dto.LearningTemplateData
import uzuzjmd.competence.shared.dto.Graph
import uzuzjmd.competence.shared.dto.GraphTriple
import uzuzjmd.competence.shared.dto.LearningTemplateResultSet
import uzuzjmd.competence.owl.access.PerformanceTimer
import uzuzjmd.competence.owl.access.AbstractTimer
import uzuzjmd.competence.owl.dao.exceptions.OntClassForDaoNotInitializedException
import uzuzjmd.competence.owl.dao.exceptions.UserNotExistsException
import uzuzjmd.competence.owl.dao.exceptions.ContextNotExistsException
import uzuzjmd.competence.owl.ontology.CompObjectProperties

/**
 * @author dehne
 *
 * Dieses Objekt konvertiert
 */
object LearningTemplateToOnt extends TDBWriteTransactional[LearningTemplateData] with PerformanceTimer[LearningTemplateData, Unit] {

  def convert(changes: LearningTemplateData) {
    execute(convertHelper1 _, changes)
    execute(convertHelper _, changes)
  }

  private def convertHelper1(comp: CompOntologyManager, changes: LearningTemplateData) {
    val context = new CourseContext(comp, changes.getGroupId);
    context.persist()
    val user = new User(comp, changes.getUserName, new TeacherRole(comp), context, changes.getUserName);
    user.persist()
    context.createEdgeWith(CompObjectProperties.CourseContextOf, user)
  }

  def convertLearningTemplateResultSet(learningTemplateResultSet: LearningTemplateResultSet) {
    executeX[LearningTemplateResultSet](convertLearningTemplateResultSet, learningTemplateResultSet)
  }

  private def convertHelper(comp: CompOntologyManager, changes: LearningTemplateData) {

    val context = new CourseContext(comp, changes.getGroupId);
    val user = new User(comp, changes.getUserName, new TeacherRole(comp), context, changes.getUserName);
    if (!user.exists()) {
      throw new UserNotExistsException
    }

    if (!context.exists()) {
      throw new ContextNotExistsException
    }
    val selected = new SelectedLearningProjectTemplate(comp, user, context, null, null);
    selected.persist();
    val learningTemplate = new LearningProjectTemplate(comp, changes.getSelectedTemplate, null, changes.getSelectedTemplate);
    selected.addAssociatedTemplate(learningTemplate);
  }

  private def convertLearningTemplateResultSet(comp: CompOntologyManager, learningTemplateResultSet: LearningTemplateResultSet) {
    convertLearningTemplate(comp, learningTemplateResultSet.getResultGraph, learningTemplateResultSet.getCatchwordMap, learningTemplateResultSet.getNameOfTheLearningTemplate)
  }

  private def convertLearningTemplate(comp: CompOntologyManager, graph: Graph, tripleCatchwordMap: java.util.HashMap[GraphTriple, Array[String]], learningTemplateName: String) {
    if (graph != null) {
      if (graph.triples != null) {
        if (!graph.triples.asScala.forall { x => tripleCatchwordMap.keySet().asScala.contains(x) }) {
          logger.error("The triples are: " + graph.triples)
          val theProblemTriple = graph.triples.asScala.filter(x => tripleCatchwordMap.keySet().asScala.contains(x) )
          logger.error("the problem triple is:" + theProblemTriple)
          throw new WebApplicationException(new Exception("All the triples must be contained in the catchwordMap"))
        }

        graph.triples.asScala.foreach { x => convertTriple(x, comp, tripleCatchwordMap) }
        val template = new LearningProjectTemplate(comp, learningTemplateName, graph.nodes.asScala.map(x => new Competence(comp, x.getLabel, x.getLabel, null)).toList, learningTemplateName)
        template.persist()
      }
    }
    
    else {
        val learningProjectTemplate = new LearningProjectTemplate(comp, learningTemplateName, null, null);      
        learningProjectTemplate.persist()
    }
  }

  private def convertTriple(triple: GraphTriple, comp: CompOntologyManager, tripleCatchwordMap: java.util.HashMap[GraphTriple, Array[String]]) {
    val competenceFrom = new Competence(comp, triple.fromNode, triple.fromNode, null)
    val competenceTo = new Competence(comp, triple.toNode, triple.toNode, null)
    competenceTo.addSuggestedCompetenceRequirement(competenceFrom)
    val catchwords = tripleCatchwordMap.get(triple)

    catchwords.foreach { x => competenceFrom.addCatchword(new Catchword(comp, x, x)) }
    catchwords.foreach { x => competenceTo.addCatchword(new Catchword(comp, x, x)) }
    competenceTo.persist(true)
    competenceFrom.persist(true)
  }

}