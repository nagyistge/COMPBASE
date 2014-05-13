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
import java.net.URLEncoder

abstract class CompetenceOntologyDao(comp: CompOntologyManager, compOntClass: CompOntClass, val identifier: String) extends Dao(comp) {
  val util = comp.getUtil()
  val encodedstring = identifier.trim().replaceAll("[^a-zA-ZäöüÄÖÜß1-9]", "_").replaceAll("[\u0000-\u001f]", "").replaceAll("\\.", "__").replaceAll("[\n\r]", "").replaceAll("[\n]", "").replaceAll("_", "");

  def persist(): Individual = {
    val result = createIndividual
    persistMore()
    return result
  }

  def delete {
    if (exists) {
      deleteMore()
      val individual = util.getIndividualForString(encodedstring)
      individual.remove()
    }
  }

  protected def deleteMore()

  def exists(): Boolean = {
    val result = util.getIndividualForString(encodedstring)
    return result != null
  }

  def createIndividual: Individual = {
    val ontClass = util.createOntClass(compOntClass)

    return util.createIndividualForString(ontClass, encodedstring)
  }

  def getId: String = {
    return encodedstring;
  }

  def getFullDao(): CompetenceOntologyDao

}