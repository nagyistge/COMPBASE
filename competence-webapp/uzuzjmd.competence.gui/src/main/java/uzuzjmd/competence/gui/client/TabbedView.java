package uzuzjmd.competence.gui.client;

import java.util.Date;

import uzuzjmd.competence.gui.client.viewcontroller.ContextSelectionController;
import uzuzjmd.competence.gui.client.viewcontroller.Controller;

import com.github.gwtbootstrap.client.ui.TabPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class TabbedView extends Composite {

	private static TabbedViewUiBinder uiBinder = GWT
			.create(TabbedViewUiBinder.class);
	@UiField
	TabPanel tabPanel;
	@UiField
	TabPanel taxonomy;
	@UiField
	TabPanel courseContext;
	@UiField
	HTMLPanel requirementTabPlaceholder;
	@UiField
	HTMLPanel linkTabPlaceholder;
	@UiField
	HTMLPanel progressTabPlaceholder;
	// @UiField
	// HTMLPanel pathTabPlaceholder;
	@UiField
	HTMLPanel competenceCreationHolder;
	@UiField
	HTMLPanel competenceDeleteHolder;
	@UiField
	HTMLPanel competenceHierarchieChangeHolder;
	@UiField
	HTMLPanel competenceEditHolder;

	@UiField
	SimplePanel coursesSelectionPlaceHolder;

	// @UiField
	// DropdownButton courseSelection;

	// @UiField
	// DropdownButton coursesDropdown;

	interface TabbedViewUiBinder extends
			UiBinder<Widget, TabbedView> {
	}

	public TabbedView() {
		initWidget(uiBinder.createAndBindUi(this));
		tabPanel.selectTab(0);
		taxonomy.selectTab(0);
		courseContext.selectTab(0);
		Controller.courseContextTabPanel = courseContext;
	}

	@Override
	protected void onLoad() {
		GWT.log("loading tabbed view xx");
		super.onLoad();
		Date date = new Date();
		GWT.log("attaching Tabbed View at: "
				+ date.toString());
		GWT.log(Controller.contextFactory.getUser());

		// super.onAttach();
		// GWT.log("finished Tabbed View");

		GWT.log("initiating graphTab");
		initGraph();
		GWT.log("finished initiating graph tab");
	}

	public void initGraph() {
		GWT.log("start initializing graph");
		// ReloadController.graphTab = new GraphTab();
		// pathTabPlaceholder.add(ReloadController.graphTab);
	}

	public void updateCourseSelectionPanel() {
		coursesSelectionPlaceHolder.clear();
		coursesSelectionPlaceHolder
				.add(ContextSelectionController.courseSelectionButton);
	}

}
