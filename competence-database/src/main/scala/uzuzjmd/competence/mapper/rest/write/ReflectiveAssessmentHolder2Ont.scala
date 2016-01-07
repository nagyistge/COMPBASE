package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, WriteTransactional}
import uzuzjmd.competence.persistence.owl.{CompOntologyAccessScala, CompOntologyManagerJenaImpl}
import uzuzjmd.competence.service.rest.dto.ReflectiveAssessmentChangeData

import scala.collection.JavaConverters.asScalaBufferConverter
import uzuzjmd.competence.persistence.dao.Competence
import uzuzjmd.competence.persistence.dao.CourseContext
import uzuzjmd.competence.persistence.dao.SelfAssessment
import uzuzjmd.competence.persistence.dao.TeacherRole
import uzuzjmd.competence.persistence.dao.User
import uzuzjmd.competence.shared.ReflectiveAssessment
import uzuzjmd.competence.shared.ReflectiveAssessmentsListHolder

object ReflectiveAssessmentHolder2Ont extends WriteTransactional[ReflectiveAssessmentChangeData] {

  def convert(reflectiveAssement: ReflectiveAssessmentChangeData) {
    execute(convertHelper _, reflectiveAssement)
  }

  def convertHelper(comp: CompOntologyManager, reflectiveAssessment: ReflectiveAssessmentChangeData) {
    val context = new CourseContext(comp, reflectiveAssessment.getGroupId);
    val user = new User(comp, reflectiveAssessment.getUserName, new TeacherRole(comp), context, reflectiveAssessment.getUserName);
    ReflectiveAssessmentHolder2Ont.convertAssessment(comp, user, context, reflectiveAssessment.getReflectiveAssessmentHolder);
  }

  def convertAssessment(comp: CompOntologyManager, user: User, courseContext: CourseContext, reflectiveAssessmentHolder: ReflectiveAssessmentsListHolder) {
    reflectiveAssessmentHolder.getReflectiveAssessmentList().asScala.foreach(updateSingleAssessment(_, comp, user, courseContext))
  }

  def convertAssessmentStringToIndex(assessment: String): java.lang.Integer = {
    val enumMap = List("gar nicht" -> 0, "schlecht" -> 1, "mittel" -> 2, "gut" -> 3)
    val map = enumMap.toMap.get(assessment).get
    return map
  }

  def updateSingleAssessment(reflectiveAsssessment: ReflectiveAssessment, comp: CompOntologyManager, user: User, courseContext: CourseContext) = {
    val competence = new Competence(comp, reflectiveAsssessment.getCompetenceDescription())
    val selfAssnew = new SelfAssessment(comp, CompOntologyAccessScala.createIdentifierForAssessment(user, competence), competence, user, convertAssessmentStringToIndex(reflectiveAsssessment.getAssessment()), reflectiveAsssessment.getIsLearningGoal())
    selfAssnew.persist

  }
}