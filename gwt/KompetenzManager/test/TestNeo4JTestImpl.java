import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import com.google.gwt.benchmarks.client.Setup;

import de.unipotsdam.kompetenzmanager.server.Neo4JGraphBackendImpl;
import de.unipotsdam.kompetenzmanager.server.neo4j.Neo4JStarter;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;

public class TestNeo4JTestImpl {

	private Neo4JGraphBackendImpl neo4JGraphImpl;

	@Before
	public void before() {
		this.neo4JGraphImpl = new Neo4JGraphBackendImpl();
	}

	@After
	public void after() {
		this.neo4JGraphImpl.shutdown();
	}

//	@Test
//	public void createtwice() {
//		GraphDatabaseService first = new GraphDatabaseFactory().newEmbeddedDatabase("database/store/store1");
//		first.shutdown();
//		GraphDatabaseService second = new GraphDatabaseFactory().newEmbeddedDatabase("database/store/store1");
//		second.shutdown();
//	}
	
	@Ignore
	@Test
	public void testAddNode() {
//		this.neo4JGraphImpl.addNode(new GraphNode("rootnode"), new GraphNode(
//				"testlabel1"), "subclassOf");
		this.neo4JGraphImpl.addNode(new GraphNode("rootnode"), new GraphNode(
				"testlabel2"), "subclassOf");
	}

	@Test
	public void testGetFullGraph() {
		System.out.println(this.neo4JGraphImpl.getFullGraph());
		assertFalse(this.neo4JGraphImpl.getFullGraph().nodes.isEmpty());
		Graph graph = this.neo4JGraphImpl.getFullGraph();
		assertFalse(this.neo4JGraphImpl.getFullGraph().triples.isEmpty());
	}

	@Test
	public void testFindShortestPath() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveNode() {
		fail("Not yet implemented");
	}

}
