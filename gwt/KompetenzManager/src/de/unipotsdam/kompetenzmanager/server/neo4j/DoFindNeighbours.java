package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.Graph;

public class DoFindNeighbours extends DoNeoGraph {

	private String nodeLabel;

	//TODO remove this class when merging changes
	public DoFindNeighbours(GraphDatabaseService graphDB, Index<Node> nodeIndex, RelationshipIndex relationshipIndex, String nodeLabel) {
		super(graphDB, nodeIndex, relationshipIndex);
		this.nodeLabel = nodeLabel;
	}

	@Override
	public Graph doit() {
		return convertRelationShipsToGraph(convertIteratorToList(this.nodeIndex.get(NODE_KEY,nodeLabel).getSingle().getRelationships()));
	}

}
