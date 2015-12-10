package uzuzjmd.competence.tests

import uzuzjmd.competence.neo4j.{Neo4jIndividual, Neo4JQueryManager}

import scala.collection.JavaConverters.seqAsJavaListConverter
import org.junit.AfterClass
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.mapper.gui.read.Ont2CompetenceTree
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
import uzuzjmd.competence.mapper.gui.read.Ont2CompetenceLinkMap
import uzuzjmd.competence.owl.dao.AbstractEvidenceLink
import uzuzjmd.competence.mapper.gui.Ont2ProgressMap
import uzuzjmd.competence.owl.dao.CourseContext
import uzuzjmd.competence.owl.dao.SelectedLearningProjectTemplate
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import uzuzjmd.competence.owl.dao.CompetenceInstance
import uzuzjmd.competence.owl.dao.Competence
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
import uzuzjmd.competence.mapper.rest.read.GetProgressMInOnt
import uzuzjmd.competence.service.rest.model.dto.LearningTemplateData
import uzuzjmd.competence.mapper.gui.write.LearningTemplateToOnt
import uzuzjmd.competence.main.OntologyWriter
import uzuzjmd.competence.owl.access.PropUtil
import org.junit.BeforeClass
import org.junit.Assert._
import org.junit.Test
import org.junit.Before
import org.apache.log4j.LogManager
import org.apache.log4j.xml.DOMConfigurator
import org.apache.log4j.Logger
import uzuzjmd.competence.logging.LoggingTDBWriteTransactional


/**
  * Created by dehne on 04.12.2015.
  */

@RunWith(classOf[JUnitRunner])
class Neo4jTests  extends FunSuite with ShouldMatchers {

  test("just persisting individual and deleting") {
    val indvidualy = new Neo4jIndividual("julian", "julian is strong", null);
  }

}
