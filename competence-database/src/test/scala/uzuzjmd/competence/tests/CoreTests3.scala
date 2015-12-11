package uzuzjmd.competence.tests

import uzuzjmd.competence.persistence.abstractlayer.TDBWriteTransactional

import scala.collection.JavaConverters.seqAsJavaListConverter
import org.junit.AfterClass
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.mapper.gui.read.Ont2CompetenceTree
import uzuzjmd.competence.persistence.performance._
import uzuzjmd.competence.persistence.dao.StudentRole
import uzuzjmd.competence.persistence.dao.TeacherRole
import org.scalatest.junit.JUnitRunner
import org.specs2.specification.After
import org.specs2.mutable.After
import uzuzjmd.competence.persistence.dao.User
import uzuzjmd.competence.persistence.ontology.CompObjectProperties
import uzuzjmd.competence.persistence.ontology.CompOntClass
import uzuzjmd.competence.persistence.dao.Role
import uzuzjmd.competence.persistence.dao.Comment
import uzuzjmd.competence.persistence.dao.EvidenceActivity
import uzuzjmd.competence.persistence.dao.AbstractEvidenceLink
import uzuzjmd.competence.persistence.dao.StudentRole
import uzuzjmd.competence.persistence.dao.CourseContext
import uzuzjmd.competence.persistence.dao.TeacherRole
import uzuzjmd.competence.persistence.dao.Competence
import uzuzjmd.competence.mapper.gui.read.Ont2CompetenceLinkMap
import uzuzjmd.competence.persistence.dao.AbstractEvidenceLink
import uzuzjmd.competence.mapper.gui.Ont2ProgressMap
import uzuzjmd.competence.persistence.dao.CourseContext
import uzuzjmd.competence.persistence.dao.SelectedLearningProjectTemplate
import uzuzjmd.competence.persistence.dao.LearningProjectTemplate
import uzuzjmd.competence.persistence.dao.CompetenceInstance
import uzuzjmd.competence.persistence.dao.Competence
import org.apache.log4j.Level
import com.hp.hpl.jena.rdf.model.InfModel
import com.hp.hpl.jena.rdf.model.ModelFactory
import uzuzjmd.competence.persistence.dao.Competence
import uzuzjmd.competence.main.CompetenceImporter
import uzuzjmd.competence.main.EposImporter
import uzuzjmd.competence.service.rest.CompetenceOntologyInterface
import uzuzjmd.competence.persistence.dao.AbstractEvidenceLink
import uzuzjmd.competence.persistence.dao.EvidenceActivity
import uzuzjmd.competence.persistence.dao.AbstractEvidenceLink
import scala.collection.JavaConverters._
import uzuzjmd.competence.mapper.gui.write.LearningTemplateToOnt
import uzuzjmd.competence.mapper.gui.read.Ont2SuggestedCompetenceGrid
import uzuzjmd.competence.persistence.dao.LearningProjectTemplate
import java.util.HashMap
import uzuzjmd.competence.shared.dto.GraphTriple
import uzuzjmd.competence.shared.dto.Graph
import uzuzjmd.competence.shared.dto.GraphNode
import uzuzjmd.competence.shared.dto.LearningTemplateResultSet
import uzuzjmd.competence.persistence.dao.CourseContext
import sun.security.krb5.internal.ccache.CCacheInputStream
import uzuzjmd.competence.persistence.dao.CourseContext
import uzuzjmd.competence.mapper.gui.read.Ont2CompetenceTree
import java.util.LinkedList

/**
 * @author dehne
 */

@RunWith(classOf[JUnitRunner])
class CoreTests3 extends FunSuite with ShouldMatchers with TDBWriteTransactional[Any] {
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
