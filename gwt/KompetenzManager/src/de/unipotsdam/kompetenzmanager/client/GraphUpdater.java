package de.unipotsdam.kompetenzmanager.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.unipotsdam.kompetenzmanager.shared.Graph;

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
	}
	
	@Override
	public void onFailure(Throwable caught) {
		GWT.log("Graph konnte nicht geladen werden " + caught.getMessage());					
	}

}
