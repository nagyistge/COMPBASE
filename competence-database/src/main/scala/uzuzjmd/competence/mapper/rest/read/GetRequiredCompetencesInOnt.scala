package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, ReadTransactional}
import uzuzjmd.competence.persistence.dao.Competence

/**
 * @author jbe
 */

object GetRequiredCompetencesInOnt extends ReadTransactional[String, Array[String]] {
  def convert(changes: String): Array[String] = {
    return execute(convertGetRequiredCompetencesInOnt _, changes)
  }

  def convertGetRequiredCompetencesInOnt(comp: CompOntologyManager, changes: String): Array[String] = {
    comp.startReasoning(false);
    val competence = new Competence(comp, changes, null, null);
    return competence.getRequiredCompetencesAsArray();

  }
}