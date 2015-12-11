package uzuzjmd.competence.tests

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, TDBWriteTransactional, CompOntologyAccess}
import uzuzjmd.competence.persistence.owl.{CompFileUtil, CompOntologyManagerJenaImpl}

import scala.collection.JavaConverters.seqAsJavaListConverter
import org.junit.AfterClass
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.mapper.gui.read.Ont2CompetenceTree
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
import uzuzjmd.competence.service.rest.model.dto.ReflectiveAssessmentChangeData
import uzuzjmd.competence.shared.ReflectiveAssessmentsListHolder
import uzuzjmd.competence.persistence.dao.SelfAssessment
import uzuzjmd.competence.shared.Assessment
import uzuzjmd.competence.shared.ReflectiveAssessment
import uzuzjmd.competence.mapper.gui.write.ReflectiveAssessmentHolder2Ont
import uzuzjmd.competence.logging.LogConfigurator
import uzuzjmd.competence.persistence.dao.LearningProjectTemplate
import uzuzjmd.competence.service.rest.model.dto.CompetenceData
import uzuzjmd.competence.mapper.rest.write.Competence2Ont
import uzuzjmd.competence.service.rest.model.dto.LearningTemplateData
import uzuzjmd.competence.persistence.dao.SelectedLearningProjectTemplate
import uzuzjmd.competence.persistence.dao.User
import uzuzjmd.competence.persistence.dao.CourseContext

/**
 * @author dehne
 */

@RunWith(classOf[JUnitRunner])
class GridTests extends FunSuite with ShouldMatchers with TDBWriteTransactional[Any] {
  //  test("if a superdao is added, one should be able to retrieve it by getSuperDAO") {
  //
  //  }

  val learningTemplatenName = "Neues Lernprojekt"
  val rootCompetence = "adf"
  val testGroupID = "20182"
  val testUser = "test@liferay.com"

  test("SETUP create template and select it for user before usage") {
    LogConfigurator.initLogger()

    // change this, if you want to really reset the database
    CompFileUtil.deleteTDB()
    executeNoParam(createBasicDB)

    val competenceData = new CompetenceData(
      "adfing", ("ADFADF" :: Nil).asJava, null,
      null, learningTemplatenName,
      rootCompetence);
    Competence2Ont
      .convert(competenceData);

    val data = new LearningTemplateData(
      testUser, testGroupID, learningTemplatenName);
    LearningTemplateToOnt.convert(data);

  }

  private def createBasicDB(comp: CompOntologyManager) {
    val rootInstance = new CompetenceInstance(comp)
    rootInstance.persist(false)

    val learningProjectTemplate = new LearningProjectTemplate(comp, learningTemplatenName)
    learningProjectTemplate.persist

  }

  test("if an assessment is send to the server it should be persisted") {

    // create learningTemplateResultSet
    createLearningTemplateResultSet
    createReflectiveAssessment()

    val learningTemplateData = new LearningTemplateData(testUser, testGroupID, learningTemplatenName)
    val result = Ont2SuggestedCompetenceGrid.convert(learningTemplateData)
    result.getSuggestedCompetenceRows should not be ('empty)

    val expectedAssessment = new Assessment().getItems.get(2)
    result.getSuggestedCompetenceRows.get(0)
      .getSuggestedCompetenceColumns.get(0)
      .getReflectiveAssessmentListHolder.getReflectiveAssessmentList.get(0)
      .getAssessment.equals(expectedAssessment) should not be false

    result.getSuggestedCompetenceRows.get(0).getSuggestedCompetenceColumns.get(0).getProgressInPercent > 0 should not be false
  }

  private def createReflectiveAssessment() {
    // create assessment
    val assessmentHolder = new ReflectiveAssessmentsListHolder()
    assessmentHolder.setAssessment(new Assessment)
    assessmentHolder.setSuggestedMetaCompetence(rootCompetence);

    val reflectiveAssessment1 = new ReflectiveAssessment
    reflectiveAssessment1.setCompetenceDescription(rootCompetence)
    reflectiveAssessment1.setIsLearningGoal(true)
    reflectiveAssessment1.setAssessment(assessmentHolder.getAssessment.getItems.get(2))
    assessmentHolder.setReflectiveAssessmentList((reflectiveAssessment1 :: Nil).asJava);
    val changes = new ReflectiveAssessmentChangeData(testUser, testGroupID, assessmentHolder)
    ReflectiveAssessmentHolder2Ont.convert(changes)
  }

  private def createLearningTemplateResultSet() {
    val learningTemplateResultSet = new LearningTemplateResultSet(new GraphNode(rootCompetence));
    learningTemplateResultSet.setNameOfTheLearningTemplate(learningTemplatenName)
    LearningTemplateToOnt.convertLearningTemplateResultSet(learningTemplateResultSet)
  }

}
