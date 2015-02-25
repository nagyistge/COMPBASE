package uzuzjmd.competence.liferay;

import java.io.Serializable;

import uzuzjmd.competence.gui.client.LmsContextFactory;

public class LiferayContextFactory extends LmsContextFactory implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LiferayContextFactory() {
		
	}
	
	public LiferayContextFactory(int courseId, String serverUrl,
			String evidenceServerUrl, String role, String userName) {
		super(courseId, serverUrl, evidenceServerUrl, role, userName);
	}
}
