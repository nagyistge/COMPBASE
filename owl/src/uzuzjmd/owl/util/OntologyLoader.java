package uzuzjmd.owl.util;

import java.io.InputStream;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

public class OntologyLoader {
	
	private OWLOntologyManager manager;
	private final OWLOntology ontology;
	public static final String defaultIri = "http://www.uzuzjmd.de/proof-of-concept.owl#";


	public OntologyLoader(InputStream file) throws OWLOntologyCreationException {
		this.manager = OWLManager.createOWLOntologyManager();
		this.ontology = manager.loadOntologyFromOntologyDocument(file);

		OWLDataFactory dataFactory = manager.getOWLDataFactory();
		
		PrefixManager prefixManager = new DefaultPrefixManager(defaultIri);

	}
		
	public OWLOntology loadOntologyFromXML() throws OWLOntologyCreationException {		
		return ontology;
	}
	
	public OWLOntologyManager getManager() {
		return this.manager;
	}
}
