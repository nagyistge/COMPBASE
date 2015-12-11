package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager
import uzuzjmd.competence.persistence.dao.{Role, StudentRole, TeacherRole}

/**
 * @author dehne
 */
trait RoleConverter {
  def convertRole(role: String, comp: CompOntologyManager): Role = {

    if (role.equals("student")) {
      return new StudentRole(comp);
    } else {
      return new TeacherRole(comp);
    }

  }
}
