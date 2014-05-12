package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.service.rest.CompetenceServiceWrapper
import uzuzjmd.competence.owl.ontology.CompOntClass
import com.hp.hpl.jena.ontology.OntClass
import uzuzjmd.competence.owl.access.CompOntologyManagerFactory
import com.hp.hpl.jena.ontology.Individual
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.access.CompOntologyAccess
import com.hp.hpl.jena.rdf.model.Statement
import com.hp.hpl.jena.rdf.model.Literal
import com.hp.hpl.jena.rdf.model.Property

abstract class CompetenceOntologyDao(comp: CompOntologyManager, compOntClass: CompOntClass, identifier: String) extends Dao(comp) {
  val util = comp.getUtil()

  def persist(): Individual = {
    val result = createIndividual
    persistMore()
    return result
  }

  def delete {
    if (exists) {
      deleteMore()
      val individual = util.getIndividualForString(identifier)
      individual.remove()
    }
  }

  protected def deleteMore()

  def exists(): Boolean = {
    val result = util.getIndividualForString(identifier)
    return result != null
  }

  def createIndividual: Individual = {
    val ontClass = util.createOntClass(compOntClass)
    return util.createIndividualForString(ontClass, identifier)
  }

}