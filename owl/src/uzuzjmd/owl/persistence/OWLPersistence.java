package uzuzjmd.owl.persistence;

import org.semanticweb.owlapi.model.OWLOntology;
import com.hp.hpl.jena.ontology.OntModel;

public interface OWLPersistence {
	public void persistOntology(OWLOntology ontology);
	public OWLOntology loadOntology();
	public void persistOntologyJena(OntModel ontology);
	public OntModel loadOntologyJena();
	
}
