package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import de.unipotsdam.kompetenzmanager.shared.Graph;

public class Neo4JStarter {
	public static GraphDatabaseService graphDb;
	private static Index<Node> nodeIndex;

	public Neo4JStarter() {
		if (Neo4JStarter.graphDb == null) {			
			Neo4JStarter.graphDb = new GraphDatabaseFactory()
					.newEmbeddedDatabase("database/store/store2");
			setNodeIndex(graphDb.index().forNodes("nodes"));
			addRootNode();			
		} 
	}

	private void addRootNode() {
		doQuery(new DoAddRootNode(getGraphDB(), nodeIndex));
	}

	public GraphDatabaseService getGraphDB() {
		return Neo4JStarter.graphDb;
	}
	
	public void shutdown() {
		graphDb.shutdown();
		graphDb = null;
	}

	public Graph doQuery(DoNeo doer) {
		Transaction tx = graphDb.beginTx();
		try {
			Graph result = doer.doit();
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
}
