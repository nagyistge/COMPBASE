package uzuzjmd.competence.owl.access

import com.hp.hpl.jena.ontology.OntClass
import uzuzjmd.competence.owl.dao.{Competence, CompetenceOntologyDao, Dao}
import uzuzjmd.competence.owl.ontology.{CompOntClass, CompObjectProperties}
import uzuzjmd.scalahacks.ScalaHacksInScala
import scala.collection.JavaConverters._

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
