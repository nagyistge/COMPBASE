package uzuzjmd.competence.evidence.service;

import uzuzjmd.competence.evidence.model.Evidence;
import uzuzjmd.competence.evidence.model.MoodleEvidence;

public interface EvidenceService {
	public MoodleEvidence[] getEvidences(String course, String user);

	public Evidence[] getEvidences(String user);
}
