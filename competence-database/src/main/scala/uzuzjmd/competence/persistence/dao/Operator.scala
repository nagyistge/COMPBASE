package uzuzjmd.competence.persistence.dao

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager
import uzuzjmd.competence.persistence.ontology.CompOntClass

class Operator(compManager: CompOntologyManager, identifer: String, val definition: String = null) extends CompetenceOntologySingletonDao(compManager, CompOntClass.Operator, identifer) {

  @Override
  protected def persistMore() {
    val operatorRoot = new OperatorInstance(comp)
    val ontClass = persist(false).getOntclass()
    ontClass.addSuperClass(operatorRoot.persist(false).getOntclass())
    if (definition != null) {
      //addDataField(DEFINITION, definition) legacy problem
      compManager.getUtil().createOntClassForString(definition, false, definition)
    }

  }

  @Override
  def getFullDao(): Operator = {
    return new Operator(compManager, identifier, getDataField(DEFINITION))
  }
}