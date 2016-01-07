package uzuzjmd.competence.persistence.dao

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager
import uzuzjmd.competence.persistence.ontology.CompOntClass

class CatchwordInstance(comp: CompOntologyManager) extends CompetenceOntologySingletonDao(comp, CompOntClass.Catchword) {
  @Override
  protected def persistMore() {
  }

  @Override
  def getFullDao(): Dao = {
    return this
  }
}