package de.unipotsdam.kompetenzmanager.client;

import java.util.Collection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.event.dom.client.MouseMoveEvent;

import de.unipotsdam.kompetenzmanager.shared.GeometryUtil;
import de.unipotsdam.kompetenzmanager.shared.Graph;

import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.json.client.JSONObject;


public class ShowCompetenceBinder2 extends Composite {

	interface ShowCompetenceBinder2UiBinder extends
			UiBinder<Widget, ShowCompetenceBinder2> {
	}

	private static ShowCompetenceBinder2UiBinder uiBinder = GWT
			.create(ShowCompetenceBinder2UiBinder.class);
	/**
	 * UI Felder
	 */
	@UiField
	AbsolutePanel absolutePanel;
	@UiField
	FocusPanel focusPanel1;

	/**
	 * Metadaten zu ClickMenus
	 */
	public ClickMenu clickMenu = null;
	private Integer x;
	private Integer y;
	private Element canvasDiv;	

	/**
	 * initialisiere View
	 */
	public ShowCompetenceBinder2() {
		GWT.log("Das Graphmodul wurde instantiiert");
		initWidget(uiBinder.createAndBindUi(this));
		this.canvasDiv = this.focusPanel1.getElement();
		this.canvasDiv.setId("canvas");																		
	}

	/**
	 * nativ js calls
	 */
	public native void setGraph(JavaScriptObject json) /*-{
		$wnd.setGraph(json);			
	}-*/;
	
	// depends on getJSON
	public native boolean existsNode(int x, int y) /*-{
		return $wnd.existsNode(x, y);
	}-*/;
	
	/**
	 * Handelt das ClickEvent auf dem Graphen
	 * 
	 * @param event
	 */
	@UiHandler("focusPanel1")
	void onFocusPanelClick(ClickEvent event) {
		ShowCompetenceBinder2 widget = (ShowCompetenceBinder2) RootPanel.get(
				"content").getWidget(0);
		com.google.gwt.dom.client.Element clickedElement = event
				.getRelativeElement();
		String id = clickedElement.getId();
		this.x = event.getClientX();
		this.y = event.getClientY();

		// / TODO find out which element TODO NULLpointer Exception
		if (widget.existsNode(x - this.canvasDiv.getAbsoluteLeft(), y
				- this.canvasDiv.getAbsoluteTop())) {
			this.clickMenu = new ClickMenu(id, x, y);
			widget.absolutePanel.add(this.clickMenu, x, y);
		}
	}

	/**
	 * Handelt das MouseEvent auf dem Graphen (Menus sollen verschwinden, wenn
	 * die Mouse die Gegend verlässt
	 * 
	 * @param event
	 */
	@UiHandler("focusPanel1")
	void onFocusPanel1MouseMove(MouseMoveEvent event) {
		ShowCompetenceBinder2 widget = (ShowCompetenceBinder2) RootPanel.get(
				"content").getWidget(0);
		if (this.x != null && this.y != null) {
			if (!GeometryUtil.inRange(event.getClientX(), event.getClientY(),
					this.x, this.y)) {
				widget.absolutePanel.remove(widget.clickMenu);
			}
		}
	}

}