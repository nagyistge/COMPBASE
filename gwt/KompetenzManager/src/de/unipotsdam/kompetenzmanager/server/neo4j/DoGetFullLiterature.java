package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.Literature;

public class DoGetFullLiterature extends DoNeoLit {

	public DoGetFullLiterature(GraphDatabaseService graphDB,
			Index<Node> nodeIndex, RelationshipIndex relIndex) {
		super(graphDB, nodeIndex, relIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Literature dolit() {
		// TODO Auto-generated method stub
		return null;
	}

}
