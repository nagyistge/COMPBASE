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
    val userDap = new User(comp, user)
    val links = userDap.getAssociatedLinks.view.map(x => x.getFullDao)
    val maps = links.map(link => (link -> link.getAllLinkedCompetences)).toMap
    val competencesLinked = MapsMagic.invertAssociation(maps)
    val resultScala: scala.collection.immutable.Map[String, java.util.List[CompetenceLinksView]] = competencesLinked.map(x => (x._1.getDefinition(), x._2.map(mapAbstractEvidenceLinkToCompetenceLinksView).flatten.asJava)).filterKeys(p => p != null)
//    val map = new TreeMap[String, java.util.List[CompetenceLinksView]]
//    if (resultScala != null && !resultScala.isEmpty) {
//      map.putAll(resultScala.asJava)
//    }
    val result = new CompetenceLinksMap(resultScala.asJava);    
    result.getMapUserCompetenceLinks.isEmpty()
    return result
  }

  def mapAbstractEvidenceLinkToCompetenceLinksView(input: AbstractEvidenceLink): List[CompetenceLinksView] = {
    val linkedEvidence = input.getAllActivities.map(x=>x.getFullDao())
    val linkedComments = input.comments.map(mapCommentToCommentEntry)
    val competenceLinksView = linkedEvidence.map(x =>new CompetenceLinksView(input.identifier, x.url, x.printableName, linkedComments.asJava, input.isValidated))
    return competenceLinksView
  }

  def mapCommentToCommentEntry(input: Comment): CommentEntry = {
    val fullComment = input.getFullDao
    val creatorName = fullComment.creator.getFullDao.readableName
    val result = new CommentEntry(creatorName, fullComment.textReadable, fullComment.created)
    return result
  }

}