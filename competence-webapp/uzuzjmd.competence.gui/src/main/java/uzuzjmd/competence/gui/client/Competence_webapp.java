package uzuzjmd.competence.gui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
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

		MyConstants myConstants = GWT.create(MyConstants.class);
		if (myConstants.contextImplementation().equals("moodle")) {
			RootPanel container = RootPanel.get("rootContainer");

			CompetenceUIContainer competenceUIContainer = new CompetenceUIContainer();
			competenceUIContainer.setContextFactory(new MoodleContextFactory());
			container.add(competenceUIContainer);
		}

		if (myConstants.contextImplementation().equals("test")) {
			RootPanel container = RootPanel.get("rootContainer");

			CompetenceUIContainer competenceUIContainer = new CompetenceUIContainer();
			competenceUIContainer.setContextFactory(new TestContextFactory());
			container.add(competenceUIContainer);
		}

		// if the context is liferay the connector should inject the stuff

	}

}
