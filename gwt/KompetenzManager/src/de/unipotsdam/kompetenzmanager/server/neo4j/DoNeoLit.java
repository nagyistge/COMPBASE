package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

public abstract class DoNeoLit extends DoNeo implements DoLit {

	public DoNeoLit(GraphDatabaseService graphDB, Index<Node> nodeIndex,
			RelationshipIndex relIndex) {
		super(graphDB, nodeIndex, relIndex);		
	}
}
