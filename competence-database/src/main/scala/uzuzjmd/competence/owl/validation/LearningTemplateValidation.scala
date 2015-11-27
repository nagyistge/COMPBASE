package uzuzjmd.scompetence.owl.validation

import scala.collection.JavaConverters._
import scala.collection.mutable.Stack
import uzuzjmd.competence.shared.dto.LearningTemplateResultSet
import java.util.HashMap

/**
 * @author dehne
 * implements Tarjans strongly connected components algorithm in order to identify cycles in graph
 * https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm
 */
case class LearningTemplateValidation(learningTemplate: LearningTemplateResultSet) {

  val graph = learningTemplate.getResultGraph
  val vertices = graph.nodes.asScala.map { x => x.getLabel }
  //  val edgesMap = graph.triples.asScala.map { x => (x.toString(), x) }
  //  val edges = edgesMap.map(x => x._1)
  val edges = graph.triples.asScala.map { x => (x.fromNode, x.toNode) }

  var index = 0
  var S = new Stack[String]

  // contains the index for vertice [string]
  var indexMap = new HashMap[String, Integer]

  var lowLinkMap = new HashMap[String, Integer]
  var onStackMap = new HashMap[String, Boolean]

  vertices.foreach { x => onStackMap.put(x, false) }
  //  vertices.foreach { x => lowLinkMap.put(x, -1) }
  //  vertices.foreach { x => indexMap.put(x, 999) }

  var sccsResult = List.empty[List[String]]

  def isValid: Boolean = {

    println("stuff")

    println("edges are: " + edges)
    println("vertices are: " + vertices)

    // check self-reference
    if (!edges.forall(x => !x._1.equals(x._2))) {
      print("stuff1")
      return false
    }

    // to tarjan
    for (v <- vertices) {
      if (!indexMap.containsKey(v)) {
        strongconnect(v)
      }
    }
    val result = sccsResult.isEmpty
    print(sccsResult.toList)
    print("stuff2")
    return result
  }

  //  def getSCCs(): List[List[String]] = {
  //    return sccsResult
  //  }

  def strongconnect(v: String) {
    indexMap.put(v, index)
    lowLinkMap.put(v, index)
    index = index + 1
    S.push(v)
    onStackMap.put(v, true)

    for (x <- edges) {

      val a = v
      val w = x._2

      if (edges.contains(a, w)) {
        println(" edge is: " + v + w)
        //      val v = a._1
        //      val w = a._2

        if (!indexMap.containsKey(w)) {
          println("is not defined" + w)
          strongconnect(w)
          lowLinkMap.put(a, Math.min(lowLinkMap.get(a), lowLinkMap.get(w)))
        } else if (onStackMap.get(w)) {
          println("is defined and on stack" + w)
          lowLinkMap.put(a, Math.min(lowLinkMap.get(a), indexMap.get(w)))
        }
      }
    }

    // if v is a root node, pop the stakc and generate an SCC
    if (lowLinkMap.get(v).equals(indexMap.get(v))) {
      // start a new strongly connected component
      var scc = List.empty: List[String]
      var break = true
      while (break) {
        var w = S.pop()
        onStackMap.put(w, false)
        scc = w :: scc
        if (w.equals(v)) {
          break = false;
        }
      }

      //      print("scc is " + scc)
      if (scc.toList.size > 1) {
        sccsResult = scc :: sccsResult
      }
    }
  }
}
