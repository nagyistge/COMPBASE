package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.OntResult

class CompetenceOntologySingletonDao(comp: CompOntologyManager, identifier: String) {
  val util = comp.getUtil()

  def persist(): OntResult = {
    return util.accessSingletonResource(identifier)
  }
}