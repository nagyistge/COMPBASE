package uzuzjmd.competence.mapper.rest.read

import java.util.HashMap

import uzuzjmd.competence.config.Logging
import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager
import uzuzjmd.competence.persistence.dao.LearningProjectTemplate
import uzuzjmd.competence.shared.dto.{Graph, GraphTriple, LearningTemplateResultSet}
import uzuzjmd.java.collections.MapsMagic
import scala.collection.JavaConverters._

/**
 * @author dehne
 */
object Ont2SuggestedCompetenceGraph extends Logging {

  implicit def listToString(input: List[String]): String = {
    return input.reduce((a, b) => "[" + a + " , " + b + "]")
  }

  def getLearningTemplateResultSet(comp: CompOntologyManager, learningProjectTemplate: LearningProjectTemplate): LearningTemplateResultSet = {

    val name = learningProjectTemplate.name
    val graph = getGraph(learningProjectTemplate)
    val hashMap = getHashMap(comp, learningProjectTemplate, graph)
    val result = new LearningTemplateResultSet(graph, hashMap, name);
    return result
  }

  def getGraph(learningProjectTemplate: LearningProjectTemplate): Graph = {

    val result = new Graph

    val competences = learningProjectTemplate.getAssociatedCompetences()

    // convert relations in 2-tuples
    val result1 = competences.map { x => (x.getSuggestedCompetenceRequirements(), x) }.map(y => y._1.map { z => (z, y._2) }).flatten

    // convert to triples
    result1.foreach(x => result.addTriple(x._1.getDefinition(), x._2.getDefinition(), learningProjectTemplate.name, true))
    return result
  }

  /** UNIT TEST expects triple : "using tags" , "using JSP tags") as one of the result keys; mit programming und catchwords**/
  def getHashMap(comp: CompOntologyManager, learningProjectTemplate: LearningProjectTemplate, graph: Graph): HashMap[GraphTriple, Array[String]] = {

    val result1 = Ont2SuggestedCompetenceGrid.convertToTwoDimensionalGrid1(comp, learningProjectTemplate).mapValues { x => x.flatten }

    logger.trace("BEFORE INVERT")
    result1.foreach(x => logger.trace(" key: " + x._1 + ", values:" + x._2))
    val resultInverted = MapsMagic.invertAssociation(result1).map { case (key, value) => (key.getDefinition(), value) }.mapValues(x => x.map(y => y.getDefinition()))
    // TODO fix TEST 5 in UNIT Test of ANH

    val resultInvertedCleaned = resultInverted.keySet.toList.distinct
    val resultInvertedCleaned2 = resultInvertedCleaned.map(x => (x, resultInverted.get(x).toList.flatten.distinct)).toMap

    logger.trace("AFTER INVERT and CLEAN")
    resultInvertedCleaned2.foreach(x => logger.trace(" key: " + x._1 + ", values:" + x._2))

    logger.trace("before matching same catchword")
    logger.trace(graph.triples)

    // test if both parts of triple have same catchword
    val result2 = graph.triples.asScala.map(mapTripleToCommonCatchwords(_)(resultInvertedCleaned2)).filterNot(p => p._2.isEmpty).toMap

    logger.debug("AFTER matching same catchword")
    result2.foreach(x => logger.debug(x._1.toString() + x._2) + "\n")

    val result = new HashMap[GraphTriple, Array[String]]
    result.putAll(result2.mapValues { x => x.toArray }.asJava)
    return result
  }

  def mapTripleToCommonCatchwords(triple: GraphTriple)(checkMap: Map[String, List[String]]): (GraphTriple, Set[String]) = {
    val fromNodeCatchwords = checkMap.get(triple.fromNode).get.toSet
    val toNodeCatchwords = checkMap.get(triple.toNode).get.toSet

    val result = (triple, fromNodeCatchwords.intersect(toNodeCatchwords))
    val result2 = (result._1, result._2)
    return result2
  }

}