package uzuzjmd.competence.gui.client.taxonomy;

import java.util.Arrays;
import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.TextCallback;

import uzuzjmd.competence.gui.client.context.LmsContextFactory;
import uzuzjmd.competence.gui.client.shared.widgets.taxonomy.CompetenceSelectionWidget;
import uzuzjmd.competence.gui.client.viewcontroller.Controller;

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

public class CompetenceEditTab extends Composite {

	private static CompetenceEditTabUiBinder uiBinder = GWT
			.create(CompetenceEditTabUiBinder.class);

	interface CompetenceEditTabUiBinder extends
			UiBinder<Widget, CompetenceEditTab> {
	}

	@UiField
	SimplePanel editAbleCompetencesHolder;

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

	String competenceToEdit = null;

	private CompetenceSelectionWidget editAbleCompetences;

	interface CompetenceCreationTabUiBinder extends
			UiBinder<Widget, CompetenceEditTab> {
	}

	public CompetenceEditTab(LmsContextFactory lmsContextFactory) {
		initWidget(uiBinder.createAndBindUi(this));
		editAbleCompetences = new CompetenceSelectionWidget("all", null,
				"Aktuelle Kompetenzen!", false, false, true);

		editAbleCompetencesHolder.add(editAbleCompetences);
	}

	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		String competence = competenceDescriptionTextArea.getValue();
		String operator = operatorTextArea.getValue();
		String catchwords = keywordsTextArea.getValue();
		emptyError3.setVisible(false);
		emptyError2.setVisible(false);
		successAlert.setVisible(false);
		emptyError.setVisible(false);
		logicError.setVisible(false);
		if (catchwords.trim().equals("")) {
			emptyError3.setVisible(true);
		} else if (competence.trim().equals("") || competenceToEdit == null) {
			emptyError.setVisible(true);
		} else if (operator.trim().equals("")) {
			emptyError2.setVisible(true);
		} else {
			Resource resource = new Resource(
					Controller.contextFactory.getServerURL()
							+ "/competences/json/editOne");
			List<String> catchwordsDTO = Arrays.asList(catchwords.split(","));

			resource.addQueryParam("competence", competence)
					.addQueryParam("originalCompetence", competenceToEdit)

					.addQueryParam("operator", operator)
					.addQueryParams("catchwords", catchwordsDTO).post()
					.send(new TextCallback() {
						@Override
						public void onSuccess(Method arg0, String arg1) {
							if (arg1.startsWith("ok")) {
								successAlert.setVisible(true);
							} else {
								logicError.setText(arg1);
								logicError.setVisible(true);
							}
							Controller.reloadController.reload();
						}

						@Override
						public void onFailure(Method arg0, Throwable arg1) {
							GWT.log("could not add Competence");

						}
					});
		}
	}

	public void reload() {
		editAbleCompetences.reload();
	}

	public void setCompetenceDescriptionTextArea(String text) {
		this.competenceDescriptionTextArea.setValue(text);
	}

	public void setKeywordsTextArea(String text) {
		this.keywordsTextArea.setValue(text);
	}

	public void setOperatorTextArea(String text) {

		this.operatorTextArea.setValue(text);
	}

	public void setCompetenceToEdit(String competenceToEdit) {
		this.competenceToEdit = competenceToEdit;
	}

}
