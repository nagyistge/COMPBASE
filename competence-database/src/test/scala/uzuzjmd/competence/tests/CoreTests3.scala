package uzuzjmd.competence.tests

import scala.collection.JavaConverters.seqAsJavaListConverter
import org.junit.AfterClass
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.mapper.gui.read.Ont2CompetenceTree
import uzuzjmd.competence.owl.access.CompFileUtil
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.StudentRole
import uzuzjmd.competence.owl.dao.TeacherRole
import org.scalatest.junit.JUnitRunner
import org.specs2.specification.After
import org.specs2.mutable.After
import uzuzjmd.competence.owl.dao.User
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.dao.Role
import uzuzjmd.competence.owl.dao.Comment
import uzuzjmd.competence.owl.dao.EvidenceActivity
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink
import uzuzjmd.competence.owl.dao.StudentRole
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.TeacherRole
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.mapper.gui.read.Ont2CompetenceLinkMap
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink
import uzuzjmd.competence.mapper.gui.Ont2ProgressMap
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.SelectedLearningProjectTemplate
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import uzuzjmd.competence.owl.dao.CompetenceInstance
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.access.CompOntologyAccess
import org.apache.log4j.Level
import com.hp.hpl.jena.rdf.model.InfModel
import com.hp.hpl.jena.rdf.model.ModelFactory
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.main.CompetenceImporter
import uzuzjmd.competence.main.EposImporter
import uzuzjmd.competence.owl.access.MagicStrings
import uzuzjmd.competence.service.rest.CompetenceOntologyInterface
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink
import uzuzjmd.competence.owl.dao.EvidenceActivity
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink
import scala.collection.JavaConverters._
import uzuzjmd.competence.mapper.gui.write.LearningTemplateToOnt
import uzuzjmd.competence.mapper.gui.read.Ont2SuggestedCompetenceGrid
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import java.util.HashMap
import uzuzjmd.competence.shared.dto.GraphTriple
import uzuzjmd.competence.shared.dto.Graph
import uzuzjmd.competence.shared.dto.GraphNode
import uzuzjmd.competence.shared.dto.LearningTemplateResultSet
import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.owl.dao.CourseContext
import sun.security.krb5.internal.ccache.CCacheInputStream
import uzuzjmd.competence.owl.dao.CourseContext

/**
 * @author dehne
 */

@RunWith(classOf[JUnitRunner])
class CoreTests3 extends FunSuite with ShouldMatchers with TDBWriteTransactional[Any] {
  //  test("if a superdao is added, one should be able to retrieve it by getSuperDAO") {
  //
  //  }

  test("The CSV import should run without errors") {

    //      change this, if you want to really reset the database
    CompFileUtil.deleteTDB()
    val compOntManag = new CompOntologyManager()
    CompetenceImporter.convertCSVArray();

  }

  test("if a competence is added to a courseContext, the supercompetence should also be available in that Kontext") {
    executeNoParamWithReasoning(doCourseContextRule1 _)
    executeNoParam(doCourseContextRule2 _)
  }

  def doCourseContextRule1(comp: CompOntologyManager) {
    val competenceSub = new Competence(comp, "I like to move it move it")
    competenceSub.persist(true)
    val superCompetence = new Competence(comp, "I really like to move it top");
    superCompetence.persist(true)
    competenceSub.addSuperCompetence(superCompetence)
    val courseContext = new CourseContext(comp, "MovingCourse")
    competenceSub.addCourseContext(courseContext)
  }

  def doCourseContextRule2(comp: CompOntologyManager) {
    val superCompetence = new Competence(comp, "I really like to move it top");
    val courseContext = new CourseContext(comp, "MovingCourse")
    courseContext.getLinkedCompetences().map(x => x.getDefinition()).contains(superCompetence.getDefinition()) should not be false
    courseContext.getLinkedCompetences().map(x => x.getDefinition()).foreach { x => println(x) }
  }
}
