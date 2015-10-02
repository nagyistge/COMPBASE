package uzuzjmd.competence.tests

import scala.collection.JavaConverters.seqAsJavaListConverter
import org.junit.AfterClass
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.mapper.gui.Ont2CompetenceTree
import uzuzjmd.competence.owl.access.CompFileUtil
import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.StudentRole
import uzuzjmd.competence.owl.dao.TeacherRole
import org.scalatest.junit.JUnitRunner
import org.specs2.specification.After
import org.specs2.mutable.After
import uzuzjmd.competence.owl.dao.User
import uzuzjmd.competence.owl.ontology.CompObjectProperties
import uzuzjmd.competence.owl.ontology.CompOntClass
import uzuzjmd.competence.owl.dao.Role
import uzuzjmd.competence.owl.dao.Comment
import uzuzjmd.competence.owl.dao.EvidenceActivity
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink
import uzuzjmd.competence.owl.dao.StudentRole
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.TeacherRole
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.mapper.gui.Ont2CompetenceLinkMap
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink
import uzuzjmd.competence.mapper.gui.Ont2ProgressMap
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.SelectedLearningProjectTemplate
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import uzuzjmd.competence.owl.dao.CompetenceInstance
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.access.CompOntologyAccess
import org.apache.log4j.Level
import com.hp.hpl.jena.rdf.model.InfModel
import com.hp.hpl.jena.rdf.model.ModelFactory
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.main.CompetenceImporter
import uzuzjmd.competence.main.EposImporter
import uzuzjmd.competence.owl.access.MagicStrings
import uzuzjmd.competence.owl.access.TDBWriteTransactional
import uzuzjmd.competence.owl.access.TDBREADTransactional
import uzuzjmd.competence.mapper.gui.Ont2ProgressMap
import uzuzjmd.competence.service.rest.model.dto.CourseData
import uzuzjmd.competence.mapper.rest.GetProgressMInOnt
import uzuzjmd.competence.service.rest.model.dto.LearningTemplateData
import uzuzjmd.competence.mapper.gui.LearningTemplateToOnt

@RunWith(classOf[JUnitRunner])
class CoreTests extends FunSuite with ShouldMatchers with TDBWriteTransactional[Any] {

  test("The CSV import should run without errors") {

    // change this, if you want to really reset the database
    CompFileUtil.deleteTDB()
    val compOntManag = new CompOntologyManager()
    CompetenceImporter.convertCSVArray();

    EposImporter.importEpos()
    val fileUtil = new CompFileUtil(compOntManag.getM())
    fileUtil.writeOntologyout()
  }

  test("if a user is persisted, the course context should be acessable") {
    executeNoParam(userPersistTest _)
  }

  def userPersistTest(comp: CompOntologyManager) {
    val teacherRole = new TeacherRole(comp)
    val coursecontext = new CourseContext(comp, "2")
    coursecontext.persist
    val user = new User(comp, "me", teacherRole, coursecontext, "Julian Dehne")
    user.persist()
    val user2 = new User(comp, "me")
    val fullUser = user2.getFullDao
    fullUser.getName.equals(user.getName) should not be false
    fullUser.hasCourseContext(coursecontext)
    user.hasCourseContext(coursecontext)
    user.delete
    coursecontext.delete
  }

