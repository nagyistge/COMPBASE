package uzuzjmd.competence.mapper.gui

import uzuzjmd.competence.owl.access.TDBREADTransactional
import uzuzjmd.competence.service.rest.model.dto.LearningTemplateData
import edu.stanford.nlp.ling.StringLabel
import uzuzjmd.competence.shared.StringList
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.TeacherRole
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.SelectedLearningProjectTemplate
import uzuzjmd.competence.owl.dao.User

/**
 * @author dehne
 */
object Ont2SelectedLearningTemplate extends TDBREADTransactional[LearningTemplateData, StringList] {

  def convert(changes: LearningTemplateData): StringList = {
    return execute(convertHelper _, changes)
  }

  def convertHelper(comp: CompOntologyManager, changes: LearningTemplateData): StringList = {
    if (changes.getGroupId == null) {
      changes.setGroupId("university")
    }
    val context = new CourseContext(comp, changes.getGroupId);
    val user = new User(comp, changes.getUserName, new TeacherRole(comp), context, changes.getUserName);
    val selected = new SelectedLearningProjectTemplate(comp, null, context, null, null);
    val result = selected.getAssociatedTemplatesAsStringList();
    return result;
  }
}