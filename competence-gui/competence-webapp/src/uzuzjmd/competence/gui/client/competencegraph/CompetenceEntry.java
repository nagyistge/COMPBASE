package uzuzjmd.competence.gui.client.competencegraph;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class CompetenceEntry extends Composite {

	private static CompetenceEntryUiBinder uiBinder = GWT
			.create(CompetenceEntryUiBinder.class);
	@UiField
	Button deleteButton;
	@UiField
	Button prerequisiteCreationButton;
	@UiField
	HorizontalPanel competenceEntryHorizontalPanel;
	@UiField
	SimplePanel competenceNumber;
	@UiField
	SimplePanel competenceText;

	interface CompetenceEntryUiBinder extends UiBinder<Widget, CompetenceEntry> {
	}

	public CompetenceEntry(String id, String text) {
		initWidget(uiBinder.createAndBindUi(this));
		this.competenceEntryHorizontalPanel.setStyleName(id, true);
		this.competenceNumber.add(new HTML(id));
		this.competenceText.add(new HTML(text));
	}

}
