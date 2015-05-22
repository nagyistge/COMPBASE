package uzuzjmd.competence.gui.client.course.portfolio;

import org.fusesource.restygwt.client.JsonEncoderDecoder;

import uzuzjmd.competence.gui.client.context.LmsContextFactory;
import uzuzjmd.competence.gui.client.viewcontroller.Controller;
import uzuzjmd.competence.service.rest.client.CompetenceLinksMap;

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
	private LmsContextFactory context;

	public interface CompetenceLinksMapCodec extends
			JsonEncoderDecoder<CompetenceLinksMap> {
	}

	interface EvidenceStackPanelUiBinder extends
			UiBinder<Widget, EvidenceStackPanel> {
	}

	public EvidenceStackPanel(PopupPanel container, String userName,
			LmsContextFactory context) {
		this.userName = userName;
		this.context = context;
		initWidget(uiBinder.createAndBindUi(this));
		// evidenceStackPanel.add(new Label("test"), userName);
		captionPanel.setCaptionHTML("Kompetenzübersicht für " + this.userName);
		this.container = container;

		StackPanelReloader stackPanelReloader = new StackPanelReloader(
				evidenceStackPanel, userName, context, null);
		stackPanelReloader.reload();
	}

	@UiHandler("closeButton")
	void onCloseButtonClick(ClickEvent event) {
		container.hide();
		Controller.progressTab.showProgressEntries(Controller.contextFactory,
				true);
	}
}
