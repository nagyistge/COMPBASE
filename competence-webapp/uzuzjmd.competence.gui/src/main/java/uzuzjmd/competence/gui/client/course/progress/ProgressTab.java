package uzuzjmd.competence.gui.client.course.progress;

import java.util.Map;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.shared.JsonUtil;
import uzuzjmd.competence.gui.client.shared.widgets.CompetenceTab;
import uzuzjmd.competence.gui.client.shared.widgets.taxonomy.CompetenceSelectionWidget;
import uzuzjmd.competence.gui.client.viewcontroller.Controller;

import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ProgressTab extends CompetenceTab {

	private static ProgressTabUiBinder uiBinder = GWT
			.create(ProgressTabUiBinder.class);

	@UiField
	SimplePanel HrPanelContainer;
	@UiField
	VerticalPanel progressPlaceHolder;
	@UiField
	SimplePanel hrPanelContainer2;
	@UiField
	SimplePanel tabExplainationPanel;
	@UiField
	Panel competenceSelectionPanelPlaceholder;
	@UiField
	FocusPanel warningPlaceholder;
	@UiField
	Button filterButton;

	private Alert alert;
	private CompetenceSelectionWidget competenceSelectionWidget;

	interface ProgressTabUiBinder extends UiBinder<Widget, ProgressTab> {
	}

	public ProgressTab() {
		initWidget(uiBinder.createAndBindUi(this));

		String infoText = "Hier können Sie die Zuordnung von den Kompetenzen und den Teilnehmern einsehen. Die Balken zeigen an, wie viele der ausgewählten Kompetenzen mit einer Aktivität eines Teilnehmers verknüpft wurden.";
		infoText += "Wenn keine Fortschrittsbalken angezeigt werden, müssen sie in dem Zuordung-Tab Aktivitäten zuordnen";
		fillInfoTab(infoText, tabExplainationPanel);
		initHrLines(HrPanelContainer);

		competenceSelectionWidget = new CompetenceSelectionWidget(null,
				"coursecontext/", "Auswahl der Kompetenzen für Fortschritt",
				true);

		competenceSelectionPanelPlaceholder.add(competenceSelectionWidget);

		showProgressEntries(true);

	}

	public void showProgressEntries(final boolean firstShow) {
		progressPlaceHolder.clear();
		GWT.log("Initiating progress entries");
		Resource resource = new Resource(
				Controller.contextFactory.getServerURL()
						+ "/competences/json/link/progress/"
						+ Controller.contextFactory.getOrganization());
		resource.addQueryParams("competences",
				competenceSelectionWidget.getSelectedCompetences()).get()
				.send(new JsonCallback() {

					@Override
					public void onSuccess(Method arg0, JSONValue arg1) {
						Map<String, String> userProgressMap = JsonUtil
								.toMap(arg1);
						GWT.log("addin progress entries to Progress Tab");
						for (String userName : userProgressMap.keySet()) {
							progressPlaceHolder.add(new ProgressEntry(userName,
									Integer.valueOf(userProgressMap
											.get(userName))));
						}
						GWT.log("finished adding progress entries to progress tab");
						if (!firstShow) {
							alert = new Alert("Erfolgreich gefiltert",
									AlertType.SUCCESS);
							warningPlaceholder.add(alert);
						}
					}

					@Override
					public void onFailure(Method arg0, Throwable arg1) {
						GWT.log("could not get progress map from server for course context"
								+ Controller.contextFactory.getOrganization());

						alert = new Alert(
								"Es gab Probleme bei der Datenbank, kontaktieren Sie einen Entwickler",
								AlertType.ERROR);
						warningPlaceholder.add(alert);
					}
				});
		GWT.log("Initiated progress entries");
	}

	@UiHandler("filterButton")
	void onFilterButtonClick(ClickEvent event) {
		showProgressEntries(false);
	}

	@UiHandler("warningPlaceholder")
	void onWarningPlaceholderMouseOut(MouseOutEvent event) {
		warningPlaceholder.remove(alert);
	}

	@UiHandler("warningPlaceholder")
	void onWarningPlaceholderClick(ClickEvent event) {
		warningPlaceholder.remove(alert);
	}

	public void reload() {
		competenceSelectionWidget.reload();
		showProgressEntries(false);
	}
}
