import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import uzuzjmd.owl.util.OntologyManager;


public class OntologyManagerTest {

	@Test
	public void test() throws IOException {
		OntologyManager ontologyManager = new OntologyManager();
		ontologyManager.loadOntology();
	}

}
