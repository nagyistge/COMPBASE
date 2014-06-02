package uzuzjmd.competence.gui.client.competencegraph;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class CompetenceGraphFilterPanel extends Composite {

	private static CompetenceGraphFilterPanelUiBinder uiBinder = GWT
			.create(CompetenceGraphFilterPanelUiBinder.class);
	@UiField Button cancelButton;

	interface CompetenceGraphFilterPanelUiBinder extends
			UiBinder<Widget, CompetenceGraphFilterPanel> {
	}

	public CompetenceGraphFilterPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("cancelButton")
	void onCancelButtonClick(ClickEvent event) {
	}
	@UiHandler("filterButton")
	void onFilterButtonClick(ClickEvent event) {
	}
}
