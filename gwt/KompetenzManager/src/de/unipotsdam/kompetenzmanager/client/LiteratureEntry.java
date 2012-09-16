package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Button;

public class LiteratureEntry extends Composite {

	private static LiteratureEntryUiBinder uiBinder = GWT
			.create(LiteratureEntryUiBinder.class);
	@UiField ScrollPanel TagsContainer;
	@UiField TextBox titleContent;
	@UiField TextBox authorContent;
	@UiField TextBox publicationDate;
	@UiField TextArea abstractContent;
	@UiField Button speichernButton;
	@UiField Button addThemeButton;

	interface LiteratureEntryUiBinder extends UiBinder<Widget, LiteratureEntry> {
	}

	public LiteratureEntry() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
