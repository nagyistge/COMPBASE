package uzuzjmd.competence.gui.client;

import uzuzjmd.competence.gui.client.tabs.LinkEvidenceTab;
import uzuzjmd.competence.gui.client.tabs.ProgressTab;
import uzuzjmd.competence.gui.client.tabs.RequirementTab;
import uzuzjmd.competence.gui.client.viewcontroller.ReloadController;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.ui.RootPanel;

//import com.gwtext.client.core.Function;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Competence_webapp implements EntryPoint {

	private NodeList<Element> element;
	private static ProgressTab progessTab;
	public static ReloadController reloadController;
	public static ContextFactory contextFactory;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		MyConstants myConstants = GWT.create(MyConstants.class);

		if (myConstants.contextImplementation().equals("moodle")) {
			contextFactory = new MoodleContextFactory();
		}

		if (myConstants.contextImplementation().equals("liferay")) {
			contextFactory = new LiferayContextFactory();
		}

		RootPanel container = RootPanel.get("rootContainer");
		LinkEvidenceTab tab2 = new LinkEvidenceTab(contextFactory);
		progessTab = new ProgressTab(contextFactory);
		reloadController = new ReloadController(null, tab2, progessTab);
		RequirementTab tab = new RequirementTab(contextFactory);
		initTabbedView(container, tab, tab2, progessTab);

		// GraphTab graphTab = new GraphTab();
		// container.add(graphTab);

	}

	public static ProgressTab getProgressTab() {
		return progessTab;
	}

	private void initTabbedView(RootPanel container, RequirementTab tab,
			LinkEvidenceTab tab2, ProgressTab tab3) {
		TabbedView tabbedView = new TabbedView();
		tabbedView.linkTabPlaceholder.add(tab2);
		tabbedView.progressTabPlaceholder.add(tab3);

		if (!Competence_webapp.contextFactory.getRole().equals("student")) {
			tabbedView.requirementTabPlaceholder.add(tab);
			tabbedView.pathTabPlaceholder.setVisible(false);
		} else {
			tabbedView.tabPanel.remove(0);
			tabbedView.tabPanel.remove(0);
			// tabbedView.tabPanel.remove(1);
			tab.setVisible(false);
		}

		// tabbedView.tabPanel.selectTab(0, true);
		// tabbedView.tabPanel.selectTab(1, true);
		// tabbedView.tabPanel.selectTab(2, true);
		// tabbedView.tabPanel.selectTab(3, true);
		// tabbedView.tabPanel.selectTab(0);

		// container.add(tab2);

		container.add(tabbedView);
	}

	public static native void showPreview(String url, String selector,
			String whereTo)/*-{
		$wnd.preview(url, selector, whereTo);
		//		$wnd.previewdebug(url, selector, whereTo);
	}-*/;

	public static void showMoodlePreview(String url, String whereTo) {
		showPreview(url, ".region-content", whereTo);
		showPreview(url, ".region-main", whereTo);
	}
}
