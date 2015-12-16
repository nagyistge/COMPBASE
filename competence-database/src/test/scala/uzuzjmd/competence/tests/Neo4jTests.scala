package uzuzjmd.competence.tests

import com.hp.hpl.jena.ontology.OntClass
import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.persistence.neo4j.{Neo4jOntClass, Model2Neo4j, Neo4jIndividual, Neo4jModelFactory}
import uzuzjmd.competence.persistence.ontology.CompOntClass
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl


/**
  * Created by Julian Dehne on 04.12.2015.
  */
@RunWith(classOf[JUnitRunner])
class Neo4jTests  extends FunSuite with ShouldMatchers {

  test("just persisting individual and deleting") {
    val ind = new Neo4jIndividual("julian", "julian thinks he is strong", new Neo4jOntClass("User"), false)
    val testInd = ind.create()
    assertEquals(ind, testInd)

  }
  test("testing the jena to neo4j converters") {
    val manager = new CompOntologyManagerJenaImpl();
    Model2Neo4j.convertModel2Neo4jDB(manager)

    val model = Neo4jModelFactory.createModelFromNeo4j();
    manager.getM.containsAll(model) should not be false
  }

}
