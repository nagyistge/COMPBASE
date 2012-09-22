package de.unipotsdam.kompetenzmanager.client.viewcontroller;

import java.util.Collection;

import de.unipotsdam.kompetenzmanager.client.LiteratureView;
import de.unipotsdam.kompetenzmanager.client.ManyToManyConnector;
import de.unipotsdam.kompetenzmanager.client.MultiClickMenu;
import de.unipotsdam.kompetenzmanager.client.ShowCompetenceBinder2;
import de.unipotsdam.kompetenzmanager.client.TabbedView;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;
import de.unipotsdam.kompetenzmanager.shared.Literature;

public class ViewController {
	private ShowCompetenceBinder2 widget;
	private LiteratureView literatureview;
	private TabbedView tabbedView;
	private Boolean graphIsVisible;

	public ViewController(ShowCompetenceBinder2 widget, LiteratureView literatureView, TabbedView tabbedView) { 
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
			tabbedView.getTabLayoutPanel().selectTab(1);
		} else {
			tabbedView.getTabLayoutPanel().selectTab(0);
		}
	}
	
	public void addConnectLitAndGraphMenu() {
		ManyToManyConnector manyToManyConnector = new ManyToManyConnector(this);
		this.widget.addMultiClickMenu(manyToManyConnector);
		this.literatureview.addMultiClickMenu(manyToManyConnector);
	}
	
	public void removeMultiClickMenu() {
		this.widget.removeMultiClickMenu();
		this.literatureview.removeMultiClickMenu();
	}

	/**
	 * @param widget the widget to set
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
	 * @param literatureview the literatureview to set
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
	 * @param tabbedView the tabbedView to set
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

	public void addThemeTag() {
		// TODO Auto-generated method stub
		
	}

	public void tagSelectionToLiterature() {
		addConnectLitAndGraphMenu();		
	}

	public void showLiteratureToTags() {
		Graph selectedGraph = convertToGraph(this.widget.getSelectedElements());
		GraphBackendImpl backendImpl = new GraphBackendImpl(widget);
		backendImpl.getLiteratureForTags(selectedGraph, new LiteratureUpdater<Literature>(this.literatureview));
		changeSelectedTab(false);		
	}

	public Graph convertToGraph(Collection<String> selectedElements) {
		Graph result = new Graph();
		for(String elem: selectedElements) {
			result.nodes.add(new GraphNode(elem));
		}
		return result;
		
	}
}
