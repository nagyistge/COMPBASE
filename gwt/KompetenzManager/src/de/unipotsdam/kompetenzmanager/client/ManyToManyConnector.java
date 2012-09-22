package de.unipotsdam.kompetenzmanager.client;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphAndLiteratureUpdater;
import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphBackendImpl;
import de.unipotsdam.kompetenzmanager.client.viewcontroller.ViewController;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphLiteraturePair;
import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class ManyToManyConnector extends Composite {

	private static ManyToManyConnectorUiBinder uiBinder = GWT
			.create(ManyToManyConnectorUiBinder.class);
	@UiField
	ListBox literaturListe;
	@UiField
	ListBox tagListe;
	@UiField
	Button tagButton;
	@UiField
	Button abbrechenButton;

	interface ManyToManyConnectorUiBinder extends
			UiBinder<Widget, ManyToManyConnector> {
	}

	private ShowCompetenceBinder2 widget;
	private ViewController viewcontroller;
	private HashMap<String, LiteratureEntry> literatureStringMap;

	public ManyToManyConnector() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ManyToManyConnector(ViewController viewController) {
		initWidget(uiBinder.createAndBindUi(this));
		this.literatureStringMap = new HashMap<String, LiteratureEntry>();
		this.viewcontroller = viewController;
		this.widget = viewController.getWidget();
		fillMultiBoxes(viewController);
	}

	private void fillMultiBoxes(ViewController viewController) {
		for (String elem : widget.getSelectedElements()) {
			this.tagListe.addItem(elem);
		}
		int i = 0;
		for (LiteratureEntry literatureEntry : viewController
				.getLiteratureview().getStoredLiterature().literatureEntries) {
			if (this.widget.getRelevanteLiteratur().contains(literatureEntry)) {
				this.literaturListe.setItemSelected(i, true);
			}			
			this.literatureStringMap.put(literatureEntry.shortName,literatureEntry);
			i++;
		}
	}

	@UiHandler("tagButton")
	void onTagButtonClick(ClickEvent event) {
		Graph graph = getGraphFromSelection();
		Literature literature = getLiteratureFromSelectedList();
		GraphBackendImpl backendImpl = new GraphBackendImpl(widget);
		backendImpl.connectLiteratureToGraph(literature, graph, new GraphAndLiteratureUpdater<GraphLiteraturePair>());
		viewcontroller.removeMultiClickMenu();
	}

	private Literature getLiteratureFromSelectedList() {
		Collection<String> selected = new HashSet<String>();
		while (this.literaturListe.getItemCount() > 0) {
			selected.add(this.tagListe.getElement().getChild(this.tagListe.getSelectedIndex()).getNodeValue());
		}
		Literature literature = new Literature();
		for (String string : selected) {
			literature.literatureEntries.add(this.literatureStringMap.get(string));
		}
		return literature;
	}

	private Graph getGraphFromSelection() {
		Collection<String> selected = new HashSet<String>();
		while (this.tagListe.getItemCount() > 0) {
			selected.add(this.tagListe.getElement().getChild(this.tagListe.getSelectedIndex()).getNodeValue());
		}
		Graph graph = viewcontroller.convertToGraph(selected);
		return graph;
	}

	@UiHandler("abbrechenButton")
	void onAbbrechenButtonClick(ClickEvent event) {
		this.viewcontroller.removeMultiClickMenu();
	}
}
