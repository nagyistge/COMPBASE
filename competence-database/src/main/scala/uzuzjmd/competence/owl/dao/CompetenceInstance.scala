package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass

class CompetenceInstance(comp: CompOntologyManager) extends CompetenceOntologySingletonDao(comp, CompOntClass.Competence) {
  @Override
  protected def persistMore() {
  }

  @Override
  def getFullDao(): Dao = {
    return this
  }
}