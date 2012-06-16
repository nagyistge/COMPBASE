package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;

public class FindCompetence extends Composite {

	private static FindCompetenceUiBinder uiBinder = GWT
			.create(FindCompetenceUiBinder.class);
	@UiField FormPanel formPanel;

	interface FindCompetenceUiBinder extends UiBinder<Widget, FindCompetence> {
	}

	public FindCompetence() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public FindCompetence(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));		
	}

	public void setText(String text) {		
	}
	
	@UiHandler("formPanel")
	void onFormPanelSubmit(SubmitEvent event) {		
	}
}
