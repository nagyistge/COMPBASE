package uzuzjmd.competence.tests

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.persistence.neo4j.{Model2Neo4j, Neo4jIndividual, Neo4jModelFactory}
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl


/**
  * Created by dehne on 04.12.2015.
  */

@RunWith(classOf[JUnitRunner])
class Neo4jTests  extends FunSuite with ShouldMatchers {

  test("just persisting individual and deleting") {
    val indvidualy = new Neo4jIndividual("julian", "julian is strong", null);
  }

  test("testing the jena to neo4j converters") {
    val manager = new CompOntologyManagerJenaImpl();
    Model2Neo4j.convertModel2Neo4jDB(manager)

    val model = Neo4jModelFactory.createModelFromNeo4j();
    manager.getM.containsAll(model) should not be false
  }

}
