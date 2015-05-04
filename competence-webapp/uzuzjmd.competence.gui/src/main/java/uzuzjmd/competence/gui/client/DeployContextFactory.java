package uzuzjmd.competence.gui.client;

public class DeployContextFactory extends MoodleContextFactory {

	public DeployContextFactory(String serverUrl, String evidenceUrl) {
		super();
		setCourseContext("n2");
		this.serverUrl = serverUrl;
		this.evidenceServerUrl = evidenceUrl;
	}

	public String getUserFromContext() {
		return "student student";
	}

	public String getRoleFromContext() {
		return Role.teacher.toString();
	}

	public String getEvidenceServerUrlFromContext() {
		return evidenceServerUrl;
	}

	public String getCompetenceServerURL() {
		return serverUrl;
	}

	@Override
	public String getMoodleCourseId() {
		return "2";
	}

}
