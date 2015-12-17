package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, WriteTransactional}
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl
import uzuzjmd.competence.service.rest.dto.CompetenceLinkData

import scala.collection.JavaConverters.asScalaBufferConverter
import uzuzjmd.competence.persistence.dao.AbstractEvidenceLink
import uzuzjmd.competence.persistence.dao.Competence
import uzuzjmd.competence.persistence.dao.CourseContext
import uzuzjmd.competence.persistence.dao.EvidenceActivity
import uzuzjmd.competence.persistence.dao.StudentRole
import uzuzjmd.competence.persistence.dao.User
import uzuzjmd.competence.persistence.dao.CourseContext

/**
 * @author dehne
 */
object Link2Ont extends WriteTransactional[CompetenceLinkData] with RoleConverter {

  def writeLinkToDatabase(data: CompetenceLinkData) {
    execute(linkCompetencesToJson _, data)
  }

  def linkCompetencesToJson(comp: CompOntologyManager, data: CompetenceLinkData) {

    val creatorRole = convertRole(data.getRole, comp);
    for (evidence <- data.getEvidences().asScala) {
      for (competence <- data.getCompetences.asScala) {
        val courseContext = new CourseContext(comp, data.getCourse);
        courseContext.persist();
        val creatorUser = new User(comp, data.getCreator, creatorRole, courseContext, data.getCreator);
        creatorUser.persist();
        val linkedUserUser = new User(comp, data.getLinkedUser, new StudentRole(comp), courseContext, data.getLinkedUser);
        linkedUserUser.persist();

        val evidenceActivity = new EvidenceActivity(comp, evidence.split(",")(1), evidence.split(",")(0));
        evidenceActivity.persist()
        val competenceDao = new Competence(comp, competence, competence, null);
        competenceDao.persistManualCascades(true);
        val abstractEvidenceLink = new AbstractEvidenceLink(comp, null, creatorUser,
          linkedUserUser, courseContext, evidenceActivity,
          System
            .currentTimeMillis(), false, competenceDao,
          null);
        abstractEvidenceLink.persist();
      }
    }
  }

}