package uzuzjmd.competence.gui.client;

public class TestContextFactory extends MoodleContextFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestContextFactory() {
		super();
		setCourseContext("n2");
	}

	public String getUserFromContext() {
		return "student student";
	}

	public String getRoleFromContext() {
		return Role.teacher.toString();
	}

	public String getEvidenceServerUrlFromContext() {
		return "http://localhost:8083";
	}

	public String getCompetenceServerURL() {
		return "http://localhost:8084";
	}

	@Override
	public String getMoodleCourseId() {
		return "2";
	}

}
