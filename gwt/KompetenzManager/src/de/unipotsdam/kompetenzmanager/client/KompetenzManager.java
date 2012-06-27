package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import de.unipotsdam.kompetenzmanager.shared.Graph;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class KompetenzManager implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side 
	 * service.
	 */
	private final GraphBackendAsync graphBackEnd = GWT
	.create(GraphBackend.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		//instantiiere ShowCompetenzWidget
		ShowCompetenceBinder2 widget = new ShowCompetenceBinder2();		 
		RootPanel.get("content").add(widget);		
		//frage Server nach initialen Daten für den Graph TODO: should be only on request
		AsyncCallback<Graph> graphCallback = new AsyncCallback<Graph>() {
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Graph konnte nicht geladen werden " + caught.getMessage());
			}
			@Override
			public void onSuccess(Graph result) {				
				ShowCompetenceBinder2 widget = 
					(ShowCompetenceBinder2) RootPanel.get("content").getWidget(0);
				JavascriptUtil util = new JavascriptUtil();
				JSONObject json = util.toJSON(result);
				GWT.log("Graph konnte geladen werden mit den Daten" + json.toString());
				widget.setGraph(json.getJavaScriptObject());
								
			};
		};		
		graphBackEnd.getFullGraph(graphCallback);
	}

	
}
