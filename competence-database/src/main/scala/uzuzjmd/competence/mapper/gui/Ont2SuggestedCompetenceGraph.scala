package uzuzjmd.competence.mapper.gui

import uzuzjmd.competence.owl.access.CompOntologyManager
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import uzuzjmd.competence.shared.dto.LearningTemplateResultSet
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import uzuzjmd.competence.shared.dto.Graph
import java.util.HashMap
import uzuzjmd.competence.shared.dto.GraphTriple
import uzuzjmd.java.collections.MapsMagic
import scala.collection.JavaConverters._

/**
 * @author dehne
 */
object Ont2SuggestedCompetenceGraph {

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
    val result1 = competences.map { x => (x.getSuggestedCompetenceRequirements(),x) }.map(y => y._1.map { z => (z, y._2) }).flatten
    
    // convert to triples 
    result1.foreach(x=>result.addTriple(x._1.getDefinition(), x._2.getDefinition(), learningProjectTemplate.name, true)) 
    return result
  }

  def getHashMap(comp : CompOntologyManager, learningProjectTemplate: LearningProjectTemplate, graph: Graph): HashMap[GraphTriple, Array[String]] = {
    
    val result1= Ont2SuggestedCompetenceGrid.convertToTwoDimensionalGrid1(comp, learningProjectTemplate).mapValues { x => x.flatten }
    val resultInverted = MapsMagic.invertAssociation(result1).map {case(key, value) => (key.getDefinition(),value)}.mapValues(x=>x.map(y=>y.getDefinition()))
    // test if both parts of triple have same catchword
    val result2 = graph.triples.asScala.map { x =>  (x, resultInverted.get(x.fromNode).toSet.intersect(resultInverted.get(x.toNode).toSet).flatten.toArray) }.filterNot(p => p._2.isEmpty).toMap
    
    val result = new HashMap[GraphTriple, Array[String]]
    result.putAll(result2.asJava)
    return result
  }
  
  
}