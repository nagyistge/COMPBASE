package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;

import de.unipotsdam.kompetenzmanager.shared.Graph;

public class DoAddRootNode extends DoNeo {

	public DoAddRootNode(GraphDatabaseService graphDB, Index<Node> nodeIndex) {
		super(graphDB, nodeIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Graph doit() {		
		Node nodeFrom = createAndIndexNode("rootnode");
		Node nodeTo = createAndIndexNode("Informatik");
		connectNodes(nodeFrom, nodeTo, "no specific label");		
		return null;
	}



}
