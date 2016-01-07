package uzuzjmd.competence.tests

import java.util

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.logging.LoggingWriteTransactional
import uzuzjmd.competence.main.OntologyWriter
import uzuzjmd.competence.mapper.rest.read.{GetProgressMInOnt, Ont2CompetenceLinkMap, Ont2CompetenceTree}
import uzuzjmd.competence.mapper.rest.write.LearningTemplateToOnt
import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManagerFactory, CompOntologyManager}
import uzuzjmd.competence.persistence.dao.{AbstractEvidenceLink, Comment, Competence, CompetenceInstance, CourseContext, EvidenceActivity, StudentRole, TeacherRole, User}
import uzuzjmd.competence.persistence.ontology.CompObjectProperties
import uzuzjmd.competence.service.rest.dto.{CourseData, LearningTemplateData}

import scala.collection.JavaConverters.seqAsJavaListConverter


@RunWith(classOf[JUnitRunner])
class CoreTests extends JuliansUnit with ShouldMatchers with LoggingWriteTransactional[Any] {

  /**
   * TEST CASE 1
   */
  test("SETUP AND The CSV import should run without errors") {
    TestCommons.setup()
  }

  /**
   * TEST CASE 2
   */
  test("if a user is persisted, the course context should be acessable") {
    executeNoParam(userPersistTest _)
    executeNoParam(userPersistTest2 _)
  }

  /**
   * TEST CASE 3
   */
  def userPersistTest(comp: CompOntologyManager) {
    val teacherRole = new TeacherRole(comp)
    val courseContext = new CourseContext(comp, "2")
    courseContext.persist
    val user = new User(comp, "me", teacherRole, courseContext, "Julian Dehne")
    user.persist()
  }

  /**
   * TEST CASE 4
   */
  def userPersistTest2(comp: CompOntologyManager) {
    val courseContext = new CourseContext(comp, "2")
    val user2 = new User(comp, "me")
    val fullUser = user2.getFullDao
    user2.hasCourseContext(courseContext) should not be false
    fullUser.getName.equals("Julian Dehne") should not be false
    fullUser.hasCourseContext(courseContext)
    val user = new User(comp, "me", null, courseContext, "Julian Dehne")
    user.delete
    courseContext.delete
  }

