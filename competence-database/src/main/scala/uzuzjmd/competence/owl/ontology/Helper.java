package uzuzjmd.competence.owl.ontology;

import uzuzjmd.competence.owl.access.CompOntologyManager;

public class Helper {
	public CompOntologyManager getOntologyManagerInit() {
		CompOntologyManager compOntologyManager = new CompOntologyManager();
		compOntologyManager.begin();
		return compOntologyManager;
	}
}
