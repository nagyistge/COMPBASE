package uzuzjmd.competence.gui.client.competencegraph;

import java.util.LinkedList;

import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.Competence_webapp;
import uzuzjmd.competence.gui.client.tabs.GraphTab;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class CompetenceEntry extends Composite {

	private static CompetenceEntryUiBinder uiBinder = GWT
			.create(CompetenceEntryUiBinder.class);
	@UiField
	Button deleteButton;
	@UiField
	Button prerequisiteCreationButton;
	@UiField
	HorizontalPanel competenceEntryHorizontalPanel;
	@UiField
	SimplePanel competenceNumber;
	@UiField
	SimplePanel competenceText;
	private String competenceSelected;
	private GraphTab graphTab;

	interface CompetenceEntryUiBinder extends UiBinder<Widget, CompetenceEntry> {
	}

	public CompetenceEntry(String id, String text, GraphTab graphTab) {
		initWidget(uiBinder.createAndBindUi(this));
		this.competenceEntryHorizontalPanel.setStyleName(id, true);
		this.competenceNumber.add(new HTML(id));
		this.competenceText.add(new HTML(text));
		this.competenceSelected = text;
		this.graphTab = graphTab;
	}

	@UiHandler("deleteButton")
	void onDeleteButtonClick(ClickEvent event) {
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

	@UiHandler("prerequisiteCreationButton")
	void onPrerequisiteCreationButtonClick(ClickEvent event) {
	}
}
