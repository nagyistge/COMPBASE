package uzuzjmd.competence.gui.client.tabs;

import java.util.LinkedList;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.Competence_webapp;
import uzuzjmd.competence.gui.client.competencegraph.CompetenceClickPanel;
import uzuzjmd.competence.gui.client.competencegraph.CompetenceEntry;
import uzuzjmd.competence.gui.client.competencegraph.CompetenceLinkCreationWidget;
import uzuzjmd.competence.gui.shared.widgets.MyGraphPanel;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class GraphTab extends Composite {

	private static GraphTabUiBinder uiBinder = GWT
			.create(GraphTabUiBinder.class);
	@UiField
	SimplePanel graphPanelPlaceHolder;
	@UiField
	Button addCompetencesButton;
	@UiField
	VerticalPanel verticalPanel;
	@UiField
	Button filterCompetencesButton;
	@UiField
	Button refreshButton;
	private MyGraphPanel graphPanel;
	private PopupPanel competenceCreationPopup;

	interface GraphTabUiBinder extends UiBinder<Widget, GraphTab> {
	}

	public GraphTab() {
		initWidget(uiBinder.createAndBindUi(this));

		graphPanel = new MyGraphPanel(new CompetenceClickPanel(),
				new CompetenceClickPanel());
		graphPanelPlaceHolder.add(graphPanel);
		this.verticalPanel.add(new CompetenceEntry());

		// create competenceCreationPopup
		competenceCreationPopup = new PopupPanel(false, false);
		competenceCreationPopup.setGlassEnabled(true);
		competenceCreationPopup.add(new CompetenceLinkCreationWidget(
				competenceCreationPopup));
		competenceCreationPopup.hide();				
	}

	@Override
	protected void onAttach() {
		super.onAttach();			
		loadGraphFromServer();			
	}

	private void loadGraphFromServer() {
		Resource resource = new Resource(Competence_webapp.contextFactory.getServerURL()+ "/competences/json/prerequisite/graph/"+Competence_webapp.contextFactory.getCourseId());
		resource.addQueryParams("selectedCompetences", new LinkedList<String>()).get().send(new JsonCallback() {
			
			@Override
			public void onSuccess(Method method, JSONValue response) {				
				graphPanel.setGraph(response);
			}
			
			@Override
			public void onFailure(Method method, Throwable exception) {
				// TODO Auto-generated method stub
				GWT.log("konnte Graph nicht erstellen");
			}
		});
	}

	@UiHandler("addCompetencesButton")
	void onAddCompetencesButtonClick(ClickEvent event) {
		competenceCreationPopup.show();
	}
}