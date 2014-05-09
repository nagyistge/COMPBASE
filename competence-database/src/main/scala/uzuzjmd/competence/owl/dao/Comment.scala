package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager

class Comment(created: Long, text: String, comp: CompOntologyManager) extends CompetenceOntologyDao(comp, CompOntClass.Comment, text.hashCode() + "") {
  @Override
  protected def deleteMore() {
    //TODO
  }

  @Override
  protected def persistMore() {
    //TODO  
  }
}