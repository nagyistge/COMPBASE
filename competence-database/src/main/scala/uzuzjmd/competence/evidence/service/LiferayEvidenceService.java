package uzuzjmd.competence.evidence.service;

import uzuzjmd.competence.evidence.model.Evidence;
import uzuzjmd.competence.evidence.model.MoodleEvidence;
import uzuzjmd.competence.evidence.service.moodle.MoodleContentResponse;
import uzuzjmd.competence.evidence.service.moodle.MoodleContentResponseList;
import uzuzjmd.competence.evidence.service.rest.dto.UserTree;

public class LiferayEvidenceService implements EvidenceService {
	private String adminUserName;
	private String adminPassword;
	private String liferayURL;

	public LiferayEvidenceService(String adminUserName, String adminPassword, String liferayURL) {
		super();
		this.adminUserName = adminUserName;
		this.adminPassword = adminPassword;
		this.liferayURL = liferayURL;
	}

	@Override
	public MoodleEvidence[] getMoodleEvidences(String course, String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Evidence[] getEvidences(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MoodleEvidence[] getUserEvidencesforMoodleCourse(String course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTree[] getUserTree(String course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MoodleContentResponse[] getCourseContentXML(String course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MoodleContentResponseList getCourseContent(String course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MoodleContentResponseList getCourseContents(String course) {
		// TODO Auto-generated method stub
		return null;
	}

}
