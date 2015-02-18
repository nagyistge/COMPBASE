package uzuzjmd.competence.gui.client.competencegraph;

import uzuzjmd.competence.gui.client.shared.widgets.ClickMenu;
import uzuzjmd.competence.gui.client.tabs.GraphTab;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class CompetenceClickPanel extends ClickMenu {

	private String idClicked;
	private String nodeClicked;
	private PopupPanel competenceDeletePopup;
	private CompetencePrerequisiteDeleteBinder competencePrerequisiteDeleteBinder;
	private PopupPanel competenceCreatePopup;
	private CompetencePrerequisiteCreateBinder competencePrerequisiteCreateBinder;

	public CompetenceClickPanel(GraphTab graphTab) {
		initWidget(uiBinder.createAndBindUi(this));
		this.buttonWrapper.setVisible(true);
		this.buttonWrapper.setStyleName("competenceLinkButtonWrapper", true);
		this.setStyleName("competenceLinkButtonWrapper", true);
		competenceDeletePopup = new PopupPanel(false, false);
		competencePrerequisiteDeleteBinder = new CompetencePrerequisiteDeleteBinder(
				competenceDeletePopup, graphTab);
		competenceDeletePopup.add(competencePrerequisiteDeleteBinder);
		competenceDeletePopup.hide();

		competenceCreatePopup = new PopupPanel(false, false);
		competencePrerequisiteCreateBinder = new CompetencePrerequisiteCreateBinder(
				competenceCreatePopup, graphTab, null);
		competenceCreatePopup.add(competencePrerequisiteCreateBinder);
		competenceCreatePopup.hide();
	}

	private static CompetenceClickPanelUiBinder uiBinder = GWT
			.create(CompetenceClickPanelUiBinder.class);
	@UiField
	Button deleteRequirementsButton;
	@UiField
	Button createRequirmentsbutton;
	@UiField
	DecoratorPanel buttonWrapper;

	interface CompetenceClickPanelUiBinder extends
			UiBinder<Widget, CompetenceClickPanel> {
	}

	@Override
	public void onMouseOut(MouseOutEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setId(String id) {
		this.idClicked = id;
	}

	@Override
	public void setNodeId(String nodeId) {
		this.nodeClicked = nodeId;
		this.competencePrerequisiteDeleteBinder.setCompetenceSelected(nodeId);
		this.competencePrerequisiteCreateBinder.setCompetenceSelected(nodeId);
	}

	@Override
	public void loadContent() {
	}

	@UiHandler("deleteRequirementsButton")
	void onDeleteRequirementsButtonClick(ClickEvent event) {
		competenceDeletePopup.show();
	}

	@UiHandler("createRequirmentsbutton")
	void onCreateRequirmentsbuttonClick(ClickEvent event) {
		competenceCreatePopup.show();
	}
}
