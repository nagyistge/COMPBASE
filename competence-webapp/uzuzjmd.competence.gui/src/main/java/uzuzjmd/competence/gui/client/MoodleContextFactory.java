package uzuzjmd.competence.gui.client;

public class MoodleContextFactory extends ContextFactory {

	public native int getCourseIdFromContext()/*-{
		return $wnd.courseId;
	}-*/;

	public native String getServerUrlFromContext()/*-{
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
}
