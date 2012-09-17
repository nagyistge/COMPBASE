package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ReturnableEvaluator;
import org.neo4j.graphdb.StopEvaluator;
import org.neo4j.graphdb.Traverser;
import org.neo4j.graphdb.Traverser.Order;
import org.neo4j.graphdb.index.Index;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;
import de.unipotsdam.kompetenzmanager.shared.GraphTriple;
import de.unipotsdam.kompetenzmanager.shared.util.QueryUtil;

public abstract class DoNeo implements Do {
	protected GraphDatabaseService graphDb;
	protected Index<Node> nodeIndex;
	protected QueryUtil queryUtil;
	protected int maxDepth;
	public static final String NODE_KEY = "nodename";
	public static final String REL_KEY = "relname";
	public static final String TABLE_KEY = "tablename";
	public static final String REL_VALUE = "label";

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
	
	protected Node getRelNode() {
		return this.nodeIndex.get(TABLE_KEY, "reltable").getSingle();
	}
	
	protected void connectNodes(Node nodeFrom, Node nodeTo, String label, RelTypes relType) {
		Relationship relationship = nodeFrom.createRelationshipTo(nodeTo, relType);
		String index = createRelIndex(nodeFrom, nodeTo, label, relType);
		relationship.setProperty("label", label);
		relationship.setProperty(REL_KEY, index);
//		getRelNode().setProperty(index, index);
	}
	private String createRelIndex(Node nodeFrom, Node nodeTo, String label, RelTypes relTypes) {
		return nodeFrom.getProperty(NODE_KEY).toString()+nodeTo.getProperty(NODE_KEY).toString() + relTypes +label;
	}
		
	protected Graph convertRelationShipsToGraph(
			Relationship ... relationships) {
		Graph result = new Graph();
		for (Relationship rel : relationships) {
			result.addTriple((String)rel.getStartNode().getProperty(NODE_KEY),(String) rel.getEndNode().getProperty(NODE_KEY), (String) rel.getProperty(REL_VALUE), true);
		}
		return result;
	}
	
	
	protected Traverser createTraverser(StopEvaluator stopEvaluator,
			ReturnableEvaluator returnableEvaluator, RelTypes toTraverse) {
		Traverser traverserAssociatedWith = this.nodeIndex
				.get(NODE_KEY, "rootnode")
				.getSingle()
				.traverse(Order.DEPTH_FIRST, stopEvaluator,
						returnableEvaluator,toTraverse,
						Direction.BOTH);
		return traverserAssociatedWith;
	}

	protected Graph traverseGraph(Traverser traverser) {
		Graph result = new Graph();		
		while (traverser.iterator().hasNext()) {									
			Node currentNode = traverser.iterator().next();	
			Relationship relationShip = traverser.currentPosition()
					.lastRelationshipTraversed();
			if (traverser.currentPosition().notStartNode()) {
//				String toNode = (String) currentNode.getProperty(NODE_KEY);
//				result.nodes.add(new GraphNode(toNode));
//				String kantenLabel = (String) relationShip.getProperty("REL_VALUE");
//				String fromNode = (String) relationShip.getOtherNode(
//						currentNode).getProperty(NODE_KEY);				
//				Boolean directed = relationShip.isType(RelTypes.subclassOf);
//				result.triples.add(new GraphTriple(fromNode, toNode,
//						kantenLabel, directed));
				result.mergeWith(convertRelationShipsToGraph(relationShip));
			}			
		}
		result.nodes.add(new GraphNode("rootnode"));
		return result;
	}
}
