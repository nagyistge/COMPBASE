package uzuzjmd.competence.gui.client;

import uzuzjmd.competence.gui.client.tabs.LinkEvidenceTab;
import uzuzjmd.competence.gui.client.tabs.ProgressTab;
import uzuzjmd.competence.gui.client.tabs.RequirementTab;
import uzuzjmd.competence.gui.client.viewcontroller.ReloadController;

import com.google.gwt.core.client.GWT;

public class Controller {
	public static ProgressTab progessTab;
	public static ReloadController reloadController;
	public static LmsContextFactory contextFactory;
	public static LinkEvidenceTab linkEvidenceTab;
	public static RequirementTab requirementTab;

	public static void init() {
//		MyConstants myConstants = GWT.create(MyConstants.class);

//		if (myConstants.contextImplementation().equals("moodle")) {
//			Controller.contextFactory = new MoodleContextFactory();
//		}
//
//		if (myConstants.contextImplementation().equals("liferay")) {
//			Controller.contextFactory = CompetenceUIContainer.contextFactory;
//		}

		Controller.progessTab = new ProgressTab(Controller.contextFactory);

		Controller.linkEvidenceTab = new LinkEvidenceTab(
				Controller.contextFactory);

		Controller.reloadController = new ReloadController(null,
				linkEvidenceTab, Controller.progessTab);
		Controller.requirementTab = new RequirementTab(
				Controller.contextFactory);
	}

}
