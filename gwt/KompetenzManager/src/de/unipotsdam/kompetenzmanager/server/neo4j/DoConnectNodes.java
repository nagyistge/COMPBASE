package de.unipotsdam.kompetenzmanager.server.neo4j;

import java.util.Collection;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;

public class DoConnectNodes extends DoNeo {

	private Collection<String> graphNodes;
	private String nodeTo;

	public DoConnectNodes(GraphDatabaseService graphDB, Index<Node> nodeIndex, RelationshipIndex relationshipIndex, Collection<String> graphNodes2, String toNode) {
		super(graphDB, nodeIndex, relationshipIndex);
		this.graphNodes= graphNodes2;
		this.nodeTo = toNode;
	}

	@Override
	public Graph doit() {
		System.out.println("verbinde " + graphNodes.size() + "Knoten");
		for (String nodeFrom : graphNodes) {
			Node fromNode = this.nodeIndex.get(NODE_KEY, nodeFrom).getSingle();
			Node toNode = this.nodeIndex.get(NODE_KEY, nodeTo).getSingle();
//			if (fromNode.hasRelationship(RelTypes.subclassOf, Direction.OUTGOING)) {
//				Node endNode = fromNode.getRelationships(RelTypes.subclassOf).iterator().next().getEndNode();
//				if (endNode.getProperty(NODE_KEY).equals(toNode.getProperty(NODE_KEY))) {
//					return null;
//				}
//			}						
			connectNodes(fromNode, toNode, "subClass", RelTypes.subclassOf);
		}
		return null;
	}

}
