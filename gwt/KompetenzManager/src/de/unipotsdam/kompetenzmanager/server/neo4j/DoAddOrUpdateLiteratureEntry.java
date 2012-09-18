package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class DoAddOrUpdateLiteratureEntry extends DoNeoLit {


	private LiteratureEntry literatureEntry;

	public DoAddOrUpdateLiteratureEntry(GraphDatabaseService graphDB,
			Index<Node> nodeIndex, RelationshipIndex relationshipIndex,
			LiteratureEntry literatureEntry) {
		super(graphDB, nodeIndex, relationshipIndex);
		this.literatureEntry = literatureEntry;
	}

	@Override
	public Literature dolit() {
		// TODO Auto-generated method stub
		return null;
	}

}
