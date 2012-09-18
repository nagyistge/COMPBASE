package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SourcesTabEvents;
import com.google.gwt.user.client.ui.TabListener;

import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphBackendAsync;
import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphBackendImpl;


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
		TabbedView tabbedView = new TabbedView();		
		ShowCompetenceBinder2 widget = new ShowCompetenceBinder2(canvasId);		
		widget.widget = widget; // ermöglicht referenzierung aus EventMethoden
		widget.tabbed = tabbedView;
		GraphBackendAsync graphBackendImpl = new GraphBackendImpl(widget);
		graphBackendImpl.getFullGraph(null);		
//		KeyBoardListener keyBoardListener = new KeyBoardListener(widget);
//		tabbedView.ThemeViewTab.add(keyBoardListener);
		tabbedView.ThemeViewTab.add(widget);
		this.literatureView = new LiteratureView();
		tabbedView.LiteratureViewTab.add(literatureView);
//		tabbedView.tabView.getTabWidget(index).getTabBar().selectTab(0);
		tabbedView.tabView.selectTab(0, true);
		tabbedView.tabView.selectTab(1, true);
		
		tabbedView.tabView.getWidget(1).setTitle("Literatur");
		tabbedView.tabView.selectTab(0);
		RootPanel.get("content").add(tabbedView);		
//		TestWrapper testWrapper = new TestWrapper();
//		testWrapper.getElement().appendChild(widget.getElement());
//		testWrapper.getElement().appendChild(widget2.getElement());
		return widget;
	}
}
