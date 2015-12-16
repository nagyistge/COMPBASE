package uzuzjmd.competence.service.rest

import com.google.common.collect.Lists
import org.junit.Assert._
import org.junit.{After, Before, BeforeClass, Test}
import uzuzjmd.competence.datasource.rcd.generated.Rdceo
import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, WriteTransactional}
import uzuzjmd.competence.persistence.dao.{CourseContext, Competence}
import uzuzjmd.competence.shared.dto.{HierarchyChange, HierarchyChangeSet}
import uzuzjmd.competence.tests.{CoreTests, TestCommons}

/**
  * Created by dehne on 14.12.2015.
  */
object CompetenceServiceRestJSONTest {
  @BeforeClass
  @throws(classOf[Exception])
  def setUpBeforeClass {
    //TestCommons.setup
  }
}

/**
  * TEST CLASS for the JSON interface
  */
class CompetenceServiceRestJSONTest extends WriteTransactional[Any] {
  private var jsonService: CompetenceServiceRestJSON = null

  @Before
  @throws(classOf[Exception])
  def setUp {
    this.jsonService = new CompetenceServiceRestJSON
  }

  @After
  @throws(classOf[Exception])
  def tearDown {
  }

  @Test
  @throws(classOf[Exception])
  def testGetRdceo {
    val testInstance: java.util.List[Rdceo] = this.jsonService.getRdceo
    assertFalse(testInstance.isEmpty)
  }

  @Test
  @throws(classOf[Exception])
  def testUpdateHierarchy {
    val competenceA: String = "I know how to program hierarchies"
    val competenceB: String = "I know how to program"
    val competenceC: String = "I know little"
    val changes: HierarchyChangeSet = new HierarchyChangeSet
    val hierarchyChange: HierarchyChange = new HierarchyChange(competenceB, competenceC, competenceA)
    changes.setElements(Lists.newArrayList(hierarchyChange))
    this.jsonService.updateHierarchie2(changes)

    // validate
    executeNoParam(updateHierarchyAssertions(_,changes))

    // clean
    executeNoParam(cleanUpdateHierarchyAssertions(_,changes))
  }

  def updateHierarchyAssertions(comp : CompOntologyManager, changes: HierarchyChangeSet): Unit = {
    val hierarchyChange = changes.getElements.get(0)
    val competenceADAO = new Competence(comp,hierarchyChange.getNodeSelected)
    val competenceBDAO = new Competence(comp,hierarchyChange.getNewClass)
    val competenceCDAO = new Competence(comp,hierarchyChange.getOldClass)
    assertTrue(competenceADAO.isSubClass(competenceBDAO))
    assertFalse(competenceADAO.isSubClass(competenceCDAO))
  }

  def cleanUpdateHierarchyAssertions(comp: CompOntologyManager, changes: HierarchyChangeSet): Unit = {
    val hierarchyChange = changes.getElements.get(0)
    val competenceADAO = new Competence(comp,hierarchyChange.getNodeSelected)
    val competenceBDAO = new Competence(comp,hierarchyChange.getNewClass)
    val competenceCDAO = new Competence(comp,hierarchyChange.getOldClass)
    competenceADAO.delete()
    competenceBDAO.delete()
    competenceCDAO.delete()
  }

  @Test
  @throws(classOf[Exception])
  def testLinkCompetencesToCourseContext: Unit = {
    val coreTests = new CoreTests()
    executeNoParam(coreTests.createLinkTestContexts _)
    executeNoParam(coreTests.doEvidenceLinkTest _)
    executeNoParam(coreTests.doDeleteEvidenceLink _)
  }

  @Test
  @throws(classOf[Exception])
  def testCreateUser: Unit = {
    val coreTests = new CoreTests()
    executeNoParam(coreTests.regularDaoTest _)
  }

  @Test
  @throws(classOf[Exception])
  def testDeleteCourseContext: Unit = {
    assertTrue(true) // has been tested in create User
  }

  @Test
  @throws(classOf[Exception])
  def testGetRequirements: Unit = {
    // TODO implement (not prio)
  }

  @Test
  @throws(classOf[Exception])
  def testGetSelected: Unit = {
    // not testing deprecated method
  }

  @Test
  @throws(classOf[Exception])
  def testGetSelected2: Unit = {
    executeNoParam(testGetSelectedCreateContexts _)
    val course = "university"
    val result = CompetenceServiceWrapper.getSelected(course);
    assertFalse(result.isEmpty)
    executeNoParam(testGetSelectedDeleteContexts _)
  }

  def testGetSelectedCreateContexts(comp : CompOntologyManager): Unit = {
    val competence = new Competence(comp, "TestKompetenz")
    competence.persist(false)
  }

  def testGetSelectedDeleteContexts(comp : CompOntologyManager): Unit = {
    val competence = new Competence(comp, "TestKompetenz")
    competence.delete()
  }

  @Test
  @throws(classOf[Exception])
  def testLinkCompetencesToUser: Unit ={
    assertTrue(true)
    // already tested in testLinkCompetencesToCourseContext
  }

  @Test
  @throws(classOf[Exception])
  def testCommentCompetence: Unit = {
    val coreTests = new CoreTests
    coreTests.commentTest
  }

  @Test
  @throws(classOf[Exception])
  def testValidateLink {
  }

  @Test
  @throws(classOf[Exception])
  def testInvalidateLink {
  }

  @Test
  @throws(classOf[Exception])
  def testDeleteLink {
  }

  @Test
  @throws(classOf[Exception])
  def testDeleteCompetence {
  }

  @Test
  @throws(classOf[Exception])
  def testDeleteCompetenceTree {
  }

  @Test
  @throws(classOf[Exception])
  def testGetCompetenceLinksMap {
  }

  @Test
  @throws(classOf[Exception])
  def testGetProgressM {
  }

  @Test
  @throws(classOf[Exception])
  def testCreatePrerequisite {
  }

  @Test
  @throws(classOf[Exception])
  def testDeletePrerequisite {
  }

  @Test
  @throws(classOf[Exception])
  def testGetPrerequisiteGraph {
  }

  @Test
  @throws(classOf[Exception])
  def testGetRequiredCompetences {
  }

  @Test
  @throws(classOf[Exception])
  def testGetOperatorForCompetence {
  }

  @Test
  @throws(classOf[Exception])
  def testGetCatchwordsForCompetence {
  }

  @Test
  @throws(classOf[Exception])
  def testAddCompetenceToModel {
  }

  @Test
  @throws(classOf[Exception])
  def testEditCompetenceToModel {
  }

  @Test
  @throws(classOf[Exception])
  def testCreateSuggestedCourseForCompetence {
  }

  @Test
  @throws(classOf[Exception])
  def testCreateSuggestedActivityForCompetence {
  }

  @Test
  @throws(classOf[Exception])
  def testDeleteSuggestedCourseForCompetence {
  }

  @Test
  @throws(classOf[Exception])
  def testDeleteSuggestedActivityForCompetence {
  }
}