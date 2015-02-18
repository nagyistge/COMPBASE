package uzuzjmd.competence.shared;

public abstract class LMSContextFactory {

	private int courseId;
	private String serverUrl;
	private String evidenceServerUrl;
	private String role;
	private String userName;

	public LMSContextFactory() {
		this.courseId = getCourseIdFromContext();
		this.serverUrl = getCompetenceServerURL();
		this.evidenceServerUrl = getEvidenceServerUrlFromContext();
		this.role = getRoleFromContext();
		this.userName = getUserFromContext();
		
	}

	public abstract String getUserFromContext();

	public abstract String getRoleFromContext();

	public abstract String getEvidenceServerUrlFromContext();

	public abstract String getCompetenceServerURL();

	/**
	 * in moodle this is the course id in liferay this is the site id
	 * 
	 * @return
	 */
	public abstract int getCourseIdFromContext();

	public String getCourseId() {
		return "n" + courseId;
	}

	public String getMoodleCourseId() {
		return courseId + "";
	}

	public String getServerURL() {
		return serverUrl;
	}

	public String getEvidenceServerURL() {
		return evidenceServerUrl;
	}

	public String getRole() {
		return role;
	}

	public String getUser() {
		return userName;
	}

}
