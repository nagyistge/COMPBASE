package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.Literature;

public class DoConnectGraphAndLiterature extends DoNeoLit implements  Do {

	private DoNeoGraph doNeoGraph;
	private Literature literature;
	private Graph graph;

	public DoConnectGraphAndLiterature(GraphDatabaseService graphDB,
			Index<Node> nodeIndex, RelationshipIndex relIndex, Literature literature, Graph graph) {
		super(graphDB, nodeIndex, relIndex);
		this.doNeoGraph = new DoNeoGraph(graphDB, nodeIndex, relIndex);
		this.literature = literature;
		this.graph = graph;
	}

	@Override
	public Graph doit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Literature dolit() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
