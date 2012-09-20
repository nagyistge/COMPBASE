package de.unipotsdam.kompetenzmanager.client;

import com.gargoylesoftware.htmlunit.javascript.host.Event;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.shared.EventHandler;

public class TabbedView extends Composite {

	private static TabbedViewUiBinder uiBinder = GWT
			.create(TabbedViewUiBinder.class);
	@UiField
	HTMLPanel ThemeViewTab;
	@UiField
	HTMLPanel LiteratureViewTab;
	@UiField
	TabLayoutPanel tabView;

	interface TabbedViewUiBinder extends UiBinder<Widget, TabbedView> {
	}

	public TabbedView() {
		initWidget(uiBinder.createAndBindUi(this));
		this.tabView.setAnimationVertical(false);		
	}
	
}
