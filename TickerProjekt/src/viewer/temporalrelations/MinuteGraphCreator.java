package viewer.temporalrelations;

import util.ReasonableOntology;
import util.Timeable;

public class MinuteGraphCreator implements Timeable {

	private ReasonableOntology _ontology;

	public MinuteGraphCreator(ReasonableOntology ontology) {
		this._ontology = ontology;
	}

	@Override
	public void time() {
	}
}
