package uzuzjmd.competence.persistence.dao

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager
import uzuzjmd.competence.persistence.ontology.CompOntClass

class CompetenceInstance(comp: CompOntologyManager) extends CompetenceOntologySingletonDao(comp, CompOntClass.Competence, CompOntClass.Competence.toString) {

  @Override
  protected def persistMore() {
    addDataField(DEFINITION, CompOntClass.Competence.toString())
    addDataField("compulsory", new java.lang.Boolean(false))
  }

  @Override
  def getFullDao(): Dao = {
    return this
  }

  override def getDefinition(): String = {
    return CompOntClass.Competence.toString()
  }

  override def hasSuperClass : Boolean = {
    return false;
  }

}