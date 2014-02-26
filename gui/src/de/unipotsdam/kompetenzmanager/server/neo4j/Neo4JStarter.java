package de.unipotsdam.kompetenzmanager.server.neo4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Properties;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;
import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestAPIFacade;
import org.neo4j.rest.graphdb.RestGraphDatabase;

import de.unipotsdam.kompetenzmanager.server.excel.DoReadExcel;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class Neo4JStarter {
	public static GraphDatabaseService graphDb;
	private static Index<Node> nodeIndex;
	private static RelationshipIndex relationshipIndex;
	public static String DATABASE_PATH = "database/store/real";
	public static String PROPERTIES_PATH = "ddi-graph.xml";
	public static String PATH_PREFIX = "";

	public static void initPaths() {
		URL ClassUrl = Neo4JStarter.class.getProtectionDomain().getCodeSource()
				.getLocation();
		String jarURL = ClassUrl.getFile();
		jarURL = jarURL.replace(Neo4JStarter.class.getCanonicalName(), "");

		File currentDir = new File(jarURL);
		while (!currentDir.getName().equals("WEB-INF")) {
			currentDir = currentDir.getParentFile();
		}
		
		PATH_PREFIX = currentDir.getAbsolutePath() + "/";
		DATABASE_PATH = PATH_PREFIX + DATABASE_PATH;
		PROPERTIES_PATH = PATH_PREFIX + PROPERTIES_PATH;
	}

	public Neo4JStarter() throws IOException {
		initPaths();

		if (Neo4JStarter.graphDb == null) {
			Neo4JStarter.graphDb = new GraphDatabaseFactory()
					.newEmbeddedDatabase(DATABASE_PATH);								
			//graphDb = new RestGraphDatabase("http://localhost:7474/db/data");			
			setNodeIndex(graphDb.index().forNodes("nodes"));
			setRelationshipIndex(graphDb.index().forRelationships("rels"));
			addRootNode();
			//addLiteratureRootNode();
			//initilaizeLiterature();
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

	private void initilaizeLiterature() throws IOException {
		DoReadExcel doReadExcel;
		FileInputStream fileInput = null;
		FileOutputStream fileOutputStream = null;
		try {
			Properties props = new Properties();
			fileInput = new FileInputStream(new File(PROPERTIES_PATH));
			props.loadFromXML(fileInput);
			if (!props.containsKey("literatureLoaded")) {
				doReadExcel = new DoReadExcel();
				HashSet<LiteratureEntry> generatedEntries = doReadExcel
						.generateLiteratureEntriesFromExcel().literatureEntries;
				for (LiteratureEntry literatureEntry : generatedEntries) {
					DoAddOrUpdateLiteratureEntry addOrUpdateLiteratureEntry = new DoAddOrUpdateLiteratureEntry(
							this.getGraphDB(), this.getNodeIndex(),
							this.getRelationshipIndex(), literatureEntry);
					this.doQueryLit(addOrUpdateLiteratureEntry);
				}
				props.setProperty("literatureLoaded", generatedEntries.size()
						+ "");
				fileOutputStream = new FileOutputStream(new File(
						PROPERTIES_PATH));
				props.storeToXML(fileOutputStream, null);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fileInput != null)
				fileInput.close();
			if (fileOutputStream != null)
				fileOutputStream.close();
		}
	}

	private void addLiteratureRootNode() {
		doQueryLit(new DoAddLiteratureRoot(getGraphDB(), nodeIndex,
				relationshipIndex));

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

	public synchronized Graph doQuery(Do doer) {
		Transaction tx = graphDb.beginTx();
		try {
			Graph result = doer.doit();
			tx.success();
			return result;
		} finally {
			tx.finish();
		}
	}

	public synchronized Literature doQueryLit(DoNeoLit doer) {
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
	 * @param relationshipIndex
	 *            the relationshipIndex to set
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
