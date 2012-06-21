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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.event.dom.client.MouseMoveEvent;

import de.unipotsdam.kompetenzmanager.shared.GeometryUtil;

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
	private Integer x;
	private Integer y;	
	ShowCompetenceBinder2 widget = (ShowCompetenceBinder2) RootPanel.get(
	"content").getWidget(0);

	interface ClickMenuUiBinder extends UiBinder<Widget, ClickMenu> {
	}

	public ClickMenu(String id, Integer x, Integer y) {
		initWidget(uiBinder.createAndBindUi(this));
		this.id = id;		
		this.x = x;
		this.y = y;
	}

	@UiHandler("focusPanel2")
	void onFocusPanelMouseOut(MouseOutEvent event) {		
		widget.absolutePanel.remove(widget.clickMenu);
	}

	@UiHandler("newNode")
	void onnewNodeClick(ClickEvent event) {
		
	}
	
	@UiHandler("removeNode")
	void onremoveNodeClick(ClickEvent event) {
	}
	
}
