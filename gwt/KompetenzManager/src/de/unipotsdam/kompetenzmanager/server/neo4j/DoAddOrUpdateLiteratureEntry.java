package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class DoAddOrUpdateLiteratureEntry extends DoNeoLit {


	private LiteratureEntry literatureEntry;

	public DoAddOrUpdateLiteratureEntry(GraphDatabaseService graphDB,
			Index<Node> nodeIndex, RelationshipIndex relationshipIndex,
			LiteratureEntry literatureEntry) {
		super(graphDB, nodeIndex, relationshipIndex);
		this.literatureEntry = literatureEntry;
	}

	@Override
	public Literature dolit() {
		Node node = null;
		if (!existsNode()) {
			node = graphDb.createNode();
			this.nodeIndex.add(node, LIT_NODE_KEY, literatureEntry.hashCode());
			createRelationShip(node);
		}
		else {
			node = this.nodeIndex.get(LIT_NODE_KEY, literatureEntry.hashCode()).getSingle();
		}
		updateLitNode(node);
		return null;
	}

	private void createRelationShip(Node node) {
		Node superNode = this.nodeIndex.get(LIT_ROOT_NODE, LIT_ROOT_VALUE).getSingle();
		connectNodes(node, superNode, "", RelTypes.litSubclassOf);		
	}

	private void updateLitNode(Node node) {				
		node.setProperty(LIT_NODE_KEY, this.literatureEntry.hashCode());
		node.setProperty(LIT_NODE_ABSTRACT, literatureEntry.abstractText);
		node.setProperty(LIT_NODE_AUTHOR, literatureEntry.author);
		node.setProperty(LIT_NODE_TITEL, literatureEntry.titel);
		node.setProperty(LIT_NODE_YEAR, literatureEntry.year);
	}

	private boolean existsNode() {
		return this.nodeIndex.get(LIT_NODE_KEY, literatureEntry.hashCode()).hasNext();		
	}

}
