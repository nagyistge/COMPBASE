package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.persistence.abstractlayer.WriteTransactional
import uzuzjmd.competence.persistence.dao.{User, CourseContext}
import uzuzjmd.competence.service.rest.dto.UserData

/**
 * @author dehne
 */
object User2Ont extends RoleConverter with WriteTransactional[UserData] {

  def convert(data: UserData) {
    execute(createUser _, data)
  }

  def createUser(data: UserData) {
    val creatorRole = convertRole(data.getRole);
    val courseContext = new CourseContext(data.getCourseContext);
    courseContext.persist();
    val creator = new User(data.getUser, creatorRole, courseContext);
    creator.persist();
  }
}