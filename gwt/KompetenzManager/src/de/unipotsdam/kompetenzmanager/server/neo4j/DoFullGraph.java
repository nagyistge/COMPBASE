package de.unipotsdam.kompetenzmanager.server.neo4j;

import java.util.ArrayList;
import java.util.Collection;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.Graph;

public class DoFullGraph extends DoNeo {

	public DoFullGraph(GraphDatabaseService graphDB, Index<Node> nodeIndex, RelationshipIndex relationshipIndex) {
		super(graphDB, nodeIndex, relationshipIndex);		
	}

	@Override
	public Graph doit() {						
		return convertRelationShipsToGraph((getAllRelationShips(getRelNode())));
	}

	private Relationship[] getAllRelationShips(Node relNode) {
		Collection<Relationship> result = new ArrayList<Relationship>();
		for (String key : relNode.getPropertyKeys()) {			
			if (!key.equals(TABLE_KEY)) {
				IndexHits<Relationship> hits = relIndex.get(REL_KEY, key);										
				result.add(hits.getSingle());
			}
		}
		return (Relationship[]) result.toArray(new Relationship[result.size()]);
		
	}

	

}
