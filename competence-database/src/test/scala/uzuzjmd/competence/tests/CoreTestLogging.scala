package uzuzjmd.competence.tests

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.mapper.gui.write.LearningTemplateToOnt
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.TeacherRole
import uzuzjmd.competence.owl.dao.User
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
    val coursecontext = new CourseContext(comp, "2")
    val user = new User(comp, "me", teacherRole, coursecontext, "Julian Dehne")
    user.persist()
    logger.info("User is created and persists. " + user.toString());
  }
}