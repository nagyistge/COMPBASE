package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.monopersistence.daos._
import uzuzjmd.competence.persistence.abstractlayer.WriteTransactional
import uzuzjmd.competence.service.rest.dto.CommentData

/**
 * @author dehne
 */
object Comment2Ont extends RoleConverter with WriteTransactional[CommentData] {

  def convert(data: CommentData) {
    execute(createComment _, data)
  }

  def createComment(data: CommentData) {
    val creator2 = new User(data.getUser);
    val abstractEvidenceLink = new AbstractEvidenceLink(data.getLinkId)
    val comment = new Comment(data.getText, creator2, System.currentTimeMillis());
    comment.persist();
    abstractEvidenceLink.linkComment(comment);
  }
}