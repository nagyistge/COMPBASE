package uzuzjmd.competence.mapper.gui

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.User
import uzuzjmd.competence.owl.dao.DaoFactory
import uzuzjmd.java.collections.MapsMagic
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink
import uzuzjmd.competence.service.rest.dto.CompetenceLinksView
import uzuzjmd.competence.service.rest.dto.CommentEntry
import uzuzjmd.competence.owl.dao.Comment
import scala.collection.JavaConverters._
import uzuzjmd.competence.service.rest.dto.CompetenceLinksMap
import java.util.HashMap
import java.util.TreeMap

class Ont2CompetenceLinkMap(comp: CompOntologyManager, user: String) {
  def getCompetenceLinkMap(): CompetenceLinksMap = {
    comp.begin()
    val userDap = new User(comp, user)
    val links = userDap.getAssociatedLinks.view.map(x => x.getFullDao)
    val maps = links.map(link => (link -> link.getAllLinkedCompetences)).toMap
    val competencesLinked = MapsMagic.invertAssociation(maps)
    val resultScala = competencesLinked.map(x => (x._1.getDataField(x._1.DEFINITION), x._2.map(mapAbstractEvidenceLinkToCompetenceLinksView).asJava)).toMap
    val map = new TreeMap[String, java.util.List[CompetenceLinksView]]
    map.putAll(resultScala.asJava)
    val result = new CompetenceLinksMap(map);
    comp.close()
    return result
  }

  def mapAbstractEvidenceLinkToCompetenceLinksView(input: AbstractEvidenceLink): CompetenceLinksView = {
    val linkedEvidence = input.getAllActivities.head.getFullDao
    val linkedComments = input.comments.map(mapCommentToCommentEntry)
    val competenceLinksView = new CompetenceLinksView(input.identifier, linkedEvidence.printableName, linkedEvidence.url, linkedComments.asJava, input.isValidated)
    return competenceLinksView
  }

  def mapCommentToCommentEntry(input: Comment): CommentEntry = {
    val fullComment = input.getFullDao
    val creatorName = fullComment.creator.getFullDao.readableName
    val result = new CommentEntry(creatorName, fullComment.textReadable, fullComment.created)
    return result
  }

}