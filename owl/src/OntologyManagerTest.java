import java.io.IOException;

import org.junit.Test;

import uzuzjmd.owl.util.CompOntologyManager;


public class OntologyManagerTest {
	
	@Test
	public void testBaseOntology() throws IOException {		
		CompOntologyManager manager = new CompOntologyManager();		
		manager.createBaseOntology();
		manager.getUtil().writeOntologyout();
	}
	
}
