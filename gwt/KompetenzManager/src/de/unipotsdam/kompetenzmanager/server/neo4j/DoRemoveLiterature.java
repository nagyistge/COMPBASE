package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class DoRemoveLiterature extends DoNeoLit {

	private LiteratureEntry literatureEntry;

	public DoRemoveLiterature(GraphDatabaseService graphDB,
			Index<Node> nodeIndex, RelationshipIndex relIndex, LiteratureEntry literatureEntry) {
		super(graphDB, nodeIndex, relIndex);
		this.literatureEntry = literatureEntry;
	}

	@Override
	public Literature dolit() {
		Node node = getLitNode(this.literatureEntry);
		for (Relationship rel : node.getRelationships()) {
			rel.delete();
			this.relIndex.remove(rel);
		}
		node.delete();
		this.nodeIndex.remove(node);		
		return null;
	}

}
