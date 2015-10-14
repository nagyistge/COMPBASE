package uzuzjmd.competence.owl.dao

import com.hp.hpl.jena.ontology.Individual
import com.hp.hpl.jena.ontology.OntClass
import com.hp.hpl.jena.rdf.model.Property
import com.hp.hpl.jena.rdf.model.Statement

import uzuzjmd.competence.owl.access.CompOntologyAccess
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.exceptions.DataFieldNotInitializedException
import uzuzjmd.competence.owl.dao.exceptions.IndividualNotFoundException
import uzuzjmd.competence.owl.dao.exceptions.OntClassForDaoNotInitializedException
import uzuzjmd.competence.owl.ontology.CompOntClass

abstract class CompetenceOntologyDao(comp: CompOntologyManager, compOntClass: CompOntClass, val identifier: String) extends Dao(comp) {

  val util = comp.getUtil()

  override def getOntClass: OntClass = {
    return comp.getUtil.getClass(compOntClass, true)

  }

  @throws[OntClassForDaoNotInitializedException]
  @throws[DataFieldNotInitializedException]
  @throws[IndividualNotFoundException]
  override def getPropertyPair(key: String): (Property, Statement) = {
    val keyEncoded = CompOntologyAccess.encode(key)
    val literal = comp.getM().createProperty(keyEncoded);

    val individual = getIndividual
    if (individual == null) {
      throw new IndividualNotFoundException
    }
    val prop: Statement = individual.getProperty(literal);
    if (prop == null) {
      throw new DataFieldNotInitializedException
    }
    return (literal, prop)
  }

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
      throw new IndividualNotFoundException
    }
    return getIndividual.getLocalName
  }

}