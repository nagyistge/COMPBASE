package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import com.hp.hpl.jena.ontology.Individual
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.access.CompOntologyAccess
import uzuzjmd.competence.owl.access.CompOntologyAccessScala
//import com.google.gwt.thirdparty.guava.common.collect.Collections2.PermutationCollection

case class AbstractEvidenceLink(

    val comp: CompOntologyManager,
    val identifier2: String = null,
    val creator: User,
    val linkedUser: User,
    val courseContexts: CourseContext,
    val evidenceActivity: EvidenceActivity = null,
    val dateCreated: java.lang.Long,
    val isValidated: java.lang.Boolean = false,
    val competence: Competence = null,
    val comments: List[Comment] = null) extends CompetenceOntologyDao(comp, CompOntClass.AbstractEvidenceLink, CompOntologyAccessScala.computeEncodedStringForLink(identifier2, competence, evidenceActivity)) {

  def CREATED = "datecreated"
  def ISVALIDATED = "isValidated"

  def this(comp: CompOntologyManager, identifier2: String, competence: Competence = null, evidenceActivity: EvidenceActivity = null) = this(comp, CompOntologyAccessScala.computeEncodedStringForLink(identifier2, competence, evidenceActivity), null, null, null, null, null, null, competence, null)

  def this(comp: CompOntologyManager, identifier2: String, competence2: Competence, evidenceActivity2: EvidenceActivity, comments: List[Comment]) = this(comp, CompOntologyAccessScala.computeEncodedStringForLink(identifier2, competence2, evidenceActivity2), null, null, null, null, null, null, competence2, comments)

  //  def computeEncodedString: String = {
  //    if (identifier2 != null) {
  //      return identifier2
  //    } else {
  //      if (competence == null || evidenceActivity == null) {
  //        throw new Exception("evidenceActivity and competence need to be defined")
  //      }
  //      return competence.getId + evidenceActivity.getId
  //    }
  //  }

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
    competence.persist(false)
    createEdgeWith(CompObjectProperties.linksCompetence, competence)
    addDataField(CREATED, dateCreated)
    addDataField(ISVALIDATED, isValidated)
  }

  def linkComments(comments: List[Comment]) {
    comments.foreach(linkComment)
  }

  def linkComment(comment: Comment) {
    comment.persist
    createEdgeWith(comment, CompObjectProperties.CommentOf)
  }

  @Override
  def getFullDao(): AbstractEvidenceLink = {
    val comments = getAssociatedStandardDaosAsDomain(CompObjectProperties.CommentOf, classOf[Comment])
    val creator = getAssociatedStandardDaosAsRange(CompObjectProperties.createdBy, classOf[User]).head
    val courseContext = getAssociatedStandardDaosAsRange(CompObjectProperties.LinkOfCourseContext, classOf[CourseContext])
    val evidenceActivity = getAssociatedStandardDaosAsDomain(CompObjectProperties.ActivityOf, classOf[EvidenceActivity])
    return new AbstractEvidenceLink(comp, identifier, creator.getFullDao, null, null, null, getDataFieldLong(CREATED), getDataFieldBoolean(ISVALIDATED), null, comments)
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

  def getAllLinkedCompetences(): List[Competence] = {
    return getAssociatedSingletonDaosAsRange(CompObjectProperties.linksCompetence, classOf[Competence]).map(x => x.getFullDao.asInstanceOf[Competence])
  }

}