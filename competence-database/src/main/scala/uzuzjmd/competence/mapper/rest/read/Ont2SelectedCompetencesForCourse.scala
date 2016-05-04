package uzuzjmd.competence.mapper.rest.read

import com.google.common.collect.Lists
import com.liferay.portal.kernel.language.Language
import uzuzjmd.competence.persistence.abstractlayer.ReadTransactional
import uzuzjmd.competence.persistence.dao.CourseContext
import uzuzjmd.competence.service.rest.dto.CourseData
import uzuzjmd.competence.util.LanguageConverter
import scala.collection.JavaConverters._

import scala.collection.JavaConverters.asScalaBufferConverter

/**
 * @author dehne
 */
object Ont2SelectedCompetencesForCourse extends ReadTransactional[String, Array[String]] with LanguageConverter {
  def convert(changes: String): Array[String] = {
    execute(convertHelper _, changes)
  }

  def convertHelper(changes: String): Array[String] = {
      val course = new CourseContext(changes);
      return course.getLinkedCompetences.asScala.map(x=>x.getDefinition).toArray;
  }

  def convertDao(changes: String) : CourseData =  {
    val course = new CourseContext(changes);
    val competences: List[String] = course.getLinkedCompetences.asScala.map(x=>x.getDefinition).toList;
    course.getFullDao.getPrintableName();
    val courseData = new CourseData(course.getId, course.getPrintableName, competences.asJava);
    return courseData;
  }

}