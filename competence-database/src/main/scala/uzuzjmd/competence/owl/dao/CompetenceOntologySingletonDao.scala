package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.OntResult
import com.hp.hpl.jena.ontology.Individual

class CompetenceOntologySingletonDao(comp: CompOntologyManager, compOntClass : CompOntClass) extends Dao (comp) {
  val util = comp.getUtil()

  def persist(): OntResult = {
    return util.accessSingletonResourceWithClass(compOntClass)
  }
  
  def createIndividual : Individual = {
    return persist.getIndividual()
  }
}