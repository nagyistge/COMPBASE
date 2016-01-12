package uzuzjmd.competence.persistence.dao

import com.hp.hpl.jena.ontology.Individual
import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager
import uzuzjmd.competence.persistence.ontology.CompObjectProperties
import com.hp.hpl.jena.rdf.model.Property
import com.hp.hpl.jena.rdf.model.Statement
import com.hp.hpl.jena.ontology.OntClass
import uzuzjmd.competence.persistence.owl.{CompetenceQueriesJenaImpl, CompOntologyAccessScala, CompOntologyManagerJenaImpl}
import uzuzjmd.scala.reflection.{DAOFactory, ScalaHacks}
import scala.collection.JavaConverters._
import uzuzjmd.competence.exceptions.{DataFieldNotInitializedException, OntClassForDaoNotInitializedException}
import uzuzjmd.competence.persistence.ontology.CompOntClass

abstract class Dao(comp: CompOntologyManager, val compOntClassTop: CompOntClass, val identifierTop: String = null) {
  def createIndividual: Individual;

  def getId: String;

  def getOntClass: OntClass;


  protected def persistMore()

  def getIndividual: Individual

  def getFullDao(): Dao

  def exists(): Boolean;

  override def toString(): String = {
    var str = ""
    try {
      str += compOntClassTop.toString + "{identifier:" + identifierTop + "}"
    } catch {
      case e: Exception => return ""
    }
    return str
  }

  def createEdgeWith(edgeType: CompObjectProperties, range: Dao) {
    val domainIndividual = createIndividual
    val rangeIndividual = range.createIndividual
    comp.getUtil().createObjectPropertyWithIndividual(domainIndividual, rangeIndividual, edgeType)
  }

  def createEdgeWith(domain: Dao, edgeType: CompObjectProperties) {
    val domainIndividual = domain.createIndividual
    val rangeIndividual = createIndividual
    comp.getUtil().createObjectPropertyWithIndividual(domainIndividual, rangeIndividual, edgeType)
  }

  def deleteEdgeWith(domain: Dao, edgeType: CompObjectProperties) {
    val domainIndividual = domain.createIndividual
    val rangeIndividual = createIndividual
    comp.getUtil().deleteObjectPropertyWithIndividual(domainIndividual, rangeIndividual, edgeType)
  }

  def deleteEdge(edgeType: CompObjectProperties, range: Dao) {
    val domainIndividual = createIndividual
    val rangeIndividual = range.createIndividual
    comp.getUtil().deleteObjectPropertyWithIndividual(domainIndividual, rangeIndividual, edgeType)
  }

  def hasEdge(edgeType: CompObjectProperties, range: Dao): Boolean = {
    if (this.exists && range.exists) {
      val domainIndividual = getIndividual
      val rangeIndividual = range.getIndividual
      val result = comp.getUtil().existsObjectPropertyWithIndividual(domainIndividual, rangeIndividual, edgeType)
      return result
    } else {
      return false
    }
  }

  def hasEdge(domain: Dao, edgeType: CompObjectProperties): Boolean = {
    if (this.exists && domain.exists) {
      val domainIndividual = domain.getIndividual
      val rangeIndividual = getIndividual
      val result = comp.getUtil().existsObjectPropertyWithIndividual(domainIndividual, rangeIndividual, edgeType)
      return result
    } else {
      return false
    }
  }

  def addSuperClass(superClass: CompetenceOntologySingletonDao) {
    persistMore
    if (superClass != null) {
      val ontSuperClass = superClass.persistManualCascades(true).getOntclass()
      createIndividual.getOntClass().addSuperClass(ontSuperClass)
    }
  }

  def addDataField(key: String, value: Object) {
    val literal = comp.getM().createProperty(CompOntologyAccessScala.encode(key));
    createIndividual.removeAll(literal)
    createIndividual.addLiteral(literal, value);
  }

