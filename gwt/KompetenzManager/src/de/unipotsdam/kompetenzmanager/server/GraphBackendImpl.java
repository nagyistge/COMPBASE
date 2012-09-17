package de.unipotsdam.kompetenzmanager.server;

import java.util.Collection;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.unipotsdam.kompetenzmanager.client.GraphBackend;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;

/**
 * 
 * @author Julian
 *	TODO @Hendrik please implement with OWL libraries
 */
@SuppressWarnings("serial")
public class GraphBackendImpl extends RemoteServiceServlet implements
GraphBackend {

	private GraphBackendFactory factory;

	public GraphBackendImpl() {
		this.factory = new GraphBackendFactory();
	}
	
	@Override
	public Graph getFullGraph() {
		return factory.createInstance().getFullGraph();
	}

	@Override
	public Graph addNode(GraphNode sourceNode, GraphNode newNode,
			String kantenLabel) {
		return factory.createInstance().addNode(sourceNode, newNode, kantenLabel);
	}

	@Override
	public Graph findShortestPath(String keyword) {
		return factory.createInstance().findShortestPath(keyword);
	}

	@Override
	public Graph removeNode(GraphNode targetNode) {
		return factory.createInstance().removeNode(targetNode);
	}

	@Override
	public Graph findShortestPath(String fromNode, String toNode) {
		return factory.createInstance().findShortestPath(fromNode, toNode);
	}

	@Override
	public Graph expandNode(String nodeName) {
		return factory.createInstance().expandNode(nodeName);
	}

	@Override
	public Graph addNode(Graph graph, GraphNode sourceNode, GraphNode newNode,
			String kantenLabel) {
		return factory.createInstance().addNode(graph, sourceNode, newNode, kantenLabel);
	}

	@Override
	public Graph findShortestPath(Graph graph, String keyword) {
		return factory.createInstance().findShortestPath(graph, keyword);
	}

	@Override
	public Graph removeNode(Graph graph, GraphNode targetNode) {
		return factory.createInstance().removeNode(graph, targetNode);
	}

	@Override
	public Graph findShortestPath(Graph graph, String fromNode, String toNode) {
		return factory.createInstance().findShortestPath(graph, fromNode, toNode);
	}

	@Override
	public Graph expandNode(Graph graph, String nodeName) {
		return factory.createInstance().expandNode(graph, nodeName);
	}

	@Override
	public Graph connectNodes(Collection<String> graphNodes, String toNode) {
		return factory.createInstance().connectNodes(graphNodes, toNode);
	}

	@Override
	public Graph connectNodes(Graph graph, Collection<String> graphNodes,
			String toNode) {
		return factory.createInstance().connectNodes(graph, graphNodes, toNode);
	}

}
