package uzuzjmd.competence.persistence.dao

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager
import uzuzjmd.competence.persistence.ontology.CompOntClass

abstract class Role(comp: CompOntologyManager, compOntClass: CompOntClass) extends CompetenceOntologySingletonDao(comp, compOntClass) {
  /**
   * should be called after persist
   */
  def setRole() {
    val role = new RoleInstance(comp)
    role.persist(false)
    addSuperClass(role)
  }
}