package uzuzjmd.competence.persistence.dao

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager

class UniversityContext(comp: CompOntologyManager) extends CourseContext(comp, "university") {
	def toStrinz : String = {
	   return "university" 
	}  
  
}