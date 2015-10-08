package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.service.rest.CompetenceServiceWrapper
import uzuzjmd.competence.owl.ontology.CompOntClass
import com.hp.hpl.jena.ontology.OntClass
import com.hp.hpl.jena.ontology.Individual
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.access.CompOntologyAccess
import com.hp.hpl.jena.rdf.model.Statement
import com.hp.hpl.jena.rdf.model.Literal
import com.hp.hpl.jena.rdf.model.Property
import java.net.URLEncoder
import uzuzjmd.competence.owl.access.MagicStrings

abstract class CompetenceOntologyDao(comp: CompOntologyManager, compOntClass: CompOntClass, val identifier: String) extends Dao(comp) {

  val util = comp.getUtil()

  protected def computeEncodedString(): String = {
    val encodedString = replaceWrongCharacters(identifier)
    return encodedString
  }

  @Override
  def getOntClass: OntClass = {
    val result = comp.getUtil().getOntClassForString(MagicStrings.SINGLETONPREFIX + identifier)
    return result

  }

  //  @Override
  //  def getPropertyPair(key: String): (Property, Statement) = {
  //    val literal = comp.getM().createProperty(CompOntologyAccess.encode(key));
  //    val prop: Statement = createIndividual.getProperty(literal);
  //    return (literal, prop)
  //  }

  def persist(): Individual = {
    val result = createIndividual
    persistMore()
    return result
  }

  def delete {
    if (exists) {
      deleteMore()
      val individual = util.getIndividualForString(computeEncodedString)
      individual.remove()
    }
  }

  protected def deleteMore()

  def exists(): Boolean = {
    val result = util.getIndividualForString(computeEncodedString)
    if (result != null) {
      return util.getClass(compOntClass, true).equals(result.getOntClass())
    } else {
      return false
    }
  }

  def createIndividual: Individual = {
    val ontClass = util.createOntClass(compOntClass, false)
    val encodedString = computeEncodedString
    return util.createIndividualForString(ontClass, encodedString, false)
  }

  override def getIndividual: Individual = {
    val ontClass = util.createOntClass(compOntClass, true)
    val encodedString = computeEncodedString
    return util.createIndividualForString(ontClass, encodedString, true)
  }

  override def getId: String = {
    return computeEncodedString;
  }

}