package uzuzjmd.competence.persistence.owl.reasoning;

import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager;

import com.hp.hpl.jena.rdf.listeners.ChangedListener;
import com.hp.hpl.jena.rdf.model.Statement;
import uzuzjmd.competence.persistence.abstractlayer.SimpleRulesReasoner;

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
