package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass
import scala.collection.JavaConverters._

class CompetenceInstance(comp: CompOntologyManager) extends CompetenceOntologySingletonDao(comp, CompOntClass.Competence) {

  @Override
  protected def persistMore() {
    addDataField(DEFINITION, CompOntClass.Competence.toString())
  }

  @Override
  def getFullDao(): Dao = {
    return this
  }

}