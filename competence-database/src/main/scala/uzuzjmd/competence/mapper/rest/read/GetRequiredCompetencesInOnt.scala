package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.TDBREADTransactional
import uzuzjmd.competence.owl.dao.Competence

/**
* @author jbe
**/

object GetRequiredCompetencesInOnt extends TDBREADTransactional[String, Array[String]]{
  def convert(changes:String) : Array[String]= {
    return execute(convertGetRequiredCompetencesInOnt _, changes)
  }
  
  def convertGetRequiredCompetencesInOnt(comp: CompOntologyManager, changes: String) : Array[String]= {
    comp.startReasoning();
		comp.switchOffDebugg();
		val competence = new Competence(comp, changes, null, null);
		return competence.getRequiredCompetencesAsArray();

  }
}