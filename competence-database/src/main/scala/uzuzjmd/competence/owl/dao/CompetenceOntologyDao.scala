package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.service.rest.CompetenceServiceWrapper
import uzuzjmd.competence.owl.ontology.CompOntClass
import com.hp.hpl.jena.ontology.OntClass
import uzuzjmd.competence.owl.access.CompOntologyManagerFactory
import com.hp.hpl.jena.ontology.Individual
import uzuzjmd.competence.owl.ontology.CompObjectProperties

abstract class CompetenceOntologyDao(comp: CompOntologyManager, compOntClass: CompOntClass, identifier: String) extends Dao (comp) {
  val util = comp.getUtil()

  def persist(): Individual = {
    val ontClass = util.createOntClass(compOntClass)
    val result = util.createIndividualForString(ontClass, identifier)
    persistMore()
    return result
  }

  def delete {
    if (exists) {
      val individual = util.getIndividualForString(identifier)      
      individual.remove()
      deleteMore()
    }
  }
  


  protected def deleteMore()
  protected def persistMore()

  def exists(): Boolean = {
    val result = util.getIndividualForString(identifier)
    return result != null
  }
  
  def createIndividual : Individual = {
     return persist()
  }

}