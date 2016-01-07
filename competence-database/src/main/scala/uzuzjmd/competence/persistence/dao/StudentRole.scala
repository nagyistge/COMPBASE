package uzuzjmd.competence.persistence.dao

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager
import uzuzjmd.competence.persistence.ontology.CompOntClass

class StudentRole(comp: CompOntologyManager) extends Role(comp, CompOntClass.StudentRole) {

  @Override
  protected def persistMore() {

  }

  @Override
  def getFullDao(): Dao = {
    return this
  }
}