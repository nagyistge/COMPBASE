import java.io.IOException;

import org.junit.Test;

import uzuzjmd.owl.util.OntologyManager;
import uzuzjmd.owl.util.OntologyUtil;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;


public class OntologyManagerTest {

	
	@Test
	public void testBaseOntology() {
		OntologyManager manager = new OntologyManager();
		manager.createBaseOntology();
	}
	
	//@Test
	public void test() throws IOException {

		OntologyUtil ontologyUtil = new OntologyUtil();
		
		OntModel m = ontologyUtil.initializeOntologyModel();
		ontologyUtil.readFileOntology(m);

		// create a dummy paper for this example
		String className = "Competence";
		OntClass paper = ontologyUtil.getOntClassForString(m, className);
		
		String individualName = "competence1";
		Individual p1 = ontologyUtil.createIndividualForString(m, paper, individualName);
	

	}

	

}
