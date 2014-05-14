package uzuzjmd.competence.gui.client.evidenceView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EvidenceLinkWidget extends Composite {

	private static EvidenceLinkWidgetUiBinder uiBinder = GWT
			.create(EvidenceLinkWidgetUiBinder.class);
	@UiField
	HorizontalPanel titelPanel;

	interface EvidenceLinkWidgetUiBinder extends
			UiBinder<Widget, EvidenceLinkWidget> {
	}

	public EvidenceLinkWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
