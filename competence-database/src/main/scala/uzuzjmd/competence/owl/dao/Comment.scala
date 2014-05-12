package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager

class Comment(created: Long, text: String, comp: CompOntologyManager) extends CompetenceOntologyDao(comp, CompOntClass.Comment, text.hashCode() + "") {

  def DATECRATED = "datecreated"
  def TEXT = "text"

  @Override
  protected def deleteMore() {
    //TODO
  }

  @Override
  protected def persistMore() {
    addDataField(DATECRATED, created + "");
    addDataField(TEXT, text)
  }
}