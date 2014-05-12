package uzuzjmd.competence.owl.dao

import com.hp.hpl.jena.ontology.Individual
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.CompOntologyAccess
import com.hp.hpl.jena.rdf.model.Property
import com.hp.hpl.jena.rdf.model.Statement

abstract class Dao(comp: CompOntologyManager) {
  def createIndividual: Individual;

  def createEdgeWith(edgeType: CompObjectProperties, range: Dao) {
    val domainIndividual = createIndividual
    val rangeIndividual = range.createIndividual
    comp.getUtil().createObjectPropertyWithIndividual(domainIndividual, rangeIndividual, edgeType)
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

}