package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass

class Operator(compManager: CompOntologyManager, identifer: String, val definition: String = null) extends CompetenceOntologySingletonDao(compManager, CompOntClass.Operator, identifer) {

  @Override
  protected def persistMore() {
    val operatorRoot = new OperatorInstance(comp)
    val ontClass = persist(false).getOntclass()
    ontClass.addSuperClass(operatorRoot.persist(false).getOntclass())
    if (definition != null) {
      //addDataField(DEFINITION, definition) legacy problem
      compManager.getUtil().createOntClassForString(definition, definition)
    }

  }

  @Override
  def getFullDao(): Operator = {
    return new Operator(compManager, identifier, getDataField(DEFINITION))
  }
}