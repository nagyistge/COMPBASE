package uzuzjmd.competence.mapper.gui

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.service.rest.dto.ProgressMap
import uzuzjmd.competence.owl.dao.CourseContext

class Ont2ProgressMap(comp: CompOntologyManager, val course: String) {
  def getProgressMap(comp: CompOntologyManager): ProgressMap = {
    comp.begin()

    comp.close()
    return null
  }

  def getNumberOfCompetencesLinkedToCourse(): Integer = {
    val courseDao = new CourseContext(comp, course)
    return courseDao.getLinkedCompetences.length
  }

  def getUserNumberOfLinksMap() {

  }
}