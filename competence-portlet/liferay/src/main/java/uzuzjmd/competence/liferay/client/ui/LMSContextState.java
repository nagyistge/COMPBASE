package uzuzjmd.competence.liferay.client.ui;

import uzuzjmd.competence.gui.client.LmsContextFactory;

import com.vaadin.shared.AbstractComponentState;


public class LMSContextState extends AbstractComponentState  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LmsContextFactory lmsContextFactory = new LmsContextFactory();
	
	public void setLmsContextFactory(LmsContextFactory contextFactory) {
		this.lmsContextFactory = contextFactory;
	}
	
	public LmsContextFactory getLmsContextFactory() {
		return lmsContextFactory;
	}
}
