package uzuzjmd.competence.tests

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.persistence.neo4j.{Model2Neo4j, Neo4jIndividual}
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl


/**
  * Created by dehne on 04.12.2015.
  */

@RunWith(classOf[JUnitRunner])
class Neo4jTests  extends FunSuite with ShouldMatchers {

  test("just persisting individual and deleting") {
    val indvidualy = new Neo4jIndividual("julian", "julian is strong", null);
  }

  test("testing the model to neo4j converter") {
    val manager = new CompOntologyManagerJenaImpl();
    Model2Neo4j.convertModel2Neo4jDB(manager)
  }

}
