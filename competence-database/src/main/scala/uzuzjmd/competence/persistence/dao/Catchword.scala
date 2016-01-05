package uzuzjmd.competence.persistence.dao

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager
import uzuzjmd.competence.persistence.ontology.CompOntClass

class Catchword(compManager: CompOntologyManager, identifer: String, val definition: String) extends CompetenceOntologySingletonDao(compManager, CompOntClass.Catchword, identifer) {

  @Override
  protected def persistMore() {
    val catchwordRoot = new CatchwordInstance(comp)
    val ontClass = persistManualCascades(false).getOntclass()
    ontClass.addSuperClass(catchwordRoot.persistManualCascades(false).getOntclass())
    if (definition != null) {
      //addDataField(DEFINITION, definition) legacy problem
      compManager.getUtil().createOntClassForString(definition, false, definition)
    }

  }

  @Override
  def getFullDao(): Catchword = {
    return new Catchword(compManager, identifier, getDataField(DEFINITION))
  }
}