package uzuzjmd.competence.gui.client.tabs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.Competence_webapp;
import uzuzjmd.competence.gui.client.competencegraph.CompetenceClickPanel;
import uzuzjmd.competence.gui.client.competencegraph.CompetenceEntry;
import uzuzjmd.competence.gui.client.competencegraph.CompetenceGraphFilterPanel;
import uzuzjmd.competence.gui.client.competencegraph.CompetenceLinkCreationWidget;
import uzuzjmd.competence.gui.shared.widgets.MyGraphPanel;
import uzuzjmd.competence.service.rest.client.Graph;

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
	@UiField
	VerticalPanel listedCompetencesPlaceholder;
	private MyGraphPanel graphPanel;
	private PopupPanel competenceCreationPopup;
	private PopupPanel competenceFilterPopup;

	public interface GraphCodec extends JsonEncoderDecoder<Graph> {
	}

	interface GraphTabUiBinder extends UiBinder<Widget, GraphTab> {
	}

	public GraphTab() {
		initWidget(uiBinder.createAndBindUi(this));

		graphPanel = new MyGraphPanel(new CompetenceClickPanel(this),
				new CompetenceClickPanel(this));
		graphPanelPlaceHolder.add(graphPanel);

		// create competenceCreationPopup
		competenceCreationPopup = new PopupPanel(false, false);
		competenceCreationPopup.setGlassEnabled(true);
		competenceCreationPopup.add(new CompetenceLinkCreationWidget(
				competenceCreationPopup, this));
		competenceCreationPopup.hide();
		this.setStyleName("graphTab", true);

		competenceFilterPopup = new PopupPanel(false, false);
		competenceFilterPopup.setGlassEnabled(true);
		competenceFilterPopup.add(new CompetenceGraphFilterPanel(this,
				competenceFilterPopup));
		competenceFilterPopup.hide();

	}

	@Override
	protected void onAttach() {
		super.onAttach();
		reload(new LinkedList<String>());
	}

	public void loadGraphFromServer(List<String> selectedCompetences) {
		Resource resource = new Resource(
				Competence_webapp.contextFactory.getServerURL()
						+ "/competences/json/prerequisite/graph/"
						+ Competence_webapp.contextFactory.getCourseId());
		resource.addQueryParams("selectedCompetences", selectedCompetences)
				.get().send(new JsonCallback() {

					@Override
					public void onSuccess(Method method, JSONValue response) {
						initCompetenceList(response);
						graphPanel.setGraph(response);
					}

					@Override
					public void onFailure(Method method, Throwable exception) {
						// TODO Auto-generated method stub
						GWT.log("konnte Graph nicht erstellen");
					}
				});
	}

	private void initCompetenceList(JSONValue response) {
		GraphCodec codec = GWT.create(GraphCodec.class);
		Graph graph = codec.decode(response);
		for (Integer key : new HashSet<Integer>(graph.getNodeIdValues()
				.keySet())) {
			listedCompetencesPlaceholder.add(new CompetenceEntry(key + "",
					graph.getNodeIdValues().get(key)));
		}
	}

	@UiHandler("addCompetencesButton")
	void onAddCompetencesButtonClick(ClickEvent event) {
		competenceCreationPopup.show();
	}

	@UiHandler("filterCompetencesButton")
	void onFilterCompetencesButtonClick(ClickEvent event) {
		this.competenceFilterPopup.show();
	}

	@UiHandler("refreshButton")
	void onRefreshButtonClick(ClickEvent event) {
		reload(new LinkedList<String>());
	}

	public void reload(List<String> selectedCompetences) {
		graphPanel.removeGraph();
		listedCompetencesPlaceholder.clear();
		loadGraphFromServer(selectedCompetences);
	}
}