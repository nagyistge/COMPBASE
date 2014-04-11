package uzuzjmd.competence.evidence.service;

import javax.jws.WebService;

import uzuzjmd.competence.evidence.model.MoodleEvidence;

@WebService(name = "EvidenceService", targetNamespace = "http://uzuzjmd.competence.service.evidence/")
public interface EvidenceService {
	public MoodleEvidence[] getEvidences(String course, String user);
}
