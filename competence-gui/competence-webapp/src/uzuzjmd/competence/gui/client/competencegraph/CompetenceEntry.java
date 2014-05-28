package uzuzjmd.competence.gui.client.competencegraph;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Label;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CompetenceEntry extends Composite {

	private static CompetenceEntryUiBinder uiBinder = GWT
			.create(CompetenceEntryUiBinder.class);
	@UiField
	Label numberLabel;
	@UiField
	Label competenceTextLabel;
	@UiField
	Button deleteButton;
	@UiField
	Button prerequisiteCreationButton;

	interface CompetenceEntryUiBinder extends UiBinder<Widget, CompetenceEntry> {
	}

	public CompetenceEntry() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
