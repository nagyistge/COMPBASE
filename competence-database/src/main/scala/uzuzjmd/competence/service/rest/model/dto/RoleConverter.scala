package uzuzjmd.competence.service.rest.model.dto

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.TeacherRole
import uzuzjmd.competence.owl.dao.StudentRole
import uzuzjmd.competence.owl.dao.Role

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