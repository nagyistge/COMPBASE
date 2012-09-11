package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.util.QueryUtil;

public abstract class DoNeo implements Do {
	protected GraphDatabaseService graphDb;
	protected Index<Node> nodeIndex;
	protected QueryUtil queryUtil;
	protected int maxDepth;
	public static final String NODE_KEY = "nodename";
	public static final String REL_KEY = "relname";
	public static final String TABLE_KEY = "tablename";

	public DoNeo(GraphDatabaseService graphDB, Index<Node> nodeIndex) {
		this.graphDb = graphDB;
		this.nodeIndex = nodeIndex;
		this.queryUtil = new QueryUtil();
		this.maxDepth = 10;
	}

	protected Node createAndIndexNode(String label) {
		if (!nodeIndex.get(NODE_KEY, label).hasNext()) {
			Node node = graphDb.createNode();
			node.setProperty(NODE_KEY, label);
			node.setProperty("label", label);
			nodeIndex.add(node, NODE_KEY, label);
			getTableNode().setProperty(label, label);
			return node;
		} else {
			return nodeIndex.get(NODE_KEY, label).getSingle();
		}
	}
	
	protected Node getTableNode() {
		return this.nodeIndex.get(TABLE_KEY, "nodetable").getSingle();
	}
	
	protected void connectNodes(Node nodeFrom, Node nodeTo, String label) {
		Relationship relationship = nodeFrom.createRelationshipTo(nodeTo, RelTypes.assoziatedWith);
		relationship.setProperty("label", label );
		relationship.setProperty(REL_KEY, label);
	}
	protected Graph convertRelationShipsToGraph(
			Iterable<Relationship> relationships) {
		Graph result = new Graph();
		for (Relationship rel : relationships) {
			result.addTriple((String)rel.getStartNode().getProperty(NODE_KEY),(String) rel.getEndNode().getProperty(NODE_KEY), (String) rel.getProperty(REL_KEY), true);
		}
		return result;
	}	
}
