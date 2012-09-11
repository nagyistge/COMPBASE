package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class KompetenzManager implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {		
		ShowCompetenceBinder2 graph1 = addGraphWidget("canvas");		
	}	
	
	private ShowCompetenceBinder2 addGraphWidget(String canvasId) {
		ShowCompetenceBinder2 widget = new ShowCompetenceBinder2(canvasId);		 
		RootPanel.get("content").add(widget);		
		widget.widget = widget; // ermöglicht referenzierung aus EventMethoden
		GraphBackendAsync graphBackendImpl = new GraphBackendImpl(widget);
		graphBackendImpl.getFullGraph(null);
		return widget;
	}
}
