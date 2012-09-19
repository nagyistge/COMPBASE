import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mortbay.util.IO;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import com.google.gwt.benchmarks.client.Setup;

import de.unipotsdam.kompetenzmanager.server.Neo4JGraphBackendImpl;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoNeo;
import de.unipotsdam.kompetenzmanager.server.neo4j.Neo4JStarter;
import de.unipotsdam.kompetenzmanager.server.neo4j.RelTypes;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;
import de.unipotsdam.kompetenzmanager.shared.GraphTriple;
import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class TestNeo4JTestImpl {

	private Neo4JGraphBackendImpl neo4JGraphImpl;
	private Literature dummyLiterature;

	@Before
	public void before() {
		this.neo4JGraphImpl = new Neo4JGraphBackendImpl();
		this.dummyLiterature = new Literature();		
		this.dummyLiterature.literatureEntries.add(new LiteratureEntry("schöner Aufsatz", "Ju Deh", 2000, "wichtiger abstract", "thepaper", "important volume", 22));
	}

	@After
	public void after() {
		this.neo4JGraphImpl.shutdown();
	}
	
	@AfterClass
	public static void cleanup() {
		deleteDB();
	}
	
	public static void deleteDB() {
		IO.delete(new File(Neo4JStarter.DATABASE_PATH));
		IO.delete(new File(Neo4JStarter.DATABASE_PATH+"\\index\\lucene\\node\\nodes"));
		
	}
	
	
	@Test
	public void getTableNode() {
		Neo4JStarter starter = new Neo4JStarter();
		Node node = starter.getNodeIndex().get(DoNeo.TABLE_KEY, "nodetable").getSingle();
		assertNotNull(node);		
	}

//	@Test
//	public void createtwice() {
//		GraphDatabaseService first = new GraphDatabaseFactory().newEmbeddedDatabase("database/store/store1");
//		first.shutdown();
//		GraphDatabaseService second = new GraphDatabaseFactory().newEmbeddedDatabase("database/store/store1");
//		second.shutdown();
//	}
		
	@Test
	public void testAddNode() {
		this.neo4JGraphImpl.addNode(new GraphNode("rootnode"), new GraphNode(
				"testlabel1"), "subclassOf");
		this.neo4JGraphImpl.addNode(new GraphNode("testlabel1"), new GraphNode(
		"testlabel2"), "subclassOf");
		this.neo4JGraphImpl.addNode(new GraphNode("testlabel2"), new GraphNode(
		"testlabel3"), "subclassOf");
		this.neo4JGraphImpl.addNode(new GraphNode("testlabel1"), new GraphNode(
		"testlabel4"), "subclassOf");
		assertTrue(true);
	}

	@Test
	public void testGetFullGraph() {				
		assertFalse(this.neo4JGraphImpl.getFullGraph().nodes.isEmpty());
		Graph graph = this.neo4JGraphImpl.getFullGraph();		
		assertFalse(this.neo4JGraphImpl.getFullGraph().triples.isEmpty());
	}
	
	@Test
	public void testGetShortestPath() {
//		Iterator<GraphNode> graphIterator =  this.neo4JGraphImpl.getFullGraph().nodes.iterator();
//		String firstLabel = graphIterator.next().label;
//		graphIterator.next();
//		String secondLabel = graphIterator.next().label;
		Graph result = this.neo4JGraphImpl.findShortestPath("testlab");
		assertFalse(result.triples.isEmpty());
	}
	
	@Test 
	public void testConnectNodes() {
		ArrayList<String> dummsnodes = new ArrayList<String>();
		dummsnodes.add("testlabel1");
		Graph result = this.neo4JGraphImpl.connectNodes(dummsnodes, "testlabel3");		
		GraphTriple graphTriple = new GraphTriple("testlabel1", "testlabel3", "subClass", true);
		System.out.println(graphTriple);
		assertTrue(result.triples.contains(graphTriple));
	}
	
	@Test
	public void equalityTest() {
	assertTrue("fromNode: testlabel1, toNode: testlabel3, Label: subClass, directed:true".equals("fromNode: testlabel1, toNode: testlabel3, Label: subClass, directed:true"));
	}
	
	@Test
	public void testGetFullLiterature() {
		testAddNode();
		testAddOrUpdateLiteratureEntry();
		testConnectLiteratureToGraph();
		Literature result = this.neo4JGraphImpl.getFullLiterature();		
		assertNotNull(result);
		LiteratureEntry literatureEntryExample = this.neo4JGraphImpl.getFullLiterature().literatureEntries.iterator().next();
		System.out.println(literatureEntryExample);
		Graph graph = literatureEntryExample.graph;
		System.out.println(graph);
		assertNotNull(graph);
		assertFalse(graph.triples.isEmpty());
		
	}
	
	@Test
	public void testAddOrUpdateLiteratureEntry() {		
		this.neo4JGraphImpl.addOrUpdateLiteratureEntry(dummyLiterature, new LiteratureEntry("titel", "author", 2012, "abstract", "new york times", "vol", 222));
		assertTrue(this.neo4JGraphImpl.getFullLiterature().literatureEntries.size() > 0);
//		int id = UUID.randomUUID().hashCode();
		int id = 8987879;
		this.neo4JGraphImpl.addOrUpdateLiteratureEntry(dummyLiterature, new LiteratureEntry("titel2", "author2", 2011, "abstract2", "new york times2", "vol2", id));
		assertTrue(this.neo4JGraphImpl.getFullLiterature().literatureEntries.size() > 1);
		this.neo4JGraphImpl.addOrUpdateLiteratureEntry(dummyLiterature, new LiteratureEntry("titel3", "author3", 2012, "abstract3", "new york times3", "vol3", id));
		assertFalse(this.neo4JGraphImpl.getFullLiterature().literatureEntries.size() > 2);
	
	}

	@Test
	public void testConnectLiteratureToGraph() {
		testAddNode();
		testAddOrUpdateLiteratureEntry();
		Literature input = neo4JGraphImpl.getFullLiterature();
		Graph graphInput = neo4JGraphImpl.getFullGraph();
		this.neo4JGraphImpl.connectLiteratureToGraph(input,graphInput );
		Neo4JStarter neo4jStarter = new Neo4JStarter();
		Transaction tx = neo4jStarter.getGraphDB().beginTx();
		Node node = null;
		Node litNode = null;
		String litNodeString = null;
		try {
			node = neo4jStarter.getNodeIndex().get(DoNeo.NODE_KEY, "testlabel2").getSingle();
			assertNotNull(node);		
			Iterable<Relationship> rels = node.getRelationships(RelTypes.isTagOf);
			assertTrue(rels.iterator().hasNext());
			Relationship relationship = rels.iterator().next();
			litNode = relationship.getOtherNode(node);
			litNodeString = (String) litNode.getProperty(DoNeo.LIT_NODE_TITEL);
			tx.success();			
		} finally {
			tx.finish();
		}
		assertNotNull(litNode);
		assertNotNull(litNodeString);
		System.out.println(litNodeString);
	}

	
	@Test
	public void testGetLiteratureForTags() {
		Literature litForTags = this.neo4JGraphImpl.getLiteratureForTags(neo4JGraphImpl.getFullGraph());
		assertNotNull(litForTags);		
		Literature reference = this.neo4JGraphImpl.getFullLiterature();	
		assertTrue(litForTags.equals(reference));
	}

	@Test
	public void testGetTagsforLiterature() {
		testAddNode();
		testAddOrUpdateLiteratureEntry();
		testConnectLiteratureToGraph();
		Graph tags = this.neo4JGraphImpl.getTagsforLiterature(neo4JGraphImpl.getFullLiterature());
		Graph tagsCopy = new Graph();
		tagsCopy.mergeWith(tags);
		assertNotEmpty(tags.nodes);		
		Graph fullGraph = neo4JGraphImpl.getFullGraph();
//		System.out.println("compare1" + fullGraph.toString());
		assertNotNull(tags);
//		System.out.println("compare2"+tags.toString());
		int sizeBeforeNodes = tags.nodes.size();
		int sizeBeforeTriple = tags.triples.size();
		assertFalse(tags.nodes.retainAll((fullGraph.nodes)));
		assertFalse(tags.triples.retainAll(fullGraph.triples));	
		fullGraph.nodes.retainAll((tagsCopy.nodes));
		fullGraph.triples.retainAll(tagsCopy.triples);				
		assertTrue(tags.nodes.size() == sizeBeforeNodes && sizeBeforeNodes == fullGraph.nodes.size());
		assertTrue(tags.triples.size() == sizeBeforeTriple && sizeBeforeTriple == fullGraph.triples.size());
	}

	private void assertNotEmpty(HashSet<GraphNode> nodes) {
		assertTrue(nodes.size() > 0);
		
	}

	@Test
	public void testRemoveLiteratureEntry() {
		testAddOrUpdateLiteratureEntry();
		Literature literature = this.neo4JGraphImpl.getFullLiterature();
		int beforeSize = literature.literatureEntries.size();
		LiteratureEntry literatureEntries = literature.literatureEntries.iterator().next();
		assertNotNull(literatureEntries);
		this.neo4JGraphImpl.removeLiteratureEntry(literature, literatureEntries);
		int afterSize = this.neo4JGraphImpl.getFullLiterature().literatureEntries.size();
		assertTrue(beforeSize > afterSize);
	}

}
