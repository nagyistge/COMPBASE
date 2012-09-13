package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SourcesTabEvents;
import com.google.gwt.user.client.ui.TabListener;


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
		TabbedView tabbedView = new TabbedView();		
		ShowCompetenceBinder2 widget = new ShowCompetenceBinder2(canvasId);		
		widget.widget = widget; // ermöglicht referenzierung aus EventMethoden
		widget.tabbed = tabbedView;
		GraphBackendAsync graphBackendImpl = new GraphBackendImpl(widget);
		graphBackendImpl.getFullGraph(null);		
		tabbedView.ThemeViewTab.add(widget);
		tabbedView.tabView.getTabBar().selectTab(0);		
		RootPanel.get("content").add(tabbedView);		
//		TestWrapper testWrapper = new TestWrapper();
//		testWrapper.getElement().appendChild(widget.getElement());
//		testWrapper.getElement().appendChild(widget2.getElement());
		return widget;
	}
}
