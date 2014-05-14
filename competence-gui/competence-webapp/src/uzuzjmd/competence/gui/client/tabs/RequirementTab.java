package uzuzjmd.competence.gui.client.tabs;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.TextCallback;

import uzuzjmd.competence.gui.client.ContextFactory;
import uzuzjmd.competence.gui.client.competenceSelection.CompetenceSelectionWidget;

import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.base.HtmlWidget;
import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class RequirementTab extends Composite {

	interface RequirementTabUiBinder extends UiBinder<Widget, RequirementTab> {
	}

	private static RequirementTabUiBinder uiBinder = GWT
			.create(RequirementTabUiBinder.class);

	private CompetenceSelectionWidget competenceSelectionWidget;

	@UiField
	SimplePanel tabExplainationPanel;

	@UiField
	VerticalPanel competenceSelectionAndRequirementPanel;
	@UiField
	HorizontalPanel buttonPanel;
	@UiField
	Button submitButton;
	@UiField
	Button deleteContextButton;
	@UiField
	TextArea requirementTextArea;

	@UiField
	Panel competenceSelectionPanelPlaceholder;

	@UiField
	SimplePanel hrDividerPanel;
	@UiField
	SimplePanel hrDividerPanel2;
	@UiField
	FocusPanel warningPlaceHolder;

	private HtmlWidget alert;

	public RequirementTab(final ContextFactory contextFactory) {
		initWidget(uiBinder.createAndBindUi(this));
		this.tabExplainationPanel
				.add(new Label(
						"Wählen Sie mit STRG-Click die Kompetenzen aus, die für diesen Kurs erfüllt sein müssen! Setzen sie ein Häckchen, wenn diese als verpflichtend für den Scheinerwerb gelten! Beschrieben Sie die Anforderungen, die Sie für den Kompetenzerwerb stellen und klicken Sie auf abschicken!"));
		HTML html = new HTML(
				"<hr class=\"fancy-line\" style=\"width:100%;\" />");
		this.hrDividerPanel2.add(html);
		this.hrDividerPanel.add(html);

		competenceSelectionWidget = new CompetenceSelectionWidget(
				contextFactory, "selected");
		// competenceSelectionWidget = new CompetenceSelectionWidget(
		// contextFactory, null, "coursecontext/");

		competenceSelectionPanelPlaceholder.add(competenceSelectionWidget);
		// this.competenceSelectionAndRequirementPanel.setSize("auto", "auto");

		Resource resource = new Resource(contextFactory.getServerURL()
				+ "/competences/json/coursecontext/requirements/"
				+ contextFactory.getCourseId());
		resource.get().send(new TextCallback() {

			@Override
			public void onSuccess(Method arg0, String arg1) {
				requirementTextArea.setText(arg1);
			}

			@Override
			public void onFailure(Method arg0, Throwable arg1) {
				GWT.log("could not get requirements for course");
			}
		});
	}

	public RequirementTab(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("deleteContextButton")
	void onDeleteContextButtonClick(ClickEvent event) {
		competenceSelectionWidget.handleDeleteClick();
		alert = new Alert("Die Verknüpfungen wurden erfolgreich gelöscht",
				AlertType.SUCCESS);
		warningPlaceHolder.add(alert);
	}

	@UiHandler("submitButton")
	void onSubmitButtonClick(ClickEvent event) {
		competenceSelectionWidget.handleSubmit(requirementTextArea.getText());
		alert = new Alert("Die Verknüpfungen wurden erfolgreich erstellt",
				AlertType.SUCCESS);
		warningPlaceHolder.add(alert);
	}

	public void setText(String text) {

	}

	@UiHandler("warningPlaceHolder")
	void onWarningPlaceHolderClick(ClickEvent event) {
		warningPlaceHolder.remove(alert);
	}

	@UiHandler("warningPlaceHolder")
	void onWarningPlaceHolderMouseOut(MouseOutEvent event) {
		warningPlaceHolder.remove(alert);
	}
}
