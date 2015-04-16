package uzuzjmd.competence.gui.client.tabs;

import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.TextCallback;

import uzuzjmd.competence.gui.client.Controller;
import uzuzjmd.competence.gui.client.LmsContextFactory;
import uzuzjmd.competence.gui.client.competenceSelection.CompetenceSelectionWidget;

import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class CompetenceCreationTab extends Composite {

	private static CompetenceCreationTabUiBinder uiBinder = GWT
			.create(CompetenceCreationTabUiBinder.class);

	@UiField
	SimplePanel superCompetenceSelectionHolder;
	@UiField
	SimplePanel subCompetencSelectionHolder;
	@UiField
	Button button;
	@UiField
	TextArea competenceDescriptionTextArea;
	@UiField
	TextArea keywordsTextArea;
	@UiField
	TextArea operatorTextArea;
	@UiField
	Alert successAlert;
	@UiField
	Alert emptyError;
	@UiField
	Alert emptyError2;
	@UiField
	Alert emptyError3;
	@UiField
	Alert logicError;

	private CompetenceSelectionWidget superCompetencesSelectionWidget;
	private CompetenceSelectionWidget subCompetencesSelectionWidget;

	interface CompetenceCreationTabUiBinder extends
			UiBinder<Widget, CompetenceCreationTab> {
	}

	public CompetenceCreationTab(LmsContextFactory lmsContextFactory) {
		initWidget(uiBinder.createAndBindUi(this));
		superCompetencesSelectionWidget = new CompetenceSelectionWidget(
				lmsContextFactory, "all", null,
				"Wählen sie Oberkompetenzen für die von Ihnen erstellte Kompetenz aus!");
		subCompetencesSelectionWidget = new CompetenceSelectionWidget(
				lmsContextFactory, "all", null,
				"Wählen sie Unterkompetenzen für die von Ihnen erstellte Kompetenz aus!");
		superCompetenceSelectionHolder.add(superCompetencesSelectionWidget);
		subCompetencSelectionHolder.add(subCompetencesSelectionWidget);
	}

	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		// TODO call service

		List<String> superCompetences = superCompetencesSelectionWidget
				.getSelectedCompetences();
		List<String> subCompetences = subCompetencesSelectionWidget
				.getSelectedCompetences();
		String competence = competenceDescriptionTextArea.getValue();
		String operator = operatorTextArea.getValue();
		String catchwords = keywordsTextArea.getValue();
		if (catchwords.trim().equals("")) {
			emptyError3.setVisible(true);
			successAlert.setVisible(false);
		} else if (competence.trim().equals("")) {
			emptyError3.setVisible(false);
			emptyError.setVisible(true);
			successAlert.setVisible(false);
		} else if (operator.trim().equals("")) {
			emptyError.setVisible(false);
			emptyError2.setVisible(true);
			successAlert.setVisible(false);
		} else {
			emptyError2.setVisible(false);
			Resource resource = new Resource(
					Controller.contextFactory.getServerURL()
							+ "/competences/json/addOne");
			resource.addQueryParam("competence", competence)
					.addQueryParam("operator", operator)
					.addQueryParam("catchwords", catchwords)
					.addQueryParams("superCompetences", superCompetences)
					.addQueryParams("subCompetences", subCompetences).post()
					.send(new TextCallback() {
						@Override
						public void onSuccess(Method arg0, String arg1) {
							if (arg1.startsWith("ok")) {
								successAlert.setVisible(true);
								logicError.setVisible(false);
							} else {
								logicError.setText(arg1);
								successAlert.setVisible(false);
								logicError.setVisible(true);
							}
						}

						@Override
						public void onFailure(Method arg0, Throwable arg1) {

							GWT.log("could not add Competence");

						}
					});
		}
	}
}
