package uzuzjmd.competence.gui.client.tabs;

import uzuzjmd.competence.gui.client.ContextFactory;
import uzuzjmd.competence.gui.client.competenceSelection.CompetenceSelectionWidget;
import uzuzjmd.competence.gui.shared.ActivityPanel2;
import uzuzjmd.competence.gui.shared.MyTreePanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class LinkEvidenceTab extends Composite {

	private static LinkEvidenceTabUiBinder uiBinder = GWT
			.create(LinkEvidenceTabUiBinder.class);
	@UiField
	SimplePanel HrPanelContainer;
	@UiField
	HTMLPanel activityPlaceholder;
	@UiField
	SimplePanel hrPanelContainer2;
	@UiField
	SimplePanel buttonContainer;
	@UiField
	SimplePanel tabExplainationPanel;
	@UiField
	Panel competenceSelectionPanelPlaceholder;
	private CompetenceSelectionWidget competenceSelectionWidget;

	interface LinkEvidenceTabUiBinder extends UiBinder<Widget, LinkEvidenceTab> {
	}

	public LinkEvidenceTab(ContextFactory contextFactory) {
		initWidget(uiBinder.createAndBindUi(this));

		this.tabExplainationPanel
				.add(new Label(
						"Ordnen Sie die Kursaktivitäten den Kompetenzen zu! Dies ermöglicht eine Übersicht über die erreichten Kompetenzen pro Teilnehmer. Mit Doppelklick auf die Kompetenz können Sie Pfade für den Kompetenzerwerb definieren!"));

		HTML html = new HTML(
				"<hr class=\"fancy-line\" style=\"width:100%;\" />");
		this.HrPanelContainer.add(html);

		competenceSelectionWidget = new CompetenceSelectionWidget(
				contextFactory, null, "coursecontext/");

		competenceSelectionPanelPlaceholder.add(competenceSelectionWidget);

		MyTreePanel activityPanel = new ActivityPanel2(
				contextFactory.getEvidenceServerURL()
						+ "/moodle/activities/usertree/xml/crossdomain/"
						+ contextFactory.getCourseId(), "Aktivitäten",
				"activityView", 650, 250, "Aktivitäten", contextFactory);
		activityPlaceholder.add(activityPanel);

	}
}
