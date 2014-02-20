import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

import uzuzjmd.owl.util.CompetenceReasoner;
import uzuzjmd.owl.util.OntologyLoader;
import uzuzjmd.owl.util.XMLLoader;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.util.FileManager;

public class OntologyLoaderTest {

	@Test
	public void testxmlload() throws OWLOntologyCreationException {
		XMLLoader loader = new XMLLoader();
		assertNotNull(loader.getOntology());
	}

	@Test
	public void ontologyloadtest() throws OWLOntologyCreationException {
		XMLLoader loader = new XMLLoader();
		OntologyLoader ontologyLoader = new OntologyLoader(loader.getOntology());
		ontologyLoader.loadOntologyFromXML();
	}

	@Test
	public void reasonertest() throws OWLOntologyCreationException {
		XMLLoader loader = new XMLLoader();
		OntologyLoader ontologyLoader = new OntologyLoader(loader.getOntology());
		OWLOntology ontology = ontologyLoader.loadOntologyFromXML();
		CompetenceReasoner competenceReasoner = new CompetenceReasoner();
		OWLReasoner reasoner = competenceReasoner.getReasoner(ontology);
		reasoner.isConsistent();

	}

	@Test
	public void reasoning2test() {
		Model schema = FileManager.get().loadModel(
				"file:proof-of-conceptrdf.owl");
		Model data = FileManager.get()
				.loadModel("file:proof-of-conceptrdf.owl");
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
		reasoner = reasoner.bindSchema(schema);
		InfModel infmodel = ModelFactory.createInfModel(reasoner, data);
		
		Resource r = infmodel.getResource( OntologyLoader.defaultIri + "Competence" );		
		OntClass cls = r.as( OntClass.class );
		
		// spielereien		
		
		System.out.println("finish");
		
//		Iterator<Node> nodes = infmodel.getGraph().getReifier().allNodes();
//		while (nodes.hasNext()) {
//			System.out.println(nodes.next());
//		}
	}

}
