package de.unipotsdam.kompetenzmanager.server;

import com.google.gwt.user.server.rpc.UnexpectedException;

import de.unipotsdam.kompetenzmanager.client.GraphBackend;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoAddNode;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoFullGraph;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoRemove;
import de.unipotsdam.kompetenzmanager.server.neo4j.Neo4JStarter;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;

public class Neo4JGraphBackendImpl implements GraphBackend {

		

	private Neo4JStarter neo;
	
	public Neo4JGraphBackendImpl() {
		this.neo = new Neo4JStarter();
	}

	@Override
	public synchronized Graph getFullGraph() {		
		Graph graph = null;
		try {
			graph = neo.doQuery(new DoFullGraph(neo.getGraphDB(), neo.getNodeIndex()));
		} catch(UnexpectedException e) {
			e.printStackTrace();
			shutdown();			
		}
		return graph;
		
	}

	@Override
	public synchronized Graph addNode(GraphNode sourceNode, GraphNode newNode,
			String kantenLabel) {		
		Graph graph = neo.doQuery(new DoAddNode(neo.getGraphDB(), neo.getNodeIndex(),
				sourceNode, newNode, kantenLabel));
		return getFullGraph();
	}

	@Override
	public synchronized Graph findShortestPath(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized Graph removeNode(GraphNode targetNode) {
		 this.neo.doQuery(new DoRemove(neo.getGraphDB(), neo.getNodeIndex(), targetNode.label));		 
		 return getFullGraph();
	}
	
	public void shutdown() {
		new Neo4JStarter().shutdown();
	}

}
