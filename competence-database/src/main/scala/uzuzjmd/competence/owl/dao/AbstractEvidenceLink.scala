package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import com.hp.hpl.jena.ontology.Individual
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.ontology.CompObjectProperties

class AbstractEvidenceLink(val comp: CompOntologyManager, identifier: String, val creator: User = null,
  val linkedUser: User = null,
  val courseContexts: CourseContext = null,
  val comments: List[Comment] = null,
  val evidenceActivity: EvidenceActivity = null,
  val dateCreated: java.lang.Long = null,
  val isValidated: java.lang.Boolean = null) extends CompetenceOntologyDao(comp, CompOntClass.AbstractEvidenceLink, identifier) {

  def CREATED = "datecreated"
  def ISVALIDATED = "isValidated"

  @Override
  protected def deleteMore() {
    if (comments != null) {
      comments.foreach(x => x.delete)
    }
  }

  @Override
  protected def persistMore() {
    if (comments != null) {
      linkComments(comments)
    }
    linkedUser.persist
    createEdgeWith(linkedUser, CompObjectProperties.UserOfLink)
    creator.persist
    createEdgeWith(CompObjectProperties.createdBy, creator)
    courseContexts.persist
    createEdgeWith(CompObjectProperties.LinkOfCourseContext, courseContexts)
    evidenceActivity.persist
    createEdgeWith(evidenceActivity, CompObjectProperties.ActivityOf)
    addDataField(CREATED, dateCreated)
    addDataField(ISVALIDATED, isValidated)
  }

  def linkComments(comments: List[Comment]) {
    comments.foreach(x => x.persist)
    comments.foreach(x => createEdgeWith(x, CompObjectProperties.CommentOf))
  }

  @Override
  def getFullDao(): AbstractEvidenceLink = {
    val comments = getAssociatedStandardDaosAsDomain(CompObjectProperties.CommentOf, classOf[Comment])
    val creator = getAssociatedStandardDaosAsRange(CompObjectProperties.createdBy, classOf[User]).head
    val courseContext = getAssociatedStandardDaosAsRange(CompObjectProperties.LinkOfCourseContext, classOf[CourseContext])
    val evidenceActivity = getAssociatedStandardDaosAsDomain(CompObjectProperties.ActivityOf, classOf[EvidenceActivity])
    return new AbstractEvidenceLink(comp, identifier, creator, null, null, comments, null)
  }

  def getAllLinkedUsers(): List[User] = {
    return getAssociatedStandardDaosAsDomain(CompObjectProperties.UserOfLink, classOf[User]).map(x => x.getFullDao.asInstanceOf[User])
  }

  def getAllActivities(): List[EvidenceActivity] = {
    return getAssociatedStandardDaosAsDomain(CompObjectProperties.ActivityOf, classOf[EvidenceActivity]).map(x => x.getFullDao.asInstanceOf[EvidenceActivity])
  }

  def getAllCourseContexts(): List[CourseContext] = {
    return getAssociatedStandardDaosAsRange(CompObjectProperties.LinkOfCourseContext, classOf[CourseContext]).map(x => x.getFullDao.asInstanceOf[CourseContext])
  }

}