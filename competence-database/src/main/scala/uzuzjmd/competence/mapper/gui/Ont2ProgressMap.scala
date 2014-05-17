package uzuzjmd.competence.mapper.gui

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.service.rest.dto.ProgressMap
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.User
import scala.collection.JavaConverters._

class Ont2ProgressMap(comp: CompOntologyManager, val course: String, val selectedCompetences: java.util.List[String]) {
  val courseDao = new CourseContext(comp, course)
  val linkedCompetences = courseDao.getLinkedCompetences
  val userOfCourseContext = courseDao.getLinkedUser

  def getProgressMap(): ProgressMap = {
    comp.begin()
    val resultScala: Map[User, java.lang.Double] = getUserNumberOfLinksMap.mapValues(x => ((x / getNumberOfCompetencesLinkedToCourse) * 100))
    val resultScala2 = resultScala.map(x => (x._1.getDataField(x._1.NAME), x._2))
    val resultJava = new ProgressMap();
    resultJava.putAll(resultScala2.asJava)
    comp.close()
    return resultJava
  }

  def getNumberOfCompetencesLinkedToCourse(): Integer = {
    return linkedCompetences.filter(x => selectedCompetences.contains(x.getDataField(x.DEFINITION))).length
  }

  def getUserNumberOfLinksMap(): Map[User, Int] = {
    return userOfCourseContext.map(x => x.getFullDao).map(x => (x -> x.getAssociatedLinks.filter(link => link.getAllCourseContexts.contains(courseDao)).length)).toMap
  }
}