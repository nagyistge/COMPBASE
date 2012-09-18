package de.unipotsdam.kompetenzmanager.server.neo4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.NotFoundException;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ReturnableEvaluator;
import org.neo4j.graphdb.StopEvaluator;
import org.neo4j.graphdb.Traverser;
import org.neo4j.graphdb.Traverser.Order;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;

public abstract class DoNeoGraph extends DoNeo implements Do {

	public DoNeoGraph(GraphDatabaseService graphDB, Index<Node> nodeIndex,
			RelationshipIndex relIndex) {
		super(graphDB, nodeIndex, relIndex);		
	}

	@Override
	public Graph doit() {
		// TODO Auto-generated method stub
		return null;
	}

	protected Graph convertRelationShipsToGraph(Relationship... relationships) {
		Graph result = new Graph();
		for (Relationship rel : relationships) {
			String kantenlabel = "";
			try {
			kantenlabel =  (String) rel.getProperty(REL_VALUE);
			} catch (NotFoundException e) {				
			}
			result.addTriple((String) rel.getStartNode().getProperty(NODE_KEY),
					(String) rel.getEndNode().getProperty(NODE_KEY),
					kantenlabel, true);
		}
		return result;
	}

	protected Traverser createTraverser(StopEvaluator stopEvaluator, ReturnableEvaluator returnableEvaluator, RelTypes toTraverse) {
		Traverser traverserAssociatedWith = this.nodeIndex
				.get(NODE_KEY, "rootnode")
				.getSingle()
				.traverse(Order.DEPTH_FIRST, stopEvaluator,
						returnableEvaluator, toTraverse, Direction.BOTH);
		return traverserAssociatedWith;
	}

	protected Graph traverseGraph(Traverser traverser) {
		Graph result = new Graph();
		while (traverser.iterator().hasNext()) {
			Node currentNode = traverser.iterator().next();
			Relationship relationShip = traverser.currentPosition()
					.lastRelationshipTraversed();
			if (traverser.currentPosition().notStartNode()) {
				// String toNode = (String) currentNode.getProperty(NODE_KEY);
				// result.nodes.add(new GraphNode(toNode));
				// String kantenLabel = (String)
				// relationShip.getProperty("REL_VALUE");
				// String fromNode = (String) relationShip.getOtherNode(
				// currentNode).getProperty(NODE_KEY);
				// Boolean directed = relationShip.isType(RelTypes.subclassOf);
				// result.triples.add(new GraphTriple(fromNode, toNode,
				// kantenLabel, directed));
				result.mergeWith(convertRelationShipsToGraph(relationShip));
			}
		}
		result.nodes.add(new GraphNode("rootnode"));
		return result;
	}

	protected Relationship[] convertIteratorToList(Iterable<Relationship> relationships) {
		List<Relationship> result = new ArrayList<Relationship>();
		Iterator<Relationship> it = relationships.iterator();
		while (it.hasNext()) {
			result.add(it.next());
		}
		return (Relationship[]) result.toArray(new Relationship[result.size()]);
	}

}
