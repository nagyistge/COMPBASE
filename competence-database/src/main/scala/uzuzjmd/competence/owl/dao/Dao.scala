package uzuzjmd.competence.owl.dao

import com.hp.hpl.jena.ontology.Individual
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.access.CompOntologyManager

abstract class Dao (comp: CompOntologyManager){
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
  
  def hasEdge(edgeType: CompObjectProperties, range: Dao) : Boolean = {
    val domainIndividual = createIndividual
    val rangeIndividual = range.createIndividual
    comp.getUtil().existsObjectPropertyWithIndividual(domainIndividual, rangeIndividual, edgeType)
  }
}