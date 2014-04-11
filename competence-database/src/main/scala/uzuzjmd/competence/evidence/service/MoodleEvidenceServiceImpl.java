package uzuzjmd.competence.evidence.service;

import java.util.ArrayList;

import javax.jws.WebService;

import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import uzuzjmd.competence.evidence.model.Evidence;
import uzuzjmd.competence.evidence.model.MoodleEvidence;
import uzuzjmd.competence.owl.access.MagicStrings;
import uzuzjmd.mysql.database.MysqlConnect;
import uzuzjmd.mysql.database.VereinfachtesResultSet;

@WebService(endpointInterface = "uzuzjmd.competence.evidence.service.EvidenceService")
public class MoodleEvidenceServiceImpl implements EvidenceService {

	Logger logger = LogManager.getLogger(MoodleEvidenceServiceImpl.class.getName());
	private String moodleURl;
	private String moodledb;
	private String adminname;
	private String adminpassword;

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

	@Override
	public Evidence[] getEvidences(String user) {
		logger.info("getting evidences" + " user " + user);

		throw new NotImplementedException(
				"this method on Evidenzserver is not implemented");
	}

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

		ArrayList<MoodleEvidence> ergebnisAlsArray = new ArrayList<MoodleEvidence>();
		while (result.next()) {
			MoodleEvidence moodleEvidence = new MoodleEvidence(
					result.getString("info"), MagicStrings.MOODLEURL
							+ result.getString("url"),
					result.getString("userid"), result.getString("course"),
					result.getString("module"));
			ergebnisAlsArray.add(moodleEvidence);
		}

		return ergebnisAlsArray.toArray(new MoodleEvidence[ergebnisAlsArray
				.size()]);
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
}
