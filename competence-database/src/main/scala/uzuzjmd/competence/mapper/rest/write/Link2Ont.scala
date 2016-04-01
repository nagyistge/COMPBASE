package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.persistence.abstractlayer.WriteTransactional
import uzuzjmd.competence.persistence.dao._
import uzuzjmd.competence.service.rest.dto.CompetenceLinkData

import scala.collection.JavaConverters.asScalaBufferConverter

/**
  * @author dehne
  */
object Link2Ont extends WriteTransactional[CompetenceLinkData] with RoleConverter {

  def writeLinkToDatabase(data: CompetenceLinkData) {
    execute(linkCompetencesToJson _, data)
  }

  def linkCompetencesToJson(data: CompetenceLinkData) {
    val creatorRole = convertRole(data.getRole);
    for (evidence <- data.getEvidences().asScala) {
      for (competence <- data.getCompetences.asScala) {
        val courseContext = new CourseContext(data.getCourse);
        courseContext.persist();
        val creatorUser = new User(data.getCreator, creatorRole, courseContext);
        creatorUser.persist();
        val linkedUserUser = new User(data.getLinkedUser, Role.student, courseContext);
        linkedUserUser.persist();
        val evidenceActivity = new EvidenceActivity(evidence.split(",")(1), evidence.split(",")(0));
        evidenceActivity.persist()
        val competenceDao = new Competence(competence);
        competenceDao.persist
        val abstractEvidenceLink = new AbstractEvidenceLink(creatorUser,linkedUserUser, courseContext, evidenceActivity,System.currentTimeMillis(), false, competenceDao,
          null);
        abstractEvidenceLink.persist();
        linkedUserUser.addCompetencePerformed(competenceDao)
      }
    }
  }

}