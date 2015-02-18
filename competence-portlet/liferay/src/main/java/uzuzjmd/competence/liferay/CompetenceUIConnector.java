package uzuzjmd.competence.liferay;

import uzuzjmd.competence.gui.client.CompetenceUIContainer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(uzuzjmd.competence.liferay.CompetenceUIWrapper.class)
public class CompetenceUIConnector extends AbstractComponentConnector {
	@Override
	protected Widget createWidget() {
	
	return GWT.create(CompetenceUIContainer.class);	
	}
}
