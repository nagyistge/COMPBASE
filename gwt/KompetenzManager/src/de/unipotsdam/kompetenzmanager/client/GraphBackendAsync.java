package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;

public interface GraphBackendAsync {
	void getFullGraph(AsyncCallback<Graph> graph);	
	void addNode(GraphNode sourceNode, GraphNode newNode, String kantenLabel, AsyncCallback<Graph> callback);
	void findShortestPath(String keyword, AsyncCallback<Graph> graph);
}
