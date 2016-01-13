package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.config.Logging
import uzuzjmd.competence.monopersistence.daos.{Competence, CourseContext}
import uzuzjmd.competence.persistence.abstractlayer.ReadTransactional
import uzuzjmd.competence.service.rest.dto.GraphFilterData
import uzuzjmd.competence.shared.dto.Graph
import scala.collection.JavaConverters._


object Ont2CompetenceGraph extends ReadTransactional[GraphFilterData, Graph] with Logging{

  def convert(changes:GraphFilterData): Graph = {
    return execute(getCompetenceGraphInternal _, changes)
  }

  def selectionFilter(x: Competence, changes:GraphFilterData): Boolean = {
    val competenceDefinition = x.getDefinition
    return changes.getCompetencesSelected.contains(competenceDefinition) || changes.getCompetencesSelected.isEmpty()
  }
  
  def getCompetenceGraphInternal(changes: GraphFilterData) : Graph = {
    val courseContext = new CourseContext(changes.getCourse)
    val competences = courseContext.getLinkedCompetences.asScala.view.filter(x => x.isLinkedAsRequired).filter(selectionFilter(_,changes))
    logger.debug("Filtered Competences are: " + competences.toList)
    val result = new Graph()
    competences.map(x => (x, x.getRequiredCompetences)).foreach(y => y._2.asScala.foreach(z => result.addTriple(z.getDefinition, y._1.getDefinition, "Voraussetzung für", true)))
    logger.debug("triples are:" + result.triples)
    return result
  }

}