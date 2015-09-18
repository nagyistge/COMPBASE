package uzuzjmd.competence.gui.client.course.progress;

import java.util.Map;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.shared.JsonUtil;
import uzuzjmd.competence.gui.client.shared.widgets.CompetenceTab;
import uzuzjmd.competence.gui.client.shared.widgets.taxonomy.CompetenceSelectionWidget;
import uzuzjmd.competence.gui.client.viewcontroller.Controller;
import uzuzjmd.competence.service.rest.client.api.RestUrlFactory;

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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ProgressTab extends CompetenceTab {

	private static ProgressTabUiBinder uiBinder = GWT
			.create(ProgressTabUiBinder.class);

	@UiField
	VerticalPanel progressPlaceHolder;

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
		competenceSelectionWidget = new CompetenceSelectionWidget(null,
				"coursecontext/", "Auswahl der Kompetenzen für Fortschritt",
				true);

		competenceSelectionPanelPlaceholder.add(competenceSelectionWidget);

		showProgressEntries(true);

	}

	public void showProgressEntries(final boolean firstShow) {
		progressPlaceHolder.clear();
		GWT.log("Initiating progress entries");
		Resource resource = new Resource(RestUrlFactory.getProgressMapURL());
		resource.addQueryParams("competences",
				competenceSelectionWidget.getSelectedCompetences()).get()
				.send(new JsonCallback() {

					@Override
					public void onSuccess(Method arg0, JSONValue arg1) {
						GWT.log("got progress");
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
