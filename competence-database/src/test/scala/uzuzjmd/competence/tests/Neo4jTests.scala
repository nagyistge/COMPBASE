package uzuzjmd.competence.tests

import java.util

import com.hp.hpl.jena.ontology.OntClass
import org.apache.log4j.xml.DOMConfigurator
import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.persistence.neo4j._
import uzuzjmd.competence.persistence.ontology.CompOntClass
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl

import scala.collection.immutable.HashMap


/**
  * Created by Julian Dehne on 04.12.2015.
  */
@RunWith(classOf[JUnitRunner])
class Neo4jTests  extends FunSuite with ShouldMatchers {

  val qm = new Neo4JQueryManagerImpl()

  test("just persisting individual and deleting") {
    DOMConfigurator.configure("/development/scala_workspace/Wissensmodellierung/competence-database/log4j.xml")
    val ind = new Neo4jIndividual("julian", "julian thinks he is strong", new Neo4jOntClass("User"), false)
    val testInd = ind.create()
    assertEquals(ind, testInd)

  }

  test("Test my query class") {
    val nqs1 = new Neo4jQueryStatement()
    nqs1.setGroup("User")
    nqs1.setVar("n")
    nqs1.setQueryType(Neo4jQuery.queryType.MERGE)
    nqs1.addArgument("id", "julian")

    val nqs2 = new Neo4jQueryStatement()
    nqs2.setQueryType(Neo4jQuery.queryType.SET)
    nqs2.setVar("n")
    nqs2.addArgument("definition", "Zielstrebiger Pre-Dok")

    val nqs3 = new Neo4jQueryStatement()
    nqs3.setQueryType(Neo4jQuery.queryType.RETURN)
    nqs3.setVar("n")

    val query = nqs1.toString +" " + nqs2.toString + " " + nqs3.toString
    val payload = "{\"statements\" : [ {\"statement\" : \"" + query + "\"} ]}";

    val r = new RequestableImpl[util.ArrayList[HashMap[String, String]]]().doRequest(payload)

    assertEquals(1, r.size())


  }

  test("Test SetClassForNode") {
    print (qm.createIndividualOfRelation("test"))
  }
  /*
  test("testing the jena to neo4j converters") {
    val manager = new CompOntologyManagerJenaImpl();
    Model2Neo4j.convertModel2Neo4jDB(manager)

    val model = Neo4jModelFactory.createModelFromNeo4j();
    manager.getM.containsAll(model) should not be false
  }
  */

}
