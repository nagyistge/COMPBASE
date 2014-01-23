package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class ConnectNodesMenu extends Composite {

	private static ConnectNodesMenuUiBinder uiBinder = GWT
			.create(ConnectNodesMenuUiBinder.class);
	@UiField Button finishedButton;
	@UiField Button button;
	private String nodeId;

	interface ConnectNodesMenuUiBinder extends
			UiBinder<Widget, ConnectNodesMenu> {
	}

	public ConnectNodesMenu(String nodeId) {
		initWidget(uiBinder.createAndBindUi(this));
		this.nodeId = nodeId;
	}

	@UiHandler("finishedButton")
	void onFinishedButtonClick(ClickEvent event) {
	}
	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
	}
}
