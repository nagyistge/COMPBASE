package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager

class StudentRole(comp: CompOntologyManager, var role : Dao) extends Role(comp, CompOntClass.StudentRole) {
   role =  new Role(comp, CompOntClass.Role)
}