  @throws[OntClassForDaoNotInitializedException]
  def getDataField(key: String): String = {
    comp.getUtil.getDataField(key, getIndividual)
  }

  def getDataFieldBoolean(key: String): java.lang.Boolean = {
    comp.getUtil.getDataFieldBoolean(key, getIndividual)
  }

  def getDataFieldLong(key: String): java.lang.Long = {
    comp.getUtil.getDataFieldLong(key, getIndividual)
  }

  def getDataFieldInt(key: String): java.lang.Integer = {
    comp.getUtil.getDataFieldInt(key, getIndividual)
  }

  def deleteDataField(key: String) = {
    comp.getUtil.deleteDataField(key, getIndividual)
  }


  def hasDataField(key: String): Boolean = {
    try {
      return getDataField(key) != null
    } catch  {
      case e: DataFieldNotInitializedException =>  return false;
    }
  }

  private def getAssociatedIndividuals(edgeType: CompObjectProperties, range: Dao): List[Individual] = {
    val hacks = new ScalaHacks;
    val individualDummy = hacks.getIndividualArray()
    val queries = comp.getQueries;
    return queries.getRelatedIndividuals(edgeType, range.getId).toArray(individualDummy).toList
  }

  private def getAssociatedIndividuals(domain: Dao, edgeType: CompObjectProperties): List[Individual] = {
    val hacks = new ScalaHacks;
    val individualDummy = hacks.getIndividualArray()
    val queries = comp.getQueries;
    return queries.getRelatedIndividualsDomainGiven(domain.getId, edgeType).toArray(individualDummy).toList
  }

  /**
    * assumes that alle dao classes have a constructor available that only takes the ontclass and the manager
    */
  protected def getAssociatedStandardDaosAsDomain[T <: CompetenceOntologyDao](edgeType: CompObjectProperties, clazz: java.lang.Class[T]): List[T] = {
    val assocIndividuals = getAssociatedIndividuals(edgeType, this)
    val result = assocIndividuals.map(x => DAOFactory.instantiateDao(clazz)(comp, x.getLocalName()).asInstanceOf[T]).map(x => x.getFullDao)
    return result.asInstanceOf[List[T]]
  }

  /**
    * assumes that alle dao classes have a constructor available that only takes the ontclass and the manager
    */
  protected def getAssociatedSingletonDaosAsDomain[T <: CompetenceOntologySingletonDao](edgeType: CompObjectProperties, clazz: java.lang.Class[T]): List[T] = {
    val assocIndividuals = getAssociatedIndividuals(edgeType, this)
    val result = assocIndividuals.map(x => DAOFactory.instantiateDao(clazz)(comp, x.getOntClass().getLocalName()).asInstanceOf[T]).map(x => x.getFullDao)
    return result.asInstanceOf[List[T]]
  }

  /**
    * assumes that alle dao classes have a constructor available that only thakes the ontclass and the manager
    */
  protected def getAssociatedStandardDaosAsRange[T <: CompetenceOntologyDao](edgeType: CompObjectProperties, clazz: java.lang.Class[T]): List[T] = {
    val ontClasses = getAssociatedIndividuals(this, edgeType)
    val result = ontClasses.map(x => DAOFactory.instantiateDao(clazz)(comp, x.getLocalName()).asInstanceOf[T]).map(x => x.getFullDao)
    return result.asInstanceOf[List[T]]
  }

  /**
    * assumes that alle dao classes have a constructor available that only thakes the ontclass and the manager
    */
  protected def getAssociatedSingletonDaosAsRange[T <: CompetenceOntologySingletonDao](edgeType: CompObjectProperties, clazz: java.lang.Class[T]): List[T] = {
    val ontClasses = getAssociatedIndividuals(this, edgeType)
    val result = ontClasses.map(x => DAOFactory.instantiateDao(clazz)(comp, x.getLocalName()).asInstanceOf[T]).map(x => x.getFullDao)
    return result.asInstanceOf[List[T]]
  }


}