package uzuzjmd.competence.tests

import java.util.LinkedList

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.mapper.rest.read.Ont2CompetenceTree
import uzuzjmd.competence.persistence.abstractlayer.WriteTransactional

/**
 * @author dehne
 */

@RunWith(classOf[JUnitRunner])
class CoreTests3 extends FunSuite with ShouldMatchers with WriteTransactional[Any] {
  //  test("if a superdao is added, one should be able to retrieve it by getSuperDAO") {
  //
  //  }

  //  test("SETUP AND The CSV import should run without errors") {
  //    TestCommons.setup()
  //  }

  //  test("if a competence is added to a courseContext, the supercompetence should also be available in that Kontext") {
  //    executeNoParamWithReasoning(doCourseContextRule1 _)
  //    executeNoParam(doCourseContextRule2 _)
  //  }
  //
  //  def doCourseContextRule1(comp: CompOntologyManager) {
  //    val competenceSub = new Competence(comp, "I like to move it move it")
  //    competenceSub.persist(true)
  //    val superCompetence = new Competence(comp, "I really like to move it top");
  //    superCompetence.persist(true)
  //    competenceSub.addSuperCompetence(superCompetence)
  //    val courseContext = new CourseContext(comp, "MovingCourse")
  //    competenceSub.addCourseContext(courseContext)
  //  }
  //
  //  def doCourseContextRule2(comp: CompOntologyManager) {
  //    val superCompetence = new Competence(comp, "I really like to move it top");
  //    val courseContext = new CourseContext(comp, "MovingCourse")
  //    courseContext.getLinkedCompetences().map(x => x.getDefinition()).contains(superCompetence.getDefinition()) should not be false
  //    courseContext.getLinkedCompetences().map(x => x.getDefinition()).foreach { x => println(x) }
  //  }

  test("if a competence is filtered by operator the competencetree should not be empty") {
    val operator = "Schreiben" // assumes epos competences are imported
    val operators = new LinkedList[String]
    operators.add(operator)

    //    val competenceTreeconverter2 = new Ont2CompetenceTree(new LinkedList, new LinkedList, "university", null, null)
    //    val result2 = competenceTreeconverter2.getComptenceTreeForCourse
    //    result2.get(0).getChildren should not be ('empty)

    val competenceTreeconverter = new Ont2CompetenceTree(new LinkedList, operators, "university", null, null)
    val result = competenceTreeconverter.getComptenceTreeForCourse
    result.get(0).getChildren should not be ('empty)
    //    println(result)
  }
}
