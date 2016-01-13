package uzuzjmd.competence.mapper.rest.write

import sun.reflect.generics.reflectiveObjects.NotImplementedException
import uzuzjmd.competence.exceptions.{ContextNotExistsException, UserNotExistsException}
import uzuzjmd.competence.monopersistence.daos._
import uzuzjmd.competence.persistence.abstractlayer.WriteTransactional
import uzuzjmd.competence.persistence.ontology.CompObjectProperties
import uzuzjmd.competence.persistence.performance.PerformanceTimer
import uzuzjmd.competence.service.rest.dto.LearningTemplateData
import uzuzjmd.competence.shared.dto.LearningTemplateResultSet

/**
 * @author dehne
 *
 * This object saves a learning template in the database
 */
object LearningTemplateToOnt extends WriteTransactional[LearningTemplateData] with PerformanceTimer[LearningTemplateData, Unit] {

  def convert(changes: LearningTemplateData) {
    execute(convertHelper1 _, changes)
    execute(convertHelper _, changes)
  }

  private def convertHelper1(changes: LearningTemplateData) {
    val context = new CourseContext(changes.getGroupId);
    context.persist()
    val user = new User(changes.getUserName, Role.teacher, context);
    user.persist()
    context.createEdgeWith(CompObjectProperties.CourseContextOf, user)
  }

  private def convertHelper(changes: LearningTemplateData) {
    val context = new CourseContext(changes.getGroupId);
    val user = new User(changes.getUserName, Role.teacher, context);
    if (!user.exists()) {
      throw new UserNotExistsException
    }
    if (!context.exists()) {
      throw new ContextNotExistsException
    }
    val selected = new SelectedLearningProjectTemplate(user, context, changes.getSelectedTemplate);
    selected.persist();
  }

  def convertLearningTemplateResultSet(changes : LearningTemplateResultSet): Unit = {
     throw new NotImplementedException();
  }
}