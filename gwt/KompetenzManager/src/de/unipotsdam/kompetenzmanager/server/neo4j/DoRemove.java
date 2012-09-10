package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;

import de.unipotsdam.kompetenzmanager.shared.Graph;

public class DoRemove extends DoNeo {

	private String label;

	public DoRemove(GraphDatabaseService graphDB, Index<Node> nodeIndex, String label) {
		super(graphDB, nodeIndex);		
		this.label = label;
	}

	@Override
	public Graph doit() {
		Node node = this.nodeIndex.get(NODE_KEY, label).getSingle();
		for (Relationship rel : node.getRelationships()) {
			rel.delete();			
		}		
		node.delete();
		this.nodeIndex.remove(node);
		return null;
	}

}
