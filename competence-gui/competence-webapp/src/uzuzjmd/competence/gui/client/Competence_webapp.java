package uzuzjmd.competence.gui.client;

import uzuzjmd.competence.gui.client.tabs.LinkEvidenceTab;
import uzuzjmd.competence.gui.client.tabs.ProgressTab;
import uzuzjmd.competence.gui.client.tabs.RequirementTab;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.ui.RootPanel;

//import com.gwtext.client.core.Function;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Competence_webapp implements EntryPoint {

	private NodeList<Element> element;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		RootPanel container = RootPanel.get("rootContainer");
		final ContextFactory contextFactory = new ContextFactory();
		RequirementTab tab = new RequirementTab(contextFactory);
		LinkEvidenceTab tab2 = new LinkEvidenceTab(contextFactory);
		ProgressTab tab3 = new ProgressTab(contextFactory);
		container.add(tab3);
	}

	public static native void showPreview(String url, String selector,
			String whereTo)/*-{
		$wnd.preview(url, selector, whereTo);
		//$wnd.previewdebug(url, selector, whereTo);

	}-*/;

}