  /**
   * TEST CASE 5
   */
  test("The CompetenceTree should not be empty") {
    val mapper = new Ont2CompetenceTree(new util.ArrayList[String](), new util.ArrayList[String](), "university", false, null)
    val competenceTree = mapper.getComptenceTreeForCourse()
    competenceTree should not be ('empty)
  }

  /**
   * TEST CASE 6
   */
  test("The filtered CompetenceTree should not be empty") {
    val catchwords = "Kooperation" :: "Diagnostik" :: List.empty
    val operators = "bewerten" :: "kooperieren" :: List.empty
    val mapper = new Ont2CompetenceTree(catchwords.asJava, operators.asJava, "university", false, null)
    val competenceTree = mapper.getComptenceTreeForCourse()
    competenceTree should not be ('empty)
  }

  /**
   * TEST CASE 7
   */
  test("the operator tree should not be empty") {
    val mapper = new Ont2CompetenceTree(new util.ArrayList[String](), new util.ArrayList[String](), "university", false, null)
    val result = mapper.getOperatorXMLTree
    result should not be ('empty)
  }

  /**
   * TEST CASE 8
   */
  test("the catchword tree should not be empty") {
    val mapper = new Ont2CompetenceTree(new util.ArrayList[String](), new util.ArrayList[String](), "university", false, null)
    val result = mapper.getCatchwordXMLTree
    result should not be ('empty)
  }

  /**
   * TEST CASE 9
   */
  test("the singletondao should persist without error") {
    executeNoParam(singletonTest)

  }

  def singletonTest(comp: CompOntologyManager) {
    val studentRole = new StudentRole(comp)
    studentRole.persistManualCascades(true)
    studentRole.setRole
    studentRole.persistManualCascades(false).getIndividual() should not be null
    studentRole.persistManualCascades(false).getOntclass() should not be null
    val teacherRole = new TeacherRole(comp)
    teacherRole.persistManualCascades(true)
    teacherRole.setRole
    teacherRole.persistManualCascades(false).getIndividual() should not be null
    teacherRole.persistManualCascades(false).getOntclass() should not be null

  }

  /**
   * TEST CASE 10
   */
  test("the regular dao should persist without error") {
    executeNoParam(regularDaoTest _)
  }

  def regularDaoTest(comp: CompOntologyManager) {
    val teacherRole = new TeacherRole(comp)
    val courseContext = new CourseContext(comp, "2")
    courseContext.persist()
    val user = new User(comp, "me", teacherRole, courseContext, "me")
    user.persist
    user.exists should not be false
    user.delete
    user.exists should not be true
    courseContext.delete
  }

  /**
   * TEST CASE 11
   */
  test("if a dao is linked the link should exist") {
    executeNoParam(createLinkTestContexts _)
  }

  def createLinkTestContexts(compOntologyManager: CompOntologyManager) {
    val teacherRole = new TeacherRole(compOntologyManager)
    val courseContext = new CourseContext(compOntologyManager, "2")
    val user = new User(compOntologyManager, "me", teacherRole, courseContext, "me")
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

  /**
   * TEST CASE 12
   */
  test("if a comment is persisted it should have its dataFields in place") {
    commentTest
  }

  def commentTest: Unit = {
    executeNoParam(doCommentTest _)
    executeNoParam(doCommentTestValidation _)
    executeNoParam(doCommentTestCleanUp _)
  }

  def doCommentTest(comp: CompOntologyManager) {
    val testComment = "mein testComment"
    val teacherRole = new TeacherRole(comp)
    val courseContext = new CourseContext(comp, "2")
    val user = new User(comp, "me", teacherRole, courseContext, "me")
    val comment = new Comment(comp, testComment, user, System.currentTimeMillis())
    comment.persist

  }

  def doCommentTestValidation(comp: CompOntologyManager) {
    val testComment = "mein testComment"
    val teacherRole = new TeacherRole(comp)
    val courseContext = new CourseContext(comp, "2")
    val user = new User(comp, "me", teacherRole, courseContext, "me")
    val comment = new Comment(comp, testComment, user, System.currentTimeMillis())
    comment.exists should not be false
    comment.getDataField(comment.TEXT).equals(testComment) should not be false
    (comment.getDataField(comment.DATECRATED) != null) should not be false
    comment.deleteDataField(comment.TEXT)
    comment.hasDataField(comment.TEXT) should not be true
  }

  def doCommentTestCleanUp(comp: CompOntologyManager): Unit = {
    val testComment = "mein testComment"
    val teacherRole = new TeacherRole(comp)
    val courseContext = new CourseContext(comp, "2")
    val user = new User(comp, "me", teacherRole, courseContext, "me")
    val comment = new Comment(comp, testComment, user, System.currentTimeMillis())
    comment.delete
  }
  /**
   * TEST CASE 14
   */
  test("if a evidence link is created this should not cause errors") {
    executeNoParam(doEvidenceLinkTest _)
    executeNoParam(doDeleteEvidenceLink _)
  }

  def doEvidenceLinkTest(compOntManag: CompOntologyManager) {
    val studentRole = new StudentRole(compOntManag)
    val courseContext = new CourseContext(compOntManag, "2")
    val userStudent = new User(compOntManag, "student meäää 10AA", studentRole, courseContext, "student meäää 10AA")
    val testComment = "mein testComment"
    val comment = new Comment(compOntManag, testComment, userStudent, System.currentTimeMillis())
    val testComment2 = "mein testkommentar2"
    val comment2 = new Comment(compOntManag, testComment2, userStudent, System.currentTimeMillis())
    val teacherRole = new TeacherRole(compOntManag)
    val user = new User(compOntManag, "me", teacherRole, courseContext)
    val competence = new Competence(compOntManag, "Die Lehramtsanwärter kooperieren mit Kolleginnen und Kollegen bei der  Erarbeitung von Beratung/Empfehlung")
    competence.persistManualCascades(true)
    val evidenceActivity = new EvidenceActivity(compOntManag, "http://testest", "meine testaktivitat")
    val link = new AbstractEvidenceLink(compOntManag, null, user, userStudent, courseContext, evidenceActivity, System.currentTimeMillis(), false, competence, (comment :: comment2 :: Nil))
    link.persist
    link.exists should not be false
  }

  def doDeleteEvidenceLink(compOntologyManager: CompOntologyManager) {
    val studentRole = new StudentRole(compOntologyManager)
    val courseContext = new CourseContext(compOntologyManager, "2")
    val userStudent = new User(compOntologyManager, "student meäää 10AA", studentRole, courseContext, "student meäää 10AA")
    val teacherRole = new TeacherRole(compOntologyManager)
    val user = new User(compOntologyManager, "me", teacherRole, courseContext)
    val competence = new Competence(compOntologyManager, "Die Lehramtsanwärter kooperieren mit Kolleginnen und Kollegen bei der  Erarbeitung von Beratung/Empfehlung")
    val evidenceActivity = new EvidenceActivity(compOntologyManager, "http://testest", "meine testaktivitat")
    val link = new AbstractEvidenceLink(compOntologyManager, null, user, userStudent, courseContext, evidenceActivity, System.currentTimeMillis(), false, competence, null)
    link.exists should not be false
    link.delete
    evidenceActivity.delete
    evidenceActivity.exists should not be true
    user.delete
    competence.delete
    userStudent.delete
    courseContext.delete
  }

  /**
   * TEST CASE 15
   */
  test("if a string is given the identified full dao should be returnable") {
    executeNoParamWithReturn(createAbstractLink _)
    executeNoParam(deleteAbstractEvidenceLink _)
  }

  /**
   *
   */
  def deleteAbstractEvidenceLink(comp: CompOntologyManager) {
    val exampleLink = new AbstractEvidenceLink(comp, "DieLehramtsanwärterkooperierenmitKolleginnenundKollegenbeiderErarbeitungvonBeratungEmpfehlunghttptestest")
    val fullExampleLink = exampleLink.getFullDao
    fullExampleLink.getAllActivities should not be ('empty)
    fullExampleLink.getAllCourseContexts should not be ('empty)
    fullExampleLink.getAllLinkedUsers should not be ('empty)
    val link = createAbstractLink(comp)
    link.delete
  }

  /**
   * TEST CASE 16
   */
  test("the competencelinksmap should not be empty") {
    testCompetenceLinksMapsCreation
  }

  /**
   * TEST CASE 17
   */
  def testCompetenceLinksMapsCreation: Unit = {
    executeNoParamWithReturn(createAbstractLink _)
    val tmp0 = Ont2CompetenceLinkMap.convert("student meäää 10AA")
    val tmp1 = tmp0.getMapUserCompetenceLinks()
    tmp1.entrySet() should not be ('empty)
  }

  test("progresbarmap should not be empty") {
    testProgressMapCreation
  }

  /**
   * TEST CASE 18
   */
  def testProgressMapCreation: Unit = {
    executeNoParamWithReturn(createAbstractLink _)
    executeNoParam(doEvidenceLinkTest _)
    val changes = new CourseData("2", ("Die Lehramtsanwärter kooperieren mit Kolleginnen und Kollegen bei der  Erarbeitung von Beratung/Empfehlung" :: Nil).asJava)
    val progressMap = GetProgressMInOnt.convert(changes);
    progressMap should not be null
    progressMap.entrySet() should not be ('empty)
    executeNoParam(doDeleteEvidenceLink _)
  }

  test("if a requires b and b requires c, a should require c") {
    executeNoParamWithReasoning(doRequiresTransitivityTest)
    executeNoParam(doRequiresTransitivityTest2)

  }
  def doRequiresTransitivityTest(compOntologyManager: CompOntologyManager) {
    val competenceA = new Competence(compOntologyManager, "Die Lehramtsanwärter kooperieren mit Kolleginnen und Kollegen bei der  Erarbeitung von Beratung/Empfehlung")
    val competenceB = new Competence(compOntologyManager, "Die Lehramtsanwärter erkennen Entwicklungsstände, Lernpotentiale, Lernhindernisseund Lernfortschritte")
    val competenceC = new Competence(compOntologyManager, "Die Lehramtsanwärter beschreiben den Lernstand der SuS und ihren eigenen Wissensstand.")
    competenceC.addRequiredCompetence(competenceB)
    competenceB.addRequiredCompetence(competenceA)

  }

  def doRequiresTransitivityTest2(compOntologyManager: CompOntologyManager) {
    val competenceA = new Competence(compOntologyManager, "Die Lehramtsanwärter kooperieren mit Kolleginnen und Kollegen bei der  Erarbeitung von Beratung/Empfehlung")
    val competenceC = new Competence(compOntologyManager, "Die Lehramtsanwärter beschreiben den Lernstand der SuS und ihren eigenen Wissensstand.")
    competenceC.hasEdge(competenceA, CompObjectProperties.PrerequisiteOf) should not be false
  }

  /**
   * TEST CASE 19
   */
  test("testing the rules") {
    executeNoParamWithReasoning(doTestTheRules _)

  }

  def doTestTheRules(comp: CompOntologyManager) {
    val courseContext = new CourseContext(comp, "n2");
    val competenceA = new Competence(comp, "Die Lehramtsanwärter kooperieren mit Kolleginnen und Kollegen bei der  Erarbeitung von Beratung/Empfehlung")
    courseContext.createEdgeWith(CompObjectProperties.CourseContextOf, competenceA)
    val competenceB = new Competence(comp, "Die Lehramtsanwärter erkennen Entwicklungsstände, Lernpotentiale, Lernhindernisseund Lernfortschritte")
    courseContext.createEdgeWith(CompObjectProperties.CourseContextOf, competenceB)
    val competenceC = new Competence(comp, "Die Lehramtsanwärter beschreiben den Lernstand der SuS und ihren eigenen Wissensstand.")
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

  }

  /**
   * TEST CASE 20
   */
  test("if a learning template is selected it should be persisted without errors") {
    val groupId = "111332";
    val selectedTemplateName = "Sprachkompetenz, Univ. (ELC, DE)";
    val userId = "Julian Dehne 12 12"
    val changes = new LearningTemplateData(userId, groupId, selectedTemplateName)
    LearningTemplateToOnt.convertTimed(changes)
  }

  /**
   * TEST CASE 21
   */
  test("listing all the subdaos should not throw any error and provide an existing list") {
    executeNoParam(doListingAllSubDaos _)
  }

  def doListingAllSubDaos(compOntManager: CompOntologyManager) {
    val competenceRoot = new CompetenceInstance(compOntManager)
    competenceRoot.listSubClasses(classOf[Competence]) should not be ('empty)
    val competenceLevel2 = new Competence(compOntManager, "SchreibenB2").getFullDao
    val result = competenceLevel2.listSubClasses(classOf[Competence])
    result should not be ('empty)
    (result.head.getDefinition != null) should not be false

  }

  /**
   * TEST CASE 22
   */
  test("listing all the the shortest path should not throw any error and provide an existing list") {
    executeNoParam(doListingAllSubDaos _)
  }

  def doListingShortestPath(compOntManag: CompOntologyManager) {

    val competenceRoot = new CompetenceInstance(compOntManag)
    val topClass = competenceRoot.toOntClass

    val definition2 = "Ich kann die Hauptinformation von Fernsehmeldungen über Ereignisse, Unglücksfälle usw. erfassen, wenn der Kommentar durch Bilder unterstützt wird."
    //val bottomCompetence = new Competence(compOntManag, definition2)
    val bottomClass = compOntManag.getUtil().createOntClassForString(definition2, false, definition2)

    val result = compOntManag.getUtil().getShortestSubClassPath(bottomClass, topClass)
    result should not be ('empty)

  }

  /**
   * TEST CASE 23
   */
  test("getOperations should not leave status in database") {
    executeNoParam(doNoSideEffectTest _)
  }

  def doNoSideEffectTest(comp: CompOntologyManager) {
    val competenceRoot = new CompetenceInstance(comp)


    val definition2 = "some random competence"
    //val bottomCompetence = new Competence(compOntManag, definition2)
    val bottomClass = new Competence(comp, definition2, definition2, null)

    bottomClass.exists should not be true
    // test whether exist relation modifies database
    bottomClass.exists should not be true

    bottomClass.toOntClass
    // test whether toOntClass modifies database
    bottomClass.exists should not be true

    bottomClass.isSubClass(competenceRoot.listSubClasses(classOf[Competence]).head) should not be true

    // test whether isSublass modifies database
    bottomClass.exists should not be true

    bottomClass.isSuperClass(competenceRoot.listSubClasses(classOf[Competence]).head) should not be true

    // test whether isSuperClass modifies database
    bottomClass.exists should not be true
  }

  //  test("test consistency of subclass relations") {
  //    val compOntManag = CompOntologyManagerFactory.createManager()
  //    compOntManag.beginWrite()
  //    compOntManag.getM().getIndividual("http://comp#I3org227owlNothing").remove()
  //    val compOntManagInMemory = CompOntologyManagerFactory.createManager(compOntManag.getM())
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
  //    val compOntManag2 = CompOntologyManagerFactory.createManager()
  //    compOntManag2.beginWrite()
  //    val validationreport2 = compOntManag2.getM.validate()
  //    println(compOntManag2.getUtil().validityReportTostring(validationreport2));
  //    validationreport2.isValid() should not be false
  //    compOntManag2.close()
  //
  //  }

  def createAbstractLink(comp: CompOntologyManager): AbstractEvidenceLink = {

    val studentRole = new StudentRole(comp)
    val courseContext = new CourseContext(comp, "2")
    val userStudent = new User(comp, "student meäää 10AA", studentRole, courseContext, "student meäää 10AA")
    userStudent.persist
    val teacherRole = new TeacherRole(comp)
    val user = new User(comp, "me", teacherRole, courseContext, "me")
    user.persist
    // and now a more complicated example
    val testComment = "mein testkommentar"
    val comment = new Comment(comp, testComment, userStudent, System.currentTimeMillis())
    comment.persist
    val testComment2 = "mein testkommentar2"
    val comment2 = new Comment(comp, testComment2, userStudent, System.currentTimeMillis())
    comment2.persist
    val evidenceActivity = new EvidenceActivity(comp, "http://testest", "meine testaktivitat")
    val competence = new Competence(comp, "Die Lehramtsanwärter kooperieren mit Kolleginnen und Kollegen bei der  Erarbeitung von Beratung/Empfehlung")
    competence.persistManualCascades(true)
    competence.createEdgeWith(courseContext, CompObjectProperties.CourseContextOf)
    val link = new AbstractEvidenceLink(comp, null, user, userStudent, courseContext, evidenceActivity, System.currentTimeMillis(), false, competence, (comment :: comment2 :: Nil))
    link.persist
    comment.hasEdge(userStudent, CompObjectProperties.UserOfComment) should not be false
    comment2.hasEdge(userStudent, CompObjectProperties.UserOfComment) should not be false
    return link
  }

}
