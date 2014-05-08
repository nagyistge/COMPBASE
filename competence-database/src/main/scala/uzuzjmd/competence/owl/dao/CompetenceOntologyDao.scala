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
    val individual = persist()
    individual.remove()
    deleteMore()
  }

  protected def deleteMore()
  protected def persistMore()

}