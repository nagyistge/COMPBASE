package uzuzjmd.competence.mapper.rest.write

import javax.ws.rs.WebApplicationException

import uzuzjmd.competence.persistence.abstractlayer.WriteTransactional
import uzuzjmd.competence.persistence.dao._
import uzuzjmd.competence.service.rest.dto.ReflectiveAssessmentChangeData
import uzuzjmd.competence.shared.{ReflectiveAssessment, ReflectiveAssessmentsListHolder}

import scala.collection.JavaConverters.asScalaBufferConverter

object ReflectiveAssessmentHolder2Ont extends WriteTransactional[ReflectiveAssessmentChangeData] {

  def convert(reflectiveAssement: ReflectiveAssessmentChangeData) {
    execute(convertHelper _, reflectiveAssement)
  }

  def convertHelper(reflectiveAssessment: ReflectiveAssessmentChangeData) {
    val context = new CourseContext(reflectiveAssessment.getGroupId);
    val user = new User(reflectiveAssessment.getUserName, Role.teacher, context);
    if (!user.exists()) {
      throw new WebApplicationException(new Exception("User not known in database"));
    }
    if (!context.exists()) {
      throw new WebApplicationException(new Exception("Course or group not known in database"));
    }

    ReflectiveAssessmentHolder2Ont.convertAssessment(user, context, reflectiveAssessment.getReflectiveAssessmentHolder);
  }

  def convertAssessment(user: User, courseContext: CourseContext, reflectiveAssessmentHolder: ReflectiveAssessmentsListHolder) {
    reflectiveAssessmentHolder.getReflectiveAssessmentList().asScala.foreach(updateSingleAssessment(_, user, courseContext))
  }

  def convertAssessmentStringToIndex(assessment: String): java.lang.Integer = {
    val enumMap = List("gar nicht" -> 0, "schlecht" -> 1, "mittel" -> 2, "gut" -> 3)
    val map = enumMap.toMap.get(assessment).get
    return map
  }

  def updateSingleAssessment(reflectiveAssessment: ReflectiveAssessment, user: User, courseContext: CourseContext) = {
    val competence = new Competence(reflectiveAssessment.getCompetenceDescription())
    val selfAssessment = new SelfAssessment(competence, user, convertAssessmentStringToIndex(reflectiveAssessment.getAssessment()), reflectiveAssessment.getIsLearningGoal())
    selfAssessment.persist
  }
}