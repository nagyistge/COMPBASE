package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, TDBWriteTransactional}
import uzuzjmd.competence.persistence.dao.LearningProjectTemplate
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl

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