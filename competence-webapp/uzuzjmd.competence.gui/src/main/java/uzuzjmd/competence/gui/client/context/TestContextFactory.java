package uzuzjmd.competence.gui.client.context;

public class TestContextFactory extends StandaloneContextFactory {

	public TestContextFactory(String serverUrl, String evidenceUrl) {
		super(serverUrl, evidenceUrl);
		// TODO Auto-generated constructor stub
		setLmsSystem("moodle");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
