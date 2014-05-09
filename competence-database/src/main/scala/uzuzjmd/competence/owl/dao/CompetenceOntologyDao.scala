package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.service.rest.CompetenceServiceWrapper
import uzuzjmd.competence.owl.ontology.CompOntClass
import com.hp.hpl.jena.ontology.OntClass
import uzuzjmd.competence.owl.access.CompOntologyManagerFactory
import com.hp.hpl.jena.ontology.Individual

abstract class CompetenceOntologyDao(comp: CompOntologyManager, compOntClass: CompOntClass, identifier: String) {
  val util = comp.getUtil()

  def persist(): Individual = {
    val ontClass = util.createOntClass(compOntClass)
    persistMore()
    return util.createIndividualForString(ontClass, identifier)
  }

  def delete {
    if (exists) {
      val individual = comp.getM().getIndividual(identifier)
      individual.remove()
      deleteMore()
    }
  }

  protected def deleteMore()
  protected def persistMore()

  def exists(): Boolean = {    
    return comp.getM().getIndividual(identifier) != null
  }

}