package uzuzjmd.competence.gui.client.viewcontroller;

import uzuzjmd.competence.gui.client.TabbedView;
import uzuzjmd.competence.gui.client.context.LmsContextFactory;
import uzuzjmd.competence.gui.client.course.activity.LinkEvidenceTab;
import uzuzjmd.competence.gui.client.course.progress.ProgressTab;
import uzuzjmd.competence.gui.client.course.requirement.RequirementTab;
import uzuzjmd.competence.gui.client.taxonomy.CompetenceCreationTab;
import uzuzjmd.competence.gui.client.taxonomy.CompetenceDeleteWidget;
import uzuzjmd.competence.gui.client.taxonomy.CompetenceEditTab;
import uzuzjmd.competence.gui.client.taxonomy.CompetenceHierarchieTab;

import com.github.gwtbootstrap.client.ui.TabPanel;

public class Controller {
	public static ProgressTab progressTab;
	public static ReloadController reloadController;
	public static LmsContextFactory contextFactory;
	public static LinkEvidenceTab linkEvidenceTab;
	public static RequirementTab requirementTab;
	public static CompetenceCreationTab competenceCreationTab;
	public static CompetenceDeleteWidget competenceDeleteTab;
	public static CompetenceHierarchieTab competenceHierarchieTab;
	public static CompetenceEditTab competenceEditTab;
	public static TabbedView tabbedView;
	public static TabPanel courseContextTabPanel;

	public static void init() {

		Controller.progressTab = new ProgressTab();
		Controller.competenceDeleteTab = new CompetenceDeleteWidget(
				Controller.contextFactory);
		Controller.competenceHierarchieTab = new CompetenceHierarchieTab(
				contextFactory);
		Controller.linkEvidenceTab = new LinkEvidenceTab();
		Controller.competenceCreationTab = new CompetenceCreationTab(
				Controller.contextFactory);
		Controller.competenceEditTab = new CompetenceEditTab(
				contextFactory);
		Controller.reloadController = new ReloadController(
				null, linkEvidenceTab,
				Controller.progressTab,
				Controller.competenceCreationTab,
				Controller.competenceDeleteTab,
				competenceHierarchieTab, competenceEditTab);
		Controller.requirementTab = new RequirementTab(
				Controller.contextFactory);

	}
}
