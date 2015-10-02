package uzuzjmd.competence.mapper.rest

import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.LearningProjectTemplate

/**
 * @author dehne
 */
object DeleteLearningTemplateinOnt extends TDBWriteTransactional[String] {

  def convert(changes: String) {
    execute(convertHelper _, changes)
  }

  def convertHelper(comp: CompOntologyManager, changes: String) {
    val learningProjectTemplate = new LearningProjectTemplate(comp, changes, null, null)
    learningProjectTemplate.delete
  }
}