package uzuzjmd.competence.mapper.gui

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.shared.ReflectiveAssessmentsListHolder
import uzuzjmd.competence.owl.dao.User
import scala.collection.JavaConverters._
import uzuzjmd.competence.shared.Assessment
import scala.collection.mutable.LinkedHashMap
import uzuzjmd.competence.shared.ReflectiveAssessment
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.SelfAssessment
import uzuzjmd.competence.owl.dao.Competence

object ReflectiveAssessmentHolder2Ont {
  def convert(comp: CompOntologyManager, user: User, courseContext: CourseContext, reflectiveAssessmentHolder: ReflectiveAssessmentsListHolder) {
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