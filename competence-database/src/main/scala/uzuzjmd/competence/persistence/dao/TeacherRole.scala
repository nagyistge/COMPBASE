package uzuzjmd.competence.persistence.dao

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager
import uzuzjmd.competence.persistence.ontology.CompOntClass

class TeacherRole(comp: CompOntologyManager) extends Role(comp, CompOntClass.TeacherRole) {
  @Override
  protected def persistMore() {

  }

  @Override
  def getFullDao(): Dao = {
    return this
  }
}