package uzuzjmd.competence.mapper.rest.write

import java.util

import uzuzjmd.competence.logging.Logging
import uzuzjmd.competence.persistence.dao._
import uzuzjmd.competence.persistence.ontology.Contexts
import uzuzjmd.competence.service.rest.dto.{CommentData, EvidenceData}
import uzuzjmd.competence.shared.dto.{CompetenceLinksView, AbstractAssessment, UserProgress}
import uzuzjmd.competence.util.LanguageConverter
import scala.collection.JavaConverters._
import scala.collection.mutable


/**
  * Created by dehne on 31.05.2016.
  */
object UserProgress2Ont extends LanguageConverter with Logging{

  def convert(input: UserProgress, user: User): Unit = {
    val selfAssessments: mutable.Buffer[(mutable.Buffer[AbstractAssessment], Competence)] = input.getUserCompetenceProgressList.asScala.map(x => (x.getAbstractAssessment.asScala, new Competence(x.getCompetence)))
    val evidences: mutable.Buffer[(Array[CompetenceLinksView], Competence)] = input.getUserCompetenceProgressList.asScala.map(x => (x.getCompetenceLinksView, new Competence(x.getCompetence)))
    selfAssessments.foreach(x => x._1.foreach(persistSelfAssessments(_, x._2)(user)))
    evidences.foreach(x => x._1.foreach(persistEvidences(_, x._2)(user)))
    logger.debug("progress updated")
  }

  def persistSelfAssessments(input: AbstractAssessment, competence: Competence)(user: User): Unit = {
    val assessmentIndex = input.getAssessmentIndex
    val assessment = competence.getAssessment(user)
    assessment.setAssessmentIndex(assessmentIndex)
    assessment.addCompetenceToAssessment(competence)
    assessment.addUserToAssessment(user)
    assessment.persist()
  }

  def persistEvidences(input: CompetenceLinksView, competence: Competence)(user: User): Unit = {
    // persist evidence links
    val evidence = new util.HashMap[String, String]() {
      {
        put(input.getEvidenceTitel, input.getEvidenceUrl);
      }
    }
    val data = EvidenceData.instance(
      "university",
      null,
      "teacher",
      user.getId,
      (competence.getDefinition :: Nil).asJava,
      evidence,
      user.getName)
    Evidence2Ont.writeLinkToDatabase(data)

    // persist comments
    val linkId = AbstractEvidenceLink.computeId(competence.getDefinition, input.getEvidenceUrl)
    val commentData = input.getComments.asScala.map(x=>new CommentData(x.getCreated,linkId, x.getUserName, x.getCommentName, Contexts.university.toString, data.getRole))
    commentData.foreach(Comment2Ont.convert)

  }
}
