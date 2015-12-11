package uzuzjmd.competence.persistence.ontology;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;

public class CompOntReasoner {
	
	private OntModel m;

	public CompOntReasoner(OntModel m) {
		this.m = m;
	}
	
	public InfModel getInfModel() {
	    Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
	    reasoner = reasoner.bindSchema(m);
	    InfModel infmodel = ModelFactory.createInfModel(reasoner, m);
	    return infmodel;
	}
}
