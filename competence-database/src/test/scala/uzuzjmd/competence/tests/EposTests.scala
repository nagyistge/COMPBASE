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
import javax.xml.bind.JAXBContext
import uzuzjmd.competence.datasource.epos.DESCRIPTORSETType
import java.util.ArrayList
import java.io.File
import uzuzjmd.competence.owl.access.MagicStrings
import uzuzjmd.competence.datasource.epos.mapper.EposXMLToSuggestedLearningPath

@RunWith(classOf[JUnitRunner])
class EposTests extends FunSuite with ShouldMatchers {

  test("if the xml is loaded no error should occur") {

    val jaxbContext = JAXBContext.newInstance(classOf[DESCRIPTORSETType])
    val eposUnMarshallUnmarshaller = jaxbContext.createUnmarshaller()
    val descriptorsetType = eposUnMarshallUnmarshaller.unmarshal(new File(MagicStrings.EPOSLocation)).asInstanceOf[DESCRIPTORSETType]
    // we assume that there will be more than just one descriptorset (but
    // don't know ey)
    val eposList = List(descriptorsetType).asJava

    val manager = new CompOntologyManager();
    EposXMLToSuggestedLearningPath.convertLevelsToOWLRelations(manager, eposList);
    //EposXMLToSuggestedLearningPath.convertLevelsAndLearningGoalToTemplate(manager, eposList)

    manager.begin()
    val fileUtil = new CompFileUtil(manager.getM())
    fileUtil.writeOntologyout()
    manager.close()
  }

}

