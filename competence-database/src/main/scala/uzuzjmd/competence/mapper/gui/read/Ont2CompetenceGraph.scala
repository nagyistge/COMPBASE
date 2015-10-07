package uzuzjmd.competence.mapper.gui.read

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.TDBREADTransactional
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.shared.dto.Graph


class Ont2CompetenceGraph(comp: CompOntologyManager, selectedCompetences: java.util.List[String], course: String)  extends TDBREADTransactional[Any, Graph]{
  
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