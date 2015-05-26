package uzuzjmd.competence.gui.client.context;

public class JavaScriptContextFactory extends LmsContextFactory {

	public JavaScriptContextFactory() {
		this.serverUrl = getCompetenceServerURL();
		this.evidenceServerUrl = getEvidenceServerUrl();
		this.role = getRoleFromContext();
		this.userName = getUserFromContext();
		this.courseContext = "n" + getCourseIdFromContext();
		this.organization = "university";
	}

	public native int getCourseIdFromContext()/*-{
		return $wnd.courseId;
	}-*/;

	public native String getCompetenceServerURL()/*-{
		return $wnd.serverUrl;
	}-*/;

	public native String getEvidenceServerUrl()/*-{
		return $wnd.evidenceServerUrl;
	}-*/;

	public native String getRoleFromContext()/*-{
		return $wnd.role;
	}-*/;

	public native String getUserFromContext()/*-{
		return $wnd.user;
	}-*/;

	public String getMoodleCourseId() {
		return getCourseIdFromContext() + "";
	}

	@Override
	public Long getCourseId() {
		return Long.parseLong(getMoodleCourseId());
	}
}
