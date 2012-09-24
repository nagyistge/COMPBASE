import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gwt.editor.client.Editor.Ignore;

import de.unipotsdam.kompetenzmanager.server.Neo4JGraphBackendImpl;
import de.unipotsdam.kompetenzmanager.server.excel.DoReadExcel;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoAddOrUpdateLiteratureEntry;
import de.unipotsdam.kompetenzmanager.server.neo4j.Neo4JStarter;
import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;


public class InsertLiteratureEntries {


	private Neo4JGraphBackendImpl neo4JGraphImpl;
	private Neo4JStarter neo;

	@Before
	public void before() {
		this.neo4JGraphImpl = new Neo4JGraphBackendImpl();		
		this.neo = new Neo4JStarter();
		
	}

	@After
	public void after() {
		this.neo4JGraphImpl.shutdown();
	}
	
	@Test
	public void testTryReadSomething() throws IOException {
		DoReadExcel doReadExcel = new DoReadExcel();
		doReadExcel.readRawData();
		doReadExcel.showData();
	}
	
		
	@Test
	public void testDoAddOrUpdateLiteratureEntry() throws IOException {
		DoReadExcel doReadExcel = new DoReadExcel();
		for (LiteratureEntry literatureEntry : doReadExcel.generateLiteratureEntriesFromExcel().literatureEntries) {			
			DoAddOrUpdateLiteratureEntry addOrUpdateLiteratureEntry = new DoAddOrUpdateLiteratureEntry(neo.getGraphDB(), neo.getNodeIndex(), neo.getRelationshipIndex(), literatureEntry);
			this.neo.doQueryLit(addOrUpdateLiteratureEntry);
		}
		Literature result = this.neo4JGraphImpl.getFullLiterature();
		assertTrue(result.literatureEntries.size() > 20);
	}

}
