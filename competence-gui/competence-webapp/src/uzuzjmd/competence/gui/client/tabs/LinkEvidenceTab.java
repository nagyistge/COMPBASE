package uzuzjmd.competence.gui.client.tabs;

import java.util.HashMap;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.XmlCallback;

import uzuzjmd.competence.gui.client.ContextFactory;
import uzuzjmd.competence.gui.client.competenceSelection.CompetenceSelectionWidget;
import uzuzjmd.competence.gui.shared.ActivityPanel2;
import uzuzjmd.competence.gui.shared.MyTreePanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

public class LinkEvidenceTab extends Composite {

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
	private CompetenceSelectionWidget competenceSelectionWidget;
	private ContextFactory contextFactory;

	final HashMap<String, String> activityMap = new HashMap<String, String>();
	final HashMap<String, String> activityMapToUser = new HashMap<String, String>();

	interface LinkEvidenceTabUiBinder extends UiBinder<Widget, LinkEvidenceTab> {
	}

	public LinkEvidenceTab(ContextFactory contextFactory) {
		initWidget(uiBinder.createAndBindUi(this));
		this.contextFactory = contextFactory;

		getActivityUrlMap();

		this.tabExplainationPanel
				.add(new Label(
						"Ordnen Sie die Kursaktivitäten den Kompetenzen zu! Dies ermöglicht eine Übersicht über die erreichten Kompetenzen pro Teilnehmer."));

		HTML html = new HTML(
				"<hr class=\"fancy-line\" style=\"width:100%;\" />");
		this.HrPanelContainer.add(html);

		competenceSelectionWidget = new CompetenceSelectionWidget(
				contextFactory, null, "coursecontext/");

		competenceSelectionPanelPlaceholder.add(competenceSelectionWidget);

		String moodleEvidenceUrl = getMoodleEvidenceServerUrl();
		MyTreePanel activityPanel = new ActivityPanel2(moodleEvidenceUrl,
				"Aktivitäten", "activityView", 650, 250, "Aktivitäten",
				contextFactory);
		activityPlaceholder.add(activityPanel);
	}

	private String getMoodleEvidenceServerUrl() {
		String moodleEvidenceUrl = contextFactory.getEvidenceServerURL()
				+ "/moodle/activities/usertree/xml/crossdomain/"
				+ contextFactory.getCourseId();
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
						activityMap.put(key, value);
						activityMapToUser.put(key, uservalue);
					}
				}
				GWT.log("activity map initialized" + activityMap.toString());
			}

			@Override
			public void onFailure(Method arg0, Throwable arg1) {
				GWT.log("could not get moodle evidences");
			}
		});
	}
}
