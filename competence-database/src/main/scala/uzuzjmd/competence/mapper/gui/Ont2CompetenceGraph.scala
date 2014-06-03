package uzuzjmd.competence.mapper.gui

import scala.collection.JavaConverters._
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.service.rest.client.Graph

class Ont2CompetenceGraph(comp: CompOntologyManager, selectedCompetences: java.util.List[String], course: String) {
  def getCompetenceGraph(): Graph = {
    comp.begin();
    val courseContext = new CourseContext(comp, course)
    val comptenes = courseContext.getLinkedCompetences.view.filter(selectionFilter).filter(x => x.isLinkedAsRequired)
    val result = new Graph()
    val requiredmap = comptenes.map(x => (x, x.getRequiredCompetences)).foreach(y => y._2.foreach(z => result.addTriple(z.getDataField(z.DEFINITION), y._1.getDataField(y._1.DEFINITION), "Voraussetzung für", true)))
    comp.close()
    return result;
  }

  def selectionFilter(x: Competence): Boolean = {
    return selectedCompetences.contains(x.getDataField(x.definition)) || selectedCompetences.isEmpty()
  }

}