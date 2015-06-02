package uzuzjmd.competence.gui.client;

import uzuzjmd.competence.gui.client.context.JavaScriptContextFactory;
import uzuzjmd.competence.gui.client.context.StandaloneContextFactory;
import uzuzjmd.competence.gui.client.context.TestContextFactory;
import uzuzjmd.competence.gui.client.login.LoginView;
import uzuzjmd.competence.gui.client.viewcontroller.Controller;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.PopupPanel;
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
			competenceUIContainer
					.setContextFactory(new JavaScriptContextFactory());
			container.add(competenceUIContainer);
		}

		if (myConstants.contextImplementation().equals("test")
				|| myConstants.contextImplementation().equals("standalone")
				|| myConstants.contextImplementation().equals("deploy")) {
			RootPanel container = RootPanel.get("rootContainer");

			CompetenceUIContainer competenceUIContainer = new CompetenceUIContainer();
			if (myConstants.contextImplementation().equals("deploy")
					|| myConstants.contextImplementation().equals("standalone")) {
				String serverUrl = myConstants.serverName() + "/"
						+ myConstants.restserverPrefix();
				String evidenceUrl = myConstants.serverName() + "/"
						+ myConstants.evidenceserverPrefix();
				competenceUIContainer
						.setContextFactory(new StandaloneContextFactory(
								serverUrl, evidenceUrl));
			} else {
				competenceUIContainer.setContextFactory(new TestContextFactory(
						"http://localhost:8084", "http://localhost:8084"));
			}
			container.add(competenceUIContainer);

			if (myConstants.contextImplementation().equals("standalone")
					|| myConstants.contextImplementation().equals("test")) {
				PopupPanel popupPanel = new PopupPanel();
				popupPanel.setModal(true);
				LoginView loginView = new LoginView(popupPanel, myConstants
						.contextImplementation().equals("test"));
				popupPanel.add(loginView);
				popupPanel.setGlassEnabled(true);
				container.add(popupPanel);
				popupPanel.show();
			}

			Controller.contextFactory.setMode(myConstants
					.contextImplementation());
			Controller.contextFactory.setLmsSystem(myConstants.lmsSystem());
		}

		// if the context is liferay the connector should inject the stuff

	}
}
