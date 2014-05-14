package uzuzjmd.competence.gui.client.competenceSelection;

import java.util.List;

import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.ContextFactory;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.RadioButton;
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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CompetenceSelectionWidget extends Composite {

	interface CompetenceSelectionWidgetUiBinder extends
			UiBinder<Widget, CompetenceSelectionWidget> {
	}

	private class OkFeedBack implements RequestCallback {
		@Override
		public void onError(Request request, Throwable exception) {
			// TODO Auto-generated method stub
			GWT.log(exception.getMessage());
		}

		@Override
		public void onResponseReceived(Request request, Response response) {
			GWT.log(response.getStatusText());
			competenceTree.reloadTree();
		}
	}

	@UiField
	VerticalPanel competenceTreeContainer;
	@UiField
	Panel competenceTreeCaptionPanel;

	@UiField
	Panel competenceCompulsoryCheckbox;
	@UiField
	HorizontalPanel competenceFilterPanel;
	@UiField
	VerticalPanel operatorPanel;
	// @UiField
	// RadioButton requiredFlagBox;

	@UiField
	Panel catchwordCaptionPanel;
	@UiField
	Panel operatorCaptionPanel;
	@UiField
	RadioButton alleRadioButton;
	@UiField
	RadioButton verpflichtendeRadioButton;
	@UiField
	RadioButton nichtVerpflichtendeRadioButton;
	@UiField
	Button filterButton;
	@UiField
	Button resetButton;

	private CompetenceSelectionPanel competenceTree;
	private OperatorSelectionPanel operatorTree;

	private CatchwordSelectionTree catchwordTree;

	private ContextFactory contextFactory;
	private String filter = "all";
	private String selectedFilter = null;
	private String competenceTreeFilter = "";

	private static CompetenceSelectionWidgetUiBinder uiBinder = GWT
			.create(CompetenceSelectionWidgetUiBinder.class);

	public CompetenceSelectionWidget(final ContextFactory contextFactory,
			String selectedFilter) {
		initWidget(uiBinder.createAndBindUi(this));
		initCompetenceSelectionWidget(contextFactory, selectedFilter);
		// competenceCompulsoryCheckbox
	}

	/**
	 * competennceFilter needs to end with /
	 * 
	 * @param contextFactory
	 * @param selectedFilter
	 * @param competenceTreeFilter
	 */
	public CompetenceSelectionWidget(final ContextFactory contextFactory,
			String selectedFilter, String competenceTreeFilter) {
		initWidget(uiBinder.createAndBindUi(this));
		this.competenceTreeFilter = competenceTreeFilter;
		initCompetenceSelectionWidget(contextFactory, selectedFilter);
		// competenceCompulsoryCheckbox
	}

	private void initCompetenceSelectionWidget(
			final ContextFactory contextFactory, String selectedFilter) {
		this.contextFactory = contextFactory;
		this.selectedFilter = selectedFilter;

		updateFilteredPanel("all", null);
		initOperatorTree(contextFactory);
		initCatchwordTree(contextFactory);
		this.alleRadioButton.setValue(true);
	}

	private void initCatchwordTree(final ContextFactory contextFactory) {
		this.catchwordTree = new CatchwordSelectionTree(
				contextFactory.getServerURL()
						+ "/competences/xml/catchwordtree/"
						+ contextFactory.getCourseId() + "/nocache",
				"Schlagworte", "catchwordView", 325, 130, "Schlagworte",
				contextFactory);
		catchwordCaptionPanel.add(catchwordTree);
	}

	private void initOperatorTree(final ContextFactory contextFactory) {
		this.operatorTree = new OperatorSelectionPanel(
				contextFactory.getServerURL()
						+ "/competences/xml/operatortree/"
						+ contextFactory.getCourseId() + "/nocache",
				"Operatoren", "operatorView", 300, 80, "Operatoren",
				contextFactory);
		operatorCaptionPanel.add(operatorTree);
	}

	public void handleDeleteClick() {
		Resource resourceCompulsory = new Resource(
				contextFactory.getServerURL()
						+ "/competences/json/coursecontext/delete/"
						+ contextFactory.getCourseId());
		try {
			resourceCompulsory.post().send(new OkFeedBack());
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			GWT.log(e.getMessage());
		}

	}

	public void handleSubmit(final String requirementText) {
		sendNonCompulsoryNodesToServer(requirementText);
	}

	private void sendCompulsoryNodesToServer(final String requirementText) {
		if (!competenceTree.getCheckedNodes().isEmpty()) {
			Resource resourceCompulsory = new Resource(
					contextFactory.getServerURL()
							+ "/competences/json/coursecontext/create/"
							+ contextFactory.getCourseId() + "/true");
			try {
				resourceCompulsory
						.addQueryParam("requirements", requirementText)
						.addQueryParams("competences",
								competenceTree.getCheckedNodes()).post()
						.send(new OkFeedBack());
			} catch (RequestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			GWT.log("not sending compulsory nodes because non selected");
			competenceTree.reloadTree();
		}
	}

	private void sendNonCompulsoryNodesToServer(final String requirementText) {
		if (!competenceTree.convertSelectedTreeToList().isEmpty()) {
			Resource resource = new Resource(contextFactory.getServerURL()
					+ "/competences/json/coursecontext/create/"
					+ contextFactory.getCourseId() + "/false");
			try {
				resource.addQueryParam("requirements", requirementText)
						.addQueryParams("competences",
								competenceTree.convertSelectedTreeToList())
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
								sendCompulsoryNodesToServer(requirementText);
							}
						});
			} catch (RequestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			sendCompulsoryNodesToServer(requirementText);
		}

	}

	@UiHandler("alleRadioButton")
	void onRadioButtonClick(ClickEvent event) {
		filter = "all";
		updateFilteredPanel(filter, null);
	}

	@UiHandler("verpflichtendeRadioButton")
	void onRadioButton_1Click(ClickEvent event) {
		filter = "true";
		updateFilteredPanel(filter, null);
	}

	@UiHandler("nichtVerpflichtendeRadioButton")
	void onRadioButton_2Click(ClickEvent event) {
		filter = "false";
		updateFilteredPanel(filter, null);
	}

	private void updateFilteredPanel(String filter, String query) {
		competenceTreeCaptionPanel.clear();
		String queryString = "";
		if (query != null) {
			queryString += query;
		}
		competenceTree = new CompetenceSelectionPanel(
				contextFactory.getServerURL()
						+ "/competences/xml/competencetree/"
						+ competenceTreeFilter + contextFactory.getCourseId()
						+ "/" + filter + "/nocache" + queryString,
				contextFactory, selectedFilter);
		competenceTreeCaptionPanel.add(competenceTree);
	}

	@UiHandler("resetButton")
	void onResetButtonClick(ClickEvent event) {
		updateFilteredPanel("all", null);
		operatorTree.clearSelections();
		catchwordTree.clearSelections();
	}

	@UiHandler("filterButton")
	void onFilterButtonClick(ClickEvent event) {
		String query = "?";
		for (String selectedOperator : operatorTree.convertSelectedTreeToList()) {
			query += "selectedOperators=";
			query += selectedOperator;
			query += "&";
		}
		for (String selectedCatchwords : catchwordTree
				.convertSelectedTreeToList()) {
			query += "selectedCatchwords=";
			query += selectedCatchwords;
			query += "&";
		}
		query = query.substring(0, query.length() - 1);
		updateFilteredPanel(filter, query);
	}

	public List<String> getSelectedCompetences() {
		return this.competenceTree.convertSelectedTreeToList();
	}
}
