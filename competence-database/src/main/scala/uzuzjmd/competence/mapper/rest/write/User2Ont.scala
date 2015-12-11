package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, TDBWriteTransactional}
import uzuzjmd.competence.persistence.dao.CourseContext
import uzuzjmd.competence.persistence.dao.User
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl
import uzuzjmd.competence.service.rest.model.dto.RoleConverter
import uzuzjmd.competence.service.rest.model.dto.UserData

/**
 * @author dehne
 */
object User2Ont extends RoleConverter with TDBWriteTransactional[UserData] {

  def convert(data: UserData) {
    execute(createUser _, data)
  }

  def createUser(comp: CompOntologyManager, data: UserData) {
    val creatorRole = convertRole(data.getRole, comp);
    creatorRole.persist(false);
    val coursecontext = new CourseContext(comp, data.getCourseContext);
    coursecontext.persist();
    val creator = new User(comp, data.getUser, creatorRole, coursecontext, data.getUser);
    creator.persist();
  }
}