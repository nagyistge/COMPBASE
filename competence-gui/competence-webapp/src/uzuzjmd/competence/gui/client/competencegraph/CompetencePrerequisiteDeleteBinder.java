package uzuzjmd.competence.gui.client.competencegraph;

import java.util.LinkedList;

import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.Competence_webapp;
import uzuzjmd.competence.gui.client.tabs.GraphTab;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class CompetencePrerequisiteDeleteBinder extends Composite {

	private static CompetencePrerequisiteDeleteBinderUiBinder uiBinder = GWT
			.create(CompetencePrerequisiteDeleteBinderUiBinder.class);

	interface CompetencePrerequisiteDeleteBinderUiBinder extends
			UiBinder<Widget, CompetencePrerequisiteDeleteBinder> {
	}

	private PopupPanel parent;
	private String competenceSelected;
	private GraphTab graphTab;

	public CompetencePrerequisiteDeleteBinder(PopupPanel parent,
			GraphTab graphTab) {
		initWidget(uiBinder.createAndBindUi(this));
		parent.center();
		this.parent = parent;
		this.graphTab = graphTab;
	}

	@Override
	protected void onAttach() {
		super.onAttach();
	}

	public void setCompetenceSelected(String competenceSelected) {
		this.competenceSelected = competenceSelected;
	}

	@UiHandler("submitButton")
	void onSubmitButtonClick(ClickEvent event) {
		deleteRequirements(competenceSelected);
	}

	private void deleteRequirements(String competenceSelected) {
		Resource resource = new Resource(
				Competence_webapp.contextFactory.getServerURL()
						+ "/competences/json/prerequisite/delete/"
						+ Competence_webapp.contextFactory.getCourseId());
		try {
			resource.addQueryParam("linkedCompetence", competenceSelected)
					.post().send(new RequestCallback() {

						@Override
						public void onResponseReceived(Request request,
								Response response) {
							parent.hide();
							graphTab.reload(new LinkedList<String>());
						}

						@Override
						public void onError(Request request, Throwable exception) {
							GWT.log(exception.getMessage());
						}
					});
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			GWT.log(e.getMessage());
		}
	}

	@UiHandler("cancelButton")
	void onCancelButtonClick(ClickEvent event) {
		parent.hide();
	}
}
