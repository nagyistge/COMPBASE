package de.unipotsdam.kompetenzmanager.server;

import de.unipotsdam.kompetenzmanager.client.GraphBackend;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoAddNode;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoFullGraph;
import de.unipotsdam.kompetenzmanager.server.neo4j.Neo4JStarter;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;

public class Neo4JGraphBackendImpl implements GraphBackend {

		

	@Override
	public synchronized Graph getFullGraph() {
		Neo4JStarter neo = new Neo4JStarter();
		Graph graph = neo.doQuery(new DoFullGraph(neo.getGraphDB(), neo.getNodeIndex()));
//		neo.shutdown();
		return graph;
		
	}

	@Override
	public synchronized Graph addNode(GraphNode sourceNode, GraphNode newNode,
			String kantenLabel) {
		Neo4JStarter neo = new Neo4JStarter();
		Graph graph = neo.doQuery(new DoAddNode(neo.getGraphDB(), neo.getNodeIndex(),
				sourceNode, newNode, kantenLabel));
//		neo.shutdown();
		return getFullGraph();
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
	
	public void shutdown() {
		new Neo4JStarter().shutdown();
	}

}
