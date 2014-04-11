package uzuzjmd.competence.evidence.service;

import javax.jws.WebService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import uzuzjmd.competence.evidence.model.Evidence;
import uzuzjmd.competence.evidence.model.MoodleEvidence;

@WebService(endpointInterface = "uzuzjmd.competence.evidence.service.EvidenceService")
public class EvidenceServiceImpl implements EvidenceService {

	Logger logger = LogManager.getLogger(EvidenceServiceImpl.class.getName());

	public EvidenceServiceImpl(String moodleurl, String moodledb,
			String adminname, String adminpassword) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Evidence[] getEvidences(String user) {
		logger.info("getting evidences" + " user " + user);

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MoodleEvidence[] getMoodleEvidences(String course, String user) {
		logger.info("getting moodle evidences for course " + course + " user "
				+ user);

		// TODO Auto-generated method stub
		return null;
	}

}
