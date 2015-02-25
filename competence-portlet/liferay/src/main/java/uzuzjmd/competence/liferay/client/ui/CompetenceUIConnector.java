package uzuzjmd.competence.liferay.client.ui;

import uzuzjmd.competence.liferay.CompetenceUIVaadinComponent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(uzuzjmd.competence.liferay.CompetenceUIVaadinComponent.class)
public class CompetenceUIConnector extends AbstractComponentConnector {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Widget createWidget() {	
	CompetenceUIWidgetGWT result = GWT.create(CompetenceUIWidgetGWT.class);
	System.out.println("setting context an init widget");
	result.setContextFactory(getState().getLmsContextFactory());	
//	result.initTabbedView();
	System.out.println("finished setting context an init widget");
	return result;	
	}
	
	@Override
	public LMSContextState getState() {
		// TODO Auto-generated method stub
		return (LMSContextState) super.getState();
	}
	
	
	@Override
	public CompetenceUIWidgetGWT getWidget() {
		// TODO Auto-generated method stub
		return (CompetenceUIWidgetGWT) super.getWidget();
	}
	

	
	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		// TODO Auto-generated method stub
		super.onStateChanged(stateChangeEvent);
		
		getWidget().setContextFactory(getState().getLmsContextFactory());
	}
}
