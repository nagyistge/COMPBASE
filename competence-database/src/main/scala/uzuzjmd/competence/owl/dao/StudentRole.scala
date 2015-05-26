package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager

class StudentRole(comp: CompOntologyManager) extends Role(comp, CompOntClass.StudentRole) {

  @Override
  protected def persistMore() {

  }

  @Override
  def getFullDao(): Dao = {
    return this
  }
}