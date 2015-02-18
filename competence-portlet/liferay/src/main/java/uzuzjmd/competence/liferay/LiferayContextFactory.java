package uzuzjmd.competence.liferay;

import uzuzjmd.competence.shared.LMSContextFactory;

public class LiferayContextFactory extends LMSContextFactory {

	@Override
	public String getUserFromContext() {
		return "Julian Teacher";
	}

	@Override
	public String getRoleFromContext() {
		return "teacher";
	}

	@Override
	public String getEvidenceServerUrlFromContext() {
		return "http://localhost:8083";
	}

	@Override
	public String getCompetenceServerURL() {
		return "http://localhost:8084";
	}

	@Override
	public int getCourseIdFromContext() {
		return 10184;
	}
	
//	Page.getCurrent().getJavaScript().execute("courseId = 10184;");
//	Page.getCurrent().getJavaScript().execute("serverUrl = 'http://localhost:8084';");
//	Page.getCurrent().getJavaScript().execute("evidenceServerUrl = 'http://localhost:8083';");
//	Page.getCurrent().getJavaScript().execute("role = 'teacher'");
//	Page.getCurrent().getJavaScript().execute("user = 'Julian Teacher';");	

}
