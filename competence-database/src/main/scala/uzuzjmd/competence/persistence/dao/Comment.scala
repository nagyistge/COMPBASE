package uzuzjmd.competence.persistence.dao

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, CompOntologyAccess}
import uzuzjmd.competence.persistence.ontology.CompOntClass
import uzuzjmd.competence.persistence.ontology.CompObjectProperties
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl

case class Comment(comp: CompOntologyManager, val text: String, val creator: User = null, val created: java.lang.Long = null, val textReadable: String = null) extends CompetenceOntologyDao(comp, CompOntClass.Comment, text) {

  def DATECRATED = "datecreated"
  def TEXT = "text"

  @Override
  protected def deleteMore() {
    //TODO

  }

  @Override
  protected def persistMore() {
    creator.persist
    createEdgeWith(creator, CompObjectProperties.UserOfComment)
    addDataField(TEXT, identifier)
    if (created == null) {
      addDataField(DATECRATED, System.currentTimeMillis(): java.lang.Long);
    } else {
      addDataField(DATECRATED, created);
    }
  }

  def getFullDao(): Comment = {
    val creatorNew = getAssociatedStandardDaosAsDomain(CompObjectProperties.UserOfComment, classOf[User]).head
    val textNew = getDataField(TEXT)
    val dateCreatedNew = getDataFieldLong(DATECRATED)
    return new Comment(comp, textNew, creatorNew, dateCreatedNew, textNew)
  }
}