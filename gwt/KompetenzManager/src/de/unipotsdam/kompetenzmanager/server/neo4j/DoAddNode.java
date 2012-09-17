package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import scala.util.control.Exception;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;

public class DoAddNode extends DoNeo {
	
	private GraphNode sourceNode;
	private GraphNode newNode;
	private String kantenLabel;

	public DoAddNode(GraphDatabaseService graphDb, Index<Node> nodeIndex,RelationshipIndex relationshipIndex, GraphNode sourceNode,
			GraphNode newNode, String kantenLabel) {
		super(graphDb, nodeIndex, relationshipIndex);
		if (this.newNode != null) {
			throw new Error("End node is null , kannot add node!");
		}
		this.sourceNode = sourceNode;
		this.newNode = newNode;
		this.kantenLabel = kantenLabel;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Graph doit() {		
		Node firstNode = createAndIndexNode(this.newNode.label);				
		Node secondNode = nodeIndex.get(NODE_KEY, sourceNode.label).getSingle();		
		connectNodes(firstNode, secondNode, kantenLabel, RelTypes.subclassOf);
		////		Relationship relationship = firstNode.createRelationshipTo(secondNode,
////				RelTypes.assoziatedWith);
//				Relationship relationship = firstNode.createRelationshipTo(secondNode, RelTypes.subclassOf);
//		relationship.setProperty("label", this.kantenLabel);
//		relationship.setProperty(REL_KEY, this.kantenLabel);
//		
		return null;
	}

}
