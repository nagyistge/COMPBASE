package de.unipotsdam.kompetenzmanager.client.viewcontroller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphLiteraturePair;
import de.unipotsdam.kompetenzmanager.shared.Literature;

public class GraphAndLiteratureUpdater<T> implements
		AsyncCallback<GraphLiteraturePair> {

	private ViewController viewcontroller;

	public GraphAndLiteratureUpdater(ViewController viewcontroller) {
		this.viewcontroller = viewcontroller;
	}

	@Override
	public void onFailure(Throwable caught) {
		GWT.log("Mit dem allgemeinen Updaten hat es nicht funktioniert");
	}

	@Override
	public void onSuccess(GraphLiteraturePair result) {
		GraphUpdater<Graph> graphUpdater = new GraphUpdater<Graph>(viewcontroller.getWidget());
		graphUpdater.onSuccess(result.graph);
		LiteratureUpdater<Literature> literatureUpdater = new LiteratureUpdater<Literature>(viewcontroller.getLiteratureview());
		literatureUpdater.onSuccess(result.literature);
		
	}

}
