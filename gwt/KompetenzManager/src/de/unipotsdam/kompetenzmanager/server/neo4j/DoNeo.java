package de.unipotsdam.kompetenzmanager.server.neo4j;


import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.util.QueryUtil;

public abstract class DoNeo  {
	protected GraphDatabaseService graphDb;
	protected Index<Node> nodeIndex;
	protected QueryUtil queryUtil;
	protected int maxDepth;
	protected RelationshipIndex relIndex;
	public static final String NODE_KEY = "nodename";
	public static final String REL_KEY = "relname";
	public static final String TABLE_KEY = "tablename";
	public static final String REL_VALUE = "label";
	public static final String NODE_VALUE = "label";
	public static final String LIT_NODE_KEY ="literaturenode";
	public static final String LIT_NODE_AUTHOR ="author";
	public static final String LIT_NODE_YEAR ="date";
	public static final String LIT_NODE_ABSTRACT ="abstract";
	public static final String LIT_NODE_TITEL ="title";
	public static final String LIT_ROOT_NODE ="root";
	public static final String LIT_SHORT_TITLE ="shorttitle";
	public static final String LIT_ROOT_VALUE = "litroot";

	public DoNeo(GraphDatabaseService graphDB, Index<Node> nodeIndex,
			RelationshipIndex relIndex) {
		this.graphDb = graphDB;
		this.nodeIndex = nodeIndex;
		this.queryUtil = new QueryUtil();
		this.maxDepth = 10;
		this.relIndex = relIndex;
	}

	protected Node createAndIndexNode(String label) {
		if (!nodeIndex.get(NODE_KEY, label).hasNext()) {
			Node node = graphDb.createNode();
			node.setProperty(NODE_KEY, label);
			node.setProperty(NODE_VALUE, label);
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

	protected Node getRelNode() {
		return this.nodeIndex.get(TABLE_KEY, "reltable").getSingle();
	}

	protected void connectNodes(Node nodeFrom, Node nodeTo, String label,
			RelTypes relType) {
		Relationship relationship = nodeFrom.createRelationshipTo(nodeTo,
				relType);
		String index = createRelIndex(nodeFrom, nodeTo, label, relType);
		if (!relIndex.get(REL_KEY, index).hasNext()) {
			relationship.setProperty(NODE_VALUE, label);
			relationship.setProperty(REL_KEY, index);
			getRelNode().setProperty(index, index);
			this.relIndex.add(relationship, REL_KEY, index);
		}

	}

	protected String createRelIndex(Node nodeFrom, Node nodeTo, String label,
			RelTypes relTypes) {
		return nodeFrom.getProperty(NODE_KEY).toString()
				+ nodeTo.getProperty(NODE_KEY).toString() + relTypes + label;
	}
}
