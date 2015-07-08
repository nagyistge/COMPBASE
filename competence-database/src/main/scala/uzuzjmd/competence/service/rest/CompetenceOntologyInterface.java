package uzuzjmd.competence.service.rest;

import uzuzjmd.competence.owl.access.CompOntologyManager;

public class CompetenceOntologyInterface {
	public void closeManagerInCriticalMode(CompOntologyManager compOntologyManager) {
		compOntologyManager.getM().leaveCriticalSection();
		compOntologyManager.close();
	}

	public CompOntologyManager initManagerInCriticalMode() {
		CompOntologyManager compOntologyManager = new CompOntologyManager();
		compOntologyManager.begin();
		compOntologyManager.getM().enterCriticalSection(false);
		return compOntologyManager;
	}
}
