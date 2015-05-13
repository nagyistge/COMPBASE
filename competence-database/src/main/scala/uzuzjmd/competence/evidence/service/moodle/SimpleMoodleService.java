package uzuzjmd.competence.evidence.service.moodle;

import javax.ws.rs.core.MediaType;

import uzuzjmd.competence.owl.access.MagicStrings;

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
	private Token token;
	private String errorCode;
	private boolean userExist;

	public SimpleMoodleService(String username, String userpassword) {
		Client client = Client.create();
		String connectionPath = MagicStrings.MOODLEURL + "/login/token.php?username=" + username + "&password=" + userpassword + "&service=moodle_mobile_app";
		this.setUserExist(true);
		WebResource webResource = client.resource(connectionPath);
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if (response.getStatus() != 200) {
			this.errorCode = "Failed : HTTP error code : " + response.getStatus();
			new RuntimeException(errorCode);
		} else {
			token = response.getEntity(Token.class);
		}
		if (token.get("token") == null) {
			System.out.println("admin data is incorrect or token has not been configured in moodle");
		}

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
			System.exit(-1);
		}
		return webResource.accept(MediaType.APPLICATION_JSON).get(MoodleContentResponseList.class);

	}

	private String getMoodleRestBase() {
		String moodleRestBase = "/webservice/rest/server.php?moodlewsrestformat=json&wstoken=" + token.get("token") + "&wsfunction=";
		return moodleRestBase;
	}

	public boolean isUserExist() {
		return userExist;
	}

	public void setUserExist(boolean userExist) {
		this.userExist = userExist;
	}
}
