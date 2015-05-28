package uzuzjmd.competence.mapper.gui

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.User
import uzuzjmd.competence.owl.dao.DaoFactory
import uzuzjmd.java.collections.MapsMagic
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink
import uzuzjmd.competence.owl.dao.Comment
import scala.collection.JavaConverters._
import scala.collection.breakOut
import java.util.HashMap
import java.util.TreeMap
import java.util.SortedSet
import scala.reflect.internal.util.Collections
import java.util.TreeSet
import uzuzjmd.competence.service.rest.client.dto.CommentEntry
import uzuzjmd.competence.service.rest.client.dto.CompetenceLinksViewComparator
import uzuzjmd.competence.service.rest.client.dto.CompetenceLinksMap
import uzuzjmd.competence.service.rest.client.dto.CompetenceLinksView

class Ont2CompetenceLinkMap(comp: CompOntologyManager, user: String) {

  def toSortedSet[A](input: List[CompetenceLinksView]): SortedSet[CompetenceLinksView] = {
    val sorted = java.util.Collections.synchronizedSortedSet(new TreeSet(new CompetenceLinksViewComparator));
    sorted.addAll(input.asJava)
    return sorted
  }

  implicit def toMap(input: Map[String, List[CompetenceLinksView]]): Map[String, java.util.SortedSet[CompetenceLinksView]] = {
    val result: Map[String, java.util.SortedSet[CompetenceLinksView]] = Map.empty
    result ++ input.mapValues(toSortedSet)
  }

  def getCompetenceLinkMap(): CompetenceLinksMap = {
    comp.begin()

    val userDap = new User(comp, user)
    val links = userDap.getAssociatedLinks.view.map(x => x.getFullDao)
    val maps = links.map(link => (link -> link.getAllLinkedCompetences)).toMap
    val competencesLinked = MapsMagic.invertAssociation(maps)
    val resultScala: scala.collection.immutable.Map[String, SortedSet[CompetenceLinksView]] = competencesLinked.map(x => (x._1.getDataField(x._1.DEFINITION), x._2.map(mapAbstractEvidenceLinkToCompetenceLinksView))).filterKeys(p => p != null)
    val map = new TreeMap[String, java.util.SortedSet[CompetenceLinksView]]
    if (resultScala != null && !resultScala.isEmpty) {
      map.putAll(resultScala.asJava)
    }
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