package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.persistence.abstractlayer.ReadTransactional
import uzuzjmd.competence.persistence.dao.CourseContext
import uzuzjmd.competence.service.rest.dto.mobile.CompetenceForCourseResultSet

/**
  * Created by dehne on 30.03.2016.
  */
object Ont2CompetencesForCourse extends ReadTransactional[String, CompetenceForCourseResultSet] {
    def convert(changes: String) : CompetenceForCourseResultSet = {
      val course = new CourseContext(changes)
      course.getLinkedCompetences
      return null
    }
}
