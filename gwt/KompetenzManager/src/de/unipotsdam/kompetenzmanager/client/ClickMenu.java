package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;

public class ClickMenu extends Composite {

	private static ClickMenuUiBinder uiBinder = GWT
			.create(ClickMenuUiBinder.class);
	@UiField
	FocusPanel focusPanel2;
	@UiField
	Button newNode;
	
	@UiField
	Button removeNode;
	
	public String id;
	ShowCompetenceBinder2 showCompetenceBinder2;
	private String nodeId;

	interface ClickMenuUiBinder extends UiBinder<Widget, ClickMenu> {
	}

	public ClickMenu(String id, ShowCompetenceBinder2 showCompetenceBinder2, String nodeId) {
		initWidget(uiBinder.createAndBindUi(this));
		this.showCompetenceBinder2 = showCompetenceBinder2;
		this.id = id;		
		this.nodeId = nodeId;
		GWT.log("Die NodeID ist " + nodeId);
	}

	@UiHandler("focusPanel2")
	void onFocusPanelMouseOut(MouseOutEvent event) {		
		showCompetenceBinder2.removeClickMenu();
	}

	@UiHandler("newNode")
	void onnewNodeClick(ClickEvent event) {
		GWT.log("newNode has been clicked");
		showCompetenceBinder2.removeClickMenu();		
		ElementEntryField elementEntryField = new ElementEntryField(this.showCompetenceBinder2, this.nodeId); 		
		showCompetenceBinder2.addElementEntryField(elementEntryField, showCompetenceBinder2.absolutePanel.getWidgetLeft(showCompetenceBinder2.glassPanelContainer) + 20, showCompetenceBinder2.absolutePanel.getWidgetTop(showCompetenceBinder2.glassPanelContainer) + 20);		
	}
	
	@UiHandler("removeNode")
	void onremoveNodeClick(ClickEvent event) {
		//TODO implement delete Node
	}
	
}
