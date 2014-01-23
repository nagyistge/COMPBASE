package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.event.dom.client.MouseOutEvent;

public class MultiClickMenu extends Composite {

	private static MultiClickMenuUiBinder uiBinder = GWT
			.create(MultiClickMenuUiBinder.class);
	@UiField Button selectButton;
	@UiField Button tagButton;
	@UiField VerticalPanel multiClickPanel;
	@UiField Button showLiteratureButton;	
	@UiField FocusPanel focusPanel;
	private String id;
	private String nodeId;
	private ShowCompetenceBinder2 widget;

	interface MultiClickMenuUiBinder extends UiBinder<Widget, MultiClickMenu> {
	}

	public MultiClickMenu(String id, ShowCompetenceBinder2 widget, String nodeId) {
		initWidget(uiBinder.createAndBindUi(this));
		this.id = id;
		this.nodeId = nodeId;
		this.widget = widget;
	}

	@UiHandler("selectButton")
	void onSelectButtonClick(ClickEvent event) {
		this.widget.addSelectedElementToList(nodeId);	
		this.widget.removeClickMenu();
	}
	@UiHandler("tagButton")
	void onTagButtonClick(ClickEvent event) {
		this.widget.removeClickMenu();
		this.widget.viewcontroller.tagSelectionToLiterature();
	}
	@UiHandler("focusPanel")
	void onFocusPanelMouseOut(MouseOutEvent event) {
		this.widget.removeClickMenu();
	}
	@UiHandler("showLiteratureButton")
	void onShowLiteratureButtonClick(ClickEvent event) {
		this.widget.removeClickMenu();
		this.widget.viewcontroller.showLiteratureToTags();
	}
}
