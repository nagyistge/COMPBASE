import static org.junit.Assert.*;

import org.junit.Test;

import uzuzjmd.owl.util.OntologyManager;


public class OntologyManagerTest {

	@Test
	public void test() {
		OntologyManager ontologyManager = new OntologyManager();
		ontologyManager.loadOntology();
	}

}
