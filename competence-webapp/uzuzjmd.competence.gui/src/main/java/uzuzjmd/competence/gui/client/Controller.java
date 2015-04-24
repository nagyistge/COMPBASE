package uzuzjmd.competence.gui.client;

import uzuzjmd.competence.gui.client.tabs.CompetenceCreationTab;
import uzuzjmd.competence.gui.client.tabs.CompetenceDeleteWidget;
import uzuzjmd.competence.gui.client.tabs.CompetenceHierarchieTab;
import uzuzjmd.competence.gui.client.tabs.LinkEvidenceTab;
import uzuzjmd.competence.gui.client.tabs.ProgressTab;
import uzuzjmd.competence.gui.client.tabs.RequirementTab;
import uzuzjmd.competence.gui.client.viewcontroller.ReloadController;

public class Controller {
	public static ProgressTab progressTab;
	public static ReloadController reloadController;
	public static LmsContextFactory contextFactory;
	public static LinkEvidenceTab linkEvidenceTab;
	public static RequirementTab requirementTab;
	public static CompetenceCreationTab competenceCreationTab;
	public static CompetenceDeleteWidget competenceDeleteTab;
	public static CompetenceHierarchieTab competenceHierarchieTab;

	public static void init() {

		Controller.progressTab = new ProgressTab(Controller.contextFactory);
		Controller.competenceDeleteTab = new CompetenceDeleteWidget(
				Controller.contextFactory);
		Controller.competenceHierarchieTab = new CompetenceHierarchieTab(
				contextFactory);
		Controller.linkEvidenceTab = new LinkEvidenceTab(
				Controller.contextFactory);
		Controller.competenceCreationTab = new CompetenceCreationTab(
				Controller.contextFactory);
		Controller.reloadController = new ReloadController(null,
				linkEvidenceTab, Controller.progressTab,
				Controller.competenceCreationTab,
				Controller.competenceDeleteTab, competenceHierarchieTab);
		Controller.requirementTab = new RequirementTab(
				Controller.contextFactory);

	}

}
