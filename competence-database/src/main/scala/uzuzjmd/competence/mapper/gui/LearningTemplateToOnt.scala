package uzuzjmd.competence.mapper.gui

import uzuzjmd.competence.owl.access.CompOntologyManager
import scala.collection.JavaConverters._
import uzuzjmd.competence.owl.dao.Competence
import uzuzjmd.competence.owl.dao.LearningProjectTemplate
import uzuzjmd.competence.owl.dao.Catchword
import javax.ws.rs.WebApplicationException
import uzuzjmd.competence.shared.dto.GraphTriple
import uzuzjmd.competence.shared.dto.Graph

/**
 * @author dehne
 * 
 * Dieses Objekt konvertiert 
 */
object LearningTemplateToOnt {

  def convert(comp : CompOntologyManager, graph: Graph, tripleCatchwordMap : java.util.HashMap[GraphTriple, Array[String]],  learningTemplateName: String) {
     if (!graph.triples.asScala.forall { x => tripleCatchwordMap.keySet().asScala.contains(x) }) {
        throw new WebApplicationException(new Exception("All the triples must be contained in the catchwordMap"))
     }    
    
     graph.triples.asScala.foreach { x => convertTriple(x, comp, tripleCatchwordMap) }
     val template = new LearningProjectTemplate(comp, learningTemplateName, graph.nodes.asScala.map(x => new Competence(comp, x.getLabel, x.getLabel, null)).toList, learningTemplateName)
     template.persist()
  }
  
  def convertTriple(triple : GraphTriple, comp : CompOntologyManager,  tripleCatchwordMap : java.util.HashMap[GraphTriple, Array[String]])  {
     val competenceFrom = new Competence(comp, triple.fromNode, triple.fromNode, null)
     val competenceTo = new Competence(comp, triple.toNode, triple.toNode, null)
     competenceTo.addSuggestedCompetenceRequirement(competenceFrom)
     val catchwords = tripleCatchwordMap.get(triple)
   
     catchwords.foreach { x => competenceFrom.addCatchword(new Catchword(comp, x, x)) }
     catchwords.foreach { x => competenceTo.addCatchword(new Catchword(comp, x, x)) }       
     competenceTo.persist(true)
     competenceFrom.persist(true)
  }
  
}