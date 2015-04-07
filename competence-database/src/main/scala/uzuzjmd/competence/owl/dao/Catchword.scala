package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass

class Catchword(compManager: CompOntologyManager, identifer: String, val definition: String = null) extends CompetenceOntologySingletonDao(compManager, CompOntClass.Catchword, identifer) {

  @Override
  protected def persistMore() {
    val catchwordRoot = new CatchwordInstance(comp)
    val ontClass = persist(false).getOntclass()
    ontClass.addSuperClass(catchwordRoot.persist(false).getOntclass())
    if (definition != null) {
      //addDataField(DEFINITION, definition) legacy problem
      compManager.getUtil().createOntClassForString(definition, definition)
    }
  }

  @Override
  def getFullDao(): Catchword = {
    return new Catchword(compManager, identifier, getDataField(DEFINITION))
  }
}