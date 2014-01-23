package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import de.unipotsdam.kompetenzmanager.client.viewcontroller.ViewController;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class MyButton extends Composite {

	private static MyButtonUiBinder uiBinder = GWT
			.create(MyButtonUiBinder.class);
	@UiField Button tagButton;

	interface MyButtonUiBinder extends UiBinder<Widget, MyButton> {
	}

	private String label;
	private ViewController viewcontroller;

	public MyButton(String label, ViewController viewcontroller) {
		initWidget(uiBinder.createAndBindUi(this));	
		this.label = label;
		this.viewcontroller = viewcontroller;
		this.tagButton.setText(label);
	}

	public String getLabel() {
		return this.label;
	}
	
	

	@UiHandler("tagButton")
	void onTagButtonClick(ClickEvent event) {
		this.viewcontroller.showTagsForLiterature(this);
	}
}
