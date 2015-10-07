package uzuzjmd.competence.mapper.rest.write

import java.util.LinkedList

import scala.collection.JavaConversions.asScalaBuffer

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.service.rest.model.dto.PrerequisiteData

/**
* @author jbe
**/

object DeletePrerequisiteInOnt extends TDBWriteTransactional[PrerequisiteData]{
  def convert(changes:PrerequisiteData) {
    execute(convertDeletePrerequisiteInOnt _, changes)
  }
  
  def convertDeletePrerequisiteInOnt(comp: CompOntologyManager, changes: PrerequisiteData) {
    comp.startReasoning();
		comp.switchOffDebugg();
		val competence = new Competence(comp, changes.getLinkedCompetence, null, null);
		if (changes.getSelectedCompetences == null || changes.getSelectedCompetences.isEmpty()) {
			changes.setSelectedCompetences(new LinkedList[String]());
			competence.getRequiredCompetencesAsArray.foreach { x => changes.addElementSelectedCompetences(x) }
		}
		changes.getSelectedCompetences.foreach { x => competence.addNotRequiredCompetence(new Competence(comp, x, null, null)) }
  }
}