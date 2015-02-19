package uzuzjmd.competence.liferay;

import uzuzjmd.competence.gui.client.LmsContextFactory;
import uzuzjmd.competence.liferay.client.ui.LMSContextState;

import com.vaadin.ui.AbstractComponent;

public class CompetenceUIVaadinComponent extends AbstractComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LmsContextFactory lmsContextFactory;
	
	public CompetenceUIVaadinComponent(LmsContextFactory lmsContextFactory) {
		super();
		this.lmsContextFactory = lmsContextFactory;
		setContextFactory();		
	}

	
	@Override
	protected LMSContextState getState() {		
		return (LMSContextState) super.getState();
	}
	
	public void setContextFactory() {		
		getState().setLmsContextFactory(lmsContextFactory);		
	}
	
}
