package uzuzjmd.competence.gui.client.shared.widgets.taxonomy;

import java.util.List;

import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.viewcontroller.Controller;
import uzuzjmd.competence.service.rest.client.api.OkFeedBack;
import uzuzjmd.competence.service.rest.client.api.PostRequestManager;
import uzuzjmd.competence.service.rest.client.api.RestUrlFactory;
import uzuzjmd.competence.service.rest.client.dto.HierarchieChangeSet;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.RadioButton;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.RequestException;
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

	@UiField
	VerticalPanel competenceTreeContainer;
	@UiField
	SimplePanel competenceTreeCaptionPanel;

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
	Button collapseButton;
	@UiField
	CaptionPanel captionPanel;
	@UiField
	SimplePanel toggleButtonPlaceholder;
	@UiField
	TextBox textSearchField;

	private CompetenceSelectionTree competenceTree;
	private OperatorSelectionTree operatorTree;

	private CatchwordSelectionTree catchwordTree;

	private String filter = "all";
	private String selectedFilter = null;
	private Boolean showChecked = false;
	private boolean isCourseContext;
	private boolean editable = false;
	private boolean clickable;

	private static CompetenceSelectionWidgetUiBinder uiBinder = GWT
			.create(CompetenceSelectionWidgetUiBinder.class);

	public CompetenceSelectionWidget(String selectedFilter,
			boolean showChecked, boolean isCourseContext) {
		initWidget(uiBinder.createAndBindUi(this));
		this.showChecked = showChecked;
		initCompetenceSelectionWidget(selectedFilter);
		// competenceCompulsoryCheckbox

	}

	/**
	 * competennceFilter needs to end with /
	 * 
	 * @param contextFactory
	 * @param selectedFilter
	 * @param competenceTreeFilter
	 */
	// public CompetenceSelectionWidget(final LmsContextFactory contextFactory,
	// String selectedFilter, String competenceTreeFilter,
	// boolean isCourseContext) {
	// initWidget(uiBinder.createAndBindUi(this));
	// this.isCourseContext = isCourseContext;
	// this.showChecked = false;
	// initCompetenceSelectionWidget(contextFactory, selectedFilter);
	// }

	/**
	 * selectedFilter can be /all, /selected or null competenceTreeFilter can be
	 * competenceTreeFilter can be coursecontextnofilter
	 * 
	 * @param contextFactory
	 * @param selectedFilter
	 * @param competenceTreeFilter
	 * @param title
	 */
	public CompetenceSelectionWidget(String selectedFilter,
			String competenceTreeFilter, String title, boolean isCourseContext) {
		initWidget(uiBinder.createAndBindUi(this));
		this.isCourseContext = isCourseContext;
		this.showChecked = false;
		initCompetenceSelectionWidget(selectedFilter);
		this.captionPanel.setCaptionHTML(title);
	}

	/**
	 * isCourseContext is important to distinguish global from course bound
	 * competences
	 * 
	 * editable is important to say, if the competences should be draggable,
	 * 
	 * selectedFilter can be /all, /selected or null
	 * 
	 * competenceTreeFilter can be coursecontext/
	 * 
	 * @param contextFactory
	 * @param selectedFilter
	 * @param competenceTreeFilter
	 * @param title
	 * @param isCourseContext
	 * @param editable
	 */
	public CompetenceSelectionWidget(String selectedFilter,
			String competenceTreeFilter, String title, boolean isCourseContext,
			boolean editable) {
		initWidget(uiBinder.createAndBindUi(this));
		this.isCourseContext = isCourseContext;
		this.showChecked = false;
		this.editable = true;
		initCompetenceSelectionWidget(selectedFilter);
		this.captionPanel.setCaptionHTML(title);
	}

	/**
	 * 
	 * should only be used for edit single competence widget
	 * 
	 * @param contextFactory
	 * @param selectedFilter
	 * @param competenceTreeFilter
	 * @param title
	 * @param isCourseContext
	 * @param editable
	 * @param clickable
	 */
	public CompetenceSelectionWidget(String selectedFilter,
			String competenceTreeFilter, String title, boolean isCourseContext,
			boolean editable, boolean clickable) {
		initWidget(uiBinder.createAndBindUi(this));
		this.isCourseContext = isCourseContext;
		this.showChecked = false;
		this.editable = true;
		this.clickable = true;
		initCompetenceSelectionWidget(selectedFilter);
		this.captionPanel.setCaptionHTML(title);
	}

	private void initCompetenceSelectionWidget(String selectedFilter) {
		this.selectedFilter = selectedFilter;

		initOperatorTree();
		initCatchwordTree();
		updateFilteredPanel();
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

	private void initCatchwordTree() {
		GWT.log("Initiating catchword tree");
		this.catchwordTree = new CatchwordSelectionTree(
				Controller.contextFactory.getServerURL()
						+ "/competences/xml/catchwordtree/"
						+ Controller.contextFactory.getOrganization()
						+ "/cached", "Schlagworte", "catchwordView", 325, 250,
				"Schlagworte", Controller.contextFactory, isCourseContext);
		catchwordCaptionPanel.add(catchwordTree);
		GWT.log("Initiated catchword tree");
	}

	private void initOperatorTree() {
		GWT.log("Initiating operator tree");
		this.operatorTree = new OperatorSelectionTree(
				Controller.contextFactory.getServerURL()
						+ "/competences/xml/operatortree/"
						+ Controller.contextFactory.getOrganization()
						+ "/cached", "Operatoren", "operatorView", 300, 200,
				"Operatoren", Controller.contextFactory, isCourseContext);
		operatorCaptionPanel.add(operatorTree);
		GWT.log("Initiated operator tree");
	}

	public void handleDeleteClick() {
		Resource resourceCompulsory = new Resource(
				Controller.contextFactory.getServerURL()
						+ "/competences/json/coursecontext/delete/"
						+ Controller.contextFactory.getCourseId());
		try {
			resourceCompulsory.post().send(new OkFeedBack());
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			GWT.log(e.getMessage());
		}

	}

	public void handleSubmit(final String requirementText) {
		PostRequestManager postRequestManager = new PostRequestManager();
		postRequestManager.addCompetencesToCourse(requirementText,
				competenceTree.convertSelectedTreeToList(),
				competenceTree.getCheckedNodes());
	}

	@UiHandler("alleRadioButton")
	void onRadioButtonClick(ClickEvent event) {
		filter = "all";
		updateFilteredPanel();
	}

	@UiHandler("verpflichtendeRadioButton")
	void onRadioButton_1Click(ClickEvent event) {
		filter = "true";
		updateFilteredPanel();
	}

	@UiHandler("nichtVerpflichtendeRadioButton")
	void onRadioButton_2Click(ClickEvent event) {
		filter = "false";
		updateFilteredPanel();
	}

	private void updateFilteredPanel() {
		competenceTreeCaptionPanel.clear();

		String query = "?";
		query += "textFilter=" + textSearchField.getValue() + "&";
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

		competenceTree = new CompetenceSelectionTree(
				RestUrlFactory.getCompetenceTreeWithFilters(filter, query,
						isCourseContext), Controller.contextFactory,
				selectedFilter, showChecked, isCourseContext, editable,
				clickable);
		// competenceTree.setShowCheckBoxes(showChecked);
		competenceTreeCaptionPanel.add(competenceTree);
	}

	@UiHandler("resetButton")
	void onResetButtonClick(ClickEvent event) {
		filter = "all";
		updateFilteredPanel();
		operatorTree.clearSelections();
		catchwordTree.clearSelections();
	}

	@UiHandler("filterButton")
	void onFilterButtonClick(ClickEvent event) {
		updateFilteredPanel();
	}

	public List<String> getSelectedCompetences() {
		return this.competenceTree.convertSelectedTreeToList();
	}

	public void reload() {
		updateFilteredPanel();
		operatorTree.reload();
		catchwordTree.reload();
		// competenceTree.reloadTree();
	}

	public HierarchieChangeSet getChanges() {
		return competenceTree.getChanges();
	}

	public void clearChanges() {
		competenceTree.clearChanges();

	}

	@UiHandler("collapseButton")
	void onCollapseButtonClick(ClickEvent event) {
		this.competenceTree.collapseAll();
	}
}
