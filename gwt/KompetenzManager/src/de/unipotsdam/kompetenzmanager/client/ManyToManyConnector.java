package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ManyToManyConnector extends Composite {

	private static ManyToManyConnectorUiBinder uiBinder = GWT
			.create(ManyToManyConnectorUiBinder.class);

	interface ManyToManyConnectorUiBinder extends
			UiBinder<Widget, ManyToManyConnector> {
	}

	public ManyToManyConnector() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
