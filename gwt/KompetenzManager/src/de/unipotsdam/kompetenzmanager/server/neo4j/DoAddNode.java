package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;

public class DoAddNode extends DoNeo {
	
	private GraphNode sourceNode;
	private GraphNode newNode;
	private String kantenLabel;

	public DoAddNode(GraphDatabaseService graphDb, Index<Node> nodeIndex, GraphNode sourceNode,
			GraphNode newNode, String kantenLabel) {
		super(graphDb, nodeIndex);
		this.sourceNode = sourceNode;
		this.newNode = newNode;
		this.kantenLabel = kantenLabel;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Graph doit() {		
		Node firstNode = createAndIndexNode(this.newNode.label);				
		Node secondNode = nodeIndex.get(NODE_KEY, sourceNode.label).getSingle();		
		Relationship relationship = firstNode.createRelationshipTo(secondNode,
				RelTypes.assoziatedWith);
		relationship.setProperty("label", this.kantenLabel);
		relationship.setProperty(REL_KEY, this.kantenLabel);
		return null;
	}

}
