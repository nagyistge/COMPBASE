package uzuzjmd.competence.service.rest

import com.google.common.collect.Lists
import org.junit.Assert._
import org.junit.{After, Before, BeforeClass, Test}
import uzuzjmd.competence.datasource.rcd.generated.Rdceo
import uzuzjmd.competence.mapper.rest.write._
import uzuzjmd.competence.persistence.abstractlayer.{CompOntologyManager, WriteTransactional}
import uzuzjmd.competence.persistence.dao.{CourseContext, AbstractEvidenceLink, Competence}
import uzuzjmd.competence.service.rest.dto.{PrerequisiteData, LinkValidationData}
import uzuzjmd.competence.shared.dto.{HierarchyChange, HierarchyChangeSet}
import uzuzjmd.competence.tests.CoreTests
import scala.collection.JavaConverters._

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
    competence.persistManualCascades(false)
  }

  def testGetSelectedDeleteContexts(comp : CompOntologyManager): Unit = {
    val competence = new Competence(comp, "TestKompetenz")
    competence.delete()
  }

  @Test
  @throws(classOf[Exception])
  def testLinkCompetencesToUser: Unit ={
    assertTrue(true)
  }

  @Test
  @throws(classOf[Exception])
  def testCommentCompetence: Unit = {
    val coreTests = new CoreTests
    coreTests.commentTest
  }

  @Test
  @throws(classOf[Exception])
  def testValidateLink: Unit = {
    val linkId = setupValidationContext
    val isValid = true
    HandleLinkValidationInOnt.convert(new LinkValidationData(linkId, isValid))
    validateValidation
    executeNoParam(deleteValidationContext _)
  }


  @Test
  @throws(classOf[Exception])
  def testInvalidateLink: Unit = {
    val linkId = setupValidationContext
    val isValid = false
    HandleLinkValidationInOnt.convert(new LinkValidationData(linkId, isValid))
    validateInValidation
    executeNoParam(deleteValidationContext _)
  }


  def validateValidation = {
    val coreTests = new CoreTests
    val link = executeNoParamWithReturn(coreTests.createAbstractLink _)
    val link2 = link.asInstanceOf[AbstractEvidenceLink].getFullDao()
    assertTrue(link2.isValidated)
  }

  def validateInValidation = {
    val coreTests = new CoreTests
    val link = executeNoParamWithReturn(coreTests.createAbstractLink _).asInstanceOf[AbstractEvidenceLink]
    assertFalse(link.isValidated)
  }

  def setupValidationContext: String = {
    val coreTests = new CoreTests
    val link = executeNoParamWithReturn(coreTests.createAbstractLink _).asInstanceOf[AbstractEvidenceLink]
    return link.getId
  }

  def deleteValidationContext(comp : CompOntologyManager): Unit = {
    val coreTests = new CoreTests
    val link = coreTests.createAbstractLink(comp)
    link.delete
  }


  @Test
  @throws(classOf[Exception])
  def testDeleteLink: Unit = {
      assertTrue(true); // has been used in
  }

  @Test
  @throws(classOf[Exception])
  def testDeleteCompetence: Unit = {
    executeNoParam(testDeleteCompetenceSetup _)
    testDeleteCompetenceDo
    executeNoParam(testDeleteCompetenceAssertions _)
  }

  def testDeleteCompetenceDo: Unit = {
    val competenceA: String = "I know how to program hierarchies"
    val competenceB: String = "I know how to program"
    val competenceC: String = "I know little"
    val competences = competenceA :: competenceB :: competenceC :: Nil
    DeleteCompetenceInOnt.convert(competences.asJava);
  }

  def testDeleteCompetenceSetup(comp: CompOntologyManager): Unit = {
    val competenceA: String = "I know how to program hierarchies"
    val competenceB: String = "I know how to program"
    val competenceC: String = "I know little"
    val competenceADao = new Competence(comp, competenceA);
    competenceADao.persistManualCascades(false)
    val competenceBDao = new Competence(comp, competenceB);
    competenceBDao.persistManualCascades(false)
    val competenceCDao = new Competence(comp, competenceC);
    competenceCDao.persistManualCascades(false)
  }

  def testDeleteCompetenceAssertions(comp : CompOntologyManager): Unit = {
    val competenceA: String = "I know how to program hierarchies"
    val competenceB: String = "I know how to program"
    val competenceC: String = "I know little"
    val competenceADao = new Competence(comp, competenceA);
    assertFalse(competenceADao.exists())
    val competenceBDao = new Competence(comp, competenceB);
    assertFalse(competenceBDao.exists())
    val competenceCDao = new Competence(comp, competenceC);
    assertFalse(competenceCDao.exists())
  }

  @Test
  @throws(classOf[Exception])
  def testDeleteCompetenceTree: Unit = {
    executeNoParam(testDeleteCompetenceSetup _)
    val competenceA: String = "I know how to program hierarchies"
    val competenceB: String = "I know how to program"
    val competenceC: String = "I know little"
    val competences = competenceA :: competenceB :: competenceC :: Nil
    DeleteCompetenceTreeInOnt.convert(competences.asJava)
    executeNoParam(testDeleteCompetenceTreeValidation _)
  }

  def testDeleteCompetenceTreeSetup(comp: CompOntologyManager): Unit = {
    val competenceA: String = "I know how to program hierarchies"
    val competenceB: String = "I know how to program"
    val competenceC: String = "I know little"
    val competenceADao = new Competence(comp, competenceA);
    competenceADao.persistManualCascades(false)
    val competenceBDao = new Competence(comp, competenceB);
    competenceBDao.persistManualCascades(false)
    val competenceCDao = new Competence(comp, competenceC);
    competenceCDao.persistManualCascades(false)
    competenceADao.addSuperCompetence(competenceBDao)
    competenceBDao.addSuperCompetence(competenceCDao)
  }

  def testDeleteCompetenceTreeValidation(comp: CompOntologyManager): Unit = {
    val competenceA: String = "I know how to program hierarchies"
    val competenceB: String = "I know how to program"
    val competenceC: String = "I know little"
    val competenceADao = new Competence(comp, competenceA);
    val competenceBDao = new Competence(comp, competenceB);
    val competenceCDao = new Competence(comp, competenceC);
    assertFalse(competenceADao.exists())
    assertFalse(competenceBDao.exists())
    assertFalse(competenceCDao.exists())
  }


  @Test
  @throws(classOf[Exception])
  def testGetCompetenceLinksMap: Unit = {
    val coreTests = new CoreTests
    coreTests.testCompetenceLinksMapsCreation
  }

  @Test
  @throws(classOf[Exception])
  def testGetProgressM: Unit = {
    val coreTests = new CoreTests
    coreTests.testProgressMapCreation
  }



  @Test
  @throws(classOf[Exception])
  def testCreatePrerequisite: Unit = {
    val competenceA: String = "I know how to program hierarchies"
    val competenceB: String = "I know how to program"
    val competenceC: String = "I know little"
    val competences = (competenceB :: competenceC :: Nil).asJava
    val course = new CourseContext(comp, "university")
    executeNoParam(testCreatePrerequisiteTestSetup _)
    CreatePrerequisiteInOnt.convert(new PrerequisiteData(course.getId, competenceA, competences))
    executeNoParam(testCreatePrerequisiteAssertions _)
    executeNoParam(testDeleteCompetencePrerequisiteCleanup _)
  }

  def testDeleteCompetencePrerequisiteCleanup(comp : CompOntologyManager): Unit = {
    val competenceA: String = "I know how to program hierarchies"
    val competenceB: String = "I know how to program"
    val competenceC: String = "I know little"
    val competenceADao = new Competence(comp, competenceA);
    competenceADao.delete()
    val competenceBDao = new Competence(comp, competenceB);
    competenceBDao.delete()
    val competenceCDao = new Competence(comp, competenceC);
    competenceCDao.delete()

    assertFalse(competenceADao.exists())
    assertFalse(competenceBDao.exists())
    assertFalse(competenceCDao.exists())
  }

  def testCreatePrerequisiteAssertions(comp : CompOntologyManager): Unit = {
    val competenceA: String = "I know how to program hierarchies"
    val competenceB: String = "I know how to program"
    val competenceC: String = "I know little"
    val competenceADao = new Competence(comp, competenceA);
    val competenceBDao = new Competence(comp, competenceB);
    val competenceCDao = new Competence(comp, competenceC);
    assertTrue(competenceADao.getRequiredCompetences().contains(competenceBDao))
    assertTrue(competenceADao.getRequiredCompetences().contains(competenceCDao))
  }

  def testCreatePrerequisiteTestSetup(comp : CompOntologyManager): Unit = {
    val course = new CourseContext(comp, "university")
    course.persist()
    val competenceA: String = "I know how to program hierarchies"
    val competenceB: String = "I know how to program"
    val competenceC: String = "I know little"

    val competenceADao = new Competence(comp, competenceA);
    competenceADao.persist
    val competenceBDao = new Competence(comp, competenceB);
    competenceBDao.persist
    val competenceCDao = new Competence(comp, competenceC);
    competenceCDao.persist

  }

  @Test
  @throws(classOf[Exception])
  def testDeletePrerequisite: Unit = {
    executeNoParam(testCreatePrerequisiteTestSetup _)
    val competenceA: String = "I know how to program hierarchies"
    val competenceB: String = "I know how to program"
    val competenceC: String = "I know little"
    val competences = (competenceB :: competenceC :: Nil).asJava
    val course = new CourseContext(comp, "university")
    DeletePrerequisiteInOnt.convert(new PrerequisiteData(course.identifier, competenceA , competences))
    executeNoParam(testDeletePrerequisiteAssertions _) //assertions
  }

  def testDeletePrerequisiteAssertions(comp:CompOntologyManager): Unit = {
    val competenceA: String = "I know how to program hierarchies"
    val competenceB: String = "I know how to program"
    val competenceC: String = "I know little"

    val competenceADao = new Competence(comp, competenceA);
    val competenceBDao = new Competence(comp, competenceB);
    val competenceCDao = new Competence(comp, competenceC);
    assertFalse(competenceADao.getRequiredCompetences().contains(competenceBDao))
    assertFalse(competenceADao.getRequiredCompetences().contains(competenceCDao))
    competenceADao.delete()
    competenceBDao.delete()
    competenceCDao.delete()

    assertFalse(competenceADao.exists())
    assertFalse(competenceBDao.exists())
    assertFalse(competenceCDao.exists())
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