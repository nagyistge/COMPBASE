package uzuzjmd.owl.reasoning.jena;

import java.io.IOException;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.listeners.ChangedListener;
import com.hp.hpl.jena.rdf.model.Statement;

public class ModelChangeListener extends ChangedListener {
	
	private OntModel m;
	private SimpleRulesReasoner rulesReasoner;

	public ModelChangeListener(OntModel m, SimpleRulesReasoner rulesReasoner) {
		this.m = m;
		this.rulesReasoner = rulesReasoner;
	}
	
	@Override
	public void addedStatement(Statement s) {		
		super.addedStatement(s);
		// implement the effect that should take place iff statements have
		// been added		
		//manager.getM().validate();
		rulesReasoner.reason();		
	}
}
