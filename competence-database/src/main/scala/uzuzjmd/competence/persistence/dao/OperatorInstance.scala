package uzuzjmd.competence.persistence.dao

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager
import uzuzjmd.competence.persistence.ontology.CompOntClass

class OperatorInstance(comp: CompOntologyManager) extends CompetenceOntologySingletonDao(comp, CompOntClass.Operator) {
  @Override
  protected def persistMore() {
  }

  @Override
  def getFullDao(): Dao = {
    return this
  }

  override def hasSuperClass : Boolean = {
    return false;
  }
}