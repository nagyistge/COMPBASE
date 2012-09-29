package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;

import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphBackendAsync;
import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphBackendImpl;
import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphUpdater;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;

public class ClickMenu extends Composite {

	private static ClickMenuUiBinder uiBinder = GWT
			.create(ClickMenuUiBinder.class);
	@UiField
	FocusPanel focusPanel2;
	@UiField
	Button newNode;

	@UiField
	Button removeNode;
	@UiField
	Button showNeighbours;
	@UiField Button connectButton;

	public String id;
	ShowCompetenceBinder2 widget;
	private String nodeId;

	interface ClickMenuUiBinder extends UiBinder<Widget, ClickMenu> {
	}

	public ClickMenu(String id, ShowCompetenceBinder2 showCompetenceBinder2,
			String nodeId) {
		initWidget(uiBinder.createAndBindUi(this));
		this.widget = showCompetenceBinder2;
		this.id = id;
		this.nodeId = nodeId;
		GWT.log("Die NodeID ist " + nodeId);
		//for presentation purposes 
		this.removeNode.setVisible(false);
		this.removeNode.setEnabled(false);
		
	}

	@UiHandler("focusPanel2")
	void onFocusPanelMouseOut(MouseOutEvent event) {
		widget.removeClickMenu();
	}

	@UiHandler("newNode")
	void onnewNodeClick(ClickEvent event) {
		GWT.log("newNode has been clicked");
		widget.removeClickMenu();
		ElementEntryField elementEntryField = new ElementEntryField(
				this.widget, this.nodeId);		
		widget
				.addDataEntryField(
						elementEntryField						
						);
	}

	@UiHandler("removeNode")
	void onremoveNodeClick(ClickEvent event) {
		GWT.log("removeNode has been clicked");
		widget.removeClickMenu();
		if (this.widget == null) {
			GWT.log("showCompetenceBinder ist null");
		}		
//		GraphBackendAsync backendImpl = new GraphBackendImpl(
//				this.widget);
		GraphNode targetNode = new GraphNode(this.nodeId);
		if (this.widget.getNewGraph()) {
			widget.viewcontroller.getBackendImpl().removeNode(targetNode, new GraphUpdater<Graph>(
					widget));
		} else {
			if (this.widget.getStoredGraph() != null) {
				widget.viewcontroller.getBackendImpl().removeNode(this.widget
						.getStoredGraph(), targetNode, new GraphUpdater<Graph>(
						widget));
			} else {
				GWT.log("Graph has not been stored");
			}
		} 		
		
		// eventuell nur das entsprechende Element rausnehmen
		widget.clearSelectedElements();
	}

	@UiHandler("showNeighbours")
	void onShowNeighboursClick(ClickEvent event) {
//		GraphBackendAsync backendImpl = new GraphBackendImpl(
//				this.widget);
		GraphNode gNode = new GraphNode(this.nodeId);
		if (this.widget.getNewGraph()) {
			widget.viewcontroller.getBackendImpl().expandNode(gNode.label, new GraphUpdater<Graph>(this.widget));
		} else {
			widget.viewcontroller.getBackendImpl().expandNode(this.widget.getStoredGraph(), gNode.label, new GraphUpdater<Graph>(widget));
		}
	}
	@UiHandler("connectButton")
	void onConnectButtonClick(ClickEvent event) {
		GWT.log("connecting nodes:" +this.nodeId + " " + widget.getSelectedElements());
//		GraphBackendAsync backendImpl = new GraphBackendImpl(
//				this.widget);		
		widget.removeClickMenu();		
		if (this.widget.getNewGraph()) {
			widget.viewcontroller.getBackendImpl().connectNodes(widget.getSelectedElements(), this.nodeId, new GraphUpdater<Graph>(widget));
		} else {
			widget.viewcontroller.getBackendImpl().connectNodes(widget.getStoredGraph(),widget.getSelectedElements(), this.nodeId, new GraphUpdater<Graph>(widget));
		}
		widget.clearSelectedElements();
	}
}
