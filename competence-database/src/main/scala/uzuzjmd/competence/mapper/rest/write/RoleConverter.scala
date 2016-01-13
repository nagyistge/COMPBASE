package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.monopersistence.daos.Role

/**
 * @author dehne
 */
trait RoleConverter {
  def convertRole(role: String): Role = {
    return Role.valueOf(role)
  }
}
