package uzuzjmd.owl.reasoning.jena;

import com.hp.hpl.jena.rdf.listeners.ChangedListener;
import com.hp.hpl.jena.rdf.model.Statement;

public class ModelChangeListener extends ChangedListener {
		
	private SimpleRulesReasoner rulesReasoner;

	public ModelChangeListener(SimpleRulesReasoner rulesReasoner) {		
		this.rulesReasoner = rulesReasoner;
	}
	
	@Override
	public void addedStatement(Statement s) {		
		super.addedStatement(s);		
		rulesReasoner.reason();		
	}
}
