package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.monopersistence.daos.CourseContext
import uzuzjmd.competence.persistence.abstractlayer.ReadTransactional

/**
 * @author dehne
 */
object Ont2CourseRequirements extends ReadTransactional[String, String] {

  def convert(changes: String): String = {
    return execute(convertHelper _, changes)
  }

  def convertHelper(changes: String): String = {
    val courseContext = new CourseContext(changes)
    return courseContext.getRequirement;
  }
}
