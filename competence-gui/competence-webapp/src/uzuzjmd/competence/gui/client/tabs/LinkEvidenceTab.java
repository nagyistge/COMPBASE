package uzuzjmd.competence.gui.client.tabs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.XmlCallback;

import uzuzjmd.competence.gui.client.ContextFactory;
import uzuzjmd.competence.gui.client.competenceSelection.CompetenceSelectionWidget;
import uzuzjmd.competence.gui.shared.ActivityPanel2;

import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.base.HtmlWidget;
import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class LinkEvidenceTab extends CompetenceTab {

	private static LinkEvidenceTabUiBinder uiBinder = GWT
			.create(LinkEvidenceTabUiBinder.class);
	@UiField
	SimplePanel HrPanelContainer;
	@UiField
	HTMLPanel activityPlaceholder;
	@UiField
	SimplePanel hrPanelContainer2;
	@UiField
	HorizontalPanel buttonContainer;
	@UiField
	SimplePanel tabExplainationPanel;
	@UiField
	Panel competenceSelectionPanelPlaceholder;
	@UiField
	Button submitButton;
	@UiField
	FocusPanel warningPlaceholder;
	private CompetenceSelectionWidget competenceSelectionWidget;
	private ContextFactory contextFactory;

	final HashMap<String, String> activityMapToUrl = new HashMap<String, String>();
	final HashMap<String, String> activityMapToUser = new HashMap<String, String>();
	private ActivityPanel2 activityPanel;
	private HtmlWidget alert;

	interface LinkEvidenceTabUiBinder extends UiBinder<Widget, LinkEvidenceTab> {
	}

	public LinkEvidenceTab(ContextFactory contextFactory) {
		initWidget(uiBinder.createAndBindUi(this));
		this.contextFactory = contextFactory;

		getActivityUrlMap();

		String infoText = "Ordnen Sie die Kursaktivitäten den Kompetenzen zu! Dies ermöglicht eine Übersicht über die erreichten Kompetenzen pro Teilnehmer.";
		fillInfoTab(infoText, tabExplainationPanel);
		initHrLines(HrPanelContainer);

		competenceSelectionWidget = new CompetenceSelectionWidget(
				contextFactory, null, "coursecontext/");

		competenceSelectionPanelPlaceholder.add(competenceSelectionWidget);

		String moodleEvidenceUrl = getMoodleEvidenceServerUrl();
		activityPanel = new ActivityPanel2(moodleEvidenceUrl, "Aktivitäten",
				"activityView", 650, 150, "Aktivitäten", contextFactory);
		activityPlaceholder.add(activityPanel);

	}

	private String getMoodleEvidenceServerUrl() {
		String moodleEvidenceUrl = contextFactory.getEvidenceServerURL()
				+ "/moodle/activities/usertree/xml/crossdomain/"
				+ contextFactory.getMoodleCourseId();
		return moodleEvidenceUrl;
	}

	private void getActivityUrlMap() {
		Resource resource = new Resource(getMoodleEvidenceServerUrl());
		resource.get().send(new XmlCallback() {

			@Override
			public void onSuccess(Method arg0, Document arg1) {
				Node activityNode = null;
				for (int k = 0; k < arg1.getFirstChild().getChildNodes()
						.getLength(); k++)
					activityNode = arg1.getFirstChild().getChildNodes().item(k);
				NodeList activityList = activityNode.getChildNodes();
				for (int i = 0; i < activityList.getLength(); i++) {
					NodeList activityLeafs = activityList.item(i)
							.getChildNodes();
					for (int j = 0; j < activityLeafs.getLength(); j++) {
						Node node = activityLeafs.item(j);
						String key = node.getAttributes().getNamedItem("name")
								.getNodeValue();
						String value = node.getFirstChild().getFirstChild()
								.toString();
						String uservalue = activityNode.getAttributes()
								.getNamedItem("name").getNodeValue();
						activityMapToUrl.put(key, value);
						activityMapToUser.put(key, uservalue);
					}
				}
				GWT.log("activity map initialized"
						+ activityMapToUrl.toString());
			}

			@Override
			public void onFailure(Method arg0, Throwable arg1) {
				GWT.log("could not get moodle evidences");
			}
		});
	}

	@UiHandler("submitButton")
	void onSubmitButtonClick(ClickEvent event) {
		List<String> competences = this.competenceSelectionWidget
				.getSelectedCompetences();
		List<String> activityEvidenceKeys = this.activityPanel
				.convertSelectedTreeToList();
		if (competences.isEmpty()) {
			alert = new Alert("Es wurden keine Kompetenzen ausgewählt!",
					AlertType.ERROR);
			warningPlaceholder.add(alert);
		} else if (activityEvidenceKeys.isEmpty()) {
			alert = new Alert("Es wurden keine Aktivitäten ausgewählt!",
					AlertType.ERROR);
			warningPlaceholder.add(alert);
		} else {
			String creator = contextFactory.getUser();
			String course = contextFactory.getCourseId() + "";
			Boolean isLeaf = checkIfActivityLeafSelected(activityEvidenceKeys);
			if (isLeaf) {
				for (String key : activityEvidenceKeys) {
					createAbstractEvidenceLink(competences, creator, course,
							key, contextFactory.getRole());
				}
			} else {
				alert = new Alert(
						"Die direkte Zuordnung von Aktivitätstypen oder Usern zu Kompetenzen wird noch nicht unterstützt! Wir arbeiten daran! Wählen sie bitte nur konkrete Aktivitäten!",
						AlertType.ERROR);
				warningPlaceholder.add(alert);
			}
		}
	}

	private void createAbstractEvidenceLink(List<String> competences,
			String creator, String course, String key, String creatorRole) {
		List<String> activityPairs = new LinkedList<String>();
		activityPairs.add(activityMapToUrl.get(key) + "," + key);
		String linkedUser = activityMapToUser.get(key);
		Resource resource = new Resource(contextFactory.getServerURL()
				+ "/competences/json/link/create/" + course + "/" + creator
				+ "/" + creatorRole + "/" + linkedUser);
		try {
			resource.addQueryParams("competences", competences)
					.addQueryParams("evidences", activityPairs).post()
					.send(new OkFeedBack());
		} catch (RequestException e) {
			GWT.log(e.getMessage());
		}
		alert = new Alert("Die Kompetenzen wurden erfolgreich verknüpft",
				AlertType.SUCCESS);
		warningPlaceholder.add(alert);
	}

	private Boolean checkIfActivityLeafSelected(
			List<String> activityEvidenceKeys) {
		Boolean isLeaf = true;
		for (String key : activityEvidenceKeys) {
			if (!activityMapToUrl.containsKey(key)) {
				isLeaf = false;
			}
		}
		return isLeaf;
	}

	private class OkFeedBack implements RequestCallback {
		@Override
		public void onError(Request request, Throwable exception) {
			// TODO Auto-generated method stub
			GWT.log(exception.getMessage());
		}

		@Override
		public void onResponseReceived(Request request, Response response) {
			GWT.log(response.getStatusText());
		}
	}

	@UiHandler("warningPlaceholder")
	void onWarningPlaceholderClick(ClickEvent event) {
		warningPlaceholder.remove(alert);
	}

	@UiHandler("warningPlaceholder")
	void onWarningPlaceholderMouseOut(MouseOutEvent event) {
		warningPlaceholder.remove(alert);
	}
}
