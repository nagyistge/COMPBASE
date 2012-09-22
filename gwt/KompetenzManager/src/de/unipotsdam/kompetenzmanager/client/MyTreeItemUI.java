package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class MyTreeItemUI extends Composite {

	private static MyTreeItemUIUiBinder uiBinder = GWT
			.create(MyTreeItemUIUiBinder.class);
	@UiField Button treeButton;
	private LiteratureView literatureView;

	interface MyTreeItemUIUiBinder extends UiBinder<Widget, MyTreeItemUI> {
	}

	public MyTreeItemUI() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public MyTreeItemUI(String paper, LiteratureView literatureView) {
		initWidget(uiBinder.createAndBindUi(this));
		this.literatureView = literatureView;
		this.treeButton.setText(paper);
	}

	@UiHandler("treeButton")
	void onTreeButtonClick(ClickEvent event) {
		
	}
}
