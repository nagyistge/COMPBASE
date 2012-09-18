import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.unipotsdam.kompetenzmanager.server.Neo4JGraphBackendImpl;
import de.unipotsdam.kompetenzmanager.shared.*;


public class LiteratureTests {
	
	private Neo4JGraphBackendImpl neo4JGraphImpl;

	@Before
	public void before() {
		this.neo4JGraphImpl = new Neo4JGraphBackendImpl();
	}

	@After
	public void after() {
		this.neo4JGraphImpl.shutdown();
	}
	
	@Test
	public void testAddOrUpdateLiteratureEntry() {
		this.neo4JGraphImpl.addOrUpdateLiteratureEntry(null, new LiteratureEntry());
		assertTrue(true);
	}

	
	@Test
	public void testConnectLiteratureToGraph() {
		this.neo4JGraphImpl.connectLiteratureToGraph(new Literature(), new Graph());
		assertTrue(true);
	}

	@Test
	public void testGetFullLiterature() {
		Literature result = this.neo4JGraphImpl.getFullLiterature();
		assertNotNull(result);
	}

	@Test
	public void testGetLiteratureForTags() {
		Literature litForTags = this.neo4JGraphImpl.getLiteratureForTags(new Graph());
		assertNotNull(litForTags);
	}

	@Test
	public void testGetTagsforLiterature() {
		Graph tags = this.neo4JGraphImpl.getTagsforLiterature(new Literature());
		assertNotNull(tags);
	}

	@Test
	public void testRemoveLiteratureEntry() {
		this.neo4JGraphImpl.removeLiteratureEntry(null, new LiteratureEntry());
		assertTrue(true);
	}

}
