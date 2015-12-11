package uzuzjmd.competence.persistence.abstractlayer

import uzuzjmd.competence.persistence.dao.Dao
import uzuzjmd.competence.persistence.ontology.CompObjectProperties
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl
import uzuzjmd.scalahacks.ScalaHacksInScala

/**
  * Created by dehne on 03.12.2015.
  */
trait ObjectPropertyCED[Domain <:Dao, Range <:Dao] extends TDBWriteTransactional[( String, String)] {

  val domainClass =  setDomain;
  val rangeClass  =setRange;
  val edgeType = setEdge;

  protected def setEdge() : CompObjectProperties
  protected def setDomain() :  java.lang.Class[Domain]
  protected def setRange() : java.lang.Class[Range]

  def write(domain: String, range: String): Unit = {
    execute(writeHelper _, (domain, range))
  }

  def writeHelper(comp: CompOntologyManager, tuple: ( String, String)): Unit = {
    val domain = ScalaHacksInScala.instantiateDao(domainClass)(comp, tuple._1).asInstanceOf[Domain]
    val range = ScalaHacksInScala.instantiateDao(rangeClass)(comp, tuple._2).asInstanceOf[Range]
    domain.createEdgeWith(edgeType,range)
  }

  def delete(domain: String, range: String): Unit = {
    executeX(deleteHelper _, (domain, range))
  }

  def deleteHelper(comp: CompOntologyManager, tuple: ( String, String)): Unit = {
    val domain = ScalaHacksInScala.instantiateDao(domainClass)(comp, tuple._1).asInstanceOf[Domain]
    val range = ScalaHacksInScala.instantiateDao(rangeClass)(comp, tuple._2).asInstanceOf[Range]
    domain.deleteEdge(edgeType,range)
  }

}
