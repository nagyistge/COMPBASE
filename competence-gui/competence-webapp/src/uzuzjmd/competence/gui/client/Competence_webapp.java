package uzuzjmd.competence.gui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.widgets.Panel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Competence_webapp implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		RootPanel container = RootPanel.get("rootContainer");

		Panel panel1 = new Panel();
		ActivityEntry activityEntry = new ActivityEntry(
				"http://localhost/moodle/mod/assign/view.php?id=6",
				"assignment", "just an assignmen");
		panel1.add(activityEntry);

		container.add(panel1);

		ActivityTooltip activityTooltip = new ActivityTooltip(
				"http://localhost/moodle/mod/assign/view.php?id=6",
				"assignmentpreview");

		activityTooltip.init(panel1);

	}
}
