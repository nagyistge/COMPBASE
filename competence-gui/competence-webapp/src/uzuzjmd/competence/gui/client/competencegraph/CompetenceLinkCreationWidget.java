package uzuzjmd.competence.gui.client.competencegraph;

import uzuzjmd.competence.gui.client.Competence_webapp;
import uzuzjmd.competence.gui.client.competenceSelection.CompetenceSelectionWidget;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class CompetenceLinkCreationWidget extends Composite {

	private static CompetenceLinkCreationWidgetUiBinder uiBinder = GWT
			.create(CompetenceLinkCreationWidgetUiBinder.class);
	@UiField
	SimplePanel requiredKompetenzesPlaceholder;
	@UiField
	SimplePanel followingCompetences;
	@UiField
	SimplePanel requiredKompetenzesPlaceholder2;
	@UiField
	SimplePanel followingCompetences2;

	@UiField
	Button submitButton;
	@UiField
	Button cancelButton;

	private CompetenceSelectionWidget competenceSelectionWidget;
	private CompetenceSelectionWidget competenceSelectionWidget2;
	private PopupPanel parent;

	interface CompetenceLinkCreationWidgetUiBinder extends
			UiBinder<Widget, CompetenceLinkCreationWidget> {
	}

	public CompetenceLinkCreationWidget(PopupPanel parent) {
		initWidget(uiBinder.createAndBindUi(this));
		this.parent = parent;
		competenceSelectionWidget = new CompetenceSelectionWidget(
				Competence_webapp.contextFactory, null, "coursecontext/",
				"Vorausgesetzte Kompetenzen");
		requiredKompetenzesPlaceholder.add(competenceSelectionWidget);
		requiredKompetenzesPlaceholder2.add(competenceSelectionWidget);

		competenceSelectionWidget2 = new CompetenceSelectionWidget(
				Competence_webapp.contextFactory, null, "coursecontext/",
				"Nachfolgende Kompetenzen");
		followingCompetences.add(competenceSelectionWidget2);
		followingCompetences2.add(competenceSelectionWidget2);
	}

	@UiHandler("submitButton")
	void onSubmitButtonClick(ClickEvent event) {
	}

	@UiHandler("cancelButton")
	void onCancelButtonClick(ClickEvent event) {
		parent.hide();
	}
}
