package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.monopersistence.daos._
import uzuzjmd.competence.persistence.abstractlayer.ReadTransactional
import uzuzjmd.competence.service.rest.dto.LearningTemplateData
import uzuzjmd.competence.shared.StringList

/**
 * @author dehne
 */
object Ont2SelectedLearningTemplate extends ReadTransactional[LearningTemplateData, StringList] {

  def convert(changes: LearningTemplateData): StringList = {
    return execute(convertHelper _, changes)
  }

  def convertHelper(changes: LearningTemplateData): StringList = {
    if (changes.getGroupId == null) {
      changes.setGroupId("university")
    }
    val context = new CourseContext(changes.getGroupId);
    val user = new User(changes.getUserName, Role.teacher, context);
    return SelectedLearningProjectTemplate.getAllSelectedLearningProjectTemplates(user, context);
  }
}
