package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.OntResult

class CompetenceOntologySingletonDao(comp: CompOntologyManager, compOntClass : CompOntClass) {
  val util = comp.getUtil()

  def persist(): OntResult = {
    return util.accessSingletonResourceWithClass(compOntClass)
  }
}