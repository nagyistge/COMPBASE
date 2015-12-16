package uzuzjmd.competence.persistence.dao

import com.hp.hpl.jena.ontology.Individual
import com.hp.hpl.jena.ontology.OntClass
import com.hp.hpl.jena.rdf.model.Property
import com.hp.hpl.jena.rdf.model.Statement
import uzuzjmd.competence.config.Logging
import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, CompOntologyAccess}
import uzuzjmd.competence.exceptions.DataFieldNotInitializedException
import uzuzjmd.competence.exceptions.IndividualNotFoundException
import uzuzjmd.competence.exceptions.OntClassForDaoNotInitializedException
import uzuzjmd.competence.persistence.ontology.CompOntClass
import uzuzjmd.competence.exceptions.IdentifierNullException
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl

abstract class CompetenceOntologyDao(comp: CompOntologyManager, compOntClass: CompOntClass, val identifier: String) extends Dao(comp, compOntClass, identifier) with Logging {

  val util = comp.getUtil()

  override def getOntClass: OntClass = {
    return comp.getUtil.getClass(compOntClass, true)

  }


  @throws[IdentifierNullException]
  def persist(): Individual = {
    if (identifier == null) {
      logger.error("tried to persist " + this.toString() + " in database with identifier null")
      throw new IdentifierNullException
    }
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
    if (result != null) {
      return util.getClass(compOntClass, true).equals(result.getOntClass())
    } else {
      return false
    }
  }

  def createIndividual: Individual = {
    val ontClass = util.createOntClass(compOntClass, false)
    return util.createIndividualForString(ontClass, identifier, false)
  }

  @throws[IndividualNotFoundException]
  override def getIndividual: Individual = {
    val ontClass = util.createOntClass(compOntClass, true)
    val result = util.createIndividualForString(ontClass, identifier, true)
    if (result == null) {
    }
    return result
  }

  @throws[IndividualNotFoundException]
  override def getId: String = {

    //    return identifier
    if (getIndividual == null) {
      logger.error("could not get Individual for class: " + compOntClass.toString() + ", und identifier: " + identifier)
      logger.error(Thread.currentThread().getStackTrace)
      throw new IndividualNotFoundException
    }
    return getIndividual.getLocalName
  }

}