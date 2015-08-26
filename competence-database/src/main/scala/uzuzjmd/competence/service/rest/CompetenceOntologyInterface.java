package uzuzjmd.competence.service.rest;

import uzuzjmd.competence.owl.access.CompOntologyManager;
import uzuzjmd.competence.owl.access.PropUtil;

public class CompetenceOntologyInterface {

	public CompetenceOntologyInterface() {
		super();
		PropUtil propUtil = new PropUtil();
		propUtil.doStandard();

	}

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
