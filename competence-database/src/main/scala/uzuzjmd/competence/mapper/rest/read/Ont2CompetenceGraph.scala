package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.config.Logging
import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, ReadTransactional}
import uzuzjmd.competence.persistence.dao.{Competence, CourseContext}
import uzuzjmd.competence.service.rest.dto.GraphFilterData
import uzuzjmd.competence.shared.dto.Graph


object Ont2CompetenceGraph extends ReadTransactional[GraphFilterData, Graph] with Logging{

  def convert(changes:GraphFilterData): Graph = {
    return executeNoParam(getCompetenceGraphInternal(_, changes))
  }

  def selectionFilter(x: Competence, changes:GraphFilterData): Boolean = {
    val competenceDefinition = x.getDataField(x.DEFINITION)
    return changes.getCompetencesSelected.contains(competenceDefinition) || changes.getCompetencesSelected.isEmpty()
  }
  
  def getCompetenceGraphInternal(comp : CompOntologyManager, changes: GraphFilterData) : Graph = {
    val courseContext = new CourseContext(comp, changes.getCourse)
    val competences = courseContext.getLinkedCompetences.view.filter(x => x.isLinkedAsRequired).filter(selectionFilter(_,changes))
    logger.debug("Filtered Competences are: " + competences.toList)
    val result = new Graph()
    competences.map(x => (x, x.getRequiredCompetences)).foreach(y => y._2.foreach(z => result.addTriple(z.getDataField(z.DEFINITION), y._1.getDataField(y._1.DEFINITION), "Voraussetzung für", true)))
    logger.debug("triples are:" + result.triples)
    return result
  }

}