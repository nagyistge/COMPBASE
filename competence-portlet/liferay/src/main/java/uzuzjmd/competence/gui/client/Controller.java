package uzuzjmd.competence.gui.client;

import uzuzjmd.competence.gui.client.tabs.LinkEvidenceTab;
import uzuzjmd.competence.gui.client.tabs.ProgressTab;
import uzuzjmd.competence.gui.client.tabs.RequirementTab;
import uzuzjmd.competence.gui.client.viewcontroller.ReloadController;

import com.google.gwt.core.client.GWT;

public class Controller {
	public static ProgressTab progressTab;
	public static ReloadController reloadController;
	public static LmsContextFactory contextFactory;
	public static LinkEvidenceTab linkEvidenceTab;
	public static RequirementTab requirementTab;

	public static void init() {

		Controller.progressTab = new ProgressTab(Controller.contextFactory);

		Controller.linkEvidenceTab = new LinkEvidenceTab(
				Controller.contextFactory);

		Controller.reloadController = new ReloadController(null,
				linkEvidenceTab, Controller.progressTab);
		Controller.requirementTab = new RequirementTab(
				Controller.contextFactory);
	}

}
