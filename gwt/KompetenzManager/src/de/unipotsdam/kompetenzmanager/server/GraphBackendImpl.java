package de.unipotsdam.kompetenzmanager.server;

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

	@Override
	public Graph getFullGraph() {
		Graph result = new Graph();
		result.addTriple("Idee1", "Idee2", "connection", true);
		return result;		
	}

	@Override
	public Graph addNode(GraphNode sourceNode, GraphNode newNode,
			String kantenLabel) {
		Graph result = new Graph();
		result.addTriple("Idee1", "Idee2", "connection", true);
		result.nodes.add(new GraphNode("node"));
		return null;
	}

	@Override
	public Graph findShortestPath(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
