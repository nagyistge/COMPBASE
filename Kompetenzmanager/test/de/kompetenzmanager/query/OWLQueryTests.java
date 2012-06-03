package de.kompetenzmanager.query;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Collection;

import org.junit.Test;
import org.semanticweb.HermiT.Reasoner.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

public class OWLQueryTests {

	private static final File LOAD_FILE = new File("testresources/KompetenzManager.owl");

	@Test
	public void shouldFindTwoIndividuals() throws Exception {
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		final OWLOntology ontology = manager.loadOntologyFromOntologyDocument(LOAD_FILE);
		OWLDataFactory dataFactory = manager.getOWLDataFactory();
		PrefixManager pm = new DefaultPrefixManager("http://www.semanticweb.org/ontologies/2012/5/Ontology1338745483425.owl#");
		OWLReasoner reasoner = new ReasonerFactory().createNonBufferingReasoner(ontology);

		OWLQuery sut = new OWLQuery(dataFactory, reasoner, pm);
		Collection<OWLIndividual> result = sut.getIndividualsWithLabel("individual");

		assertEquals(2, result.size());
	}
}
