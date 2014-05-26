package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.access.CompOntologyAccess

case class Comment(comp: CompOntologyManager, val text: String, val creator: User = null, val created: java.lang.Long = null, val textReadable : String = null) extends CompetenceOntologyDao(comp, CompOntClass.Comment, text) {

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
    addDataField(TEXT, identifierBeforeParsing)
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
    return new Comment(comp, text, creatorNew, dateCreatedNew, textNew)
  }
}