package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass

class CourseContext(comp: CompOntologyManager, val name: String) extends CompetenceOntologyDao(comp, CompOntClass.CourseContext, name) {
  @Override
  protected def deleteMore() {
    //TODO
  }

  @Override
  protected def persistMore() {
    //TODO  
  }

  def getFullDao(): CompetenceOntologyDao = {
    return this;
  }
}