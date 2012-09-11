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
		createAndIndexNodeTable("nodetable");
		Node nodeFrom = createAndIndexNode("rootnode");
		Node nodeTo = createAndIndexNode("Informatik");
		connectNodes(nodeFrom, nodeTo, "no specific label");		
		return null;
	}

	private Node createAndIndexNodeTable(String label) {
		if (!nodeIndex.get(TABLE_KEY, label).hasNext()) {
			Node node = graphDb.createNode();
			node.setProperty(TABLE_KEY, label);			
			nodeIndex.add(node, TABLE_KEY, label);
			return node;
		} else {
			return nodeIndex.get(TABLE_KEY, label).getSingle();
		}
	}



}
