package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager
import uzuzjmd.competence.persistence.dao.{CourseContext, StudentRole, User}
import uzuzjmd.competence.shared.dto.ProgressMap

import scala.collection.JavaConverters.{mapAsJavaMapConverter, seqAsJavaListConverter}


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
      val result = userOfCourseContext.
        map(x => x.getFullDao).
        map(x => (x -> x.getAssociatedLinks.filter(link => link.getAllCourseContexts.contains(courseDao)).length)).toMap
      return result
    }

    val result = userOfCourseContext.
      map(x => x.getFullDao).
      map(x => (x -> x.getAssociatedLinks.
        filter(y => selectedCompetences.containsAll(y.getAllLinkedCompetences.map(competence => competence.getDataField(competence.DEFINITION)).asJava)).
        filter(link => link.getAllCourseContexts.contains(courseDao)).length)).toMap
    return result
  }
}