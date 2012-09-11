package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Node;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widgetideas.client.GlassPanel;

import de.unipotsdam.kompetenzmanager.shared.GeometryUtil;
import de.unipotsdam.kompetenzmanager.shared.Graph;

import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.event.logical.shared.ValueChangeEvent;

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
	@UiField
	AbsolutePanel glassPanelContainer;
	@UiField
	TextArea searchTextField;
	@UiField
	Button searchButton;
	@UiField
	Button button;
	@UiField
	ToggleButton toggleButton;

	/**
	 * enthält sich selber, da es aus den EventStubs kein "this" gibt
	 */
	ShowCompetenceBinder2 widget;

	/**
	 * Metadaten zu ClickMenus
	 */
	public ClickMenu clickMenu = null;
	public ElementEntryField elementEntryField = null;
	private Integer x;
	private Integer y;
	public Element canvasDiv;
	GlassPanel glassPanel = null;

	/**
	 * initialisiere View
	 * 
	 * @param kompetenzManager
	 */
	public ShowCompetenceBinder2(String canvasId) {
		GWT.log("Das Graphmodul wurde instantiiert");
		initWidget(uiBinder.createAndBindUi(this));
		this.canvasDiv = this.focusPanel1.getElement();
		this.canvasDiv.setId(canvasId);
		this.glassPanel = new GlassPanel(true);
		this.glassPanel.setVisible(false);
		this.glassPanelContainer.add(glassPanel, 0, 0);
	}

	/**
	 * nativ js calls
	 */
	public native void setGraph(JavaScriptObject json, String canvasId) /*-{
		return $wnd.setGraph(json, canvasId);
	}-*/;

	// depends on getJSON
	public native boolean existsNode(int x, int y, String canvasId) /*-{
		return $wnd.existsNode(x, y, canvasId);
	}-*/;

	/**
	 * get Node at Position x, y relativ to div!
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public native String getNodeID(int x, int y, String canvasId) /*-{
		return $wnd.getNodeIdAtPosition(x, y, canvasId);
	}-*/;

	/**
	 * Handelt das ClickEvent auf dem Graphen
	 * 
	 * @param event
	 */
	@UiHandler("focusPanel1")
	void onFocusPanelClick(ClickEvent event) {
		com.google.gwt.dom.client.Element clickedElement = event
				.getRelativeElement();
		String id = clickedElement.getId();
		// x und y muss man sich merken, damit bei mouseout reagiert werden kann
		// this.x = event.getClientX();
		// this.y = event.getClientY();
		this.x = event.getRelativeX(this.absolutePanel.getElement());
		this.y = event.getRelativeY(this.absolutePanel.getElement());
		// Koordinaten normalisieren
		int x1 = this.x - this.canvasDiv.getAbsoluteLeft()
				+ this.canvasDiv.getScrollLeft();
		int y1 = this.y - this.canvasDiv.getAbsoluteTop()
				+ this.getAbsoluteTop();
		// schauen ob und welcher Knoten geclickt wurde
		if (widget.existsNode(x1, y1, this.canvasDiv.getId())) {
			String nodeId = this.widget.getNodeID(x1, y1,
					this.canvasDiv.getId());
			this.clickMenu = new ClickMenu(id, widget, nodeId);
			widget.addClickMenu(this.clickMenu, x, y);
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
		if (this.x != null && this.y != null) {
			if (!GeometryUtil.inRange(event.getClientX(), event.getClientY(),
					this.x, this.y)) {
				widget.removeClickMenu();
			}
		}
	}

	/**
	 * 
	 * @param clickMenu
	 * @param x
	 * @param y
	 */
	public void addClickMenu(ClickMenu clickMenu, int x, int y) {
		this.clickMenu = clickMenu;
		this.absolutePanel.add(this.clickMenu, x, y);
	}

	/**
	 * 
	 */
	public void removeClickMenu() {
		if (this.clickMenu != null) {
			this.absolutePanel.remove(this.clickMenu);
			this.clickMenu = null;
		}
	}

	/**
	 * Fügt ein EingabeFeld hinzu, um neue Einträge zu machen
	 * 
	 * @param elementEntryField
	 * @param x
	 * @param y
	 */
	public void addElementEntryField(ElementEntryField elementEntryField,
			int x, int y) {
		this.glassPanel.setVisible(true);
		this.elementEntryField = elementEntryField;
		GWT.log("versuche Feld in Koordinaten " + x + "und" + y
				+ "zu erstellen");
		this.absolutePanel.add(this.elementEntryField, x, y);
	}

	/**
	 * Zeigt wieder den Graphen, nachdem eine Eingabe gemacht wurde
	 */
	public void removeElementEntryField() {
		if (this.elementEntryField != null) {
			this.glassPanel.setVisible(false);
			this.absolutePanel.remove(this.elementEntryField);
			this.elementEntryField = null;
		}
	}

	public void removeGraph() {
		Node graph = this.canvasDiv.getChild(0);
		this.canvasDiv.removeChild(graph);
	}

	@UiHandler("searchButton")
	void onSearchButtonClick(ClickEvent event) {
		GraphBackendImpl backendImpl = new GraphBackendImpl(widget);
		backendImpl.findShortestPath(this.searchTextField.getText(),
				new GraphUpdater<Graph>(widget));
	}

	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
	}

	@UiHandler("toggleButton")
	void onToggleButtonClick(ClickEvent event) {
		GWT.log("switched");
	}
}