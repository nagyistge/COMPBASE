package de.unipotsdam.kompetenzmanager.server;

import de.unipotsdam.kompetenzmanager.client.GraphBackend;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoAddNode;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoFullGraph;
import de.unipotsdam.kompetenzmanager.server.neo4j.Neo4JStarter;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;

public class Neo4JGraphBackendImpl implements GraphBackend {

	private Neo4JStarter neo;

	public Neo4JGraphBackendImpl() {
		this.neo = new Neo4JStarter();
	}

	@Override
	public Graph getFullGraph() {
		return neo
				.doQuery(new DoFullGraph(neo.getGraphDB(), neo.getNodeIndex()));
	}

	@Override
	public Graph addNode(GraphNode sourceNode, GraphNode newNode,
			String kantenLabel) {
		this.neo.doQuery(new DoAddNode(neo.getGraphDB(), neo.getNodeIndex(),
				sourceNode, newNode, kantenLabel));
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
		this.neo.getGraphDB().shutdown();
	}
}
