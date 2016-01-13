package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.monopersistence.daos.Competence
import uzuzjmd.competence.persistence.abstractlayer.ReadTransactional

/**
 * @author jbe
 */

object GetRequiredCompetencesInOnt extends ReadTransactional[String, Array[String]] {
  def convert(changes: String): Array[String] = {
    return execute(convertGetRequiredCompetencesInOnt _, changes)
  }

  def convertGetRequiredCompetencesInOnt( changes: String): Array[String] = {
    val competence = new Competence(changes);
    return competence.getRequiredCompetencesAsArray();
  }
}