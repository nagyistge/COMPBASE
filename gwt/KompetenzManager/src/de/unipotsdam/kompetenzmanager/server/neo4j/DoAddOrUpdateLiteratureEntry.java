package de.unipotsdam.kompetenzmanager.server.neo4j;

import java.util.UUID;

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
			int id = UUID.randomUUID().hashCode();
			if (literatureEntry.id != 0) {
				id = literatureEntry.id;
			}
			node.setProperty(LIT_NODE_KEY, id);
			this.nodeIndex.add(node, LIT_NODE_KEY, id);
			createRelationShip(node);
		}
		else {
			node = this.nodeIndex.get(LIT_NODE_KEY, literatureEntry.id).getSingle();
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
		node.setProperty(LIT_NODE_PAPER, literatureEntry.paper);
		node.setProperty(LIT_NODE_VOLUME, literatureEntry.volume);
	}

	private boolean existsNode() {
		return this.nodeIndex.get(LIT_NODE_KEY, literatureEntry.id).hasNext();		
	}

}
