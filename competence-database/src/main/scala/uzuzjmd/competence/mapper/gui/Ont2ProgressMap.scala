package uzuzjmd.competence.mapper.gui

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.service.rest.dto.ProgressMap
import uzuzjmd.competence.owl.dao.CourseContext

class Ont2ProgressMap(comp: CompOntologyManager, val course: String, val selectedCompetences: java.util.List[String]) {
  val courseDao = new CourseContext(comp, course)
  val linkedCompetences = courseDao.getLinkedCompetences
  //  val userOfCourseContext = courseDao.get

  def getProgressMap(comp: CompOntologyManager): ProgressMap = {
    comp.begin()

    comp.close()
    return null
  }

  def getNumberOfCompetencesLinkedToCourse(): Integer = {
    return linkedCompetences.filter(x => selectedCompetences.contains(x.getDataField(x.DEFINITION))).length
  }

  def getUserNumberOfLinksMap() {

  }
}