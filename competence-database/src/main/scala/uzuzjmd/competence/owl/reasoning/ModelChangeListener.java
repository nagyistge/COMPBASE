package uzuzjmd.competence.owl.reasoning;

import uzuzjmd.competence.owl.access.CompOntologyManager;

import com.hp.hpl.jena.rdf.listeners.ChangedListener;
import com.hp.hpl.jena.rdf.model.Statement;

public class ModelChangeListener extends ChangedListener {

	private SimpleRulesReasoner rulesReasoner;

	public ModelChangeListener(
			SimpleRulesReasoner rulesReasoner,
			CompOntologyManager manager) {
		this.rulesReasoner = rulesReasoner;
	}

	@Override
	public void addedStatement(Statement s) {
		super.addedStatement(s);
		rulesReasoner.reason();
	}
}
