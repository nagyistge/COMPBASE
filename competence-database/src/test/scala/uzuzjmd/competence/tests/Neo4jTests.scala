package uzuzjmd.competence.tests

import uzuzjmd.competence.neo4j.{Neo4jIndividual, Neo4JQueryManager}

import scala.collection.JavaConverters.seqAsJavaListConverter
import org.junit.AfterClass
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.mapper.gui.read.Ont2CompetenceTree
import uzuzjmd.competence.persistence.performance._
import uzuzjmd.competence.persistence.dao.StudentRole
import uzuzjmd.competence.persistence.dao.TeacherRole
import org.scalatest.junit.JUnitRunner
import org.specs2.specification.After
import org.specs2.mutable.After
import uzuzjmd.competence.persistence.dao.User
import uzuzjmd.competence.persistence.ontology.CompObjectProperties
import uzuzjmd.competence.persistence.ontology.CompOntClass
import uzuzjmd.competence.persistence.dao.Role
import uzuzjmd.competence.persistence.dao.Comment
import uzuzjmd.competence.persistence.dao.EvidenceActivity
import uzuzjmd.competence.persistence.dao.AbstractEvidenceLink
import uzuzjmd.competence.persistence.dao.StudentRole
import uzuzjmd.competence.persistence.dao.CourseContext
import uzuzjmd.competence.persistence.dao.TeacherRole
import uzuzjmd.competence.persistence.dao.Competence
import uzuzjmd.competence.mapper.gui.read.Ont2CompetenceLinkMap
import uzuzjmd.competence.persistence.dao.AbstractEvidenceLink
import uzuzjmd.competence.mapper.gui.Ont2ProgressMap
import uzuzjmd.competence.persistence.dao.CourseContext
import uzuzjmd.competence.persistence.dao.SelectedLearningProjectTemplate
import uzuzjmd.competence.persistence.dao.LearningProjectTemplate
import uzuzjmd.competence.persistence.dao.CompetenceInstance
import uzuzjmd.competence.persistence.dao.Competence
import org.apache.log4j.Level
import com.hp.hpl.jena.rdf.model.InfModel
import com.hp.hpl.jena.rdf.model.ModelFactory
import uzuzjmd.competence.persistence.dao.Competence
import uzuzjmd.competence.main.CompetenceImporter
import uzuzjmd.competence.main.EposImporter
import uzuzjmd.competence.mapper.gui.Ont2ProgressMap
import uzuzjmd.competence.service.rest.model.dto.CourseData
import uzuzjmd.competence.mapper.rest.read.GetProgressMInOnt
import uzuzjmd.competence.service.rest.model.dto.LearningTemplateData
import uzuzjmd.competence.mapper.gui.write.LearningTemplateToOnt
import uzuzjmd.competence.main.OntologyWriter
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
