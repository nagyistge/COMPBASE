package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompObjectProperties

class Comment(comp: CompOntologyManager, text: String, creator: User, created: Long) extends CompetenceOntologyDao(comp, CompOntClass.Comment, text.hashCode() + "") {

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
    addDataField(DATECRATED, created + "");
    addDataField(TEXT, text)
  }

  def getFullDao(): CompetenceOntologyDao = {
    val creator = getAssociatedStandardDaosAsDomain(CompObjectProperties.UserOfComment, classOf[User]).head
    val dateCreated = getDataField(DATECRATED).toString()
    return new Comment(comp, text, creator, java.lang.Long.parseLong(dateCreated))
  }
}