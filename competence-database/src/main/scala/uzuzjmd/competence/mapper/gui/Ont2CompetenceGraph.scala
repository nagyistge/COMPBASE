package uzuzjmd.competence.mapper.gui

import scala.collection.JavaConverters._
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.service.rest.dto.Graph
import uzuzjmd.competence.owl.dao.CourseContext

class Ont2CompetenceGraph(comp: CompOntologyManager, selectedCompetences: java.util.List[String], course: String) {
  def getCompetenceGraph(): Graph = {
    comp.begin();
    val courseContext = new CourseContext(comp, course)
    val comptenes = courseContext.getLinkedCompetences.view.filter(x=>selectedCompetences.contains(x.getDataField(x.definition))).filter(x => x.isLinkedAsRequired)
    val result = new Graph()
    val requiredmap = comptenes.map(x => (x, x.getRequiredCompetences)).foreach(y => y._2.foreach(z => result.addTriple(z.getDataField(z.DEFINITION), y._1.getDataField(y._1.DEFINITION), "Voraussetzung für", true)))
    comp.close()
    return result;
  }
}