package uzuzjmd.competence.gui.client;

import uzuzjmd.competence.gui.client.context.LmsContextFactory;
import uzuzjmd.competence.gui.client.viewcontroller.Controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class CompetenceUIContainer extends Composite {

	private static CompetenceUIContainerUiBinder uiBinder = GWT
			.create(CompetenceUIContainerUiBinder.class);
	@UiField
	SimplePanel competenceUIholder;

	interface CompetenceUIContainerUiBinder extends
			UiBinder<Widget, CompetenceUIContainer> {
	}

	public CompetenceUIContainer() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		initTabbedView(competenceUIholder);
	}

	private void initTabbedView(Panel container) {

		Controller.init();

		TabbedView tabbedView = new TabbedView();
		tabbedView.linkTabPlaceholder.add(Controller.linkEvidenceTab);
		tabbedView.progressTabPlaceholder.add(Controller.progressTab);
		tabbedView.competenceDeleteHolder.add(Controller.competenceDeleteTab);
		tabbedView.competenceHierarchieChangeHolder
				.add(Controller.competenceHierarchieTab);
		tabbedView.competenceEditHolder.add(Controller.competenceEditTab);

		if (!Controller.contextFactory.getRole().equals("student")) {
			tabbedView.requirementTabPlaceholder.add(Controller.requirementTab);
			// tabbedView.pathTabPlaceholder.setVisible(false);
		} else {
			tabbedView.tabPanel.remove(0);
			tabbedView.tabPanel.remove(0);
			// tabbedView.tabPanel.remove(1);
			Controller.requirementTab.setVisible(false);
		}
		tabbedView.competenceCreationHolder
				.add(Controller.competenceCreationTab);

		container.add(tabbedView);
		Controller.tabbedView = tabbedView;
	}

	public void setContextFactory(LmsContextFactory contextFactory) {
		Controller.contextFactory = contextFactory;
	}

}
