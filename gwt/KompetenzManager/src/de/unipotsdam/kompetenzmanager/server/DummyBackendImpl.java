package de.unipotsdam.kompetenzmanager.server;

import de.unipotsdam.kompetenzmanager.client.GraphBackend;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;

public class DummyBackendImpl implements GraphBackend{

	@Override
	public Graph getFullGraph() {
		Graph result = new Graph();
		result.addTriple("Idee1", "Idee2", "connection", false);
		return result;	
	}

	@Override
	public Graph addNode(GraphNode sourceNode, GraphNode newNode,
			String kantenLabel) {
		Graph result = new Graph();
		result.addTriple("Idee1", "Idee2", "connection", true);				
		result.addTriple(sourceNode.label, newNode.label, kantenLabel, true);
		return result;
	}

	@Override
	public Graph findShortestPath(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph removeNode(GraphNode targetNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph findShortestPath(String fromNode, String toNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph expandNode(String nodeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph addNode(Graph graph, GraphNode sourceNode, GraphNode newNode,
			String kantenLabel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph findShortestPath(Graph graph, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph removeNode(Graph graph, GraphNode targetNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph findShortestPath(Graph graph, String fromNode, String toNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph expandNode(Graph graph, String nodeName) {
		// TODO Auto-generated method stub
		return null;
	}

}
