package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ConnectNodesMenu extends Composite {

	private static ConnectNodesMenuUiBinder uiBinder = GWT
			.create(ConnectNodesMenuUiBinder.class);

	interface ConnectNodesMenuUiBinder extends
			UiBinder<Widget, ConnectNodesMenu> {
	}

	// TODO remove this class when merging
	public ConnectNodesMenu(String nodeID) {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
