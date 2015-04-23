package uzuzjmd.competence.gui.client;

public class LmsContextFactory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long courseId;
	protected String serverUrl;
	protected String evidenceServerUrl;
	protected String role;
	protected String userName;
	protected String courseContext;
	protected String organization;

	public LmsContextFactory() {
	}

	public LmsContextFactory(long courseId, String serverUrl,
			String evidenceServerUrl, String role, String userName,
			String organization) {
		super();
		this.courseId = courseId;
		if (serverUrl == null) {
			this.serverUrl = getCompetenceServerURL();
		} else {
			this.serverUrl = serverUrl;
		}
		if (evidenceServerUrl == null) {
			this.evidenceServerUrl = getEvidenceServerUrlFromContext();
		} else {
			this.evidenceServerUrl = evidenceServerUrl;
		}
		this.role = role;
		this.userName = userName;
		this.organization = organization;
		this.courseContext = "n" + courseId;

	}

	public String getUserFromContext() {
		return userName;
	}

	public String getRoleFromContext() {
		return role;
	}

	public String getEvidenceServerUrlFromContext() {
		return evidenceServerUrl;
	}

	public String getCompetenceServerURL() {
		return serverUrl;
	}

	public String getOrganization() {
		return organization;
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

	public String getCourseContext() {
		return courseContext;
	}

	public void setCourseContext(String courseContext) {
		this.courseContext = courseContext;
	}

	public String getRawCourseId() {
		return courseId + "";
	}

}
