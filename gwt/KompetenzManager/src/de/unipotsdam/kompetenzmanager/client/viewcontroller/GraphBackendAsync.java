package de.unipotsdam.kompetenzmanager.client.viewcontroller;

import java.util.Collection;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphLiteraturePair;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;
import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public interface GraphBackendAsync {
	void getFullGraph(AsyncCallback<Graph> graph);	
	void addNode(GraphNode sourceNode, GraphNode newNode, String kantenLabel, AsyncCallback<Graph> callback);
	void findShortestPath(String keyword, AsyncCallback<Graph> graph);
	void removeNode(GraphNode targetNode, AsyncCallback<Graph> callback);
	void findShortestPath(String fromNode, String toNode, AsyncCallback<Graph> graphUpdater);
	void expandNode(String nodeName, AsyncCallback<Graph> graphUpdater);	
	void addNode(Graph graph, GraphNode sourceNode, GraphNode newNode, String kantenLabel, AsyncCallback<Graph> callback);
	void findShortestPath(Graph graph, String keyword, AsyncCallback<Graph> callback);
	void removeNode(Graph graph, GraphNode targetNode, AsyncCallback<Graph> callback);
	void findShortestPath(Graph graph, String fromNode, String toNode, AsyncCallback<Graph> graphUpdater);
	void expandNode(Graph graph, String nodeName, AsyncCallback<Graph> graphUpdater);
	void connectNodes(Collection<String> graphNodes, String toNode, AsyncCallback<Graph> graphUpdater );
	void connectNodes(Graph graph, Collection<String> graphNodes, String toNode, AsyncCallback<Graph> graphUpdater );
	void getFullLiterature(AsyncCallback<Literature> literature);
	void getLiteratureForTags(Graph graph, AsyncCallback<Literature> literature);
	void getTagsforLiterature(Literature literature, AsyncCallback<Graph> graph);
	void addOrUpdateLiteratureEntry(Literature storedLiterature, LiteratureEntry literatureEntry, AsyncCallback<Literature> literature);	
	void removeLiteratureEntry(Literature storedLiterature, LiteratureEntry literatureEntry, AsyncCallback<Literature> lierature);
	void connectLiteratureToGraph(Literature storedLiterature, Graph storedGraph, AsyncCallback<GraphLiteraturePair> callback);
}
