package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompObjectProperties

class Comment(creator: User, created: Long, text: String, comp: CompOntologyManager) extends CompetenceOntologyDao(comp, CompOntClass.Comment, text.hashCode() + "") {

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
}