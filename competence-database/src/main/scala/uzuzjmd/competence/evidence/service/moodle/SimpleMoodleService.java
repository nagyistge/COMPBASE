package uzuzjmd.competence.evidence.service.moodle;

import javax.ws.rs.core.MediaType;

import uzuzjmd.competence.owl.access.MagicStrings;
import uzuzjmd.competence.service.rest.client.UserCourseListResponse;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * DTOs für den Moodle REST-Service
 * 
 * @author julian
 * 
 */
public class SimpleMoodleService {
	private Token mooodleStandardInterfaceToken;
	private String errorCode;
	private Token competenceInterfaceToken;

	public SimpleMoodleService(String username, String userpassword) {
		mooodleStandardInterfaceToken = initToken(username, userpassword, "moodle_mobile_app");
		competenceInterfaceToken = initToken(username, userpassword, "upcompetence");
	}

	private Token initToken(String username, String userpassword, String serviceShortName) {
		Client client = Client.create();
		String connectionPath = MagicStrings.MOODLEURL + "/login/token.php?username=" + username + "&password=" + userpassword + "&service=" + serviceShortName;
		WebResource webResource = client.resource(connectionPath);
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if (response.getStatus() != 200) {
			this.errorCode = "Failed : HTTP error code : " + response.getStatus();
			throw new RuntimeException(errorCode);
		}

		return response.getEntity(Token.class);

	}

	public String getErrorCode() {
		return errorCode;
	}

	public MoodleContentResponseList getMoodleContents(String courseId) {
		Client client = Client.create();
		String moodleRestBase = getMoodleRestBase();
		WebResource webResource = null;
		try {
			String requestString = MagicStrings.MOODLEURL + moodleRestBase + "core_course_get_contents&courseid=" + courseId;
			webResource = client.resource(requestString);
		} catch (Exception e) {
			System.err.println("Probably the moodle web services not configured properly");
			e.printStackTrace();
		}
		return webResource.accept(MediaType.APPLICATION_JSON).get(MoodleContentResponseList.class);
	}

	public UserCourseListResponse getMoodleCourseList(String userEmail) {
		Client client = Client.create();
		String moodleRestBase = getMoodleCompetenceRestBase();
		WebResource webResource = null;
		try {
			String requestString = MagicStrings.MOODLEURL + moodleRestBase + "local_upcompetence_get_courses_for_user&user=" + userEmail;
			webResource = client.resource(requestString);
		} catch (Exception e) {
			System.err.println("Probably the moodle web services not configured properly");
			e.printStackTrace();
		}
		return webResource.accept(MediaType.APPLICATION_JSON).get(UserCourseListResponse.class);
	}

	private String getMoodleRestBase() {
		String moodleRestBase = "/webservice/rest/server.php?moodlewsrestformat=json&wstoken=" + mooodleStandardInterfaceToken.get("token") + "&wsfunction=";
		return moodleRestBase;
	}

	private String getMoodleCompetenceRestBase() {
		String moodleRestBase = "/webservice/rest/server.php?moodlewsrestformat=json&wstoken=" + competenceInterfaceToken.get("token") + "&wsfunction=";
		return moodleRestBase;
	}

	public boolean isUserExist(String userEmail) {
		Client client = Client.create();
		String moodleRestBase = getMoodleCompetenceRestBase();
		WebResource webResource = null;
		try {
			String requestString = MagicStrings.MOODLEURL + moodleRestBase + "local_upcompetence_user_exists&user=" + userEmail;
			webResource = client.resource(requestString);
		} catch (Exception e) {
			System.err.println("Probably the moodle web services not configured properly");
			e.printStackTrace();
		}
		return webResource.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
	}

}
