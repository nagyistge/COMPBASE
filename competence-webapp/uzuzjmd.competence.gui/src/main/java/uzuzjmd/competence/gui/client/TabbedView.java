package uzuzjmd.competence.gui.client;

import java.util.Date;

import uzuzjmd.competence.gui.client.tabs.GraphTab;
import uzuzjmd.competence.gui.client.viewcontroller.ReloadController;

import com.github.gwtbootstrap.client.ui.TabPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class TabbedView extends Composite {

	private static TabbedViewUiBinder uiBinder = GWT
			.create(TabbedViewUiBinder.class);
	@UiField
	TabPanel tabPanel;
	@UiField
	HTMLPanel requirementTabPlaceholder;
	@UiField
	HTMLPanel linkTabPlaceholder;
	@UiField
	HTMLPanel progressTabPlaceholder;
	@UiField
	HTMLPanel pathTabPlaceholder;
	@UiField
	HTMLPanel competenceCreationHolder;
	@UiField
	HTMLPanel competenceDeleteHolder;
	@UiField
	HTMLPanel competenceHierarchieChangeHolder;

	interface TabbedViewUiBinder extends UiBinder<Widget, TabbedView> {
	}

	public TabbedView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public TabbedView(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));

	}

	@Override
	protected void onLoad() {
		GWT.log("loading tabbed view xx");
		super.onLoad();
		Date date = new Date();
		GWT.log("attaching Tabbed View at: " + date.toString());
		GWT.log(Controller.contextFactory.getUser());

		// super.onAttach();
		// GWT.log("finished Tabbed View");

		GWT.log("initiating graphTab");
		initGraph();
		GWT.log("finished initiating graph tab");
	}

	public void initGraph() {
		GWT.log("start initializing graph");
		ReloadController.graphTab = new GraphTab();
		pathTabPlaceholder.add(ReloadController.graphTab);
	}

}
