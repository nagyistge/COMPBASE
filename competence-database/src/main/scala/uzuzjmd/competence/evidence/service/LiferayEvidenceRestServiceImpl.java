package uzuzjmd.competence.evidence.service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis.client.Stub;

import uzuzjmd.competence.evidence.model.Evidence;
import uzuzjmd.competence.evidence.model.MoodleEvidence;
import uzuzjmd.competence.evidence.service.moodle.MoodleContentResponse;
import uzuzjmd.competence.evidence.service.moodle.MoodleContentResponseList;
import uzuzjmd.competence.evidence.service.rest.dto.UserTree;

import com.liferay.portlet.social.model.SocialActivitySoap;
import com.liferay.portlet.social.service.http.SocialActivityServiceSoapProxy;

public class LiferayEvidenceRestServiceImpl implements EvidenceService {
	private String adminUserName;
	private String adminPassword;
	private String liferayURL;
	private SocialActivityServiceSoapProxy endpoint;

	public LiferayEvidenceRestServiceImpl(String adminUserName, String adminPassword, String liferayURL) {
		super();
		this.adminUserName = adminUserName;
		this.adminPassword = adminPassword;
		this.liferayURL = liferayURL;

		endpoint = new SocialActivityServiceSoapProxy(this.liferayURL + "/api/axis/Portlet_Social_SocialActivityService?wsdl");
		((Stub) endpoint.getSocialActivityServiceSoap()).setUsername(this.adminUserName);
		((Stub) endpoint.getSocialActivityServiceSoap()).setPassword(this.adminPassword);

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
		SocialActivitySoap[] activities = null;
		try {
			activities = endpoint.getGroupUsersActivities(Long.parseLong(course), -1, -1);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// todo convert them into the tree
		List<UserTree> result = new ArrayList<UserTree>();

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
