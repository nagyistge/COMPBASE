package uzuzjmd.competence.tests

import java.util.HashMap

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import uzuzjmd.competence.mapper.rest.read.Ont2SuggestedCompetenceGrid
import uzuzjmd.competence.mapper.rest.write.LearningTemplateToOnt
import uzuzjmd.competence.persistence.abstractlayer.WriteTransactional
import uzuzjmd.competence.persistence.dao.{LearningProjectTemplate, User}
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl
import uzuzjmd.competence.shared.dto.{Graph, GraphNode, GraphTriple, LearningTemplateResultSet}
import uzuzjmd.scompetence.owl.validation.LearningTemplateValidation


@RunWith(classOf[JUnitRunner])
class CoreTests2 extends FunSuite with ShouldMatchers with WriteTransactional[Any] {

  test("SETUP AND The CSV import should run without errors") {
      //TestCommons.setup()
  }

  test("if a learning template is created this should not cause any error") {
    val node1 = new GraphNode("Erste Kompetenz")
    val node2 = new GraphNode("Zweite Kompetenz")
    val node3 = new GraphNode("Dritte Kompetenz")
    val triple1 = new GraphTriple(node1.getLabel, node2.getLabel, "suggestedCompetenceRequisite", true)
    val triple2 = new GraphTriple(node3.getLabel, node1.getLabel, "suggestedCompetenceRequisite", true)
    val triple3 = new GraphTriple(node2.getLabel, node3.getLabel, "suggestedCompetenceRequisite", true)

    val graph = new Graph
    graph.addTriple(node1.getLabel, node2.getLabel, "suggestedCompetenceRequisite", true)
    graph.addTriple(node2.getLabel, node3.getLabel, "suggestedCompetenceRequisite", true)
    graph.addTriple(node3.getLabel, node1.getLabel, "suggestedCompetenceRequisite", true)

    val catchwordMap = new HashMap(): HashMap[GraphTriple, Array[String]]
    catchwordMap.put(triple1, ("die erstenbeiden" :: Nil).toArray)
    catchwordMap.put(triple2, ("die anderenbeiden" :: Nil).toArray)
    catchwordMap.put(triple3, ("die drittenbeiden" :: Nil).toArray)

    val testLearningTemplateName = "TestLearningTemplate"

    val resultSet = new LearningTemplateResultSet(graph, catchwordMap, testLearningTemplateName)
    LearningTemplateToOnt.convertLearningTemplateResultSet(resultSet)

    val learningTemplateValidator = new LearningTemplateValidation(resultSet)
    learningTemplateValidator.isValid should not be true
    //executeX[String](doLearningTemplateCreateTest _, testLearningTemplateName)
  }


  test("if a learning template is created this should not cause any error 2") {
    val node1 = new GraphNode("Erste Kompetenz")
    val node2 = new GraphNode("Zweite Kompetenz")
    val node3 = new GraphNode("Dritte Kompetenz")
    val triple1 = new GraphTriple(node1.getLabel, node2.getLabel, "suggestedCompetenceRequisite", true)
    val triple2 = new GraphTriple(node2.getLabel, node3.getLabel, "suggestedCompetenceRequisite", true)
    //val triple3 = new GraphTriple(node2.getLabel, node3.getLabel, "suggestedCompetenceRequisite", true)

    val graph = new Graph
    graph.addTriple(node1.getLabel, node2.getLabel, "suggestedCompetenceRequisite", true)
    graph.addTriple(node2.getLabel, node3.getLabel, "suggestedCompetenceRequisite", true)
    //graph.addTriple(node3.getLabel, node1.getLabel, "suggestedCompetenceRequisite", true)

    graph.nodes.add(node1)
    graph.nodes.add(node2)
    graph.nodes.add(node3)

    val catchwordMap = new HashMap(): HashMap[GraphTriple, Array[String]]
    catchwordMap.put(triple1, ("die erstenbeiden" :: Nil).toArray)
    catchwordMap.put(triple2, ("die anderenbeiden" :: Nil).toArray)
    //catchwordMap.put(triple3, ("die drittenbeiden" :: Nil).toArray)

    val testLearningTemplateName = "TestLearningTemplate"

    val resultSet = new LearningTemplateResultSet(graph, catchwordMap, testLearningTemplateName)

    val learningTemplateValidator = new LearningTemplateValidation(resultSet)
    learningTemplateValidator.isValid should not be false

    LearningTemplateToOnt.convertLearningTemplateResultSet(resultSet)
  }

  def doLearningTemplateCreateTest(comp: CompOntologyManagerJenaImpl, testLearningTemplateName: String) {
    val user = new User(comp, "TestUser")
    val learningProjectTemplate = new LearningProjectTemplate(comp, testLearningTemplateName)
    val result = Ont2SuggestedCompetenceGrid.convertToTwoDimensionalGrid(comp, learningProjectTemplate, user)
    result.getSuggestedCompetenceRows should not be ('empty)
  }
}

