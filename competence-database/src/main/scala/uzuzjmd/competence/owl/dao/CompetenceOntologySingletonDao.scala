package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.OntResult
import com.hp.hpl.jena.ontology.Individual
import com.hp.hpl.jena.ontology.OntClass
import uzuzjmd.competence.owl.access.MagicStrings

abstract class CompetenceOntologySingletonDao(comp: CompOntologyManager, compOntClass: CompOntClass) extends Dao(comp) {
  val util = comp.getUtil()

  def persist(more: Boolean): OntResult = {
    val result = util.accessSingletonResourceWithClass(compOntClass)
    if (more) {
      persistMore
    }
    return result
  }

  def createIndividual: Individual = {
    return persist(false).getIndividual()
  }

  def exists(): Boolean = {
    return util.getIndividualForString(MagicStrings.SINGLETONPREFIX + compOntClass.name()) != null
  }
}