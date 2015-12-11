package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.mapper.gui.Ont2ProgressMap
import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, TDBReadTransactional}
import uzuzjmd.competence.service.rest.model.dto.CourseData
import uzuzjmd.competence.shared.dto.ProgressMap

/**
 * @author jbe
 */

object GetProgressMInOnt extends TDBReadTransactional[CourseData, ProgressMap] {
  def convert(changes: CourseData): ProgressMap = {
    return execute(convertGetProgressMInOnt _, changes)
  }

  def convertGetProgressMInOnt(comp: CompOntologyManager, changes: CourseData): ProgressMap = {
    val map = new Ont2ProgressMap(comp, changes.getCourse, changes.getCompetences);
    val result = map.getProgressMap();
    return result
  }
}