package uzuzjmd.competence.evidence.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import uzuzjmd.competence.evidence.model.Evidence;
import uzuzjmd.competence.evidence.model.MoodleEvidence;

@WebService(name = "EvidenceService", targetNamespace = "http://service.evidence.competence.uzuzjmd/")
public interface EvidenceService {
	@WebMethod
	public MoodleEvidence[] getMoodleEvidences(String course, String user);

	@WebMethod
	public Evidence[] getEvidences(String user);

	@WebMethod
	MoodleEvidence[] getUserEvidencesforMoodleCourse(String course);
}
