package de.unipotsdam.kompetenzmanager.client.viewcontroller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.unipotsdam.kompetenzmanager.client.ShowCompetenceBinder2;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.Literature;

public class GraphUpdater<T> implements AsyncCallback<T> {

	private JavascriptUtil javascriptUtil;
	private ShowCompetenceBinder2 showCompetenceBinder;

	public GraphUpdater(ShowCompetenceBinder2 widget) {
		this.javascriptUtil = new JavascriptUtil();
		this.showCompetenceBinder = widget;		
	}
	
	@Override
	public void onSuccess(T result) {			
		JSONObject json = javascriptUtil.toJSON((Graph) result);
		GWT.log("Graph konnte geladen werden mit den Daten" + json.toString());
		showCompetenceBinder.removeGraph();
		showCompetenceBinder.setGraph(json.getJavaScriptObject(),showCompetenceBinder.canvasDiv.getId());
		this.showCompetenceBinder.storeGraph((Graph) result);
		GraphBackendImpl backendImpl = new GraphBackendImpl(showCompetenceBinder);
		backendImpl.getLiteratureForTags(showCompetenceBinder.getStoredGraph(), new RelevantLiteratureUpdater<Literature>());
	}
	
	@Override
	public void onFailure(Throwable caught) {
		GWT.log("Graph konnte nicht geladen werden " + caught.getMessage());					
	}

}
