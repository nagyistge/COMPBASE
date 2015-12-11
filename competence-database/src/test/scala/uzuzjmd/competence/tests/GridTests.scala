package uzuzjmd.competence.tests

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.logging.LogConfigurator
import uzuzjmd.competence.mapper.rest.read.Ont2SuggestedCompetenceGrid
import uzuzjmd.competence.mapper.rest.write.{ReflectiveAssessmentHolder2Ont, LearningTemplateToOnt, Competence2Ont}
import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, TDBWriteTransactional}
import uzuzjmd.competence.persistence.dao.{CompetenceInstance, LearningProjectTemplate}
import uzuzjmd.competence.persistence.owl.CompFileUtil
import uzuzjmd.competence.service.rest.dto.{CompetenceData, LearningTemplateData, ReflectiveAssessmentChangeData}
import uzuzjmd.competence.shared.{Assessment, ReflectiveAssessment, ReflectiveAssessmentsListHolder}
import uzuzjmd.competence.shared.dto.{GraphNode, LearningTemplateResultSet}

import scala.collection.JavaConverters.seqAsJavaListConverter

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
