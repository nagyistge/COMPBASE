package uzuzjmd.competence.gui.client.evidenceView;

import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.Controller;
import uzuzjmd.competence.gui.client.tabs.CompetenceTab;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

public class CommentWidget extends CompetenceTab {

	private static CommentWidgetUiBinder uiBinder = GWT
			.create(CommentWidgetUiBinder.class);
	@UiField
	TextArea commentTextArea;

	@UiField
	Button cancelButton;
	@UiField
	Button submitButton;
	private StackPanelReloader stackPanelReloader;
	private String linkId;
	private String userName;

	interface CommentWidgetUiBinder extends UiBinder<Widget, CommentWidget> {
	}

	public CommentWidget(final StackPanelReloader stackPanelReloader,
			String linkId, String userName) {
		initWidget(uiBinder.createAndBindUi(this));
		this.stackPanelReloader = stackPanelReloader;

		this.linkId = linkId;
		this.userName = userName;
	}

	@UiHandler("cancelButton")
	void onCancelButtonClick(ClickEvent event) {
		stackPanelReloader.reload();
	}

	@UiHandler("submitButton")
	void onSubmitButtonClick(ClickEvent event) {
		Resource resource = new Resource(
				Controller.contextFactory.getServerURL()
						+ "/competences/json/link/comment/" + linkId + "/"
						+ Controller.contextFactory.getUser() + "/"
						+ Controller.contextFactory.getOrganization() + "/"
						+ Controller.contextFactory.getRole());
		try {
			resource.addQueryParam("text", commentTextArea.getText()).post()
					.send(new RequestCallback() {

						@Override
						public void onResponseReceived(Request request,
								Response response) {
							stackPanelReloader.commentEntryIdLastUpdated = linkId;
							stackPanelReloader.setCommentEntryWasSuccess(true);
							stackPanelReloader.reload();
						}

						@Override
						public void onError(Request request, Throwable exception) {
							stackPanelReloader.reload();
						}

					});
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}