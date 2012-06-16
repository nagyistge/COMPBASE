package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.ScrollPanel;

public class ShowCompetenceBinder2 extends Composite {

	private static ShowCompetenceBinder2UiBinder uiBinder = GWT
			.create(ShowCompetenceBinder2UiBinder.class);
	@UiField
	ScrollPanel graphScroll;

	interface ShowCompetenceBinder2UiBinder extends
			UiBinder<Widget, ShowCompetenceBinder2> {
	}

	public ShowCompetenceBinder2() {
		initWidget(uiBinder.createAndBindUi(this));
		Element div = this.graphScroll.getElement();
		div.setId("canvas");
		this.getJSON();
	}

	public native void getJSON() /*-{
		$wnd.getJSON();
	}-*/;

}
