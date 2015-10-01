package uzuzjmd.competence.mapper.rest

import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.owl.access.CompOntologyManager
import org.specs2.internal.scalaz.std.string
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.service.rest.model.dto.PrerequisiteData
import scala.collection.JavaConversions._

/**
* @author jbe
**/

object CreatePrerequisiteInOnt extends TDBWriteTransactional[PrerequisiteData]{
  def convert(changes:PrerequisiteData) {
    execute(convertCreatePrerequisiteInOnt _, changes)
  }
  
  def convertCreatePrerequisiteInOnt(comp: CompOntologyManager, changes: PrerequisiteData) {
    comp.startReasoning();
		comp.switchOffDebugg();
		val competence = new Competence(comp, changes.getLinkedCompetence, null, null);
		changes.getSelectedCompetences.foreach {x => competence.addRequiredCompetence(new Competence(comp, x, null, null))}
  }
}