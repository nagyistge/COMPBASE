package uzuzjmd.competence.gui.client.tabs;

import uzuzjmd.competence.gui.client.ContextFactory;
import uzuzjmd.competence.gui.client.competenceSelection.CompetenceSelectionWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HorizontalPanel;

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
	
	public RequirementTab(final ContextFactory contextFactory) {
		initWidget(uiBinder.createAndBindUi(this));
		this.tabExplainationPanel
				.add(new Label(
						"Wählen Sie mit SHIFT-Click die Kompetenzen aus, die für diesen Kurs erfüllt sein müssen! Setzen sie ein Häckchen, wenn diese als verpflichtend für den Scheinerwerb gelten! Beschrieben Sie die Anforderungen, die Sie für den Kompetenzerwerb stellen und klicken Sie auf abschicken!"));
		competenceSelectionWidget = new CompetenceSelectionWidget(contextFactory);
		competenceSelectionPanelPlaceholder.add(competenceSelectionWidget);
//		this.competenceSelectionAndRequirementPanel.setSize("auto", "auto");		

	}

	public RequirementTab(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("submitButton")
	void onSubmitButtonClick(ClickEvent event) {
	}

	public void setText(String text) {

	}
}
