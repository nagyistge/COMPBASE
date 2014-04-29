package uzuzjmd.competence.gui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.VerticalSplitPanel;
import com.google.gwt.user.client.ui.Widget;

public class RequirementTab extends Composite {

	private static RequirementTabUiBinder uiBinder = GWT
			.create(RequirementTabUiBinder.class);

	interface RequirementTabUiBinder extends UiBinder<Widget, RequirementTab> {
	}

	public RequirementTab() {
		initWidget(uiBinder.createAndBindUi(this));
		this.tabExplainationPanel
				.add(new Label(
						"Wählen Sie eine Kompetenz aus. Für diese wird angezeigt, welche Anforderungen erfüllt sein müssen, damit diese als erlangt gilt!"));

	}

	@UiField
	SimplePanel tabExplainationPanel;
	@UiField
	VerticalSplitPanel competenceSelectionAndRequirementPanel;
	@UiField
	VerticalPanel buttonPanel;
	@UiField
	Button submitButton;
	@UiField
	Button deleteContextButton;

	public RequirementTab(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setText(String text) {

	}

	@UiHandler("submitButton")
	void onSubmitButtonClick(ClickEvent event) {
	}
}
