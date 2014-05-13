package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.ontology.CompObjectProperties

class User(comp: CompOntologyManager, val name: String, role: Role = null) extends CompetenceOntologyDao(comp, CompOntClass.User, name) {
  def NAME = "name"

  @Override
  protected def deleteMore() {
    role.deleteEdge(CompObjectProperties.RoleOf, this)
  }

  @Override
  protected def persistMore() {
    val thisIndividual = createIndividual
    role.createEdgeWith(CompObjectProperties.RoleOf, this)
    addDataField(NAME, name)
  }

  @Override
  def getFullDao(): CompetenceOntologyDao = {
    val teacherRole = new TeacherRole(comp)
    if (hasEdge(teacherRole, CompObjectProperties.RoleOf)) {
      return new User(comp, name, teacherRole)
    } else {
      return new User(comp, name, new StudentRole(comp))
    }
  }
}