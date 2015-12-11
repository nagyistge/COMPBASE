package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, TDBWriteTransactional}
import uzuzjmd.competence.persistence.dao.Comment
import uzuzjmd.competence.persistence.dao.CourseContext
import uzuzjmd.competence.persistence.dao.DaoFactory
import uzuzjmd.competence.persistence.dao.User
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl
import uzuzjmd.competence.service.rest.model.dto.CommentData
import uzuzjmd.competence.service.rest.model.dto.RoleConverter

/**
 * @author dehne
 */
object Comment2Ont extends RoleConverter with TDBWriteTransactional[CommentData] {

  def convert(data: CommentData) {
    execute(createComment _, data)
  }

  def createComment(comp: CompOntologyManager, data: CommentData) {
    val creatorRole2 = convertRole(data.getRole, comp);
    val coursecontext2 = new CourseContext(comp, data.getCourseContext);
    val creator2 = new User(comp, data.getUser, creatorRole2, coursecontext2, data.getUser);
    val abstractEvidenceLink = DaoFactory.getAbstractEvidenceDao(comp, data.getLinkId);
    val comment = new Comment(comp, data.getText, creator2, System.currentTimeMillis(), data.getText);
    comment.persist();
    abstractEvidenceLink.linkComment(comment);
  }
}