package de.unipotsdam.kompetenzmanager.server.neo4j;

import java.util.HashSet;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;
import de.unipotsdam.kompetenzmanager.shared.util.QueryUtil;

public abstract class DoNeo {
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
	public static final String LIT_NODE_KEY = "literaturenode";
	public static final String LIT_NODE_AUTHOR = "author";
	public static final String LIT_NODE_YEAR = "date";
	public static final String LIT_NODE_ABSTRACT = "abstract";
	public static final String LIT_NODE_TITEL = "title";
	public static final String LIT_ROOT_NODE = "root";
	public static final String LIT_SHORT_TITLE = "shorttitle";
	public static final String LIT_ROOT_VALUE = "litroot";
	public static final String LIT_NODE_PAPER = "litPAPER";
	public static final String LIT_NODE_VOLUME = "litvolume";

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
		return nodeFrom.getPropertyValues().iterator().next().toString()
				+ relTypes + label;
	}

	protected Literature convertRelationShipToLiteratureEntry(
			Iterable<Relationship> iterable) {
		Literature result = new Literature();
		for (Relationship rel : iterable) {
			result.literatureEntries.add(convertLitNodeToLitEntry(rel
					.getStartNode()));
		}
		return result;
	}

	protected LiteratureEntry convertLitNodeToLitEntry(Node node) {
		if (node.hasProperty(LIT_NODE_KEY)) {
			LiteratureEntry literatureEntry = new LiteratureEntry(
					(String) node.getProperty(LIT_NODE_TITEL),
					(String) node.getProperty(LIT_NODE_AUTHOR),
					(Integer) node.getProperty(LIT_NODE_YEAR),
					(String) node.getProperty(LIT_NODE_ABSTRACT),
					(String) node.getProperty(LIT_NODE_PAPER),
					(String) node.getProperty(LIT_NODE_VOLUME),
					(Integer) node.getProperty(LIT_NODE_KEY));
			Literature literature = new Literature(literatureEntry);
			DoGetTagsForLit doGetTagsForLit = new DoGetTagsForLit(this.graphDb,
					nodeIndex, relIndex, literature);
			Graph associatedTags = doGetTagsForLit.doit();
			literatureEntry.setGraph(associatedTags);
			return literatureEntry;
		} else {
			throw new Error(
					"diese Methode wurde mit einer falschen Node aufgerufen"
							+ node.getProperty(LIT_ROOT_NODE));
		}
	}

	protected Node getLitNode(LiteratureEntry literatureEntry) {
		return this.nodeIndex.get(LIT_NODE_KEY, literatureEntry.hashCode())
				.getSingle();
	}
}
