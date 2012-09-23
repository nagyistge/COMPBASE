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
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;

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
	private Literature literature;
	private Graph graph;

	public ManyToManyConnector() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ManyToManyConnector(ViewController viewController) {
		initWidget(uiBinder.createAndBindUi(this));
		this.literatureStringMap = new HashMap<String, LiteratureEntry>();
		this.literature = new Literature();
		this.graph = new Graph();
		this.viewcontroller = viewController;
		this.widget = viewController.getWidget();
		fillMultiBoxes(viewController);
	}

	private void fillMultiBoxes(ViewController viewController) {
		for (String elem : widget.getSelectedElements()) {
			if (!elem.equals("rootnode")) {
				this.tagListe.addItem(elem);
			}
		}
		int i = 0;
		for (LiteratureEntry literatureEntry : viewController
				.getLiteratureview().getStoredLiterature().literatureEntries) {
			this.literaturListe.addItem(literatureEntry.shortName);
			if (this.widget.getRelevanteLiteratur().contains(literatureEntry)) {
				this.literaturListe.setItemSelected(i, true);
			}
			this.literatureStringMap.put(literatureEntry.shortName,
					literatureEntry);
			i++;
		}
	}

	@UiHandler("tagButton")
	void onTagButtonClick(ClickEvent event) {
		// Graph graph = getGraphFromSelection();
		// Literature literature = getLiteratureFromSelectedList();
		if (!graph.nodes.isEmpty() && !literature.literatureEntries.isEmpty()) {
			GraphBackendImpl backendImpl = new GraphBackendImpl(widget);
			backendImpl.connectLiteratureToGraph(literature, graph,
					new GraphAndLiteratureUpdater<GraphLiteraturePair>());
		} else {
			GWT.log("Für die Abbildung muss mindest ein Literatureintrag oder Knoten gewählt sein");
		}
		viewcontroller.removeMultiClickMenu();
	}

	private Literature getLiteratureFromSelectedList() {
		Collection<String> selected = new HashSet<String>();
		getSelectedItems(selected, literaturListe);
		Literature literature = new Literature();
		for (String string : selected) {
			literature.literatureEntries.add(this.literatureStringMap
					.get(string));
		}
		return literature;
	}

	private Graph getGraphFromSelection() {
		Collection<String> selected = new HashSet<String>();
		ListBox listbox = this.tagListe;
		getSelectedItems(selected, listbox);
		Graph graph = viewcontroller.convertToGraph(selected);
		return graph;
	}

	public void getSelectedItems(Collection<String> selected, ListBox listbox) {
		HashSet<Integer> indexes = new HashSet<Integer>();
		// while (listbox.getItemCount() > 0) {
		// int i = listbox.getSelectedIndex();
		// if (i > 0) {
		// String selectedElem = listbox.getElement().getChild(i)
		// .getFirstChild().toString();
		// selected.add(selectedElem);
		// listbox.removeItem(i);
		// } else {
		// for (int j = 0; j < listbox.getItemCount(); j++) {
		// listbox.removeItem(0);
		// }
		// }
		// }
		while (listbox.getSelectedIndex() > 0) {
			int index = listbox.getSelectedIndex();
			listbox.setItemSelected(index, false);
			String selectedElem = listbox.getElement().getChild(index)
					.getFirstChild().toString();
			selected.add(selectedElem);
			indexes.add(index);
		}
		for (Integer index : indexes) {
			listbox.setItemSelected(index, true);
		}
	}

	@UiHandler("abbrechenButton")
	void onAbbrechenButtonClick(ClickEvent event) {
		this.viewcontroller.removeMultiClickMenu();
	}

	// @UiHandler("literaturListe")
	// void onLiteraturListeChange(ChangeEvent event) {
	// pullDataFromLiteratureBox();
	// }

	public void pullDataFromLiteratureBox() {
		this.literature.literatureEntries.clear();
		this.literature.literatureEntries
				.addAll(getLiteratureFromSelectedList().literatureEntries);
	}

	// @UiHandler("tagListe")
	// void onTagListeChange(ChangeEvent event) {
	// pullDataFromTagbox();
	// }

	public void pullDataFromTagbox() {
		this.graph.nodes.clear();
		this.graph.nodes.addAll(getGraphFromSelection().nodes);
	}

	@UiHandler("literaturListe")
	void onLiteraturListeMouseOut(MouseOutEvent event) {
		pullDataFromLiteratureBox();
	}

	@UiHandler("tagListe")
	void onTagListeMouseOut(MouseOutEvent event) {
		pullDataFromTagbox();
	}
}
