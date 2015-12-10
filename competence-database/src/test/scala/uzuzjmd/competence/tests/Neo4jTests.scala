package uzuzjmd.competence.tests

import com.hp.hpl.jena.ontology.OntClass
import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.neo4j.Neo4jIndividual
import uzuzjmd.competence.owl.ontology.CompOntClass


/**
  * Created by dehne on 04.12.2015.
  */

@RunWith(classOf[JUnitRunner])
class Neo4jTests  extends FunSuite with ShouldMatchers {

  test("just persisting individual and deleting") {
    val ind = new Neo4jIndividual("julian", "julian thinks he is strong", OntClass, false)
    val testInd = ind.create()
    assertEquals(ind, testInd)
  }

}
