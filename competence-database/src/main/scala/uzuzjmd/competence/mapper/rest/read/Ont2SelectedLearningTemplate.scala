package uzuzjmd.competence.mapper.rest.read

import uzuzjmd.competence.persistence.abstractlayer.ReadTransactional
import uzuzjmd.competence.persistence.dao.{CourseContext, Role, User}
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
    val learningTemplate = user.getAssociatedLearningProjectTemplateIds()
    return new StringList(learningTemplate)
  }
}
