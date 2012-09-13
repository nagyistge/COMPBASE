package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;

import de.unipotsdam.kompetenzmanager.shared.Graph;

public class DoFindNeighbours extends DoNeo {

	//TODO remove this class when merging changes
	public DoFindNeighbours(GraphDatabaseService graphDB, Index<Node> nodeIndex, String nodeLabel) {
		super(graphDB, nodeIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Graph doit() {
		// TODO Auto-generated method stub
		return null;
	}

}
