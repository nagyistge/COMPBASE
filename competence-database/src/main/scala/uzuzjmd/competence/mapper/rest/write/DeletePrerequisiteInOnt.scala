package uzuzjmd.competence.mapper.rest.write

import java.util.LinkedList
import uzuzjmd.competence.config.MagicStrings
import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, WriteTransactional}
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl
import uzuzjmd.competence.service.rest.dto.PrerequisiteData

import scala.collection.JavaConversions.asScalaBuffer
import uzuzjmd.competence.persistence.dao.Competence

/**
 * @author jbe
 */

object DeletePrerequisiteInOnt extends WriteTransactional[PrerequisiteData] {
  def convert(changes: PrerequisiteData) {
    execute(convertDeletePrerequisiteInOnt _, changes)
  }

  def convertDeletePrerequisiteInOnt(comp: CompOntologyManager, changes: PrerequisiteData) {
    comp.startReasoning(MagicStrings.WRITEDEBUGRDF);
    val competence = new Competence(comp, changes.getPostCompetence, null, null);
    if (changes.getPrerequisiteCompetences == null || changes.getPrerequisiteCompetences.isEmpty()) {
      changes.setPrerequisiteCompetences(new LinkedList[String]());
      competence.getRequiredCompetencesAsArray.foreach { x => changes.addElementSelectedCompetences(x) }
    }
    changes.getPrerequisiteCompetences.foreach { x => competence.addNotRequiredCompetence(new Competence(comp, x, null, null)) }
  }
}