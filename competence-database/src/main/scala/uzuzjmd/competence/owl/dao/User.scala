package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.ontology.CompObjectProperties

class User(val name: String, role: Role, comp: CompOntologyManager) extends CompetenceOntologyDao(comp, CompOntClass.User, name) {
  def NAME = "name"

  @Override
  protected def deleteMore() {
    role.deleteEdge(CompObjectProperties.RoleOf, this)
  }

  @Override
  protected def persistMore() {
    val thisIndividual = createIndividual
    role.createEdgeWith(CompObjectProperties.RoleOf, this)
    addDataField(NAME, name)
  }
}