package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.monopersistence.daos.LearningProjectTemplate
import uzuzjmd.competence.persistence.abstractlayer.WriteTransactional

/**
 * @author dehne
 */
object DeleteLearningTemplateinOnt extends WriteTransactional[String] {

  def convert(changes: String) {
    execute(convertHelper _, changes)
  }

  def convertHelper(changes: String) {
    val learningProjectTemplate = new LearningProjectTemplate(changes)
    learningProjectTemplate.delete
  }
}