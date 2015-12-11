package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, TDBReadTransactional}
import uzuzjmd.competence.persistence.dao.{Competence, CourseContext}
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl
import uzuzjmd.competence.shared.dto.Graph


class Ont2CompetenceGraph(comp: CompOntologyManagerJenaImpl, selectedCompetences: java.util.List[String], course: String)  extends TDBReadTransactional[Any, Graph]{
  
  def getCompetenceGraph(): Graph = {
    return executeNoParam(getCompetenceGraphInternal _)
  }

  def selectionFilter(x: Competence): Boolean = {
    val competenceDefinition = x.getDataField(x.DEFINITION)
    return selectedCompetences.contains(competenceDefinition) || selectedCompetences.isEmpty()
  }
  
  def getCompetenceGraphInternal(comp : CompOntologyManager) : Graph = {
    val courseContext = new CourseContext(comp, course)
    val comptenes = courseContext.getLinkedCompetences.view.filter(x => x.isLinkedAsRequired).filter(selectionFilter)
    val result = new Graph()
    val requiredmap = comptenes.map(x => (x, x.getRequiredCompetences)).foreach(y => y._2.foreach(z => result.addTriple(z.getDataField(z.DEFINITION), y._1.getDataField(y._1.DEFINITION), "Voraussetzung für", true)))
    return result 
  }

}