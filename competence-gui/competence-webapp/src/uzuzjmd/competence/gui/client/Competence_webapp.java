package uzuzjmd.competence.gui.client;

import uzuzjmd.competence.gui.shared.ActivityPanel2;

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

		ActivityPanel2 activityPanel = new ActivityPanel2(
				"http://localhost:8083/moodle/activities/usertree/xml/crossdomain/2");
		container.add(activityPanel);

	}

	public static native void showPreview(String url, String selector,
			String whereTo)/*-{
		$wnd.preview(url, selector, whereTo);
		//$wnd.previewdebug(url, selector, whereTo);

	}-*/;

}
