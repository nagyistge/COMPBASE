package uzuzjmd.competence.mapper.rest.write

import scala.collection.JavaConverters.asScalaBufferConverter

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.EvidenceActivity
import uzuzjmd.competence.owl.dao.StudentRole
import uzuzjmd.competence.owl.dao.User
import uzuzjmd.competence.service.rest.model.dto.CompetenceLinkData
import uzuzjmd.competence.service.rest.model.dto.RoleConverter

/**
 * @author dehne
 */
object Link2Ont extends TDBWriteTransactional[CompetenceLinkData] with RoleConverter {

  def writeLinkToDatabase(data: CompetenceLinkData) {
    execute(linkCompetencesToJson _, data)
  }

  def linkCompetencesToJson(comp: CompOntologyManager, data: CompetenceLinkData) {

    val creatorRole = convertRole(data.getRole, comp);
    for (evidence <- data.getEvidences().asScala) {
      for (competence <- data.getEvidences().asScala) {
        val courseContext = new CourseContext(comp, data.getCourse);
        courseContext.persist();
        val creatorUser = new User(comp, data.getCreator, creatorRole, courseContext, data.getCreator);
        creatorUser.persist();
        val linkedUserUser = new User(comp, data.getLinkedUser, new StudentRole(comp), courseContext, data.getLinkedUser);
        linkedUserUser.persist();

        val evidenceActivity = new EvidenceActivity(comp, evidence.split(",")(0), evidence.split(",")(1));
        val competenceDao = new Competence(comp, competence, competence, null);
        competenceDao.persist(true);
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