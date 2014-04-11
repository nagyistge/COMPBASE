package uzuzjmd.competence.evidence.service;

import javax.jws.WebService;

import uzuzjmd.competence.evidence.model.Evidence;
import uzuzjmd.competence.evidence.model.MoodleEvidence;

@WebService(endpointInterface = "uzuzjmd.competence.evidence.service.EvidenceService")
public class EvidenceServiceImpl implements EvidenceService {

	public EvidenceServiceImpl(String moodleurl, String moodledb,
			String adminname, String adminpassword) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Evidence[] getEvidences(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MoodleEvidence[] getMoodleEvidences(String course, String user) {
		// TODO Auto-generated method stub
		return null;
	}

}
