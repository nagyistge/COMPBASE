package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class Neo4JStarter {
	public static GraphDatabaseService graphDb;
	private static Index<Node> nodeIndex;
	private static RelationshipIndex relationshipIndex;
	public static final String DATABASE_PATH = "database/store/store4"; 

	public Neo4JStarter() {
		if (Neo4JStarter.graphDb == null) {			
			Neo4JStarter.graphDb = new GraphDatabaseFactory()
					.newEmbeddedDatabase(DATABASE_PATH);
			setNodeIndex(graphDb.index().forNodes("nodes"));
			setRelationshipIndex(graphDb.index().forRelationships("rels"));
			addRootNode();		
			addLiteratureRootNode();
		} 
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {			
			@Override
			public void run() {
				Neo4JStarter.nodeIndex = null;
				Neo4JStarter.setRelationshipIndex(null);
				if (graphDb != null)
				Neo4JStarter.graphDb.shutdown();				
			}
		}));
	}



	private void addLiteratureRootNode() {
		doQueryLit(new DoAddLiteratureRoot(getGraphDB(), nodeIndex, relationshipIndex));
		
	}



	private void addRootNode() {
		doQuery(new DoAddRootNode(getGraphDB(), nodeIndex, relationshipIndex));
	}

	public GraphDatabaseService getGraphDB() {
		return Neo4JStarter.graphDb;
	}
	
	public void shutdown() {
		graphDb.shutdown();
		graphDb = null;
	}

	public Graph doQuery(Do doer) {
		Transaction tx = graphDb.beginTx();
		try {
			Graph result = doer.doit();
			tx.success();
			return result;
		} finally {
			tx.finish();
		}
	}
	
	public Literature doQueryLit(DoNeoLit doer) {
		Transaction tx = graphDb.beginTx();
		try {
			Literature result = doer.dolit();
			tx.success();
			return result;
		} finally {
			tx.finish();
		}
	}
		

	/**
	 * @param nodeIndex
	 *            the nodeIndex to set
	 */
	public void setNodeIndex(Index<Node> nodeIndex) {
		Neo4JStarter.nodeIndex = nodeIndex;
	}

	/**
	 * @return the nodeIndex
	 */
	public Index<Node> getNodeIndex() {
		return Neo4JStarter.nodeIndex;
	}

	/**
	 * @param relationshipIndex the relationshipIndex to set
	 */
	public static void setRelationshipIndex(RelationshipIndex relationshipIndex) {
		Neo4JStarter.relationshipIndex = relationshipIndex;
	}

	/**
	 * @return the relationshipIndex
	 */
	public RelationshipIndex getRelationshipIndex() {
		return relationshipIndex;
	}
}
