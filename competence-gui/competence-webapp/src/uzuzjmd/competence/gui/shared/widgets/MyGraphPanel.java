package uzuzjmd.competence.gui.shared.widgets;

import java.util.Collection;

import uzuzjmd.competence.gui.client.TabbedView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Node;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;

public class MyGraphPanel extends Composite {

	interface MyGraphPanelUiBinder extends UiBinder<Widget, MyGraphPanel> {
	}

	private static MyGraphPanelUiBinder uiBinder = GWT
			.create(MyGraphPanelUiBinder.class);

	/**
	 * UI Felder
	 */
	@UiField
	AbsolutePanel absolutePanel;
	@UiField
	FocusPanel focusPanel1;

	public MyGraphPanel widget;

	/**
	 * FieldVerifier.java public ShowCompetenceBinder2 widget;
	 * 
	 * /** Metadaten zu ClickMenus
	 */
	public Composite menu = null;
	public Composite dataEntryField = null;
	public Element canvasDiv;

	// TODO refactor
	private Integer x;
	private Integer y;

	public TabbedView tabbed;
	public boolean ctrlClicked = false;
	private Collection<String> selectedElements;
	private boolean shiftKey;
	private ContentLoader optionMenuCtrlClick;
	private ContentLoader optionMenuNormalClick;

	private String canvasId = "canvas";

	/**
	 * initialisiere View
	 * 
	 * @param kompetenzManager
	 */
	public MyGraphPanel(ClickMenu optionMenuCtrlClick,
			ClickMenu optionMenuNormalClick) {
		this.optionMenuCtrlClick = optionMenuCtrlClick;
		this.optionMenuNormalClick = optionMenuNormalClick;
		GWT.log("Das Graphmodul wurde instantiiert");
		initWidget(uiBinder.createAndBindUi(this));
		this.canvasDiv = this.focusPanel1.getElement();
		this.canvasDiv.setId(canvasId);
		this.widget = this;
		this.focusPanel1.setStyleName("focusPanel1", true);
	}

	public void setGraph(JSONValue graph) {
		setGraph(graph.isObject().getJavaScriptObject(), canvasId, 625, 550);
	}

	/**
	 * nativ js calls
	 */
	private native void setGraph(JavaScriptObject json, String canvasId,
			Integer width, Integer height) /*-{
		return $wnd.setGraph(json, canvasId, width, height);
	}-*/;

	// depends on getJSON
	private native boolean existsNode(int x, int y, String canvasId) /*-{
		return $wnd.existsNode(x, y, canvasId);
	}-*/;

	/**
	 * get Node at Position x, y relativ to div!
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private native String getNodeID(int x, int y, String canvasId) /*-{
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
			// do not refactor unless you are absolutely sure what you are doing
			if (this.ctrlClicked) {
				optionMenuCtrlClick.setId(id);
				optionMenuCtrlClick.setNodeId(nodeId);
				optionMenuCtrlClick.loadContent();
				if (optionMenuCtrlClick instanceof Composite) {
					addClickMenu((Composite) optionMenuCtrlClick);
				}
				this.focusPanel1.setFocus(false);
			} else {
				optionMenuNormalClick.setId(id);
				optionMenuNormalClick.setNodeId(nodeId);
				optionMenuNormalClick.loadContent();
				if (optionMenuNormalClick instanceof Composite) {
					addClickMenu((Composite) optionMenuNormalClick);
				}
				this.focusPanel1.setFocus(false);
			}
		}
		// focusPanel1.setFocus(false);
	}

	// private void showMenu(Composite clickMenu) {
	// this.menu = clickMenu;
	// widget.addClickMenu(this.menu, x, y);
	// }

	/**
	 * Handelt das MouseEvent auf dem Graphen (Menus sollen verschwinden, wenn
	 * die Mouse die Gegend verl�sst
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
	 * Zeigt wieder den Graphen, nachdem eine Eingabe gemacht wurde
	 */
	public void removeDataEntryField() {
		if (this.dataEntryField != null) {
			this.absolutePanel.remove(this.dataEntryField);
			this.dataEntryField = null;
		}
	}

	public void removeGraph() {
		Node graph = this.canvasDiv.getChild(0);
		this.canvasDiv.removeChild(graph);
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

}