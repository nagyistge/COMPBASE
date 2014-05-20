package uzuzjmd.competence.gui.client.evidenceView;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class EvidenceStackPanel extends Composite {

	private static EvidenceStackPanelUiBinder uiBinder = GWT
			.create(EvidenceStackPanelUiBinder.class);
	@UiField
	CaptionPanel captionPanel;
	@UiField
	DecoratedStackPanel evidenceStackPanel;
	@UiField
	SimplePanel hrPanelContainer;
	@UiField
	Button closeButton;
	private PopupPanel container;
	private String userName;

	interface EvidenceStackPanelUiBinder extends
			UiBinder<Widget, EvidenceStackPanel> {
	}

	public EvidenceStackPanel(PopupPanel container, String userName) {
		this.userName = userName;
		initWidget(uiBinder.createAndBindUi(this));
		// evidenceStackPanel.add(new Label("test"), userName);
		captionPanel.setCaptionHTML("Kompetenzübersicht für " + this.userName);
		this.container = container;
	}

	@UiHandler("closeButton")
	void onCloseButtonClick(ClickEvent event) {
		container.hide();
	}
}
