package de.unipotsdam.kompetenzmanager.server;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.server.neo4j.DoNeo;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoNeoGraph;
import de.unipotsdam.kompetenzmanager.shared.Graph;

public class DoFindNeighbours extends DoNeoGraph {

	public DoFindNeighbours(GraphDatabaseService graphDB,
			Index<Node> nodeIndex, RelationshipIndex relIndex, String node) {
		super(graphDB, nodeIndex, relIndex);
		this.node = node;
	}


	private String node;


	@Override
	public Graph doit() {		
		Iterable<Relationship> relationships = nodeIndex.get(NODE_KEY, node).getSingle().getRelationships();		
		List<Relationship> list = new ArrayList<Relationship>(); 
		for (Relationship rel: relationships) {
			list.add(rel);
		}
		return convertRelationShipsToGraph((Relationship[]) list.toArray());		
	}

}
