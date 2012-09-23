package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SourcesTabEvents;
import com.google.gwt.user.client.ui.TabListener;

import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphBackendAsync;
import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphBackendImpl;
import de.unipotsdam.kompetenzmanager.client.viewcontroller.ViewController;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class KompetenzManager implements EntryPoint {

	public LiteratureView literatureView;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {		
		ShowCompetenceBinder2 graph1 = addGraphWidget("canvas");		
	}	
	
	private ShowCompetenceBinder2 addGraphWidget(String canvasId) {
		
		//create graphview
		ShowCompetenceBinder2 widget = new ShowCompetenceBinder2(canvasId);		
		widget.widget = widget; // ermöglicht referenzierung aus EventMethoden
		
		GraphBackendAsync graphBackendImpl = new GraphBackendImpl(widget);
		graphBackendImpl.getFullGraph(null);		
		
		
		//create literatureview
		this.literatureView = new LiteratureView();
		
		//create tabbed view
		TabbedView tabbedView = new TabbedView();		
		tabbedView.ThemeViewTab.add(widget);
		tabbedView.LiteratureViewTab.add(literatureView);
		
		//select correct tab
		tabbedView.tabView.selectTab(0, true);
		tabbedView.tabView.selectTab(1, true);			
		tabbedView.tabView.selectTab(0);
		
		//add viewcontroller
		ViewController viewController = new ViewController(widget, literatureView, tabbedView);
		widget.viewcontroller = viewController;
		literatureView.viewcontroller = viewController;
		
		// jetzt kann auch die Literatur initialisiert werden
		literatureView.initLiteratureView();

		//start application
		RootPanel.get("content").add(tabbedView);		
		return widget;
	}
}
