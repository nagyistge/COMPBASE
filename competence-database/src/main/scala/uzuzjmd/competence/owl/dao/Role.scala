package uzuzjmd.competence.owl.dao

import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.access.CompOntologyManager

abstract class Role(comp: CompOntologyManager, compOntClass: CompOntClass) extends CompetenceOntologySingletonDao(comp, compOntClass) {

}