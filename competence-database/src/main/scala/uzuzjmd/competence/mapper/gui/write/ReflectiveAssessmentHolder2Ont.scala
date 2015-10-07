package uzuzjmd.competence.mapper.gui.write

import scala.collection.JavaConverters.asScalaBufferConverter

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.SelfAssessment
import uzuzjmd.competence.owl.dao.TeacherRole
import uzuzjmd.competence.owl.dao.User
import uzuzjmd.competence.service.rest.model.dto.ReflectiveAssessmentChangeData
import uzuzjmd.competence.shared.ReflectiveAssessment
import uzuzjmd.competence.shared.ReflectiveAssessmentsListHolder

object ReflectiveAssessmentHolder2Ont extends TDBWriteTransactional[ReflectiveAssessmentChangeData] {

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
    val selfAssnew = new SelfAssessment(comp, competence, user, convertAssessmentStringToIndex(reflectiveAsssessment.getAssessment()), reflectiveAsssessment.getIsLearningGoal())
    selfAssnew.persist
  }
}