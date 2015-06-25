package uzuzjmd.competence.service.rest.client.api;

import java.util.List;

import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.viewcontroller.Controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;

public class PostRequestManager extends RestClientManager {
	private void sendCompulsoryNodesToServer(final String requirementText,
			List<String> competences) {
		if (!competences.isEmpty()) {
			Resource resourceCompulsory = new Resource(
					RestUrlFactory.getAddCompulsoryCompetencesUrl());
			try {
				resourceCompulsory
						.addQueryParam("requirements", requirementText)
						.addQueryParams("competences", competences).post()
						.send(new OkFeedBack());
			} catch (RequestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			GWT.log("not sending compulsory nodes because non selected");
			Controller.reloadController.reload();
		}
	}

	/**
	 * this sends the nonCompulsory and the compulsory Competences for the given
	 * course context
	 * 
	 * @param requirementText
	 * @param nonCompulsorycompetences
	 * @param compulsorycompetences
	 */
	public void addCompetencesToCourse(final String requirementText,
			List<String> nonCompulsorycompetences,
			final List<String> compulsorycompetences) {
		if (!nonCompulsorycompetences.isEmpty()) {
			Resource resource = new Resource(
					RestUrlFactory.getAddNotCompulsoryCompetencesUrl());
			try {
				resource.addQueryParam("requirements", requirementText)
						.addQueryParams("competences", nonCompulsorycompetences)
						.post().send(new RequestCallback() {
							@Override
							public void onError(Request request,
									Throwable exception) {
								GWT.log(exception.getMessage());
							}

							@Override
							public void onResponseReceived(Request request,
									Response response) {

								GWT.log("successfully send non compulsory competences to server");
								sendCompulsoryNodesToServer(requirementText,
										compulsorycompetences);
							}
						});
			} catch (RequestException e) {
				e.printStackTrace();
			}
		} else {
			sendCompulsoryNodesToServer(requirementText, compulsorycompetences);
		}

	}
}
