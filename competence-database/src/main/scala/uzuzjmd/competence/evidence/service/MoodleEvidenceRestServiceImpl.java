package uzuzjmd.competence.evidence.service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.jws.WebService;
import javax.ws.rs.core.Response;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import uzuzjmd.competence.evidence.model.LMSSystems;
import uzuzjmd.competence.evidence.service.moodle.MoodleContentResponse;
import uzuzjmd.competence.evidence.service.moodle.MoodleContentResponseList;
import uzuzjmd.competence.evidence.service.moodle.MoodleCourseListResponse;
import uzuzjmd.competence.evidence.service.moodle.MoodleEvidence;
import uzuzjmd.competence.evidence.service.moodle.SimpleMoodleService;
import uzuzjmd.competence.evidence.service.rest.dto.UserTree;
import uzuzjmd.competence.evidence.service.rest.mapper.Evidence2Tree;
import uzuzjmd.competence.owl.access.MagicStrings;
import uzuzjmd.mysql.database.MysqlConnect;
import uzuzjmd.mysql.database.VereinfachtesResultSet;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/*
 * durch einen Moodle-Webservice oder PHP-Implementation vielleicht eleganter
 * gelöst werden. Es wird vor allem die Kurs-Log-Tabelle von Moodle ausgelesen, um die letzten Aktivitäten zu bekommen
 * 
 * @author Julian Dehne
 * 
 */
@WebService(endpointInterface = "uzuzjmd.competence.evidence.service.EvidenceService")
public class MoodleEvidenceRestServiceImpl extends AbstractEvidenceService {

	public static LoadingCache<String, UserTree[]> cacheImpl;
	private String adminLogin;
	private String adminLoginPassword;
	private String adminname;
	private String adminpassword;
	Logger logger = LogManager.getLogger(MoodleEvidenceRestServiceImpl.class.getName());
	private String moodledb;
	private String moodleURl;
	private Thread cacheThread;

	public MoodleEvidenceRestServiceImpl(String moodledatabaseurl, String moodledb, String adminname, String adminpassword) {
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
		initCache();
	}

	public MoodleEvidenceRestServiceImpl(String moodledatabaseurl, String moodledb, String adminname, String adminpassword, String adminLogin, String adminLoginPassword) {
		this(moodledatabaseurl, moodledb, adminname, adminpassword);
		this.adminLogin = adminLogin;
		this.adminLoginPassword = adminLoginPassword;
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
		logger.debug("trying to log in to moodle with connection string" + builder.toString());
		connect.connect(builder.toString());
	}

	private ArrayList<MoodleEvidence> convertResultToEvidence(VereinfachtesResultSet result) {
		ArrayList<MoodleEvidence> ergebnisAlsArray = new ArrayList<MoodleEvidence>();
		while (result.next()) {
			MoodleEvidence moodleEvidence = new MoodleEvidence(result.getString("module") + ": " + result.getString("info") + " am " + result.getString("DLA"), MagicStrings.MOODLEURL + "/mod/"
					+ result.getString("module") + "/" + result.getString("url"), result.getString("userid"), result.getString("DLA"), result.getString("course"), result.getString("module"));
			moodleEvidence.setUsername(result.getString("firstname") + " " + result.getString("lastname"));

			ergebnisAlsArray.add(moodleEvidence);
		}
		return ergebnisAlsArray;
	}