  test("The CompetenceTree should not be empty") {
    val compOntManag = new CompOntologyManager()
    val mapper = new Ont2CompetenceTree(List.empty.asJava, List.empty.asJava, "university", false, null)
    val competenceTree = mapper.getComptenceTreeForCourse()
    competenceTree should not be ('empty)

  }

  test("The filtered CompetenceTree should not be empty") {
    val compOntManag = new CompOntologyManager()

    val catchwords = "Kooperation" :: "Diagnostik" :: List.empty
    val operators = "bewerten" :: "kooperieren" :: List.empty
    val mapper = new Ont2CompetenceTree(catchwords.asJava, operators.asJava, "university", false, null)
    val competenceTree = mapper.getComptenceTreeForCourse()
    competenceTree should not be ('empty)

  }

  test("the operator tree should not be empty") {
    val compOntManag = new CompOntologyManager()
    val mapper = new Ont2CompetenceTree(List.empty.asJava, List.empty.asJava, "university", false, null)
    val result = mapper.getOperatorXMLTree
    result should not be ('empty)
  }

  test("the catchword tree should not be empty") {
    val compOntManag = new CompOntologyManager()
    val mapper = new Ont2CompetenceTree(List.empty.asJava, List.empty.asJava, "university", false, null)
    val result = mapper.getCatchwordXMLTree
    result should not be ('empty)
  }

  test("the singletondao should persist without error") {
    executeNoParam(singletondaoTest)
    showResult
  }

  def singletondaoTest(comp: CompOntologyManager) {
    val studentRole = new StudentRole(comp)
    studentRole.persist(true)
    studentRole.setRole
    studentRole.persist(false).getIndividual() should not be null
    studentRole.persist(false).getOntclass() should not be null
    val teacherRole = new TeacherRole(comp)
    teacherRole.persist(true)
    teacherRole.setRole
    teacherRole.persist(false).getIndividual() should not be null
    teacherRole.persist(false).getOntclass() should not be null

  }

  test("the regular dao should persist without error") {
    val compOntManag = new CompOntologyManager()
    executeNoParam(regularDaoTest _)
    showResult
  }

  def regularDaoTest(comp: CompOntologyManager) {
    val teacherRole = new TeacherRole(comp)
    val coursecontext = new CourseContext(comp, "2")
    val user = new User(comp, "me", teacherRole, coursecontext, "me")
    user.persist
    user.exists should not be false
    user.delete
    user.exists should not be true

  }

  test("if a dao is linked the link should exist") {
    executeNoParam(linkTest _)
    showResult
  }

  def linkTest(compOntManag: CompOntologyManager) {
    val teacherRole = new TeacherRole(compOntManag)
    val coursecontext = new CourseContext(compOntManag, "2")
    val user = new User(compOntManag, "me", teacherRole, coursecontext, "me")
    user.persist()
    user.exists should not be false
    teacherRole.hasEdge(CompObjectProperties.RoleOf, user) should not be false
    user.delete
    user.exists should not be true
    teacherRole.hasEdge(CompObjectProperties.RoleOf, user) should not be true
    user.exists should not be true
    user.persist
    teacherRole.hasEdge(CompObjectProperties.RoleOf, user) should not be false
    teacherRole.deleteEdge(CompObjectProperties.RoleOf, user)
    user.exists should not be false
    teacherRole.hasEdge(CompObjectProperties.RoleOf, user) should not be true
  }

  test("if a comment is persisted it should have its datafields in place") {
    executeNoParam(doCommentTest _)
  }

  def doCommentTest(comp: CompOntologyManager) {
    val testkommentar = "mein testkommentar"
    val teacherRole = new TeacherRole(comp)
    val coursecontext = new CourseContext(comp, "2")
    val user = new User(comp, "me", teacherRole, coursecontext, "me")
    val comment = new Comment(comp, testkommentar, user, System.currentTimeMillis())
    comment.persist
    comment.exists should not be false
    val testkommentar2 = comment.getDataField(comment.TEXT)
    comment.getDataField(comment.TEXT).equals(testkommentar) should not be false
    (comment.getDataField(comment.DATECRATED) != null) should not be false
    comment.deleteDataField(comment.TEXT)
    (comment.getDataField(comment.TEXT) != null) should not be true
    comment.hasDataField(comment.TEXT) should not be true

  }

  test("if a evidence link is created this should not cause errors") {
    executeNoParam(doEvidenceLinkTest _)
    executeNoParam(doDeleteEvidenceLink _)
    showResult
  }

  def doEvidenceLinkTest(compOntManag: CompOntologyManager) {
    val studentRole = new StudentRole(compOntManag)
    val coursecontext = new CourseContext(compOntManag, "2")
    val userstudent = new User(compOntManag, "student meäää 10AA", studentRole, coursecontext, "student meäää 10AA")
    val testkommentar = "mein testkommentar"
    val comment = new Comment(compOntManag, testkommentar, userstudent, System.currentTimeMillis())
    val testkommentar2 = "mein testkommentar2"
    val comment2 = new Comment(compOntManag, testkommentar2, userstudent, System.currentTimeMillis())
    val teacherRole = new TeacherRole(compOntManag)
    val user = new User(compOntManag, "me", teacherRole, coursecontext)

    val competence = new Competence(compOntManag, "Die Lehramtsanwärter kooperieren mit Kolleginnen und Kollegen bei der  Erarbeitung von Beratung/Empfehlung")
    competence.persist(true)
    val evidenceActivity = new EvidenceActivity(compOntManag, "http://testest", "meine testaktivitat")
    val link = new AbstractEvidenceLink(compOntManag, null, user, userstudent, coursecontext, evidenceActivity, System.currentTimeMillis(), false, competence, (comment :: comment2 :: Nil))
    link.persist
    link.exists should not be false

  }

  def doDeleteEvidenceLink(compOntManag: CompOntologyManager) {
    val studentRole = new StudentRole(compOntManag)
    val coursecontext = new CourseContext(compOntManag, "2")
    val userstudent = new User(compOntManag, "student meäää 10AA", studentRole, coursecontext, "student meäää 10AA")
    val teacherRole = new TeacherRole(compOntManag)
    val user = new User(compOntManag, "me", teacherRole, coursecontext)
    val competence = new Competence(compOntManag, "Die Lehramtsanwärter kooperieren mit Kolleginnen und Kollegen bei der  Erarbeitung von Beratung/Empfehlung")
    val evidenceActivity = new EvidenceActivity(compOntManag, "http://testest", "meine testaktivitat")
    val link = new AbstractEvidenceLink(compOntManag, null, user, userstudent, coursecontext, evidenceActivity, System.currentTimeMillis(), false, competence, null)
    link.exists should not be false
    link.delete
    evidenceActivity.delete
    evidenceActivity.exists should not be true
  }

  test("if a string is given the identified full dao should be returnable") {
    executeNoParam(doidentifiedLinkTest _)
    showResult
  }

  def doidentifiedLinkTest(compOntManag: CompOntologyManager) {
    val studentRole = new StudentRole(compOntManag)
    val coursecontext = new CourseContext(compOntManag, "2")
    val userstudent = new User(compOntManag, "student meäää 10AA", studentRole, coursecontext, "student meäää 10AA")
    val link = createAbstract(compOntManag, userstudent)

    // now getting it by example
    val exampleLink = new AbstractEvidenceLink(compOntManag, link.getId)
    val fullExampleLink = exampleLink.getFullDao

    fullExampleLink.creator should not be null
    fullExampleLink.getAllActivities should not be ('empty)
    fullExampleLink.getAllCourseContexts should not be ('empty)
    fullExampleLink.getAllLinkedUsers should not be ('empty)
    val linkedUser = fullExampleLink.getAllLinkedUsers.head
    //    linkedUser.equals(userstudent) should not be false
    link.delete

  }

  test("the competencelinksmap should not be empty") {
    executeNoParam(docompetencelinksmapTest _)
    val tmp0 = Ont2CompetenceLinkMap.getCompetenceLinkMap("student meäää 10AA")
    val tmp1 = tmp0.getMapUserCompetenceLinks()
    tmp1.entrySet() should not be ('empty)
    showResult
  }

  def docompetencelinksmapTest(compOntManag: CompOntologyManager) {
    val linkId = "hellolinkId"
    val studentRole = new StudentRole(compOntManag)
    val coursecontext = new CourseContext(compOntManag, "2")
    val userstudent = new User(compOntManag, "student meäää 10AA", studentRole, coursecontext, "student meäää 10AA")
    val link = createAbstract(compOntManag, userstudent)
    link.delete
  }

  test("progresbarmap should not be empty") {
    executeNoParam(doEvidenceLinkTest _)
    val changes = new CourseData("2", ("Die Lehramtsanwärter kooperieren mit Kolleginnen und Kollegen bei der  Erarbeitung von Beratung/Empfehlung" :: Nil).asJava)
    val progressMap = GetProgressMInOnt.convert(changes);
    progressMap should not be null
    progressMap.entrySet() should not be ('empty)
    executeNoParam(doDeleteEvidenceLink _)
    showResult
  }

  test("if a requires b and b requires c, a should require c") {
    executeNoParamWithReasoning(doRequiresTransitivityTest)
    showResult
  }

  def doRequiresTransitivityTest(compOntManag: CompOntologyManager) {
    val competenceA = new Competence(compOntManag, "Die Lehramtsanwärter kooperieren mit Kolleginnen und Kollegen bei der  Erarbeitung von Beratung/Empfehlung")
    val competenceB = new Competence(compOntManag, "Die Lehramtsanwärter erkennen Entwicklungsstände, Lernpotentiale, Lernhindernisseund Lernfortschritte")
    val competenceC = new Competence(compOntManag, "Die Lehramtsanwärter beschreiben den Lernstand der SuS und ihren eigenen Wissensstand.")
    competenceC.addRequiredCompetence(competenceB)
    competenceB.addRequiredCompetence(competenceA)
    competenceC.hasEdge(competenceA, CompObjectProperties.PrerequisiteOf) should not be false
  }

  test("testing the rules") {
    executeNoParamWithReasoning(doTestTheRules _)
    showResult
  }

  def doTestTheRules(compOntManag: CompOntologyManager) {
    val courseContext = new CourseContext(compOntManag, "n2");
    val competenceA = new Competence(compOntManag, "Die Lehramtsanwärter kooperieren mit Kolleginnen und Kollegen bei der  Erarbeitung von Beratung/Empfehlung")
    courseContext.createEdgeWith(CompObjectProperties.CourseContextOf, competenceA)
    val competenceB = new Competence(compOntManag, "Die Lehramtsanwärter erkennen Entwicklungsstände, Lernpotentiale, Lernhindernisseund Lernfortschritte")
    courseContext.createEdgeWith(CompObjectProperties.CourseContextOf, competenceB)
    val competenceC = new Competence(compOntManag, "Die Lehramtsanwärter beschreiben den Lernstand der SuS und ihren eigenen Wissensstand.")
    courseContext.createEdgeWith(CompObjectProperties.CourseContextOf, competenceB)
    competenceC.addRequiredCompetence(competenceB)
    competenceB.addRequiredCompetence(competenceA)
    // testing basic transition
    competenceC.hasEdge(competenceA, CompObjectProperties.PrerequisiteOf) should not be false

    // testing not transition 1
    competenceB.addNotRequiredCompetence(competenceA)
    competenceC.hasEdge(competenceA, CompObjectProperties.PrerequisiteOf) should not be true

    // testing basic transition
    competenceB.addRequiredCompetence(competenceA)
    competenceC.hasEdge(competenceA, CompObjectProperties.PrerequisiteOf) should not be false

    // testing not transition 2
    competenceC.addNotRequiredCompetence(competenceB)
    competenceB.hasEdge(competenceA, CompObjectProperties.PrerequisiteOf) should not be true

    // testing basic transition
    competenceC.addRequiredCompetence(competenceB)
    competenceB.addRequiredCompetence(competenceA)
    competenceC.hasEdge(competenceA, CompObjectProperties.PrerequisiteOf) should not be false

    // testing the create link transition
    val linkId = "hellolinkId"
    val studentRole = new StudentRole(compOntManag)
    val coursecontext = new CourseContext(compOntManag, "2")
    val userstudent = new User(compOntManag, "student meäää 10AA", studentRole, coursecontext, "student meäää 10AA")
    val link = createAbstract(compOntManag, userstudent)
  }

  test("if a learning template is selected it should be persisted without errors") {

    val groupId = "111332";
    val selectedTemplateName = "Sprachkompetenz, Univ. (ELC, DE)";
    val userId = "Julian Dehne 12 12"

    val changes = new LearningTemplateData(userId, groupId, selectedTemplateName)
    LearningTemplateToOnt.convert(changes)
    showResult
  }

  test("listing all the subdaos should not throw any error and provide an existing list") {
    executeNoParam(doListingAllSubdaos _)
  }

  def doListingAllSubdaos(compOntManag: CompOntologyManager) {
    val competenceRoot = new CompetenceInstance(compOntManag)
    val result2 = competenceRoot.listSubClasses(classOf[Competence])
    competenceRoot.listSubClasses(classOf[Competence]) should not be ('empty)

    val competenceLevel2 = new Competence(compOntManag, "SchreibenB2").getFullDao
    val result = competenceLevel2.listSubClasses(classOf[Competence])
    result should not be ('empty)
    (result.head.getDefinition != null) should not be false

  }

  test("listing all the the shortest path should not throw any error and provide an existing list") {
    executeNoParam(doListingAllSubdaos _)
  }

  def doListingShotrestPath(compOntManag: CompOntologyManager) {
    CompOntologyAccess.logger.setLevel(Level.DEBUG)

    val competenceRoot = new CompetenceInstance(compOntManag)
    val topClass = competenceRoot.toOntClass

    val definition2 = "Ich kann die Hauptinformation von Fernsehmeldungen über Ereignisse, Unglücksfälle usw. erfassen, wenn der Kommentar durch Bilder unterstützt wird."
    //val bottomCompetence = new Competence(compOntManag, definition2)
    val bottomClass = compOntManag.getUtil().createOntClassForString(definition2, false, definition2)

    val result = compOntManag.getUtil().getShortestSubClassPath(bottomClass, topClass)
    result should not be ('empty)

  }

  test("getOperations should not leave status in database") {
    CompOntologyAccess.logger.setLevel(Level.DEBUG)
    executeNoParam(doNoSideEffectTest _)
  }

  def doNoSideEffectTest(compOntManag: CompOntologyManager) {
    val competenceRoot = new CompetenceInstance(compOntManag)
    val topClass = competenceRoot.toOntClass

    val definition2 = "some random competence"
    //val bottomCompetence = new Competence(compOntManag, definition2)
    val bottomClass = new Competence(compOntManag, definition2, definition2, null)

    bottomClass.exists should not be true
    // test whether exist relation modifies database
    bottomClass.exists should not be true

    bottomClass.toOntClass
    // test whether toOntClass modifies database
    bottomClass.exists should not be true

    bottomClass.isSublass(competenceRoot.listSubClasses(classOf[Competence]).head) should not be true

    // test whether isSublass modifies database
    bottomClass.exists should not be true

    bottomClass.isSuperClass(competenceRoot.listSubClasses(classOf[Competence]).head) should not be true

    // test whether isSuperClass modifies database
    bottomClass.exists should not be true
  }

  //  test("test consistency of subclass relations") {
  //    val compOntManag = new CompOntologyManager()
  //    compOntManag.begin()
  //    compOntManag.getM().getIndividual("http://comp#I3org227owlNothing").remove()
  //    val compOntManagInMemory = new CompOntologyManager(compOntManag.getM())
  //    compOntManag.close();
  //
  //    CompOntologyAccess.logger.setLevel(Level.DEBUG)
  //
  //    // top class
  //    val competenceRoot = new CompetenceInstance(compOntManagInMemory)
  //    val topClass = competenceRoot.toOntClass
  //
  //    // bottom class
  //    val definition2 = "Ich kann die Hauptinformation von Fernsehmeldungen über Ereignisse, Unglücksfälle usw. erfassen, wenn der Kommentar durch Bilder unterstützt wird."
  //    val bottomClass = compOntManagInMemory.getUtil().createOntClassForString(definition2, definition2)
  //
  //    // test inconsistency
  //    compOntManagInMemory.startReasoning()
  //    topClass.addSuperClass(bottomClass)
  //    bottomClass.addSuperClass(topClass)
  //
  //    // inmemory copy should not be valid
  //    val validationreport = compOntManagInMemory.getM.validate()
  //    //    println(compOntManagInMemory.getUtil().validityReportTostring(validationreport));
  //    validationreport.isValid() should not be true
  //
  //    // real copy should still be valid
  //    val compOntManag2 = new CompOntologyManager()
  //    compOntManag2.begin()
  //    val validationreport2 = compOntManag2.getM.validate()
  //    println(compOntManag2.getUtil().validityReportTostring(validationreport2));
  //    validationreport2.isValid() should not be false
  //    compOntManag2.close()
  //    showResult
  //  }

  private def showResult() {
    executeNoParam(showResultHelper _)
  }

  private def showResultHelper(comp: CompOntologyManager) {
    val fileUtil = new CompFileUtil(comp.getM())
    fileUtil.writeOntologyout()
  }

  private def createAbstract(compOntManag: CompOntologyManager, userstudent: User): AbstractEvidenceLink = {
    val coursecontext = new CourseContext(compOntManag, "2")
    val teacherRole = new TeacherRole(compOntManag)
    val user = new User(compOntManag, "me", teacherRole, coursecontext, "me")
    user.persist
    val user2 = new User(compOntManag, "me")
    val fullUser = user2.getFullDao
    fullUser.role.equals(teacherRole) should not be false
    // and now a more complicated example
    val testkommentar = "mein testkommentar"
    val comment = new Comment(compOntManag, testkommentar, userstudent, System.currentTimeMillis())
    val testkommentar2 = "mein testkommentar2"
    val comment2 = new Comment(compOntManag, testkommentar2, userstudent, System.currentTimeMillis())

    val evidenceActivity = new EvidenceActivity(compOntManag, "http://testest", "meine testaktivitat")
    val competence = new Competence(compOntManag, "Die Lehramtsanwärter kooperieren mit Kolleginnen und Kollegen bei der  Erarbeitung von Beratung/Empfehlung")
    competence.persist(true)
    competence.createEdgeWith(coursecontext, CompObjectProperties.CourseContextOf)
    val link = new AbstractEvidenceLink(compOntManag, null, user, userstudent, coursecontext, evidenceActivity, System.currentTimeMillis(), false, competence, (comment :: comment2 :: Nil))
    link.persist
    val competencex = competence.getDefinition()

    competencex should not be null

    comment.hasEdge(userstudent, CompObjectProperties.UserOfComment) should not be false
    comment2.hasEdge(userstudent, CompObjectProperties.UserOfComment) should not be false
    return link
  }

  test("A non-empty list should not be empty") {
    List(1, 2, 3) should not be ('empty)
    List("fee", "fie", "foe", "fum") should not be ('empty)

    val assessment = "schlecht"
    val enumMap = List("gar nicht" -> 0, "schlecht" -> 1, "mittel" -> 2, "gut" -> 3)
    val map = enumMap.toMap.get(assessment).get
    println(map);

  }
  //
  //  test("A list's length should equal the number of elements it contains") {
  //    List() should have length (0)
  //    List(1, 2) should have length (2)
  //    List("fee", "fie", "foe", "fum") should have length (4)
  //  }
}

