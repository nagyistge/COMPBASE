package uzuzjmd.competence.gui.client.evidenceView;

import java.util.List;

import uzuzjmd.competence.gui.shared.dto.CompetenceLinksView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EvidenceLinkWidget extends Composite {

	private static EvidenceLinkWidgetUiBinder uiBinder = GWT
			.create(EvidenceLinkWidgetUiBinder.class);
	@UiField
	HorizontalPanel titelPanel;
	@UiField
	VerticalPanel verticalPanel;

	interface EvidenceLinkWidgetUiBinder extends
			UiBinder<Widget, EvidenceLinkWidget> {
	}

	public EvidenceLinkWidget(List<CompetenceLinksView> list) {
		initWidget(uiBinder.createAndBindUi(this));
		for (CompetenceLinksView competenceLinksView : list) {
			this.verticalPanel.add(new EvidenceLinkEntry(competenceLinksView
					.getAbstractLinkId(), competenceLinksView
					.getEvidenceTitel(), competenceLinksView.getEvidenceUrl(),
					competenceLinksView.getComments(), competenceLinksView
							.getValidated()));
		}

	}

}
