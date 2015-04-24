package uzuzjmd.competence.gui.client.tabs;

import uzuzjmd.competence.gui.client.LmsContextFactory;
import uzuzjmd.competence.gui.client.competenceSelection.CompetenceSelectionWidget;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class CompetenceHierarchieTab extends Composite {

	private static CompetenceHierarchieTabUiBinder uiBinder = GWT
			.create(CompetenceHierarchieTabUiBinder.class);

	interface CompetenceHierarchieTabUiBinder extends
			UiBinder<Widget, CompetenceHierarchieTab> {
	}

	@UiField
	SimplePanel competenceHierarchieHolder;
	@UiField
	Button competenceHierarchieButton;

	private CompetenceSelectionWidget competenceSelectionWidget;

	public CompetenceHierarchieTab(LmsContextFactory lmsContextFactory) {
		initWidget(uiBinder.createAndBindUi(this));
		competenceSelectionWidget = new CompetenceSelectionWidget(
				lmsContextFactory, "all", null, "Aktuelle Kompetenztaxonomie",
				false, true);
	}

	@UiHandler("competenceHierarchieButton")
	void onButtonClick(ClickEvent event) {
	}
}
