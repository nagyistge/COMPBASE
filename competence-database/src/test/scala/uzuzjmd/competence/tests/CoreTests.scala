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

@RunWith(classOf[JUnitRunner])
class CoreTests extends FunSuite with ShouldMatchers {

  test("The CSV import should run without errors") {

    // change this, if you want to really reset the database
    CompFileUtil.deleteTDB()

    val compOntManag = new CompOntologyManager()

    compOntManag.begin()
    compOntManag.getM().validate()
    compOntManag.close()

    CompetenceImporter.convertCSV(MagicStrings.CSVLOCATION);
    compOntManag.begin()
    compOntManag.getM().validate()
    compOntManag.close()

    EposImporter.main(Array(MagicStrings.EPOSLocation));
    compOntManag.begin()
    compOntManag.getM().validate()
    compOntManag.close()

    compOntManag.begin()
    val fileUtil = new CompFileUtil(compOntManag.getM())
    fileUtil.writeOntologyout()
    compOntManag.close()
  }

  test("if a user is persisted, the course context should be acessable") {

    val compOntManag = new CompOntologyManager()
    compOntManag.begin()

    val teacherRole = new TeacherRole(compOntManag)
    val coursecontext = new CourseContext(compOntManag, "2")
    coursecontext.persist
    val user = new User(compOntManag, "me", teacherRole, coursecontext, "Julian Dehne")
    user.persist()
    val user2 = new User(compOntManag, "me")
    val fullUser = user2.getFullDao
    fullUser.equals(user) should not be false
    user.delete
    coursecontext.delete
    compOntManag.close()
  }

