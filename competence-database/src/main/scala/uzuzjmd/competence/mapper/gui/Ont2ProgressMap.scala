package uzuzjmd.competence.mapper.gui

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.User
import scala.collection.JavaConverters._
import uzuzjmd.competence.owl.dao.StudentRole
import uzuzjmd.competence.service.rest.client.dto.ProgressMap

class Ont2ProgressMap(comp: CompOntologyManager, val course: String, val selectedCompetences: java.util.List[String]) {
  val courseDao = new CourseContext(comp, course)
  val linkedCompetences = courseDao.getLinkedCompetences
  val userOfCourseContext = courseDao.getLinkedUser.filter(x => x.role.equals(new StudentRole(comp)))

  def getProgressMap(): ProgressMap = {
    val numberOfLinks = getNumberOfCompetencesLinkedToCourse
    if (numberOfLinks == 0) {
      return new ProgressMap();
    }
    val resultScala: Map[User, java.lang.Double] = getUserNumberOfLinksMap.mapValues(x => Math.round(((x.toFloat / getNumberOfCompetencesLinkedToCourse.toFloat) * 100)))
    val resultScala2 = resultScala.map(x => (x._1.getDataField(x._1.NAME), x._2))
    val resultJava = new ProgressMap();
    resultJava.putAll(resultScala2.asJava)
    return resultJava
  }

  def getNumberOfCompetencesLinkedToCourse(): Integer = {
    if (selectedCompetences.isEmpty() || selectedCompetences == null) {
      return linkedCompetences.length
    }
    return linkedCompetences.filter(x => selectedCompetences.contains(x.getDataField(x.DEFINITION))).length
  }

  def getUserNumberOfLinksMap(): Map[User, Int] = {
    if (selectedCompetences.isEmpty() || selectedCompetences == null) {
      return userOfCourseContext.
        map(x => x.getFullDao).
        map(x => (x -> x.getAssociatedLinks.filter(link => link.getAllCourseContexts.contains(courseDao)).length)).toMap
    }

    return userOfCourseContext.
      map(x => x.getFullDao).
      map(x => (x -> x.getAssociatedLinks.
        filter(y => selectedCompetences.containsAll(y.getAllLinkedCompetences.map(competence => competence.getDataField(competence.DEFINITION)).asJava)).
        filter(link => link.getAllCourseContexts.contains(courseDao)).length)).toMap
  }
}