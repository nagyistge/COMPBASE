package uzuzjmd.competence.gui.client;

public class ContextFactory {

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
	}

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

	private native int getCourseIdFromContext()/*-{
		return $wnd.courseId;
	}-*/;

	private native String getServerUrlFromContext()/*-{
		return $wnd.serverUrl;
	}-*/;

	private native String getEvidenceServerUrlFromContext()/*-{
		return $wnd.evidenceServerUrl;
	}-*/;

	private native String getRoleFromContext()/*-{
		return $wnd.role;
	}-*/;

	private native String getUserFromContext()/*-{
		return $wnd.user;
	}-*/;

}
