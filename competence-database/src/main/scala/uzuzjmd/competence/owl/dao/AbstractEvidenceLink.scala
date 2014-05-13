package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import com.hp.hpl.jena.ontology.Individual
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.ontology.CompObjectProperties

class AbstractEvidenceLink(val creator: User,
  val linkedUser: User,
  val courseContexts: CourseContext,
  val comments: List[Comment],
  val evidenceActivity: EvidenceActivity,
  val dateCreated: java.lang.Long,
  val isValidated: java.lang.Boolean,
  val comp: CompOntologyManager,
  val compOntClass: CompOntClass) extends CompetenceOntologyDao(comp, CompOntClass.AbstractEvidenceLink, linkedUser.name + evidenceActivity.printableName) {

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
      comments.foreach(x => x.persist)
      comments.foreach(x => createEdgeWith(x, CompObjectProperties.CommentOf))
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

}