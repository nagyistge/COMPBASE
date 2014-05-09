package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.ontology.CompObjectProperties

class User(val name: String, role: Role, comp: CompOntologyManager) extends CompetenceOntologyDao(comp, CompOntClass.User, name) {
  @Override
  protected def deleteMore() {
    //TODO
  }

  @Override
  protected def persistMore() {
     val thisIndividual = createIndividual
     createEdgeWith(CompObjectProperties.UserOf, role)
  }
}