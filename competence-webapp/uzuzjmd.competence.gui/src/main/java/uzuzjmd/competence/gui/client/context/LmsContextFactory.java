package uzuzjmd.competence.gui.client.context;

public class LmsContextFactory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Long courseId;
	protected String serverUrl;
	protected String evidenceServerUrl;
	protected String role;
	protected String userName;
	protected String courseContext;
	protected String organization;
	protected Boolean validUserLoggedIn;
	protected String mode;
	protected String lmsSystem;

	public LmsContextFactory() {
		setOrganization("university");
		setCourseContext("testcourse");
		setLmsSystem("all");

	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
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
			this.evidenceServerUrl = getEvidenceServerURL();
		} else {
			this.evidenceServerUrl = evidenceServerUrl;
		}
		this.role = role;
		this.userName = userName;
		this.organization = organization;
		this.courseContext = "n" + courseId;
		setIsValidUserLoggedIn(false);

	}

	public String getUserFromContext() {
		return userName;
	}

	public String getRoleFromContext() {
		return role;
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

	public void setRole(String role) {
		this.role = role;
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

	/**
	 * needs to be overwritten in order to set context specific course or group
	 * ids
	 * 
	 * @return
	 */
	public Long getCourseId() {
		if (courseId == null) {
			courseId = 0l;
		}

		return courseId;
	}

	public void setIsValidUserLoggedIn(Boolean value) {
		validUserLoggedIn = value;
	}

	public Boolean getIsValidUserLoggedIn() {
		return validUserLoggedIn;
	}

	public void setUser(String value) {
		userName = value;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getLMSsystem() {
		return this.lmsSystem;
	}

	public void setLmsSystem(String lmsSystem) {
		this.lmsSystem = lmsSystem;
	}
}