	public UserTree[] getCachedUserTree(String course) {
		try {
			return cacheImpl.get(course);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new Error("Cache not working");
	}

	public MoodleContentResponseList getCourseContent(String course) {
		return getCourseContents(course, adminname, adminLoginPassword);
	}

	public MoodleContentResponseList getCourseContents(String courseId) {
		if (this.adminLogin != null && this.adminLoginPassword != null) {
			return getCourseContents(courseId, adminLogin, adminLoginPassword);
		} else {
			// assumes admin db name and admin moodle name are the same
			SimpleMoodleService moodleService = new SimpleMoodleService(adminname, adminpassword);
			return moodleService.getMoodleContents(courseId);
		}
	}

	public MoodleContentResponseList getCourseContents(String courseId, String courseOwnerName, String courseOwnerPassword) {
		SimpleMoodleService moodleService = new SimpleMoodleService(courseOwnerName, courseOwnerPassword);
		return moodleService.getMoodleContents(courseId);
	}

	public MoodleContentResponse[] getCourseContentXML(String course) {
		MoodleContentResponseList list = getCourseContents(course);
		return list.toArray(new MoodleContentResponse[0]);
	}

	/**
	 * Holt sich alle Kursdaten für den User if user
	 */

	public MoodleEvidence[] getMoodleEvidences(String course, String user) {
		MysqlConnect connect = initMysql(course, user);

		// issue statement
		String statement = "SELECT mdl_log.*,FROM_UNIXTIME(mdl_log.time,'%d/%m/%Y %H:%i:%s' )as DATE,auth,firstname,lastname,email, " + "firstaccess, lastaccess, lastlogin, currentlogin, "
				+ "FROM_UNIXTIME(lastaccess,'%d/%m/%Y %H:%i:%s' )as DLA, " + "FROM_UNIXTIME(firstaccess,'%d/%m/%Y %H:%i:%s' )as DFA, " + "FROM_UNIXTIME(lastlogin,'%d/%m/%Y %H:%i:%s' )as DLL, "
				+ "FROM_UNIXTIME(currentlogin,'%d/%m/%Y %H:%i:%s' )as DCL " + "FROM mdl_log , mdl_user " + "WHERE mdl_log.userid = mdl_user.id " + "AND mdl_user.id = ? " + "AND mdl_log.course= ?"
				+ "ORDER BY time DESC LIMIT ? ";

		VereinfachtesResultSet result = connect.issueSelectStatement(statement, user, course, 100);

		ArrayList<MoodleEvidence> ergebnisAlsArray = convertResultToEvidence(result);

		return ergebnisAlsArray.toArray(new MoodleEvidence[ergebnisAlsArray.size()]);
	}

	public MoodleEvidence[] getUserEvidencesforMoodleCourse(String course) {
		MysqlConnect connect = initMysql(course, "adminuser");

		// issue statement
		String statement = "SELECT mdl_log.*,FROM_UNIXTIME(mdl_log.time,'%d/%m/%Y %H:%i:%s' )as DATE,auth,firstname,lastname,email, firstaccess, lastaccess, lastlogin, currentlogin,"
				+ " FROM_UNIXTIME(lastaccess,'%d/%m/%Y %H:%i:%s' )as DLA," + " FROM_UNIXTIME(firstaccess,'%d/%m/%Y %H:%i:%s' )as DFA," + " FROM_UNIXTIME(lastlogin,'%d/%m/%Y %H:%i:%s' )as DLL,"
				+ " FROM_UNIXTIME(currentlogin,'%d/%m/%Y %H:%i:%s' )as DCL" + " FROM mdl_log , mdl_user" + " INNER JOIN mdl_role_assignments ra ON ra.userid = mdl_user.id"
				+ " INNER JOIN mdl_context ct ON ct.id = ra.contextid" + " INNER JOIN mdl_course c ON c.id = ct.instanceid" + " INNER JOIN mdl_role r ON r.id = ra.roleid"
				+ " INNER JOIN mdl_course_categories cc ON cc.id = c.category" + " WHERE mdl_log.userid = mdl_user.id" + " AND mdl_log.course= ?" + " AND r.id =5" + " ORDER BY time DESC";

		VereinfachtesResultSet result = connect.issueSelectStatement(statement, course);

		ArrayList<MoodleEvidence> ergebnisAlsArray = convertResultToEvidence(result);

		return ergebnisAlsArray.toArray(new MoodleEvidence[ergebnisAlsArray.size()]);
	}

	public UserTree[] getUserTree(String course) {
		MoodleEvidence[] moodleEvidences = this.getUserEvidencesforMoodleCourse(course);
		MoodleContentResponseList listMoodleContent = this.getCourseContents(course);

		Evidence2Tree mapper = new Evidence2Tree(listMoodleContent, moodleEvidences);
		UserTree[] result = mapper.getUserTrees().toArray(new UserTree[0]);
		return result;
	}

	private void initCache() {
		if (cacheImpl == null) {
			cacheImpl = CacheBuilder.newBuilder().maximumSize(1000).build(new CacheLoader<String, UserTree[]>() {
				public UserTree[] load(final String key) {
					return getUserTree(key);
				}
			});
		}
		if (cacheThread == null) {
			cacheThread = new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						for (String key : cacheImpl.asMap().keySet()) {
							cacheImpl.refresh(key);
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			cacheThread.start();
		}

	}

	private MysqlConnect initMysql(String course, String user) {
		logger.debug("getting moodle evidences for course " + course + " user " + user);

		MysqlConnect connect = new MysqlConnect();
		connectToMoodleDB(connect);

		connect.otherStatements("use " + moodledb);
		return connect;
	}

	@Override
	public Response getUserTreeCrossDomain(String course) {
		throw new Error("decorator called");
	}

	@Override
	public MoodleCourseListResponse getCourses(String user, String lmsSystem, String organization) {
		if (!LMSSystems.moodle.toString().equals(lmsSystem)) {
			return new MoodleCourseListResponse();
		}
		SimpleMoodleService simpleService = new SimpleMoodleService(adminLogin, adminLoginPassword);
		MoodleCourseListResponse result = simpleService.getMoodleCourseList(user);
		return result;
	}

	@Override
	public Boolean exists(String username, String password) {
		SimpleMoodleService simpleService = new SimpleMoodleService(username, password);
		return simpleService.isUserExist();
	}
}
