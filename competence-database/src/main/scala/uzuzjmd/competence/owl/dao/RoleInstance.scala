package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager

class RoleInstance(comp: CompOntologyManager) extends Role(comp, CompOntClass.Role) {
  @Override
  protected def persistMore() {
  }
}