package uzuzjmd.competence.gui.client.taxonomy;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.TextCallback;

import uzuzjmd.competence.gui.client.context.LmsContextFactory;
import uzuzjmd.competence.gui.client.shared.widgets.taxonomy.CompetenceSelectionWidget;
import uzuzjmd.competence.gui.client.viewcontroller.Controller;

import com.github.gwtbootstrap.client.ui.Alert;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class CompetenceDeleteWidget extends Composite {

	private static CompetenceDeleteWidgetUiBinder uiBinder = GWT
			.create(CompetenceDeleteWidgetUiBinder.class);
	@UiField
	SimplePanel competenceSelectionHolder;
	private CompetenceSelectionWidget deleteCompetencesSelectionWidget;

	@UiField
	Alert successAlert;

	@UiField
	Alert errorAlert;
	@UiField
	HTMLPanel deleteWidgetContainer;

	interface CompetenceDeleteWidgetUiBinder extends
			UiBinder<Widget, CompetenceDeleteWidget> {
	}

	public CompetenceDeleteWidget(LmsContextFactory lmsContextFactory) {
		initWidget(uiBinder.createAndBindUi(this));
		deleteCompetencesSelectionWidget = new CompetenceSelectionWidget(
				lmsContextFactory, "all", null,
				"Wählen sie die Kompetenzen aus, die sie löschen wollen!",
				false);
		competenceSelectionHolder.add(deleteCompetencesSelectionWidget);

	}

	@UiHandler("submitButton")
	void onSubmitButtonClick(ClickEvent event) {
		successAlert.setVisible(false);
		errorAlert.setVisible(false);

		String createLink = Controller.contextFactory.getServerURL()
				+ "/competences/json/competence/delete";
		Resource resource = new Resource(createLink);
		resource.addQueryParams("competences",
				deleteCompetencesSelectionWidget.getSelectedCompetences())
				.post().send(new TextCallback() {

					@Override
					public void onSuccess(Method method, String response) {
						Controller.reloadController.reload();
						successAlert.setVisible(true);
					}

					@Override
					public void onFailure(Method method, Throwable exception) {
						errorAlert.setVisible(true);
					}
				});
	}

	public void reload() {
		deleteCompetencesSelectionWidget.reload();
	}

	@UiHandler("submitTreeButton")
	void onSubmitTreeButtonClick(ClickEvent event) {

		successAlert.setVisible(false);
		errorAlert.setVisible(false);

		String createLink = Controller.contextFactory.getServerURL()
				+ "/competences/json/competence/deleteTree";
		Resource resource = new Resource(createLink);
		resource.addQueryParams("competences",
				deleteCompetencesSelectionWidget.getSelectedCompetences())
				.post().send(new TextCallback() {

					@Override
					public void onSuccess(Method method, String response) {
						Controller.reloadController.reload();
						successAlert.setVisible(true);
					}

					@Override
					public void onFailure(Method method, Throwable exception) {
						errorAlert.setVisible(true);
					}
				});

	}
}
