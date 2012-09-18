package de.unipotsdam.kompetenzmanager.server;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.server.neo4j.Do;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoNeo;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoNeoLit;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.Literature;

public class DoGetTagsForLit extends DoNeo implements Do {

	private Literature literature;

	public DoGetTagsForLit(GraphDatabaseService graphDB, Index<Node> nodeIndex,
			RelationshipIndex relIndex, Literature literature) {
		super(graphDB, nodeIndex, relIndex);
		this.literature = literature;
	}

	@Override
	public Graph doit() {
		// TODO Auto-generated method stub
		return null;
	}

}
