package de.unipotsdam.kompetenzmanager.client.viewcontroller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class RelevantLiteratureUpdater<T> implements AsyncCallback<Literature> {
	
	private ViewController viewController;

	public RelevantLiteratureUpdater(ViewController viewController) {
		this.viewController = viewController;
	}

	@Override
	public void onFailure(Throwable caught) {
		GWT.log("Konnte die relevante Literatur für den graphen nicht finden");

	}

	@Override
	public void onSuccess(Literature result) {
		this.viewController.getWidget().setRelevanteLiteratur(result);		
	}

}
