package uzuzjmd.competence.service.rest.client.api;

import uzuzjmd.competence.gui.client.viewcontroller.Controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;

public class OkFeedBack implements RequestCallback {
	@Override
	public void onError(Request request, Throwable exception) {
		// TODO Auto-generated method stub
		GWT.log(exception.getMessage());
	}

	@Override
	public void onResponseReceived(Request request, Response response) {
		GWT.log(response.getStatusText());
		Controller.reloadController.reload();
	}
}