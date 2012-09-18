package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public abstract class DoNeoLit extends DoNeo implements DoLit {

	public DoNeoLit(GraphDatabaseService graphDB, Index<Node> nodeIndex,
			RelationshipIndex relIndex) {
		super(graphDB, nodeIndex, relIndex);		
	}

	protected Node getLitNode(LiteratureEntry literatureEntry) {
		return this.nodeIndex.get(LIT_NODE_KEY, literatureEntry.hashCode()).getSingle();
	}
}
