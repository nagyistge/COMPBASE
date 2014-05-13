package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager

class TeacherRole(comp: CompOntologyManager) extends Role(comp, CompOntClass.TeacherRole) {
  @Override
  protected def persistMore() {
    val role = new RoleInstance(comp)
    role.persist(false)
    addSuperClass(role)
  }

  @Override
  def getFullDao(): Dao = {
    return this
  }
}