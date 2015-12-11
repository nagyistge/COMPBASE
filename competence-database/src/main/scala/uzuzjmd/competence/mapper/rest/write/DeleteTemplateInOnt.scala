package uzuzjmd.competence.mapper.rest.write

import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, TDBWriteTransactional}
import uzuzjmd.competence.persistence.dao.CourseContext
import uzuzjmd.competence.persistence.dao.LearningProjectTemplate
import uzuzjmd.competence.persistence.dao.SelectedLearningProjectTemplate
import uzuzjmd.competence.persistence.dao.TeacherRole
import uzuzjmd.competence.persistence.dao.User
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl
import uzuzjmd.competence.service.rest.dto.LearningTemplateData

/**
 * @author dehne
 */
object DeleteTemplateInOnt extends TDBWriteTransactional[LearningTemplateData] {

  def convert(changes: LearningTemplateData) {
    execute(convertHelper _, changes)
  }

  def convertHelper(comp: CompOntologyManager, changes: LearningTemplateData): Unit = {
    val context = new CourseContext(comp, changes.getGroupId);
    val user = new User(comp, changes.getUserName, new TeacherRole(comp), context, changes.getUserName);
    val selected = new SelectedLearningProjectTemplate(comp, user, context, null, null);
    val learningTemplate = new LearningProjectTemplate(comp, changes.getSelectedTemplate, null, changes.getSelectedTemplate);
    selected.removeAssociatedTemplate(learningTemplate);

  }
}