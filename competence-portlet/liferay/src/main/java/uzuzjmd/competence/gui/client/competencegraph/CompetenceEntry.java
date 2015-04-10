package uzuzjmd.competence.gui.client.competencegraph;

import uzuzjmd.competence.gui.client.tabs.GraphTab;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
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
	private PopupPanel deleteCompetencePopup;
	private PopupPanel createCompetencePopup;

	interface CompetenceEntryUiBinder extends UiBinder<Widget, CompetenceEntry> {
	}

	public CompetenceEntry(String id, String text, GraphTab graphTab) {
		initWidget(uiBinder.createAndBindUi(this));
		this.competenceEntryHorizontalPanel.setStyleName(id, true);
		this.competenceNumber.add(new HTML(id));
		this.competenceText.add(new HTML(text));
		deleteCompetencePopup = new PopupPanel(true, true);
		deleteCompetencePopup.add(new CompetencePrerequisiteDeleteBinder(
				deleteCompetencePopup, graphTab, text));

		deleteCompetencePopup.hide();

		createCompetencePopup = new PopupPanel(true, true);
		createCompetencePopup.add(new CompetencePrerequisiteCreateBinder(
				createCompetencePopup, graphTab, text));

		createCompetencePopup.hide();
	}

	@UiHandler("deleteButton")
	void onDeleteButtonClick(ClickEvent event) {
		deleteCompetencePopup.show();
	}

	@UiHandler("prerequisiteCreationButton")
	void onPrerequisiteCreationButtonClick(ClickEvent event) {
		createCompetencePopup.show();
	}
}
