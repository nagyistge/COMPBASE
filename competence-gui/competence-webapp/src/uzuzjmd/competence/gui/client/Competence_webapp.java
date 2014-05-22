package uzuzjmd.competence.gui.client;

import uzuzjmd.competence.gui.client.evidenceView.EvidenceStackPanel;
import uzuzjmd.competence.gui.client.tabs.LinkEvidenceTab;
import uzuzjmd.competence.gui.client.tabs.ProgressTab;
import uzuzjmd.competence.gui.client.tabs.RequirementTab;
import uzuzjmd.competence.gui.client.viewcontroller.ReloadController;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;

//import com.gwtext.client.core.Function;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Competence_webapp implements EntryPoint {

	private NodeList<Element> element;
	public static ReloadController reloadController;
	public static final ContextFactory contextFactory = new ContextFactory();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		RootPanel container = RootPanel.get("rootContainer");

		// LinkEvidenceTab tab2 = new LinkEvidenceTab(contextFactory);
		// ProgressTab tab3 = new ProgressTab(contextFactory);
		// reloadController = new ReloadController(null, tab2, tab3);
		// RequirementTab tab = new RequirementTab(contextFactory);
		// container.add(tab);
		// initTabbedView(container, tab, tab2, tab3);

		PopupPanel popup = new PopupPanel();
		EvidenceStackPanel evidenceStackPanel = new EvidenceStackPanel(popup,
				"studentstudent", contextFactory);
		popup.add(evidenceStackPanel);
		popup.setAnimationEnabled(true);
		popup.setGlassEnabled(true);
		popup.setVisible(true);
		container.add(popup);

	}

	private void initTabbedView(RootPanel container, RequirementTab tab,
			LinkEvidenceTab tab2, ProgressTab tab3) {
		TabbedView tabbedView = new TabbedView();
		tabbedView.linkTabPlaceholder.add(tab2);
		tabbedView.progressTabPlaceholder.add(tab3);
		tabbedView.requirementTabPlaceholder.add(tab);

		tabbedView.tabPanel.selectTab(0, true);
		tabbedView.tabPanel.selectTab(1, true);
		tabbedView.tabPanel.selectTab(2, true);
		tabbedView.tabPanel.selectTab(3, true);
		tabbedView.tabPanel.selectTab(0);

		// container.add(tab2);

		container.add(tabbedView);

	}

	public static native void showPreview(String url, String selector,
			String whereTo)/*-{
		//		$wnd.preview(url, selector, whereTo);
		$wnd.previewdebug(url, selector, whereTo);
	}-*/;

	public static void showMoodlePreview(String url, String whereTo) {
		showPreview(url, ".region-content", whereTo);
	}
}
