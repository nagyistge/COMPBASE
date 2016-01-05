package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.config.MagicStrings
import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, WriteTransactional}
import uzuzjmd.competence.persistence.dao.Competence
import uzuzjmd.competence.persistence.dao.Competence
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl
import uzuzjmd.competence.service.rest.dto.PrerequisiteData
import scala.collection.JavaConversions._

/**
 * @author jbe
 */

object CreatePrerequisiteInOnt extends WriteTransactional[PrerequisiteData] {
  def convert(changes: PrerequisiteData) {
    execute(convertCreatePrerequisiteInOnt _, changes)
  }

  def convertCreatePrerequisiteInOnt(comp: CompOntologyManager, changes: PrerequisiteData) {
    comp.startReasoning(MagicStrings.WRITEDEBUGRDF);
    val competence = new Competence(comp, changes.getPostCompetence, null, null);
    changes.getPrerequisiteCompetences.foreach { x => competence.addRequiredCompetence(new Competence(comp, x, null, null)) }
  }
}