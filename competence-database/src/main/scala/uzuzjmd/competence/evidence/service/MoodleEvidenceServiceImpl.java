package uzuzjmd.competence.evidence.service;

import java.util.ArrayList;

import javax.jws.WebService;

import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import uzuzjmd.competence.evidence.model.Evidence;
import uzuzjmd.competence.evidence.model.MoodleEvidence;
import uzuzjmd.competence.evidence.service.moodle.MoodleContentResponseList;
import uzuzjmd.competence.evidence.service.moodle.SimpleMoodleService;
import uzuzjmd.competence.owl.access.MagicStrings;
import uzuzjmd.mysql.database.MysqlConnect;
import uzuzjmd.mysql.database.VereinfachtesResultSet;

/**
 * Eine sehr crude Art und Weise die Daten von Moodle zu bekommen. Dies könnte
 * durch einen Moodle-Webservice oder PHP-Implementation vielleicht eleganter
 * gelöst werden. It just works.
 * 
 * @author Julian Dehne
 * 
 */
@WebService(endpointInterface = "uzuzjmd.competence.evidence.service.EvidenceService")
public class MoodleEvidenceServiceImpl implements EvidenceService {

	Logger logger = LogManager.getLogger(MoodleEvidenceServiceImpl.class
			.getName());
	private String moodleURl;
	private String moodledb;
	private String adminname;
	private String adminpassword;
	private String adminLogin;
	private String adminLoginPassword;

	public MoodleEvidenceServiceImpl(String moodledatabaseurl, String moodledb,
			String adminname, String adminpassword) {
		if (moodledatabaseurl.endsWith("/")) {
			throw new Error("moodle database url should not end with /");
		}
		if (moodledatabaseurl.startsWith("http")) {
			throw new Error("moodle url should not start with with http");
		}

		this.moodleURl = moodledatabaseurl;
		this.moodledb = moodledb;
		this.adminname = adminname;
		this.adminpassword = adminpassword;
	}

	public MoodleEvidenceServiceImpl(String moodledatabaseurl, String moodledb,
			String adminname, String adminpassword, String adminLogin,
			String adminLoginPassword) {
		this(moodledatabaseurl, moodledb, adminname, adminpassword);
		this.adminLogin = adminLogin;
		this.adminLoginPassword = adminLoginPassword;
	}

	@Override
	public Evidence[] getEvidences(String user) {
		logger.info("getting evidences" + " user " + user);

		throw new NotImplementedException(
				"this method on Evidenzserver is not implemented");
	}

	/**
	 * Holt sich alle Kursdaten für den User if user
	 */
	@Override
	public MoodleEvidence[] getMoodleEvidences(String course, String user) {
		logger.info("getting moodle evidences for course " + course + " user "
				+ user);

		MysqlConnect connect = new MysqlConnect();
		connectToMoodleDB(connect);

		connect.otherStatements("use " + moodledb);

		// issue statement
		String statement = "SELECT mdl_log.*,FROM_UNIXTIME(mdl_log.time,'%d/%m/%Y %H:%i:%s' )as DATE,auth,firstname,lastname,email, "
				+ "firstaccess, lastaccess, lastlogin, currentlogin, "
				+ "FROM_UNIXTIME(lastaccess,'%d/%m/%Y %H:%i:%s' )as DLA, "
				+ "FROM_UNIXTIME(firstaccess,'%d/%m/%Y %H:%i:%s' )as DFA, "
				+ "FROM_UNIXTIME(lastlogin,'%d/%m/%Y %H:%i:%s' )as DLL, "
				+ "FROM_UNIXTIME(currentlogin,'%d/%m/%Y %H:%i:%s' )as DCL "
				+ "FROM mdl_log , mdl_user "
				+ "WHERE mdl_log.userid = mdl_user.id "
				+ "AND mdl_user.id = ? "
				+ "AND mdl_log.course= ?"
				+ "ORDER BY time DESC LIMIT ? ";

		VereinfachtesResultSet result = connect.issueSelectStatement(statement,
				user, course, 100);

		ArrayList<MoodleEvidence> ergebnisAlsArray = convertResultToEvidence(result);

		return ergebnisAlsArray.toArray(new MoodleEvidence[ergebnisAlsArray
				.size()]);
	}

