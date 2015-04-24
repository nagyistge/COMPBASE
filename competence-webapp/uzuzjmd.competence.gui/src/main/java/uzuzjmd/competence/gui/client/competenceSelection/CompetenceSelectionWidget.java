package uzuzjmd.competence.gui.client.competenceSelection;

import java.util.List;

import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.Controller;
import uzuzjmd.competence.gui.client.LmsContextFactory;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.RadioButton;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.ToggleButton;
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
			Controller.reloadController.reload();
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
	@UiField
	CaptionPanel captionPanel;
	@UiField
	SimplePanel toggleButtonPlaceholder;

	private CompetenceSelectionTree competenceTree;
	private OperatorSelectionTree operatorTree;

	private CatchwordSelectionTree catchwordTree;

	private LmsContextFactory contextFactory;
	private String filter = "all";
	private String selectedFilter = null;
	private Boolean showChecked = false;
	private boolean isCourseContext;
	private boolean editable = false;

	private static CompetenceSelectionWidgetUiBinder uiBinder = GWT
			.create(CompetenceSelectionWidgetUiBinder.class);

	public CompetenceSelectionWidget(final LmsContextFactory contextFactory,
			String selectedFilter, boolean showChecked, boolean isCourseContext) {
		initWidget(uiBinder.createAndBindUi(this));
		this.showChecked = showChecked;
		this.isCourseContext = isCourseContext;
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
	public CompetenceSelectionWidget(final LmsContextFactory contextFactory,
			String selectedFilter, String competenceTreeFilter,
			boolean isCourseContext) {
		initWidget(uiBinder.createAndBindUi(this));
		this.isCourseContext = isCourseContext;
		this.showChecked = false;
		initCompetenceSelectionWidget(contextFactory, selectedFilter);
	}

	/**
	 * selectedFilter can be /all, /selected or null competenceTreeFilter can be
	 * competenceTreeFilter can be coursecontextnofilter
	 * 
	 * @param contextFactory
	 * @param selectedFilter
	 * @param competenceTreeFilter
	 * @param title
	 */
	public CompetenceSelectionWidget(final LmsContextFactory contextFactory,
			String selectedFilter, String competenceTreeFilter, String title,
			boolean isCourseContext) {
		initWidget(uiBinder.createAndBindUi(this));
		this.isCourseContext = isCourseContext;
		this.showChecked = false;
		initCompetenceSelectionWidget(contextFactory, selectedFilter);
		this.captionPanel.setCaptionHTML(title);
	}

	public CompetenceSelectionWidget(final LmsContextFactory contextFactory,
			String selectedFilter, String competenceTreeFilter, String title,
			boolean isCourseContext, boolean editable) {
		initWidget(uiBinder.createAndBindUi(this));
		this.isCourseContext = isCourseContext;
		this.showChecked = false;
		this.editable = true;
		initCompetenceSelectionWidget(contextFactory, selectedFilter);
		this.captionPanel.setCaptionHTML(title);
	}

	private void initCompetenceSelectionWidget(
			final LmsContextFactory contextFactory, String selectedFilter) {
		this.contextFactory = contextFactory;
		this.selectedFilter = selectedFilter;

		updateFilteredPanel("all", null);
		initOperatorTree(contextFactory);
		initCatchwordTree(contextFactory);
		this.alleRadioButton.setValue(true);
		ToggleButton toggleButton = new ToggleButton("Filter ausklappen",
				"Filter einklappen");
		competenceFilterPanel.setVisible(false);
		toggleButton.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				competenceFilterPanel.setVisible(event.getValue());
			}
		});
		toggleButtonPlaceholder.add(toggleButton);
	}

	private void initCatchwordTree(final LmsContextFactory contextFactory) {
		GWT.log("Initiating catchword tree");
		this.catchwordTree = new CatchwordSelectionTree(
				contextFactory.getServerURL()
						+ "/competences/xml/catchwordtree/"
						+ contextFactory.getOrganization() + "/nocache",
				"Schlagworte", "catchwordView", 325, 250, "Schlagworte",
				contextFactory, isCourseContext);
		catchwordCaptionPanel.add(catchwordTree);
		GWT.log("Initiated catchword tree");
	}

	private void initOperatorTree(final LmsContextFactory contextFactory) {
		GWT.log("Initiating operator tree");
		this.operatorTree = new OperatorSelectionTree(
				contextFactory.getServerURL()
						+ "/competences/xml/operatortree/"
						+ contextFactory.getOrganization() + "/nocache",
				"Operatoren", "operatorView", 300, 200, "Operatoren",
				contextFactory, isCourseContext);
		operatorCaptionPanel.add(operatorTree);
		GWT.log("Initiated operator tree");
	}

	public void handleDeleteClick() {
		Resource resourceCompulsory = new Resource(
				contextFactory.getServerURL()
						+ "/competences/json/coursecontext/delete/"
						+ contextFactory.getOrganization());
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
							+ contextFactory.getCourseContext() + "/true");
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
			Controller.reloadController.reload();
		}
	}

	private void sendNonCompulsoryNodesToServer(final String requirementText) {
		if (!competenceTree.convertSelectedTreeToList().isEmpty()) {
			Resource resource = new Resource(contextFactory.getServerURL()
					+ "/competences/json/coursecontext/create/"
					+ contextFactory.getCourseContext() + "/false");
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

	private void updateFilteredPanel(String compulsoryFilter, String query) {
		competenceTreeCaptionPanel.clear();
		String queryString = "";
		if (query != null) {
			queryString += query;
		}

		String context = contextFactory.getOrganization();
		if (isCourseContext) {
			context = "coursecontext/" + contextFactory.getCourseContext();
		}
		competenceTree = new CompetenceSelectionTree(
				contextFactory.getServerURL()
						+ "/competences/xml/competencetree/" + context + "/"
						+ compulsoryFilter + "/nocache" + queryString,
				contextFactory, selectedFilter, showChecked, isCourseContext,
				editable);
		// competenceTree.setShowCheckBoxes(showChecked);
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

	public void reload() {
		competenceTree.reloadTree();
	}

}
