package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.Literature;

public class DoAddLiteratureRoot extends DoNeoLit {

	public DoAddLiteratureRoot(GraphDatabaseService graphDB,
			Index<Node> nodeIndex, RelationshipIndex relIndex) {
		super(graphDB, nodeIndex, relIndex);
	}

	@Override
	public Literature dolit() {
		if (!exists()) {
			Node node = this.graphDb.createNode();
			node.setProperty(LIT_ROOT_NODE, LIT_ROOT_VALUE);
			node.setProperty(LIT_SHORT_TITLE, "Literatur");
			this.nodeIndex.add(node, LIT_ROOT_NODE, LIT_ROOT_VALUE);
		}
		return null;
	}

	private boolean exists() {
		return this.nodeIndex.get(LIT_ROOT_NODE, LIT_ROOT_VALUE).hasNext();
	}

}
