package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.persistence.abstractlayer.ReadTransactional
import uzuzjmd.competence.service.rest.dto.CourseData
import uzuzjmd.competence.shared.dto.ProgressMap

/**
 * @author jbe
 */

object GetProgressMInOnt extends ReadTransactional[CourseData, ProgressMap] {
  def convert(changes: CourseData): ProgressMap = {
    return execute(convertGetProgressMInOnt _, changes)
  }

  def convertGetProgressMInOnt(changes: CourseData): ProgressMap = {
    val map = new Ont2ProgressMap(changes.getCourse, changes.getCompetences);
    val result = map.getProgressMap();
    return result
  }
}