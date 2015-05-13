package uzuzjmd.competence.evidence.service;

import uzuzjmd.competence.evidence.model.LMSSystems;

public abstract class AbstractEvidenceService implements EvidenceService {

	@Override
	public String[] getOrganizations() {
		String[] result = new String[] { "Universität Potsdam", "Universität Mannheim" };
		return result;
	}

	@Override
	public String[] getLMSSystems() {
		String[] result = new String[] { LMSSystems.liferay.toString(), LMSSystems.moodle.toString(), LMSSystems.ilias.toString() };
		return result;
	}

}
