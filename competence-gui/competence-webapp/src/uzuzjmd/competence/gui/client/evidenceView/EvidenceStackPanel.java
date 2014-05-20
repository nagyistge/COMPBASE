package uzuzjmd.competence.gui.client.evidenceView;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.ContextFactory;
import uzuzjmd.competence.gui.shared.dto.CompetenceLinksMap;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
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

	public interface CompetenceLinksMapCodec extends
			JsonEncoderDecoder<CompetenceLinksMap> {
	}

	interface EvidenceStackPanelUiBinder extends
			UiBinder<Widget, EvidenceStackPanel> {
	}

	public EvidenceStackPanel(PopupPanel container, String userName,
			ContextFactory context) {
		this.userName = userName;
		initWidget(uiBinder.createAndBindUi(this));
		// evidenceStackPanel.add(new Label("test"), userName);
		captionPanel.setCaptionHTML("Kompetenzübersicht für " + this.userName);
		this.container = container;

		Resource resource = new Resource(context.getServerURL()
				+ "/competences/json/link/overview/" + userName);
		resource.get().send(new JsonCallback() {

			@Override
			public void onSuccess(Method arg0, JSONValue arg1) {
				CompetenceLinksMapCodec codec = GWT
						.create(CompetenceLinksMapCodec.class);
				CompetenceLinksMap competenceLinksMap = codec.decode(arg1);
				for (String competenceName : competenceLinksMap
						.getMapUserCompetenceLinks().keySet()) {
					evidenceStackPanel.add(
							new EvidenceLinkWidget(competenceLinksMap
									.getMapUserCompetenceLinks().get(
											competenceName)), competenceName);
				}

			}

			@Override
			public void onFailure(Method arg0, Throwable arg1) {
				// TODO Auto-generated method stub

				Window.alert("wuff:-(1");

			}
		});
	}

	@UiHandler("closeButton")
	void onCloseButtonClick(ClickEvent event) {
		container.hide();
	}
}
