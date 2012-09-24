package de.unipotsdam.kompetenzmanager.client.viewcontroller;

import java.util.Collection;

import com.google.gwt.core.client.GWT;

import de.unipotsdam.kompetenzmanager.client.LiteratureView;
import de.unipotsdam.kompetenzmanager.client.ManyToManyConnector;
import de.unipotsdam.kompetenzmanager.client.MultiClickMenu;
import de.unipotsdam.kompetenzmanager.client.MyButton;
import de.unipotsdam.kompetenzmanager.client.ShowCompetenceBinder2;
import de.unipotsdam.kompetenzmanager.client.TabbedView;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphLiteraturePair;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;
import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class ViewController {
	private ShowCompetenceBinder2 widget;
	private LiteratureView literatureview;
	private TabbedView tabbedView;
	private Boolean graphIsVisible;
	private boolean inprocess;

	public ViewController(ShowCompetenceBinder2 widget,
			LiteratureView literatureView, TabbedView tabbedView) {
		this.setWidget(widget);
		this.setLiteratureview(literatureView);
		this.setTabbedView(tabbedView);
		this.graphIsVisible = true;
	}

	public Boolean isVisibleGraphTab() {
		return graphIsVisible;
	}

	public void switchTab() {
		this.graphIsVisible = !graphIsVisible;
	}

	public void switchTab(Boolean graphIsVisible) {
		this.graphIsVisible = graphIsVisible;
	}

	protected void changeSelectedTab(Boolean graphIsVisible) {
		if (graphIsVisible) {
			// tabbedView.getTabLayoutPanel().selectTab(widget);
			tabbedView.getTabLayoutPanel().selectTab(0);
		} else {
			// tabbedView.getTabLayoutPanel().selectTab(literatureview);
			tabbedView.getTabLayoutPanel().selectTab(1);
		}
	}

	public void addConnectLitAndGraphMenu() {
		ManyToManyConnector manyToManyConnector = new ManyToManyConnector(this);
		this.widget.addMultiClickMenu(manyToManyConnector);
		this.literatureview.addMultiClickMenu(manyToManyConnector);
		changeSelectedTab(false);
	}

	public void connectTagsToLiterature(Graph graph, Literature literature) {
		while (this.inprocess) {
		}
		this.inprocess = true;
		if (!graph.nodes.isEmpty() && !literature.literatureEntries.isEmpty()) {
			GraphBackendImpl backendImpl = new GraphBackendImpl(widget);
			backendImpl.connectLiteratureToGraph(literature, graph,
					new GraphAndLiteratureUpdater<GraphLiteraturePair>(this));
		} else {
			GWT.log("Für die Abbildung muss mindest ein Literatureintrag oder Knoten gewählt sein");
		}
		removeMultiClickMenu();
		this.inprocess = false;
		this.changeSelectedTab(false);
	}

	public void removeMultiClickMenu() {
		this.widget.removeMultiClickMenu();
		this.literatureview.removeMultiClickMenu();
	}

	/**
	 * @param widget
	 *            the widget to set
	 */
	public void setWidget(ShowCompetenceBinder2 widget) {
		this.widget = widget;
	}

	/**
	 * @return the widget
	 */
	public ShowCompetenceBinder2 getWidget() {
		return widget;
	}

	/**
	 * @param literatureview
	 *            the literatureview to set
	 */
	public void setLiteratureview(LiteratureView literatureview) {
		this.literatureview = literatureview;
	}

	/**
	 * @return the literatureview
	 */
	public LiteratureView getLiteratureview() {
		return literatureview;
	}

	/**
	 * @param tabbedView
	 *            the tabbedView to set
	 */
	public void setTabbedView(TabbedView tabbedView) {
		this.tabbedView = tabbedView;
	}

	/**
	 * @return the tabbedView
	 */
	public TabbedView getTabbedView() {
		return tabbedView;
	}

	public void addThemeTag(LiteratureEntry literatureEntry) {
		while (this.inprocess) {
		}
		this.inprocess = true;
		GraphBackendImpl backendImpl = new GraphBackendImpl(widget);
		Literature litEntry = new Literature(
				literatureview.shownLiteratureEntryBinder.shownLiteratureEntry);
		if (literatureEntry.graph.nodes.isEmpty()) {
			backendImpl.getFullGraph(null);
		} else {
			backendImpl.getTagsforLiterature(litEntry, new GraphUpdater<Graph>(
					widget));
		}
		this.inprocess = false;
		changeSelectedTab(true);

	}

	public void tagSelectionToLiterature() {
		addConnectLitAndGraphMenu();
	}

	public void showLiteratureToTags() {
		Graph selectedGraph = convertToGraph(this.widget.getSelectedElements());
		if (selectedGraph.nodes.isEmpty()) {
			GWT.log("Keine Elemente ausgewählt, kann den graphen nicht anzeigen");
		} else {
			while (this.inprocess) {
			}
			this.inprocess = true;
			GraphBackendImpl backendImpl = new GraphBackendImpl(widget);
			backendImpl.getLiteratureForTags(selectedGraph,
					new LiteratureUpdater<Literature>(literatureview));
			this.inprocess = false;
			changeSelectedTab(false);
		}
	}

	// private void showLiteratureToTags(Graph selectedGraph) {
	// this.inprocess = true;
	// GraphBackendImpl backendImpl = new GraphBackendImpl(widget);
	// backendImpl.getLiteratureForTags(selectedGraph, new
	// LiteratureUpdater<Literature>(this.literatureview));
	// this.inprocess = false;
	// changeSelectedTab(false);
	// }

	public Graph convertToGraph(Collection<String> selectedElements) {
		Graph result = new Graph();
		for (String elem : selectedElements) {
			result.nodes.add(new GraphNode(elem));
		}
		return result;

	}

	public void showTagsForLiterature(MyButton button) {
		changeSelectedTab(true);
		while (this.inprocess) {
		}
		this.inprocess = true;
		GraphBackendImpl backendImpl = new GraphBackendImpl(widget);
		backendImpl.findShortestPath(button.getLabel(),
				new GraphUpdater<Graph>(widget));
		this.inprocess = false;
	}

	public void showLiteratureToTags(LiteratureEntry literatureEntry) {
		while (this.inprocess) {
		}
		this.inprocess = true;
		LiteratureUpdater<Literature> literatureUpdater = new LiteratureUpdater<Literature>(
				getLiteratureview());
		literatureUpdater.onSuccess(new Literature(literatureEntry));
		this.inprocess = false;
		changeSelectedTab(false);
	}

	public void deleteLitEntry(LiteratureEntry shownLiteratureEntry) {
		while (this.inprocess) {
		}
		this.inprocess = true;
		GraphBackendImpl backendImpl = new GraphBackendImpl(this.widget);
		backendImpl.removeLiteratureEntry(this.literatureview.getStoredLiterature(), shownLiteratureEntry, new LiteratureUpdater<Literature>(literatureview));
		this.inprocess = false;
		changeSelectedTab(false);
		
	}
}