	@Override
	public MoodleEvidence[] getUserEvidencesforMoodleCourse(String course) {
		logger.info("getting moodle evidences for course " + course);

		MysqlConnect connect = new MysqlConnect();
		connectToMoodleDB(connect);

		connect.otherStatements("use " + moodledb);

		// issue statement
		String statement = "SELECT mdl_log.*,FROM_UNIXTIME(mdl_log.time,'%d/%m/%Y %H:%i:%s' )as DATE,auth,firstname,lastname,email, firstaccess, lastaccess, lastlogin, currentlogin,"
				+ " FROM_UNIXTIME(lastaccess,'%d/%m/%Y %H:%i:%s' )as DLA,"
				+ " FROM_UNIXTIME(firstaccess,'%d/%m/%Y %H:%i:%s' )as DFA,"
				+ " FROM_UNIXTIME(lastlogin,'%d/%m/%Y %H:%i:%s' )as DLL,"
				+ " FROM_UNIXTIME(currentlogin,'%d/%m/%Y %H:%i:%s' )as DCL"
				+ " FROM mdl_log , mdl_user"
				+ " INNER JOIN mdl_role_assignments ra ON ra.userid = mdl_user.id"
				+ " INNER JOIN mdl_context ct ON ct.id = ra.contextid"
				+ " INNER JOIN mdl_course c ON c.id = ct.instanceid"
				+ " INNER JOIN mdl_role r ON r.id = ra.roleid"
				+ " INNER JOIN mdl_course_categories cc ON cc.id = c.category"
				+ " WHERE mdl_log.userid = mdl_user.id"
				+ " AND mdl_log.course= ?"
				+ " AND r.id =5"
				+ " ORDER BY time DESC";

		VereinfachtesResultSet result = connect.issueSelectStatement(statement,
				course);

		ArrayList<MoodleEvidence> ergebnisAlsArray = convertResultToEvidence(result);

		return ergebnisAlsArray.toArray(new MoodleEvidence[ergebnisAlsArray
				.size()]);
	}

	private ArrayList<MoodleEvidence> convertResultToEvidence(
			VereinfachtesResultSet result) {
		ArrayList<MoodleEvidence> ergebnisAlsArray = new ArrayList<MoodleEvidence>();
		while (result.next()) {
			MoodleEvidence moodleEvidence = new MoodleEvidence(
					result.getString("module") + ": "
							+ result.getString("info") + " am "
							+ result.getString("DLA"), MagicStrings.MOODLEURL
							+ "mod/" + result.getString("module") + "/"
							+ result.getString("url"),
					result.getString("userid"), result.getString("DLA"),
					result.getString("course"), result.getString("module"));
			moodleEvidence.setUsername(result.getString("firstname") + " "
					+ result.getString("lastname"));

			ergebnisAlsArray.add(moodleEvidence);
		}
		return ergebnisAlsArray;
	}

	private void connectToMoodleDB(MysqlConnect connect) {
		StringBuilder builder = new StringBuilder();
		builder.append("jdbc:mysql://");
		builder.append(moodleURl);
		builder.append(":3306/");
		builder.append(moodledb);
		builder.append("?user=");
		builder.append(adminname);
		builder.append("&");
		builder.append("password=");
		builder.append(adminpassword);
		logger.info("trying to log in to moodle with connection string"
				+ builder.toString());
		connect.connect(builder.toString());
	}

	public MoodleContentResponseList getCourseContents(String courseId) {
		if (this.adminLogin != null && this.adminLoginPassword != null) {
			return getCourseContents(courseId, adminLogin, adminLoginPassword);
		} else {
			// assumes admin db name and admin moodle name are the same
			SimpleMoodleService moodleService = new SimpleMoodleService(
					adminname, adminpassword);
			return moodleService.getMoodleContents(courseId);
		}
	}

	public MoodleContentResponseList getCourseContents(String courseId,
			String courseOwnerName, String courseOwnerPassword) {
		SimpleMoodleService moodleService = new SimpleMoodleService(
				courseOwnerName, courseOwnerPassword);
		return moodleService.getMoodleContents(courseId);
	}
}
