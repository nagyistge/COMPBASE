package uzuzjmd.competence.mapper.gui.read

import java.util.{SortedSet, TreeSet}

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, TDBReadTransactional}
import uzuzjmd.competence.persistence.dao.{AbstractEvidenceLink, Comment, User}
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl
import uzuzjmd.competence.service.rest.database.dto.CompetenceLinksViewComparator
import uzuzjmd.competence.shared.dto.{CommentEntry, CompetenceLinksMap, CompetenceLinksView}
import uzuzjmd.java.collections.MapsMagic

import scala.collection.JavaConverters.{mapAsJavaMapConverter, seqAsJavaListConverter}

object Ont2CompetenceLinkMap extends TDBReadTransactional[String, CompetenceLinksMap] {

  private def toSortedSet[A](input: List[CompetenceLinksView]): SortedSet[CompetenceLinksView] = {
    val sorted = java.util.Collections.synchronizedSortedSet(new TreeSet(new CompetenceLinksViewComparator));
    sorted.addAll(input.asJava)
    return sorted
  }

  implicit def toMap(input: Map[String, List[CompetenceLinksView]]): Map[String, java.util.SortedSet[CompetenceLinksView]] = {
    val result: Map[String, java.util.SortedSet[CompetenceLinksView]] = Map.empty
    return result ++ input.mapValues(toSortedSet)
  }

  def convert(user: String): CompetenceLinksMap = {
    return execute(getCompetenceLinkMap, user)
  }

  private def getCompetenceLinkMap(comp: CompOntologyManager, user: String): CompetenceLinksMap = {
    val userDap = new User(comp, user)
    if (!userDap.exists) {
      return new CompetenceLinksMap
    }
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

  private def mapAbstractEvidenceLinkToCompetenceLinksView(input: AbstractEvidenceLink): List[CompetenceLinksView] = {
    val linkedEvidence = input.getAllActivities.map(x => x.getFullDao())
    val linkedComments = input.comments.map(mapCommentToCommentEntry)
    val competenceLinksView = linkedEvidence.map(x => new CompetenceLinksView(input.identifier, x.printableName, x.url, linkedComments.asJava, input.isValidated))
    return competenceLinksView
  }

  private def mapCommentToCommentEntry(input: Comment): CommentEntry = {
    val fullComment = input.getFullDao
    val creatorName = fullComment.creator.getFullDao.readableName
    val result = new CommentEntry(creatorName, fullComment.textReadable, fullComment.created)
    return result
  }

}