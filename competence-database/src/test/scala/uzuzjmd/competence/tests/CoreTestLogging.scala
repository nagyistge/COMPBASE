package uzuzjmd.competence.tests

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.mapper.gui.write.LearningTemplateToOnt
import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, TDBWriteTransactional}
import uzuzjmd.competence.persistence.dao.CourseContext
import uzuzjmd.competence.persistence.dao.TeacherRole
import uzuzjmd.competence.persistence.dao.User
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl
import uzuzjmd.competence.service.rest.model.dto.LearningTemplateData
import org.apache.log4j.Logger
//import uzuzjmd.competence.logging.ObjectUtil

@RunWith(classOf[JUnitRunner])
class CoreTestLogging extends JuliansUnit with ShouldMatchers with TDBWriteTransactional[Any] {

  test("Write LearningTemplateData with logging") {
    val groupId = "111332";
    val selectedTemplateName = "Sprachkompetenz, Univ. (ELC, DE)";
    val userId = "Julian Dehne 12 12"

    val changes = new LearningTemplateData(userId, groupId, selectedTemplateName)
    logger.info("Created Object: " + changes.toString)
    LearningTemplateToOnt.convert(changes)
  }

  test("Test the Dao Objects") {
    //User
    executeNoParam(userPersistTest _)

  }

  def userPersistTest(comp: CompOntologyManager) {
    val teacherRole = new TeacherRole(comp)
    val courseContext = new CourseContext(comp, "2")
    val user = new User(comp, "me", teacherRole, courseContext, "Julian Dehne")
    user.persist()
    logger.info("User is created and persists. " + user.toString());
  }
}