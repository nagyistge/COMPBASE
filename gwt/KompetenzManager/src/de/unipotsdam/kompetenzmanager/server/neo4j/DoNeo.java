package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;

public abstract class DoNeo implements Do {
	protected GraphDatabaseService graphDb;
	protected Index<Node> nodeIndex;
	public static final String NODE_KEY = "nodename";
	public static final String REL_KEY = "relname";

	public DoNeo(GraphDatabaseService graphDB, Index<Node> nodeIndex) {
		this.graphDb = graphDB;
		this.nodeIndex = nodeIndex;
	}

	protected Node createAndIndexNode(String label) {
		if (!nodeIndex.get(NODE_KEY, label).hasNext()) {
			Node node = graphDb.createNode();
			node.setProperty(NODE_KEY, label);
			node.setProperty("label", label);
			nodeIndex.add(node, NODE_KEY, label);
			return node;
		} else {
			return nodeIndex.get(NODE_KEY, label).getSingle();
		}
	}
	
	protected void connectNodes(Node nodeFrom, Node nodeTo, String label) {
		Relationship relationship = nodeFrom.createRelationshipTo(nodeTo, RelTypes.assoziatedWith);
		relationship.setProperty("label", label );
		relationship.setProperty(REL_KEY, label);
	}
}
