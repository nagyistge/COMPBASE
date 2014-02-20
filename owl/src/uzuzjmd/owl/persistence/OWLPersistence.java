package uzuzjmd.owl.persistence;

import org.semanticweb.owlapi.model.OWLOntology;

public interface OWLPersistence {
	public void persistOntology(OWLOntology ontology);
	public OWLOntology loadOntology();
}
