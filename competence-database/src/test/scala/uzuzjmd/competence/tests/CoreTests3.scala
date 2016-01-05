package uzuzjmd.competence.tests

import java.util.LinkedList

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.mapper.rest.read.Ont2CompetenceTree
import uzuzjmd.competence.persistence.abstractlayer.WriteTransactional

/**
 * @author Julian Dehne
 */
@RunWith(classOf[JUnitRunner])
class CoreTests3 extends FunSuite with ShouldMatchers with WriteTransactional[Any] {


  test("if a competence is filtered by operator the competenceTree should not be empty") {
    val operator = "Schreiben" // assumes epos competences are imported
    val operators = new LinkedList[String]
    operators.add(operator)
    val competenceTreeConverter = new Ont2CompetenceTree(new LinkedList, operators, "university", null, null)
    val result = competenceTreeConverter.getComptenceTreeForCourse
    result.get(0).getChildren should not be ('empty)
  }
}
