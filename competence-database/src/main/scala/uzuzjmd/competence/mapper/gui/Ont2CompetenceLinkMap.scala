package uzuzjmd.competence.mapper.gui

import java.util.SortedSet
import java.util.TreeSet
import scala.collection.JavaConverters.mapAsJavaMapConverter
import scala.collection.JavaConverters.seqAsJavaListConverter
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink
import uzuzjmd.competence.owl.dao.Comment
import uzuzjmd.competence.owl.dao.User
import uzuzjmd.competence.shared.dto.CommentEntry
import uzuzjmd.competence.shared.dto.CompetenceLinksMap
import uzuzjmd.competence.shared.dto.CompetenceLinksView
import uzuzjmd.java.collections.MapsMagic
import uzuzjmd.competence.service.rest.database.dto.CompetenceLinksViewComparator
import scala.collection.JavaConverters._

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
    val maps = links.map(link => (link -> link.getAllLinkedCompetences.map(x => x.getFullDao().getDefinition()))).toMap
    val competencesLinked = MapsMagic.invertAssociation(maps)
    val resultScalaTmp1: scala.collection.immutable.Map[String, List[CompetenceLinksView]] = competencesLinked.map(x => (x._1, x._2.map(mapAbstractEvidenceLinkToCompetenceLinksView).flatten))
    val resultScalaTmp2 = resultScalaTmp1.filterKeys(p => p != null)
    val resultAsArray = resultScalaTmp2.mapValues { x => x.toArray }
    val result = new CompetenceLinksMap(resultAsArray.asJava);    
    result.getMapUserCompetenceLinks.isEmpty()
    return result
  }

  def mapAbstractEvidenceLinkToCompetenceLinksView(input: AbstractEvidenceLink): List[CompetenceLinksView] = {
    val linkedEvidence = input.getAllActivities.map(x=>x.getFullDao())
    val linkedComments = input.comments.map(mapCommentToCommentEntry)
    val competenceLinksView = linkedEvidence.map(x =>new CompetenceLinksView(input.identifier, x.printableName, x.url, linkedComments.asJava, input.isValidated))
    return competenceLinksView
  }

  def mapCommentToCommentEntry(input: Comment): CommentEntry = {
    val fullComment = input.getFullDao
    val creatorName = fullComment.creator.getFullDao.readableName
    val result = new CommentEntry(creatorName, fullComment.textReadable, fullComment.created)
    return result
  }

}