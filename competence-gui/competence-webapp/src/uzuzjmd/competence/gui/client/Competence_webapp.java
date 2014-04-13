package uzuzjmd.competence.gui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Competence_webapp implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel container = RootPanel.get("rootContainer");
		Label label = new Label("test popup");
		container.add(label);

		ActivityPreview activityPreview = new ActivityPreview(
				"http://localhost/moodle/mod/assign/view.php?id=6",
				"assignmentpreview");
		// Label labe2 = new Label("hier ide Maus drüber halten");
		// activityPreview.add(labe2);
		container.add(activityPreview);

		// FlowPanel flowpanel = new FlowPanel();
		// flowpanel.getElement().setClassName("preview-container");
		// container.add(flowpanel);
		//
		// // showPreview("http://localhost/moodle/mod/forum/discuss.php?d=2",
		// // ".region-content", ".preview-container");
		//
		// showPreview("http://localhost/moodle/mod/assign/view.php?id=6",
		// ".region-content", ".preview-container");
	}

	public native void showPreview(String url, String selector, String whereTo)/*-{
		$wnd.preview(url, selector, whereTo);
	}-*/;

}
