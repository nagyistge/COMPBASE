package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import java.nio.charset.StandardCharsets
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.OntResult
import com.hp.hpl.jena.ontology.Individual
import com.hp.hpl.jena.ontology.OntClass
import uzuzjmd.competence.owl.access.MagicStrings
import uzuzjmd.competence.owl.access.CompOntologyAccess
import com.hp.hpl.jena.rdf.model.Property
import com.hp.hpl.jena.rdf.model.Statement
import scala.collection.JavaConverters._
import uzuzjmd.competence.owl.access.CompOntologyAccessScala
import uzuzjmd.scalahacks.ScalaHacksInScala
import com.hp.hpl.jena.ontology.OntTools
import com.hp.hpl.jena.util.iterator.Filter
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import scala.io.UTF8Codec
import javax.ws.rs.NotFoundException
import uzuzjmd.competence.owl.dao.exceptions.DataFieldNotInitializedException
import uzuzjmd.competence.owl.dao.exceptions.OntClassForDaoNotInitializedException

abstract case class CompetenceOntologySingletonDao(comp: CompOntologyManager, val compOntClass: CompOntClass, val identifier: String = null) extends Dao(comp) {
  val util = comp.getUtil()

  def DEFINITION = "definition"

  def persist(more: Boolean): OntResult = {
    var result: OntResult = null
    if (identifier == null) {
      result = util.accessSingletonResourceWithClass(compOntClass, false)
    } else {
      result = util.accessSingletonResource(identifier, false, identifier)
    }
    if (more) {
      val uniContext = new UniversityContext(comp)
      uniContext.createEdgeWith(CompObjectProperties.CourseContextOf, this)
      persistMore
    }
    return result
  }

  def persistFluent(more: Boolean): CompetenceOntologySingletonDao = {
    persist(more)
    return this
  }

  def createIndividual: Individual = {
    return persist(false).getIndividual()
  }

  def exists(): Boolean = {
    val result = util.getClass(compOntClass, true)
    if (result != null) {
      if (identifier != null) {
        val result = comp.getUtil().getIndividualForString(MagicStrings.SINGLETONPREFIX + identifier)
        return result != null
      } else {
        return true
      }
    }
    return false

  }

  override def getId: String = {
    //    return createIndividual.getLocalName()
    val individual = getIndividual
    if (individual == null) {
      return null
    } else {
      return individual.getLocalName
    }
  }

  //  override def getId: String = {
  //    return CompOntologyAccess.encode(identifier)
  //  }

  override def getIndividual: Individual = {
    //    return createIndividual.getLocalName()
    if (identifier != null) {
      val result = comp.getUtil().getIndividualForString(MagicStrings.SINGLETONPREFIX + identifier)
      return result
    } else {
      val result = util.getIndividualForString(MagicStrings.SINGLETONPREFIX + compOntClass.name())
      return result
    }
  }

  @Override
  def getOntClass: OntClass = {
    //    return createIndividual.getLocalName()
    if (identifier != null) {
      val result = comp.getUtil().getOntClassForString(identifier)
      return result
    } else {
      val result = util.getOntClassForString(MagicStrings.SINGLETONPREFIX + compOntClass.name())
      return result
    }
  }

  @Override
  def equals(toCompare: Competence): Boolean = {
    return getId.equals(toCompare.getId)
  }

  def listSubClasses[T <: CompetenceOntologySingletonDao](clazz: java.lang.Class[T]): List[T] = {
    var definition = getDefinition
    if (definition == null) {
      definition = compOntClass.toString()
    }
    val id = getId
    val ontClass = comp.getUtil().accessSingletonResource(definition, true).getOntclass()
    val classList = ontClass.listSubClasses(false).toList().asScala.filter(!_.toString().equals("http://www.w3.org/2002/07/owl#Nothing"))

    val identifierList = classList.map(x => x.getLocalName()).toList
    return identifierList.map(x => ScalaHacksInScala.instantiateDao(clazz)(comp, x).asInstanceOf[T]).toList
  }

  def getDefinition(): String = {
    return getDataField(DEFINITION)
  }

  def isSublass(parent: Competence): Boolean = {

    if (toOntClass == null) {
      return false
    }

    return toOntClass.hasSuperClass(parent.toOntClass, false)
  }

  def toOntClass(): OntClass = {
    if (identifier == null) {
      val result = comp.getUtil().getOntClassForString(compOntClass.toString())
      return result
      //      return comp.getUtil().createOntClass(compOntClass)
    }
    val result = comp.getUtil().getOntClassForString(identifier)
    return result
    //    return comp.getUtil().createOntClassForString(getDefinition, getDefinition)
  }

  def delete() {
    val ontClass = toOntClass
    createIndividual.remove()
    //ontClass.listInstances().toList().asScala.foreach(x => x.remove())
    ontClass.remove()
  }

  def deleteTree() {
    val ontClass = toOntClass
    createIndividual.remove()
    ontClass.listInstances().toList().asScala.foreach(x => x.remove())
    ontClass.remove()
  }

  def isSuperClass(child: CompetenceOntologySingletonDao): Boolean = {

    if (toOntClass == null) {
      return false
    }

    return toOntClass.hasSubClass(child.toOntClass, false)
  }

  /**
   * needs this override, because the definition is not placed at the level of the individual but the corresponding class
   */

  @throws[OntClassForDaoNotInitializedException]
  @throws[DataFieldNotInitializedException]
  override def getPropertyPair(key: String): (Property, Statement) = {
    val literal = comp.getM().createProperty(CompOntologyAccess.encode(key));
    if (getOntClass == null) {
      throw new OntClassForDaoNotInitializedException
    }
    val prop: Statement = getOntClass.getProperty(literal);
    if (prop == null) {
      throw new DataFieldNotInitializedException
    }
    return (literal, prop)
  }

  //  def getPathToSuperClass[T <: CompetenceOntologySingletonDao](clazz: java.lang.Class[T], parentClass: CompetenceOntologySingletonDao): List[T] = {
  //    // TODO implement
  //
  //    parentClass.toOntClass.listSubClasses(true)
  //    return null;
  //  }

}