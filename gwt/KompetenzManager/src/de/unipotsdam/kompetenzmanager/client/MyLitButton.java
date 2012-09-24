package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

import de.unipotsdam.kompetenzmanager.client.viewcontroller.ViewController;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class MyLitButton extends Composite {

	private static MyLitButtonUiBinder uiBinder = GWT
			.create(MyLitButtonUiBinder.class);
	@UiField Button literatureButton;
	private LiteratureEntry literatureEntry;
	private ViewController viewcontroller;

	interface MyLitButtonUiBinder extends UiBinder<Widget, MyLitButton> {
	}

	public MyLitButton() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public MyLitButton(ViewController viewController, LiteratureEntry literatureEntry) {
		initWidget(uiBinder.createAndBindUi(this));
		this.literatureEntry = literatureEntry;
		this.viewcontroller = viewController;
		this.literatureButton.setText(literatureEntry.shortName);
	}
	
	

	@UiHandler("literatureButton")
	void onLiteratureButtonClick(ClickEvent event) {
		viewcontroller.showLiteratureToTags(literatureEntry);
	}
}
