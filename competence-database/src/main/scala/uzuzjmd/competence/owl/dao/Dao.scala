package uzuzjmd.competence.owl.dao

import com.hp.hpl.jena.ontology.Individual
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.CompOntologyAccess
import com.hp.hpl.jena.rdf.model.Property
import com.hp.hpl.jena.rdf.model.Statement
import com.hp.hpl.jena.ontology.OntClass
import uzuzjmd.scalahacks.ScalaHacksInScala
import uzuzjmd.scalahacks.ScalaHacks
import uzuzjmd.competence.owl.queries.CompetenceQueries

abstract class Dao(comp: CompOntologyManager) {
  def createIndividual: Individual;
  def getId: String;

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
      val domainIndividual = createIndividual
      val rangeIndividual = range.createIndividual
      val result = comp.getUtil().existsObjectPropertyWithIndividual(domainIndividual, rangeIndividual, edgeType)
      return result
    } else { return false }
  }

  def hasEdge(domain: Dao, edgeType: CompObjectProperties): Boolean = {
    if (this.exists && domain.exists) {
      val domainIndividual = domain.createIndividual
      val rangeIndividual = createIndividual
      val result = comp.getUtil().existsObjectPropertyWithIndividual(domainIndividual, rangeIndividual, edgeType)
      return result
    } else { return false }
  }

  def addSuperClass(superClass: CompetenceOntologySingletonDao) {
    if (superClass != null) {
      val ontSuperClass = superClass.persist(true).getOntclass()
      createIndividual.getOntClass().addSuperClass(ontSuperClass)
    }
  }

  protected def persistMore()

  def exists(): Boolean;

  def addDataField(key: String, value: Object) {
    val literal = comp.getM().createProperty(CompOntologyAccess.encode(key));
    createIndividual.addLiteral(literal, value);
  }
  def getDataField(key: String): Object = {
    val tmpResult = getPropertyPair(key)
    if (tmpResult._2 == null) {
      return null;
    }
    return tmpResult._2.asTriple().getObject().getLiteralLexicalForm();
  }
  def deleteDataField(key: String) = {
    val tmpResult = getPropertyPair(key)
    if (tmpResult._2 != null) {
      comp.getM().remove(tmpResult._2);
    }
  }

  def hasDataField(key: String): Boolean = {
    return getDataField(key) != null
  }

  private def getPropertyPair(key: String): (Property, Statement) = {
    val literal = comp.getM().createProperty(CompOntologyAccess.encode(key));
    val prop: Statement = createIndividual.getProperty(literal);
    return (literal, prop)
  }

  private def getAssociatedIndividuals(edgeType: CompObjectProperties, range: Dao): List[Individual] = {
    val hacks = new ScalaHacks;
    val individualDummy = hacks.getIndividualArray()
    val queries = new CompetenceQueries(comp.getM())
    return queries.getRelatedIndividuals(edgeType, range.getId).toArray(individualDummy).toList
  }

  private def getAssociatedIndividuals(domain: Dao, edgeType: CompObjectProperties): List[Individual] = {
    val hacks = new ScalaHacks;
    val individualDummy = hacks.getIndividualArray()
    val queries = new CompetenceQueries(comp.getM())
    return queries.getRelatedIndividualsDomainGiven(domain.getId, edgeType).toArray(individualDummy).toList
  }

  /**
   * assumes that alle dao classes have a constructor available that only takes the ontclass and the manager
   */
  protected def getAssociatedStandardDaosAsDomain[T <: CompetenceOntologyDao](edgeType: CompObjectProperties, clazz: java.lang.Class[T]): List[T] = {
    val assocIndividuals = getAssociatedIndividuals(edgeType, this)
    val result = assocIndividuals.map(x => ScalaHacksInScala.instantiateDao(clazz)(comp, x.getLocalName()).asInstanceOf[T]).map(x => x.getFullDao)
    return result.asInstanceOf[List[T]]
  }

  /**
   * assumes that alle dao classes have a constructor available that only thakes the ontclass and the manager
   */
  protected def getAssociatedStandardDaosAsRange[T <: CompetenceOntologyDao](edgeType: CompObjectProperties, clazz: java.lang.Class[T]): List[T] = {
    val ontClasses = getAssociatedIndividuals(this, edgeType)
    val result = ontClasses.map(x => ScalaHacksInScala.instantiateDao(clazz)(comp, x.getLocalName()).asInstanceOf[T]).map(x => x.getFullDao)
    return result.asInstanceOf[List[T]]
  }

}