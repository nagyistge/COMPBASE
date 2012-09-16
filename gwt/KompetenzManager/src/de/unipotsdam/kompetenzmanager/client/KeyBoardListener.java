package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;

public class KeyBoardListener extends Composite {

	private static KeyBoardListenerUiBinder uiBinder = GWT
			.create(KeyBoardListenerUiBinder.class);
	@UiField FocusPanel focusPanel;
	private ShowCompetenceBinder2 widget;

	interface KeyBoardListenerUiBinder extends
			UiBinder<Widget, KeyBoardListener> {
	}

	public KeyBoardListener(ShowCompetenceBinder2 widget) {
		initWidget(uiBinder.createAndBindUi(this));
		this.widget = widget;
	}

	@UiHandler("focusPanel")
	void onFocusPanelKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_CTRL) {
			this.widget.ctrlClicked = true;
		}		
	}
	@UiHandler("focusPanel")
	void onFocusPanelKeyUp(KeyUpEvent event) {
		this.widget.ctrlClicked = false;
	}
}
