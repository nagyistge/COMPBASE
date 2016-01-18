package uzuzjmd.competence.mapper.rest.write

import java.util

import sun.reflect.generics.reflectiveObjects.NotImplementedException
import uzuzjmd.competence.exceptions.{ContextNotExistsException, UserNotExistsException}
import uzuzjmd.competence.monopersistence.daos._
import uzuzjmd.competence.persistence.abstractlayer.WriteTransactional
import uzuzjmd.competence.persistence.neo4j.Neo4JQueryManagerImpl
import uzuzjmd.competence.persistence.ontology.CompObjectProperties
import uzuzjmd.competence.persistence.performance.PerformanceTimer
import uzuzjmd.competence.service.rest.dto.LearningTemplateData
import uzuzjmd.competence.shared.dto.{GraphTriple, LearningTemplateResultSet}
import scala.collection.JavaConverters._
import scala.collection.mutable

/**
 * @author dehne
 *
 * This object saves a learning template in the database
 */
object LearningTemplateToOnt extends WriteTransactional[LearningTemplateData] with PerformanceTimer[LearningTemplateData, Unit] {

  def convert(changes: LearningTemplateData) {
    execute(convertHelper1 _, changes)
    execute(convertHelper _, changes)
  }

  private def convertHelper1(changes: LearningTemplateData) {
    val context = new CourseContext(changes.getGroupId);
    context.persist()
    val user = new User(changes.getUserName, Role.teacher, context);
    user.persist()
    context.createEdgeWith(CompObjectProperties.CourseContextOf, user)
  }

  private def convertHelper(changes: LearningTemplateData) {
    val context = new CourseContext(changes.getGroupId);
    val user = new User(changes.getUserName, Role.teacher, context);
    if (!user.exists()) {
      throw new UserNotExistsException
    }
    if (!context.exists()) {
      throw new ContextNotExistsException
    }
    val selected = new SelectedLearningProjectTemplate(user, context, changes.getSelectedTemplate);
    selected.persist();
  }

  def toNode: (GraphTriple) => String = _.toNode
  def fromNode: (GraphTriple) => String = _.fromNode

  @throws[ContainsCircleException]
  def convertLearningTemplateResultSet(changes : LearningTemplateResultSet): Unit = {
    // case full set is given
    val triples: util.Set[GraphTriple] = changes.getResultGraph.triples
    if (triples != null && !triples.isEmpty) {
      val competences: util.List[Competence] = triples.asScala.map(x => x.fromNode :: x.toNode :: Nil).flatten.toList.distinct.map(x => new Competence(x)).asJava
      val template = new LearningProjectTemplate(changes.getNameOfTheLearningTemplate, competences);
      template.persist()

      // create the relations maybe use batch update if it is too slow
      val manager = new Neo4JQueryManagerImpl;
      triples.asScala.view.foreach(x=>manager.createRelationShip(x.fromNode, CompObjectProperties.SuggestedCompetencePrerequisiteOf, x.toNode))

      // create Catchword relations
      val map: util.HashMap[GraphTriple, Array[String]] = changes.getCatchwordMap
      createCatchwordRelations(map, toNode)
      createCatchwordRelations(map, fromNode)
    }

    // case only root was given
    val root: String = changes.getRoot.getLabel;
    if (root != null) {
      val template2 = new LearningProjectTemplate(changes.getNameOfTheLearningTemplate);
      val rootDao = new Competence(root, template2);
      rootDao.persist()
    }
  }



  def createCatchwordRelations(map: util.HashMap[GraphTriple, Array[String]], f : (GraphTriple => String)): Unit = {
    val competenceCatchwords = map.keySet().asScala.foreach(x => new Competence(f(x)).persist().createEdgeWithAll(getCatchwordsFromMap(map, x), CompObjectProperties.CatchwordOf))
  }

  private def getCatchwordsFromMap(map : util.HashMap[GraphTriple, Array[String]], triple: GraphTriple) : util.List[Dao] ={
     return map.get(triple).map(y=>new Catchword(y).persist()).toList.asJava
  }
}