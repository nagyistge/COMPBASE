package uzuzjmd.competence.gui.client;

import com.google.gwt.core.shared.GWT;

public abstract class ContextFactory {

	private int courseId;
	private String serverUrl;
	private String evidenceServerUrl;
	private String role;
	private String userName;

	public ContextFactory() {
		this.courseId = getCourseIdFromContext();
		this.serverUrl = getServerUrlFromContext();
		this.evidenceServerUrl = getEvidenceServerUrlFromContext();
		this.role = getRoleFromContext();
		this.userName = getUserFromContext();

		GWT.log("using moodle context factory as default2");
	}

	public abstract String getUserFromContext();

	public abstract String getRoleFromContext();

	public abstract String getEvidenceServerUrlFromContext();

	public abstract String getServerUrlFromContext();

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
