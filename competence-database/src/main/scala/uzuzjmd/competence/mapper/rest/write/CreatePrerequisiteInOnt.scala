package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.persistence.abstractlayer.WriteTransactional
import uzuzjmd.competence.persistence.dao.Competence
import uzuzjmd.competence.service.rest.dto.PrerequisiteData
import scala.collection.JavaConversions._

/**
 * @author jbe
 */

object CreatePrerequisiteInOnt extends WriteTransactional[PrerequisiteData] {
  def convert(changes: PrerequisiteData) {
    execute(convertCreatePrerequisiteInOnt _, changes)
  }

  def convertCreatePrerequisiteInOnt( changes: PrerequisiteData) {
    val competence = new Competence(changes.getPostCompetence);
    changes.getPrerequisiteCompetences.foreach { x => competence.addRequiredCompetence(new Competence(x)) }
  }
}