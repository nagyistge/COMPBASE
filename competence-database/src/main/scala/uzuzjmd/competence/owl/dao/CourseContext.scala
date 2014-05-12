package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass

class CourseContext(val name: String, comp: CompOntologyManager) extends CompetenceOntologyDao(comp, CompOntClass.CourseContext, name) {
  @Override
  protected def deleteMore() {
    //TODO
  }

  @Override
  protected def persistMore() {
    //TODO  
  }
}