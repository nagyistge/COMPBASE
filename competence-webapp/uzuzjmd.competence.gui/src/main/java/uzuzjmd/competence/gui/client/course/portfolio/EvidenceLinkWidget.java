package uzuzjmd.competence.gui.client.course.portfolio;

import java.util.List;

import uzuzjmd.competence.gui.client.viewcontroller.Controller;
import uzuzjmd.competence.service.rest.client.CompetenceLinksView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EvidenceLinkWidget extends Composite {

	private static EvidenceLinkWidgetUiBinder uiBinder = GWT
			.create(EvidenceLinkWidgetUiBinder.class);
	@UiField
	HorizontalPanel titelPanel;
	@UiField
	VerticalPanel verticalPanel;
	@UiField
	SimplePanel validityPanelHeader;
	@UiField
	SimplePanel deletePanelHeader;

	interface EvidenceLinkWidgetUiBinder extends
			UiBinder<Widget, EvidenceLinkWidget> {
	}

	public EvidenceLinkWidget(List<CompetenceLinksView> list,
			StackPanelReloader stackPanelReloader, String userName) {
		initWidget(uiBinder.createAndBindUi(this));
		initCompetenceLinksView(list, stackPanelReloader, userName);
		if (Controller.contextFactory.getRole().equals("student")) {
			// validityPanelHeader.setVisible(false);
			// deletePanelHeader.setVisible(false);
			// deletePanelHeader.setTitle("");
			// deletePanelHeader.clear();
		}
	}

	private void initCompetenceLinksView(List<CompetenceLinksView> list,
			StackPanelReloader stackPanelReloader, String username) {
		for (CompetenceLinksView competenceLinksView : list) {
			this.verticalPanel.add(new EvidenceLinkEntry(competenceLinksView
					.getAbstractLinkId(), competenceLinksView
					.getEvidenceTitel(), competenceLinksView.getEvidenceUrl(),
					competenceLinksView.getComments(), competenceLinksView
							.getValidated(), stackPanelReloader, username));
		}
	}

}
