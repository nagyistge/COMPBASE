package de.unipotsdam.kompetenzmanager.client;

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
}
