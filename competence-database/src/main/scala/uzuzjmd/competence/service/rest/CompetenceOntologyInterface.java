package uzuzjmd.competence.service.rest;

import uzuzjmd.competence.owl.access.CompOntologyManager;

public class CompetenceOntologyInterface {
	protected void closeManagerInCriticalMode(CompOntologyManager compOntologyManager) {
		compOntologyManager.getM().leaveCriticalSection();
		compOntologyManager.close();
	}

	protected CompOntologyManager initManagerInCriticalMode() {
		CompOntologyManager compOntologyManager = new CompOntologyManager();
		compOntologyManager.begin();
		compOntologyManager.getM().enterCriticalSection(false);
		return compOntologyManager;
	}
}
