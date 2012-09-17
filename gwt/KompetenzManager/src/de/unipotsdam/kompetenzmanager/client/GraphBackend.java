package de.unipotsdam.kompetenzmanager.client;

import java.util.Collection;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.RemoteService;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;

@RemoteServiceRelativePath("graph")
public interface GraphBackend extends RemoteService {
	public Graph getFullGraph();	
	public Graph addNode(GraphNode sourceNode, GraphNode newNode, String kantenLabel);
	public Graph findShortestPath(String keyword);
	public Graph removeNode(GraphNode targetNode);
	public Graph findShortestPath(String fromNode, String toNode);
	public Graph expandNode(String nodeName);
	Graph addNode(Graph graph, GraphNode sourceNode, GraphNode newNode,
			String kantenLabel);
	Graph findShortestPath(Graph graph, String keyword);
	Graph removeNode(Graph graph, GraphNode targetNode);
	Graph findShortestPath(Graph graph, String fromNode, String toNode);
	Graph expandNode(Graph graph, String nodeName);
	Graph connectNodes(Collection<String> graphNodes, String toNode);
	Graph connectNodes(Graph graph, Collection<String> graphNodes,
			String toNode);
}
