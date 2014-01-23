package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.Graph;

public class DoAddRootNode extends DoNeoGraph {

	public DoAddRootNode(GraphDatabaseService graphDB, Index<Node> nodeIndex, RelationshipIndex relationshipIndex) {
		super(graphDB, nodeIndex, relationshipIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Graph doit() {		
		//refactor use NodeIndex 
		createAndIndexNodeTable("nodetable");
		createAndIndexNodeTable("reltable");
		Node nodeFrom = createAndIndexNode("rootnode");
		Node nodeTo = createAndIndexNode("Informatik");
		connectNodes(nodeFrom, nodeTo, "no specific label", RelTypes.subclassOf);		
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
