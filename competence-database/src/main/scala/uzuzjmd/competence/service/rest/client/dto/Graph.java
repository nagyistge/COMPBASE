package uzuzjmd.competence.service.rest.client.dto;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Graph {
	public Set<GraphTriple> triples;
	public Set<GraphNode> nodes;

	private Map<Integer, String> nodeIdValues = new HashMap<Integer, String>();

	public Map<Integer, String> getNodeIdValues() {
		return nodeIdValues;
	}

	public void setNodeIdValues(Map<Integer, String> nodeIdValues) {
		this.nodeIdValues = nodeIdValues;
	}

	public Graph() {
		this.triples = new HashSet<GraphTriple>(10000);
		this.nodes = new HashSet<GraphNode>(10000);
	}

	/**
	 * add new Connection on Graph
	 * 
	 * @param fromNode
	 * @param toNode
	 * @param label
	 * @param directed
	 */
	public void addTriple(String fromNode, String toNode, String label,
			Boolean directed) {
		if (fromNode != null && toNode != null && label != null) {
			GraphTriple graphTriple = new GraphTriple(fromNode, toNode, label,
					directed);
			nodeIdValues.put(fromNode.hashCode(), fromNode);
			nodeIdValues.put(toNode.hashCode(), toNode);
			GraphNode fromNode1 = new GraphNode(fromNode);
			GraphNode toNode1 = new GraphNode(toNode);
			this.triples.add(graphTriple);
			this.nodes.add(fromNode1);
			this.nodes.add(toNode1);
		}
	}

	@Override
	public String toString() {
		String result = "";
		for (GraphTriple triple : triples) {
			result += triple.toString() + "\n";
		}
		result += "\n";
		for (GraphNode graphNode : nodes) {
			result += graphNode.toString() + "\n";
		}
		return result;
	}

	public void mergeWith(Graph graph) {
		if (graph == null) {
			System.err.println("merging null Graph");
		} else {
			this.nodes.addAll(graph.nodes);
			this.triples.addAll(graph.triples);
		}
	}

	public Graph addConnectingTriples(Graph graph) {
		for (GraphTriple graphTriple : graph.triples) {
			if (this.nodes.contains(new GraphNode(graphTriple.fromNode))) {
				this.triples.add(graphTriple);
			}
		}
		return this;
	}

	/**
	 * the input graph is kept
	 * 
	 * @param graph
	 * @return
	 */
	public Graph intersectWith(Graph graph) {
		this.nodes.retainAll(graph.nodes);
		this.nodes.addAll(graph.nodes);
		this.triples.retainAll(graph.triples);
		this.triples.addAll(graph.triples);
		return this;
	}

	public void removeNode(GraphNode targetNode) {
		this.nodes.remove(targetNode);
		Iterator<GraphTriple> it = this.triples.iterator();
		GraphTriple graphTriple = null;
		Collection<GraphTriple> toRemove = new HashSet<GraphTriple>();
		while (it.hasNext())
			graphTriple = it.next();
		if (graphTriple.fromNode.equals(targetNode.getLabel())
				|| graphTriple.toNode.equals(targetNode.getLabel())) {
			toRemove.add(graphTriple);
		}
		this.triples.removeAll(toRemove);
	}

}
