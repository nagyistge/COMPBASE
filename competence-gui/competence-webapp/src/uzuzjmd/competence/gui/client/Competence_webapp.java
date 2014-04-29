package uzuzjmd.competence.gui.client;

import uzuzjmd.competence.gui.shared.ActivityPanel2;
import uzuzjmd.competence.gui.shared.CompetenceSelectionPanel;
import uzuzjmd.competence.gui.shared.MyTreePanel;

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

		MyTreePanel activityPanel = new ActivityPanel2(
				"http://localhost:8083/moodle/activities/usertree/xml/crossdomain/2",
				"Aktivitäten", "activityView", 300, "Aktivitäten");
		container.add(activityPanel);

		// Panel panel = new Panel();
		// panel.setWidth(600);

		MyTreePanel competencePanel = new CompetenceSelectionPanel(
				"http://localhost:8084/competences/tree/xml/crossdomain/4",
				"Kompetenzen", "competenceView", 600, "Kompetenzen");
		// panel.add(competencePanel);
		container.add(competencePanel);

	}

	public static native void showPreview(String url, String selector,
			String whereTo)/*-{
		$wnd.preview(url, selector, whereTo);
		//$wnd.previewdebug(url, selector, whereTo);

	}-*/;

}
