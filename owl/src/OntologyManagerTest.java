import java.io.IOException;

import org.junit.Test;

import uzuzjmd.owl.util.CompOntologyManager;
import uzuzjmd.owl.util.CompOntologyUtil;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;


public class OntologyManagerTest {

	
	@Test
	public void testBaseOntology() throws IOException {
		CompOntologyUtil ontologyUtil = new CompOntologyUtil();
		CompOntologyManager manager = new CompOntologyManager();
		OntModel m = manager.createBaseOntology();
		ontologyUtil.writeOntologyout(m);
	}
	
	//@Test
	public void test() throws IOException {

		CompOntologyUtil ontologyUtil = new CompOntologyUtil();
		
		OntModel m = ontologyUtil.initializeOntologyModel();
		ontologyUtil.readFileOntology(m, "proof-of-conceptrdf.owl");

		// create a dummy paper for this example
		String className = "Competence";
		OntClass paper = ontologyUtil.getOntClassForString(m, className);
		
		String individualName = "competence1";
		Individual p1 = ontologyUtil.createIndividualForString(m, paper, individualName);	
	}

	

}
