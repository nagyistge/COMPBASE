package uzuzjmd.competence.gui.client.evidenceView;

import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.Competence_webapp;
import uzuzjmd.competence.gui.client.tabs.CompetenceTab;

import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CommentWidget extends CompetenceTab {

	private static CommentWidgetUiBinder uiBinder = GWT
			.create(CommentWidgetUiBinder.class);
	@UiField
	TextArea commentTextArea;
	@UiField
	SimplePanel hrPanelContainer;
	@UiField
	Button cancelButton;
	@UiField
	Button submitButton;
	private StackPanelReloader stackPanelReloader;
	private String linkId;
	private String userName;
	private PopupPanel popupSuccessPanel;
	private PopupPanel popupErrorPanel;

	interface CommentWidgetUiBinder extends UiBinder<Widget, CommentWidget> {
	}

	public CommentWidget(final StackPanelReloader stackPanelReloader,
			String linkId, String userName) {
		initWidget(uiBinder.createAndBindUi(this));
		this.stackPanelReloader = stackPanelReloader;
		initHrLines(hrPanelContainer);
		this.linkId = linkId;
		this.userName = userName;

		VerticalPanel verticalPanel = new VerticalPanel();
		popupSuccessPanel = new PopupPanel(true);
		popupErrorPanel.center();
		verticalPanel.add(new Alert(
				"Der Kommentar wurde erfolgreich eingetragen!",
				AlertType.SUCCESS));
		Button button = new Button("ok");
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				popupSuccessPanel.hide();
			}
		});
		verticalPanel.add(button);
		popupSuccessPanel.add(verticalPanel);
		popupSuccessPanel.addCloseHandler(new CloseHandler<PopupPanel>() {

			@Override
			public void onClose(CloseEvent<PopupPanel> event) {
				stackPanelReloader.reload();
			}
		});

		popupErrorPanel = new PopupPanel(true);
		popupErrorPanel.center();
		popupErrorPanel
				.add(new Alert(
						"Es gab einen Fehler bei der Datenbank, bitte kontaktieren Sie einen Entwickler!",
						AlertType.ERROR));

	}

	@UiHandler("cancelButton")
	void onCancelButtonClick(ClickEvent event) {
		setVisible(false);
	}

	@UiHandler("submitButton")
	void onSubmitButtonClick(ClickEvent event) {
		Resource resource = new Resource(
				Competence_webapp.contextFactory.getServerURL()
						+ "/competences/json/link/comment/" + linkId + "/"
						+ userName);
		try {
			resource.addQueryParam("text", commentTextArea.getText()).post()
					.send(new RequestCallback() {

						@Override
						public void onResponseReceived(Request request,
								Response response) {
							popupSuccessPanel.show();
						}

						@Override
						public void onError(Request request, Throwable exception) {
							popupErrorPanel.show();
						}

					});
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}