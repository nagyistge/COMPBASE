package uzuzjmd.competence.gui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

//import com.gwtext.client.core.Function;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Competence_webapp implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		RootPanel container = RootPanel.get("rootContainer");

		CompetenceUIContainer competenceUIContainer = new CompetenceUIContainer();
		container.add(competenceUIContainer);
	}

}
