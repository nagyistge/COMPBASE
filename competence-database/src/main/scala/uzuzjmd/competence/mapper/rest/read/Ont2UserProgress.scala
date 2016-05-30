package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.persistence.dao.{CourseContext, User, SelfAssessment, Competence}
import uzuzjmd.competence.persistence.neo4j.CompetenceNeo4jQueryManagerImpl
import uzuzjmd.competence.shared.Assessment
import uzuzjmd.competence.shared.dto.{CompetenceLinksMap, AbstractAssessment, UserProgress, UserCompetenceProgress}
import uzuzjmd.competence.util.LanguageConverter

import scala.collection.mutable
import scala.collection.JavaConverters._

/**
  * Created by dehne on 30.05.2016.
  */
object Ont2UserProgress extends LanguageConverter {

  val manager = new CompetenceNeo4jQueryManagerImpl

  def convert(competence : String, userId: String) : UserCompetenceProgress = {

    val competenceDAO = new Competence(competence)
    val user = new User(userId)
    val assessment: SelfAssessment = competenceDAO.getAssessment(user)
    val selfAssessment = new AbstractAssessment("eposScale", assessment.getAssessmentIndex, new Assessment().getItems)
    val linksMap: CompetenceLinksMap = Ont2CompetenceLinkMap.convert(userId)
    val linksView = linksMap.getMapUserCompetenceLinks.get(competence)
    val result = new UserCompetenceProgress(competence, linksView, selfAssessment)

    return result
  }

  def convert2(user : String, courseId: String) : UserProgress = {
    val course = new CourseContext(courseId)
    val progressList: mutable.Buffer[UserCompetenceProgress] = course.getLinkedCompetences.asScala.map(x=>x.getDefinition).map(convert(_, user))
    val result = new UserProgress()
    if (!progressList.isEmpty) {
      result.setUserCompetenceProgressList(progressList.asJava)
    }
    return result
  }

}
