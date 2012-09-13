package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TestWrapper extends Composite {

	private static TestWrapperUiBinder uiBinder = GWT
			.create(TestWrapperUiBinder.class);

	interface TestWrapperUiBinder extends UiBinder<Widget, TestWrapper> {
	}

	public TestWrapper() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
