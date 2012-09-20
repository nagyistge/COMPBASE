package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class WillConnectNumber extends Composite {

	private static WillConnectNumberUiBinder uiBinder = GWT
			.create(WillConnectNumberUiBinder.class);

	interface WillConnectNumberUiBinder extends
			UiBinder<Widget, WillConnectNumber> {
	}

	public WillConnectNumber() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
