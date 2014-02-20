package uzuzjmd.owl.util;

import org.semanticweb.HermiT.Reasoner.ReasonerFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

public class CompetenceReasoner {	
	public OWLReasoner getReasoner(OWLOntology ontology) {
		// PelletReasoner reasoner = PelletReasonerFactory.getInstance()
		// .createNonBufferingReasoner(ontology);
		OWLReasoner reasoner = new ReasonerFactory()
				.createNonBufferingReasoner(ontology);
		return reasoner;
	}		
}
