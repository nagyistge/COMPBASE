package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ReturnableEvaluator;
import org.neo4j.graphdb.StopEvaluator;
import org.neo4j.graphdb.TraversalPosition;
import org.neo4j.graphdb.Traverser;
import org.neo4j.graphdb.Traverser.Order;
import org.neo4j.graphdb.index.Index;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;
import de.unipotsdam.kompetenzmanager.shared.GraphTriple;

public class DoFullGraph extends DoNeo {

	public DoFullGraph(GraphDatabaseService graphDB, Index<Node> nodeIndex) {
		super(graphDB, nodeIndex);
	}

	@Override
	public Graph doit() {
		StopEvaluator stopEvaluator = new StopEvaluator() {
			@Override
			public boolean isStopNode(TraversalPosition currentPos) {
				return currentPos.returnedNodesCount() > maxDepth;
			}
		};
		ReturnableEvaluator returnableEvaluator = new ReturnableEvaluator() {
			@Override
			public boolean isReturnableNode(TraversalPosition currentPos) {
				return true;
			}
		};
		Traverser traverser = this.nodeIndex
				.get(NODE_KEY, "rootnode")
				.getSingle()
				.traverse(Order.DEPTH_FIRST, stopEvaluator,
						returnableEvaluator, RelTypes.assoziatedWith,
						Direction.BOTH);

		Graph result = new Graph();		
		while (traverser.iterator().hasNext()) {									
			Node currentNode = traverser.iterator().next();	
			Relationship relationShip = traverser.currentPosition()
					.lastRelationshipTraversed();
			if (traverser.currentPosition().notStartNode()) {
				String toNode = (String) currentNode.getProperty(NODE_KEY);
				result.nodes.add(new GraphNode(toNode));
				String kantenLabel = (String) relationShip.getProperty(REL_KEY);
				String fromNode = (String) relationShip.getOtherNode(
						currentNode).getProperty(NODE_KEY);				
				Boolean directed = relationShip.isType(RelTypes.subclassOf);
				result.triples.add(new GraphTriple(fromNode, toNode,
						kantenLabel, directed));
			}			
		}
		result.nodes.add(new GraphNode("rootnode"));
		return result;
	}

}
