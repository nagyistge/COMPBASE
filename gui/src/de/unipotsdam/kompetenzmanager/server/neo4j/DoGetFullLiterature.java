package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.Literature;

public class DoGetFullLiterature extends DoNeoLit {

	public DoGetFullLiterature(GraphDatabaseService graphDB,
			Index<Node> nodeIndex, RelationshipIndex relIndex) {
		super(graphDB, nodeIndex, relIndex);		
	}

	@Override
	public Literature dolit() {
		Node rootNode = this.getLitRootNode();
		Iterable<Relationship> iterable = rootNode.getRelationships();		
		return convertRelationShipToLiteratureEntry(iterable);		
	}

}
