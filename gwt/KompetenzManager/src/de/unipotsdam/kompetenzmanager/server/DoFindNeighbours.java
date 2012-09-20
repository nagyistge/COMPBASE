package de.unipotsdam.kompetenzmanager.server;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;

import de.unipotsdam.kompetenzmanager.server.neo4j.DoNeo;
import de.unipotsdam.kompetenzmanager.shared.Graph;

public class DoFindNeighbours extends DoNeo {

	private String node;

	public DoFindNeighbours(GraphDatabaseService graphDB, Index<Node> nodeIndex, String node) {
		super(graphDB, nodeIndex);
		this.node = node;
	}

	@Override
	public Graph doit() {		
		Iterable<Relationship> relationships = nodeIndex.get(NODE_KEY, node).getSingle().getRelationships();
		return convertRelationShipsToGraph(relationships);		
	}

}
