package de.unipotsdam.kompetenzmanager.server;

import java.io.IOException;
import java.util.Collection;

import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphBackend;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphLiteraturePair;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;
import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class MixedGraphBackend implements GraphBackend {
	
	private Neo4JGraphBackendImpl neo4jbackendimpl;
	private MysqlBackend mysqlbackendimpl;

	public MixedGraphBackend() {
		try {
			this.neo4jbackendimpl = new Neo4JGraphBackendImpl();
			this.mysqlbackendimpl = new MysqlBackend();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	

	@Override
	public Graph getFullGraph() {
		return neo4jbackendimpl.getFullGraph();
	}

	@Override
	public Graph addNode(GraphNode sourceNode, GraphNode newNode,
			String kantenLabel) {		
		return neo4jbackendimpl.addNode(sourceNode, newNode, kantenLabel);
	}

	@Override
	public Graph findShortestPath(String keyword) {
		return neo4jbackendimpl.findShortestPath(keyword);
	}

	@Override
	public Graph removeNode(GraphNode targetNode) {
		return neo4jbackendimpl.removeNode(targetNode);
	}

	@Override
	public Graph findShortestPath(String fromNode, String toNode) {
		return neo4jbackendimpl.findShortestPath(fromNode, toNode);
	}

	@Override
	public Graph expandNode(String nodeName) {
		return neo4jbackendimpl.expandNode(nodeName);
	}

	@Override
	public Graph addNode(Graph graph, GraphNode sourceNode, GraphNode newNode,
			String kantenLabel) {
		return neo4jbackendimpl.addNode(sourceNode, newNode, kantenLabel);
	}

	@Override
	public Graph findShortestPath(Graph graph, String keyword) {
		return neo4jbackendimpl.findShortestPath(graph, keyword);
	}

	@Override
	public Graph removeNode(Graph graph, GraphNode targetNode) {
		return neo4jbackendimpl.removeNode(graph, targetNode);
	}

	@Override
	public Graph findShortestPath(Graph graph, String fromNode, String toNode) {
		return neo4jbackendimpl.findShortestPath(graph, fromNode, toNode);
	}

	@Override
	public Graph expandNode(Graph graph, String nodeName) {
		return neo4jbackendimpl.expandNode(graph, nodeName);
	}

	@Override
	public Graph connectNodes(Collection<String> graphNodes, String toNode) {
		return neo4jbackendimpl.connectNodes(graphNodes, toNode);
	}

	@Override
	public Graph connectNodes(Graph graph, Collection<String> graphNodes,
			String toNode) {
		return neo4jbackendimpl.connectNodes(graph, graphNodes, toNode);
	}

	@Override
	public Literature getFullLiterature() {
		return mysqlbackendimpl.getFullLiterature();
	}

	@Override
	public Literature getLiteratureForTags(Graph graph) {
		return mysqlbackendimpl.getLiteratureForTags(graph);
	}

	@Override
	public Graph getTagsforLiterature(Literature literature) {
		return neo4jbackendimpl.getTagsforLiterature2(literature);
	}

	@Override
	public Literature addOrUpdateLiteratureEntry(Literature storedLiterature,
			LiteratureEntry literatureEntry) {
		return mysqlbackendimpl.addOrUpdateLiteratureEntry(storedLiterature, literatureEntry);
	}

	@Override
	public Literature removeLiteratureEntry(Literature storedLiterature,
			LiteratureEntry literatureEntry) {
		return mysqlbackendimpl.removeLiteratureEntry(storedLiterature, literatureEntry);
	}

	@Override
	public GraphLiteraturePair connectLiteratureToGraph(Literature literature,
			Graph graph) {
		return mysqlbackendimpl.connectLiteratureToGraph(literature, graph);
	}

}
