/**
 * 
 */
package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;

/**
 * @author Julian
 *
 */
public class ElementEntryField extends Composite {

	private static ElementEntryFieldUiBinder uiBinder = GWT
			.create(ElementEntryFieldUiBinder.class);

	interface ElementEntryFieldUiBinder extends
			UiBinder<Widget, ElementEntryField> {
	}

	private ShowCompetenceBinder2 showCompetenceBinder2;		
	private String nodeId;

	/**
	 * Because this class has a default constructor, it can
	 * be used as a binder template. In other words, it can be used in other
	 * *.ui.xml files as follows:
	 * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
	 *   xmlns:g="urn:import:**user's package**">
	 *  <g:**UserClassName**>Hello!</g:**UserClassName>
	 * </ui:UiBinder>
	 * Note that depending on the widget that is used, it may be necessary to
	 * implement HasHTML instead of HasText.
	 * @param id 
	 * @param showCompetenceBinder22 
	 */
	public ElementEntryField(ShowCompetenceBinder2 showCompetenceBinder22, String nodeID) {
		initWidget(uiBinder.createAndBindUi(this));	
		this.ElementNameLabel.setStyleName("whiteLabels");
		this.KantenBeschriftung.setStyleName("whiteLabels");		
		this.showCompetenceBinder2 = showCompetenceBinder22;	
		this.nodeId = nodeID;
	}

	@UiField Button enterElementButton;
	@UiField Label ElementNameLabel;
	@UiField Label KantenBeschriftung;
	@UiField TextBox elementEntryTextfield;
	@UiField TextBox kantenbeschriftungTextField;
	@UiField Button abbrechenButton;

	@UiHandler("enterElementButton")
	void onClick(ClickEvent e) {
		//frage Server nach initialen Daten für den Graph TODO: should be only on request
		GraphBackendImpl backendImpl = new GraphBackendImpl(this.showCompetenceBinder2);
		GraphNode sourceNode = new GraphNode(this.nodeId);
		GraphNode newNode = new GraphNode(this.elementEntryTextfield.getText());
		String kantenLabel = this.kantenbeschriftungTextField.getText();
		backendImpl.addNode(sourceNode, newNode, kantenLabel, null); // if null ist default, then basic view is updated
		this.showCompetenceBinder2.removeElementEntryField();
	}
	
	@UiHandler("abbrechenButton")
	void onAbbrechenClick(ClickEvent e) {
		this.showCompetenceBinder2.removeElementEntryField();
	}



}
