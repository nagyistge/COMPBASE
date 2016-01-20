package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.persistence.abstractlayer.ReadTransactional
import uzuzjmd.competence.persistence.dao.CourseContext

import scala.collection.JavaConverters.asScalaBufferConverter

/**
 * @author dehne
 */
object Ont2SelectedCompetencesForCourse extends ReadTransactional[String, Array[String]] {
  def convert(changes: String): Array[String] = {
    execute(convertHelper _, changes)
  }

  def convertHelper(changes: String): Array[String] = {
      val course = new CourseContext(changes);
      return course.getLinkedCompetences.asScala.map(x=>x.getDefinition).toArray;
  }

}