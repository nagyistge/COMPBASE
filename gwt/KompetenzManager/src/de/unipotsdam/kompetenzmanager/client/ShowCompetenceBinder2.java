package de.unipotsdam.kompetenzmanager.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widgetideas.client.GlassPanel;

import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphBackendAsync;
import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphBackendImpl;
import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphUpdater;
import de.unipotsdam.kompetenzmanager.client.viewcontroller.ViewController;
import de.unipotsdam.kompetenzmanager.shared.GeometryUtil;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

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
	TextBox searchFromTextField;
	@UiField
	TextBox searchToTextField;
	@UiField
	Button searchButton;
	@UiField
	Button searchPathButton;
	@UiField
	ToggleButton toggleButton;
	@UiField
	ScrollPanel SelectedListPanel;
	@UiField
	Button deleteSelection;
	@UiField
	VerticalPanel VerticalPanel;
	@UiField Button resetButton;
	@UiField HorizontalPanel selectedLiterature;
	@UiField Button auswahlTaggen;
	@UiField Button literaturAnzeigen;

	/**
	 * enthält sich selber, da es aus den EventStubs kein "this" gibt
	 */
	public ShowCompetenceBinder2 widget;

	/**
	 * Metadaten zu ClickMenus
	 */
	public Composite menu = null;
	public Composite dataEntryField = null;
	public Element canvasDiv;
	public GlassPanel glassPanel = null;

	// TODO refactor
	private Integer x;
	private Integer y;

	private Boolean newGraph = true;
	private Graph storedGraph;
	public TabbedView tabbed;
	public boolean ctrlClicked = false;
	private Collection<String> selectedElements;
	private boolean shiftKey;
	public ViewController viewcontroller;
	private Literature relevanteLiterur;

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
		this.setSelectedElements(new ArrayList<String>());
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
			//do not refactor unless you are absolutely sure what you are doing
			if (this.ctrlClicked) {
				Composite optionMenu = new MultiClickMenu(id, widget, nodeId);
				showMenu(optionMenu);
			} else if (this.shiftKey) {
				addSelectedElementToList(nodeId);
			} else {
				Composite optionMenu = new ClickMenu(id, widget, nodeId);
				showMenu(optionMenu);
			}			
		}
	}

	private void showMenu(Composite clickMenu) {
		this.menu = clickMenu;
		widget.addClickMenu(this.menu, x, y);
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
				this.ctrlClicked = false;
				this.shiftKey = false;
			}
		}
	}

	/**
	 * 
	 * @param clickMenu
	 * @param x
	 * @param y
	 */
	public void addClickMenu(Composite clickMenu, int x, int y) {
		this.menu = clickMenu;
		this.absolutePanel.add(this.menu, x, y);
	}

	/**
	 * 
	 * @param clickMenu
	 * @param x
	 * @param y
	 */
	public void addClickMenu(Composite clickMenu) {
		this.menu = clickMenu;
		this.absolutePanel.add(this.menu, this.x, this.y);
	}

	/**
	 * 
	 */
	public void removeClickMenu() {
		if (this.menu != null) {
			this.absolutePanel.remove(this.menu);
			this.menu = null;
		}
	}

	/**
	 * Fügt ein EingabeFeld hinzu, um neue Einträge zu machen
	 * 
	 * @param dataEntryField
	 * @param x
	 * @param y
	 */
	public void addDataEntryField(Composite dataEntryField) {
		this.glassPanel.setVisible(true);
		this.dataEntryField = dataEntryField;
		this.absolutePanel.add(this.dataEntryField, widget.absolutePanel
				.getWidgetLeft(widget.glassPanelContainer) + 20, widget.absolutePanel
				.getWidgetTop(widget.glassPanelContainer) + 20);
		
	}

	/**
	 * Zeigt wieder den Graphen, nachdem eine Eingabe gemacht wurde
	 */
	public void removeDataEntryField() {
		if (this.dataEntryField != null) {
			this.glassPanel.setVisible(false);
			this.absolutePanel.remove(this.dataEntryField);
			this.dataEntryField = null;
		}
	}

	public void removeGraph() {
		Node graph = this.canvasDiv.getChild(0);
		this.canvasDiv.removeChild(graph);
	}

	@UiHandler("searchButton")
	void onSearchButtonClick(ClickEvent event) {
		GraphBackendAsync backendImpl = new GraphBackendImpl(widget);
		if (this.getNewGraph()) {
			backendImpl.findShortestPath(this.searchTextField.getText(),
					new GraphUpdater<Graph>(widget));
		} else {
			if (getStoredGraph() != null) {
				backendImpl.findShortestPath(getStoredGraph(),
						this.searchTextField.getText(),
						new GraphUpdater<Graph>(widget));
			} else {
				GWT.log("Graph should have been stored but is not");
			}
		}
	}

	@UiHandler("searchPathButton")
	void onButtonClick(ClickEvent event) {
		GraphBackendAsync backendImpl = new GraphBackendImpl(widget);
		String fromNode = this.searchFromTextField.getText();
		String toNode = this.searchToTextField.getText();
		if (this.getNewGraph()) {
			backendImpl.findShortestPath(fromNode, toNode,
					new GraphUpdater<Graph>(widget));
		} else {
			if (getStoredGraph() != null) {
				backendImpl.findShortestPath(getStoredGraph(), fromNode,
						toNode, new GraphUpdater<Graph>(widget));
			} else {
				GWT.log("Graph should have been stored but is not");
			}
		}
	}

	@UiHandler("toggleButton")
	void onToggleButtonClick(ClickEvent event) {
		this.newGraph = !this.newGraph;
	}

	public void storeGraph(Graph result) {
		this.setStoredGraph(result);
	}

	private void setStoredGraph(Graph storedGraph) {
		this.storedGraph = storedGraph;
	}

	public Graph getStoredGraph() {
		return storedGraph;
	}

	public Boolean getNewGraph() {
		return newGraph;
	}

	public void addSelectedElementToList(String nodeId) {
		GWT.log("adding selected element to list:" + nodeId);
		this.getSelectedElements().add(nodeId);
		Label label = new Label(nodeId);
		// this.SelectedListPanel.getElement().getChild(0)
		// .appendChild(label.getElement());
		this.VerticalPanel.add(label);		
	}

	@UiHandler("deleteSelection")
	void onDeleteSelectionClick(ClickEvent event) {
		clearSelectedElements();
	}

	public void clearSelectedElements() {
		this.getSelectedElements().clear();
		this.VerticalPanel.clear();		
	}

	@UiHandler("focusPanel1")
	void onFocusPanel1KeyPress(KeyPressEvent event) {
		this.ctrlClicked = event.isControlKeyDown();
	}

	@UiHandler("focusPanel1")
	void onFocusPanel1KeyDown(KeyDownEvent event) {
		this.ctrlClicked = event.isControlKeyDown();
		this.shiftKey = event.isShiftKeyDown();
	}

	/**
	 * @param selectedElements the selectedElements to set
	 */
	public void setSelectedElements(Collection<String> selectedElements) {
		this.selectedElements = selectedElements;
	}

	/**
	 * @return the selectedElements
	 */
	public Collection<String> getSelectedElements() {
		return selectedElements;
	}

	// @UiHandler("focusPanel1")
	// void onFocusPanel1KeyUp(KeyUpEvent event) {
	// this.ctrlClicked = !event.getNativeEvent().getCtrlKey();
	// this.ctrlClicked = !event.getNativeEvent().getShiftKey();
	// }
	@UiHandler("resetButton")
	void onResetButtonClick(ClickEvent event) {
		GraphBackendAsync backendImpl = new GraphBackendImpl(widget);
		backendImpl.getFullGraph(null);
	}

	public void addMultiClickMenu(ManyToManyConnector manyToManyConnector) {
		addDataEntryField(manyToManyConnector);		
	}

	public void removeMultiClickMenu() {
		removeDataEntryField();
		
	}

	public HashSet<LiteratureEntry> getRelevanteLiteratur() {
		return this.relevanteLiterur.literatureEntries;
	}

	public void setRelevanteLiteratur(Literature result) {
		this.relevanteLiterur = result;
		
	}
	@UiHandler("auswahlTaggen")
	void onAuswahlTaggenClick(ClickEvent event) {
		this.widget.viewcontroller.tagSelectionToLiterature();
	}
	@UiHandler("literaturAnzeigen")
	void onLiteraturAnzeigenClick_1(ClickEvent event) {
		this.widget.viewcontroller.showLiteratureToTags();
	}
}