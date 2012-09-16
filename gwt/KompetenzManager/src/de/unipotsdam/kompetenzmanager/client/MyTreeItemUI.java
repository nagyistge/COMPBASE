package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;

public class MyTreeItemUI extends Composite {

	private static MyTreeItemUIUiBinder uiBinder = GWT
			.create(MyTreeItemUIUiBinder.class);
	@UiField Label label;

	interface MyTreeItemUIUiBinder extends UiBinder<Widget, MyTreeItemUI> {
	}

	public MyTreeItemUI() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
