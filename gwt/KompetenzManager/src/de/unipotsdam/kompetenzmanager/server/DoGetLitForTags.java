package de.unipotsdam.kompetenzmanager.server;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.server.neo4j.DoNeoLit;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.Literature;

public class DoGetLitForTags extends DoNeoLit {

	private Graph graph;

	public DoGetLitForTags(GraphDatabaseService graphDB, Index<Node> nodeIndex,
			RelationshipIndex relIndex, Graph graph) {
		super(graphDB, nodeIndex, relIndex);
		this.graph = graph;
	}

	@Override
	public Literature dolit() {
		// TODO Auto-generated method stub
		return null;
	}

}
