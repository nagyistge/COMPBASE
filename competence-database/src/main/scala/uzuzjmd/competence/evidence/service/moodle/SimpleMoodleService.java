package uzuzjmd.competence.evidence.service.moodle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import config.MagicStrings;
import uzuzjmd.competence.shared.dto.UserCourseListResponse;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.net.URLDecoder;

/**
 * DTOs für den Moodle REST-Service
 *
 * DTOs for the moodle rest service
 *
 * @author Julian Dehne
 */
public class SimpleMoodleService {

    private Logger logger = LogManager
            .getLogger(getClass());
    private Token mooodleStandardInterfaceToken;

    private Token competenceInterfaceToken;

    public SimpleMoodleService(String username,
                               String password) {
        password = URLDecoder.decode(password).replaceAll(" ", "+");
        username = URLDecoder.decode(username).replaceAll(" ", "+");

        mooodleStandardInterfaceToken = initToken(username,
                password, "moodle_mobile_app");
        competenceInterfaceToken = initToken(username,
                password, "upcompetence");

    }

    private Token initToken(String username,
                            String userpassword, String serviceShortName) {
        String connectionPath = MagicStrings.MOODLEURL
                + "/login/token.php?username=" + username
                + "&password=" + userpassword + "&service="
                + serviceShortName;
        return sendRequest(connectionPath, Token.class);
    }

    private <T> T sendRequest(String url,
                              Class<T> responseTyp) {
        Client client = ClientBuilder.newClient();
        WebTarget webResource = client.target(url);
        T response = webResource.request(
                MediaType.APPLICATION_JSON)
                .get(responseTyp);
        logger.trace("gotten moodle query result: "
                + response.toString());
        return response;
    }

    public Boolean wasError() {
        if (!mooodleStandardInterfaceToken
                .containsKey("error")) {
            return false;
        } else
            return true;
    }

    public MoodleContentResponseList getMoodleContents(
            String courseId) {
        String moodleRestBase = getMoodleRestBase();
        String requestString = MagicStrings.MOODLEURL
                + moodleRestBase
                + "core_course_get_contents&courseid="
                + courseId;
        return sendRequest(requestString,
                MoodleContentResponseList.class);
    }

    public UserCourseListResponse getMoodleCourseList() {
        String moodleRestBase = getMoodleCompetenceRestBase();
        String requestString = MagicStrings.MOODLEURL
                + moodleRestBase
                + "local_upcompetence_get_courses_for_user";
        UserCourseListResponse result = sendRequest(requestString,
                UserCourseListResponse.class);

        return result;
    }

    private String getMoodleRestBase() {
        String moodleRestBase = "/webservice/rest/server.php?moodlewsrestformat=json&wstoken="
                + mooodleStandardInterfaceToken
                .get("token") + "&wsfunction=";
        return moodleRestBase;
    }

    private String getMoodleCompetenceRestBase() {
        String moodleRestBase = "/webservice/rest/server.php?moodlewsrestformat=json&wstoken="
                + competenceInterfaceToken.get("token")
                + "&wsfunction=";
        return moodleRestBase;
    }

    public MoodleEvidenceList getMoodleEvidenceList(
            String courseId) {
        if (courseId == null
                || courseId.equals("undefined")) {
            String message = "courseId is null or undefined when getting Moodle evidences for courseId: "
                    + courseId;
            logger.error(message);
            throw new WebApplicationException(
                    new Exception(message));
        }

        String moodleRestBase = getMoodleCompetenceRestBase();
        String requestString = MagicStrings.MOODLEURL
                + moodleRestBase
                + "local_upcompetence_get_evidences_for_course&courseId="
                + courseId;
        logger.trace("getting moodle evidences with url: "
                + requestString);
        return sendRequest(requestString,
                MoodleEvidenceList.class);
    }

}