  test("The CompetenceTree should not be empty") {
    val compOntManag = new CompOntologyManager()

    compOntManag.begin()
    compOntManag.getM().validate()
    compOntManag.close()

    val mapper = new Ont2CompetenceTree(compOntManag, List.empty.asJava, List.empty.asJava, "4", false)
    val competenceTree = mapper.getComptenceTree
    competenceTree should not be ('empty)

  }

  test("The filtered CompetenceTree should not be empty") {
    val compOntManag = new CompOntologyManager()

    compOntManag.begin()
    compOntManag.getM().validate()
    compOntManag.close()

    val catchwords = "Kooperation" :: "Diagnostik" :: List.empty
    val operators = "bewerten" :: "kooperieren" :: List.empty
    val mapper = new Ont2CompetenceTree(compOntManag, catchwords.asJava, operators.asJava, "4", false)
    val competenceTree = mapper.getComptenceTree
    competenceTree should not be ('empty)

  }

  test("the operator tree should not be empty") {
    val compOntManag = new CompOntologyManager()
    val mapper = new Ont2CompetenceTree(compOntManag, List.empty.asJava, List.empty.asJava, "4", false)
    val result = mapper.getOperatorXMLTree
    result should not be ('empty)
  }

  test("the catchword tree should not be empty") {
    val compOntManag = new CompOntologyManager()
    val mapper = new Ont2CompetenceTree(compOntManag, List.empty.asJava, List.empty.asJava, "4", false)
    val result = mapper.getCatchwordXMLTree
    result should not be ('empty)
  }

  test("the singletondao should persist without error") {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()
    val studentRole = new StudentRole(compOntManag)
    studentRole.persist(true)
    studentRole.persist(false).getIndividual() should not be null
    studentRole.persist(false).getOntclass() should not be null
    val teacherRole = new TeacherRole(compOntManag)
    teacherRole.persist(true)
    teacherRole.persist(false).getIndividual() should not be null
    teacherRole.persist(false).getOntclass() should not be null
    compOntManag.close()
    showResult
  }

  test("the regular dao should persist without error") {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()
    val teacherRole = new TeacherRole(compOntManag)
    val coursecontext = new CourseContext(compOntManag, "2")
    val user = new User(compOntManag, "me", teacherRole, coursecontext, "me")
    user.persist
    user.exists should not be false
    user.delete
    user.exists should not be true
    compOntManag.close()
    showResult
  }

  test("if a dao is linked the link should exist") {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()
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
    compOntManag.close()
    showResult
  }

  test("if a comment is persisted it should have its datafields in place") {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()
    val testkommentar = "mein testkommentar"
    val teacherRole = new TeacherRole(compOntManag)
    val coursecontext = new CourseContext(compOntManag, "2")
    val user = new User(compOntManag, "me", teacherRole, coursecontext, "me")
    val comment = new Comment(compOntManag, testkommentar, user, System.currentTimeMillis())
    comment.persist
    comment.exists should not be false
    val testkommentar2 = comment.getDataField(comment.TEXT)
    comment.getDataField(comment.TEXT).equals(testkommentar) should not be false
    (comment.getDataField(comment.DATECRATED) != null) should not be false
    comment.deleteDataField(comment.TEXT)
    (comment.getDataField(comment.TEXT) != null) should not be true
    comment.hasDataField(comment.TEXT) should not be true
    compOntManag.close()
    showResult
  }

  test("if a evidence link is created this should not cause errors") {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()
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
    val evidenceActivity = new EvidenceActivity(compOntManag, "http://testest", "meine testaktivitat")
    val link = new AbstractEvidenceLink(compOntManag, user.name + evidenceActivity.printableName, user, userstudent, coursecontext, (comment :: comment2 :: Nil), evidenceActivity, System.currentTimeMillis(), false, competence)
    link.persist
    link.exists should not be false
    link.delete
    evidenceActivity.delete
    evidenceActivity.exists should not be true
    compOntManag.close()
    showResult
  }

  test("if a string is given the identified full dao should be returnable") {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()
    val linkId = "hellolinkId"
    val studentRole = new StudentRole(compOntManag)
    val coursecontext = new CourseContext(compOntManag, "2")
    val userstudent = new User(compOntManag, "student meäää 10AA", studentRole, coursecontext, "student meäää 10AA")
    val link = createAbstract(compOntManag, linkId, userstudent)

    // now getting it by example
    val exampleLink = new AbstractEvidenceLink(compOntManag, linkId)
    val fullExampleLink = exampleLink.getFullDao

    fullExampleLink.creator should not be null
    fullExampleLink.getAllActivities should not be ('empty)
    fullExampleLink.getAllCourseContexts should not be ('empty)
    fullExampleLink.getAllLinkedUsers should not be ('empty)
    val linkedUser = fullExampleLink.getAllLinkedUsers.head
    //    linkedUser.equals(userstudent) should not be false
    link.delete

    compOntManag.close()
    showResult
  }

  // TODO: Find out why this test fails
  //  test("the competencelinksmap should not be empty") {
  //    val compOntManag = new CompOntologyManager()
  //    compOntManag.begin()
  //    val linkId = "hellolinkId"
  //    val studentRole = new StudentRole(compOntManag)
  //    val coursecontext = new CourseContext(compOntManag, "2")
  //    val userstudent = new User(compOntManag, "student meäää 10AA", studentRole, coursecontext, "student meäää 10AA")
  //    val link = createAbstract(compOntManag, linkId, userstudent)
  //    compOntManag.close()
  //    val mapper = new Ont2CompetenceLinkMap(compOntManag, "student meäää 10AA")
  //    mapper.getCompetenceLinkMap.getMapUserCompetenceLinks().entrySet() should not be ('empty)
  //    link.delete
  //    showResult
  //  }

  test("progresbarmap should not be empty") {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()
    val linkId = "hellolinkId"
    val studentRole = new StudentRole(compOntManag)
    val coursecontext = new CourseContext(compOntManag, "2")
    val userstudent = new User(compOntManag, "student meäää 10AA", studentRole, coursecontext, "student meäää 10AA")
    val link = createAbstract(compOntManag, linkId, userstudent)
    compOntManag.close()
    compOntManag.begin()
    val competenceList = new Competence(compOntManag, "Die Lehramtsanwärter kooperieren mit Kolleginnen und Kollegen bei der  Erarbeitung von Beratung/Empfehlung") :: Nil
    val competenceListString = competenceList.map(x => x.getFullDao.definition).asJava
    val mapper = new Ont2ProgressMap(compOntManag, coursecontext.name, competenceListString)
    mapper.getProgressMap() should not be null
    mapper.getProgressMap().entrySet() should not be ('empty)
    link.delete
    compOntManag.close()
    showResult
  }

  test("if a requires b and b requires c, a should require c") {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()
    compOntManag.getM().enterCriticalSection(false);
    compOntManag.startReasoning();
    val competenceA = new Competence(compOntManag, "Die Lehramtsanwärter kooperieren mit Kolleginnen und Kollegen bei der  Erarbeitung von Beratung/Empfehlung")
    val competenceB = new Competence(compOntManag, "Die Lehramtsanwärter erkennen Entwicklungsstände, Lernpotentiale, Lernhindernisseund Lernfortschritte")
    val competenceC = new Competence(compOntManag, "Die Lehramtsanwärter beschreiben den Lernstand der SuS und ihren eigenen Wissensstand.")
    competenceC.addRequiredCompetence(competenceB)
    competenceB.addRequiredCompetence(competenceA)
    competenceC.hasEdge(competenceA, CompObjectProperties.PrerequisiteOf) should not be false
    compOntManag.close()
    showResult
  }

  test("testing the rules") {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()
    compOntManag.getM().enterCriticalSection(false);
    compOntManag.startReasoning();
    compOntManag.switchOffDebugg();

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
    val link = createAbstract(compOntManag, linkId, userstudent)

    compOntManag.close()
    showResult
  }

  test("if a learning template is selected it should be persisted without errors") {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()
    compOntManag.getM().enterCriticalSection(false);

    val groupId = "111332";
    val selectedTemplateName = "Sprachkompetenz, Univ. (ELC, DE)";
    val userId = "Julian Dehne 12 12"

    val courseContext = new CourseContext(compOntManag, groupId)
    val user = new User(compOntManag, userId, new TeacherRole(compOntManag), courseContext, userId)
    val selectedTemplate = new SelectedLearningProjectTemplate(compOntManag, user, courseContext)
    selectedTemplate.persist()
    val learningProjectTemplate = new LearningProjectTemplate(compOntManag, selectedTemplateName, null, selectedTemplateName)
    learningProjectTemplate.persist
    selectedTemplate.addAssociatedTemplate(learningProjectTemplate)

    compOntManag.getM().leaveCriticalSection();
    compOntManag.close();
    showResult
  }

  test("a user and a course give you should be able to get all the associatedTemplates") {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()
    compOntManag.getM().enterCriticalSection(false);

    val groupId = "111332";
    val selectedTemplateName = "Sprachkompetenz, Univ. (ELC, DE)";
    val userId = "Julian Dehne 12 12"

    val courseContext = new CourseContext(compOntManag, groupId)
    val user = new User(compOntManag, userId, new TeacherRole(compOntManag), courseContext, userId)
    val selectedTemplate = new SelectedLearningProjectTemplate(compOntManag, user, courseContext)
    val selectedTemplateFull = selectedTemplate.getFullDao
    selectedTemplateFull.getAssociatedTemplates should not be ('empty)

    compOntManag.getM().leaveCriticalSection();
    compOntManag.close();
    showResult
  }

  test("listing all the subdaos should not throw any error and provide an existing list") {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()

    CompOntologyAccess.logger.setLevel(Level.DEBUG)

    val competenceRoot = new CompetenceInstance(compOntManag)
    val result2 = competenceRoot.listSubClasses(classOf[Competence])
    competenceRoot.listSubClasses(classOf[Competence]) should not be ('empty)

    val competenceLevel2 = new Competence(compOntManag, "SchreibenB2").getFullDao
    val result = competenceLevel2.listSubClasses(classOf[Competence])
    result should not be ('empty)
    (result.head.getDefinition != null) should not be false

    compOntManag.close();
  }

  test("listing all the the shortest path should not throw any error and provide an existing list") {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()

    CompOntologyAccess.logger.setLevel(Level.DEBUG)

    val competenceRoot = new CompetenceInstance(compOntManag)
    val topClass = competenceRoot.toOntClass

    val definition2 = "Ich kann die Hauptinformation von Fernsehmeldungen über Ereignisse, Unglücksfälle usw. erfassen, wenn der Kommentar durch Bilder unterstützt wird."
    //val bottomCompetence = new Competence(compOntManag, definition2)
    val bottomClass = compOntManag.getUtil().createOntClassForString(definition2, definition2)

    val result = compOntManag.getUtil().getShortestSubClassPath(bottomClass, topClass)
    result should not be ('empty)

    compOntManag.close();
  }

  test("getOperations should not leave status in database") {
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()

    CompOntologyAccess.logger.setLevel(Level.DEBUG)

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

    compOntManag.close();
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
    val compOntManag = new CompOntologyManager()
    compOntManag.begin()
    val fileUtil = new CompFileUtil(compOntManag.getM())
    fileUtil.writeOntologyout()
    compOntManag.close()
  }

  private def createAbstract(compOntManag: CompOntologyManager, linkId: String, userstudent: User): AbstractEvidenceLink = {
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
    competence.createEdgeWith(coursecontext, CompObjectProperties.CourseContextOf)
    val link = new AbstractEvidenceLink(compOntManag, linkId, user, userstudent, coursecontext, (comment :: comment2 :: Nil), evidenceActivity, System.currentTimeMillis(), false, competence)
    link.persist
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

