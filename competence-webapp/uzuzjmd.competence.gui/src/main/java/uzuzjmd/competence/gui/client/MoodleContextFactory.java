package uzuzjmd.competence.gui.client;

public class MoodleContextFactory extends LmsContextFactory {

	public MoodleContextFactory() {
		this.serverUrl = getCompetenceServerURL();
		this.evidenceServerUrl = getEvidenceServerUrlFromContext();
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

	public native String getEvidenceServerUrlFromContext()/*-{
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
	public String getRawCourseId() {
		return getMoodleCourseId();
	}
}
