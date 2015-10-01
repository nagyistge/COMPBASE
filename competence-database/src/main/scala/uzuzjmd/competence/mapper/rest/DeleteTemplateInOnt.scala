package uzuzjmd.competence.mapper.rest

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.service.rest.model.dto.LearningTemplateData
import uzuzjmd.competence.owl.dao.TeacherRole
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.SelectedLearningProjectTemplate
import uzuzjmd.competence.owl.dao.User

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