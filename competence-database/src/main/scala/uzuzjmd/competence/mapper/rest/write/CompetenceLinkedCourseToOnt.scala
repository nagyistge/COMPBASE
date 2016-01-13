package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.persistence.abstractlayer.WriteTransactional
import uzuzjmd.competence.service.rest.dto.CompetenceLinkedToCourseData

/**
 * @author dehne
 */
object CompetenceLinkedCourseToOnt extends WriteTransactional[CompetenceLinkedToCourseData]  {
  def convert(changes: CompetenceLinkedToCourseData) {
    throw new NotImplementedError()
  }
}