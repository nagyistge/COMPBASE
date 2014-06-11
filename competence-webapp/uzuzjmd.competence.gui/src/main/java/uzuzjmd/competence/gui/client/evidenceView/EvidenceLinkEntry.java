package uzuzjmd.competence.gui.client.evidenceView;

import java.util.List;

import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.Competence_webapp;
import uzuzjmd.competence.gui.client.tabs.CompetenceTab;
import uzuzjmd.competence.gui.shared.dto.CommentEntry;

import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.base.IconAnchor;
import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EvidenceLinkEntry extends CompetenceTab {

	private static EvidenceLinkEntryUiBinder uiBinder = GWT
			.create(EvidenceLinkEntryUiBinder.class);

	@UiField
	SimplePanel evidenceHref;
	@UiField
	VerticalPanel commentsVerticalPanel;
	@UiField
	Button createCommentButton;
	@UiField
	Button validateButton;
	@UiField
	Button deleteButton;
	@UiField
	ScrollPanel commentWidgetPlaceholder;
	@UiField
	FocusPanel errorPanelPlaceHolder;
	@UiField
	FocusPanel successPanelPlaceholder;
	@UiField
	SimplePanel hrPanelContainer;

	@UiField
	SimplePanel textPlaceholder;
	private String previewAnchor;
	private String evidenceUrl;
	private StackPanelReloader stackPanelReloader;
	private String abstractLinkId;
	private String userName;
	private Boolean validated;

	interface EvidenceLinkEntryUiBinder extends
			UiBinder<Widget, EvidenceLinkEntry> {
	}

	public EvidenceLinkEntry(String abstractLinkId, String evidenceTitel,
			String evidenceUrl, List<CommentEntry> list, Boolean validated,
			StackPanelReloader stackPanelReloader, String userName) {
		initWidget(uiBinder.createAndBindUi(this));
		initContent(evidenceTitel, evidenceUrl, list, validated);
		this.stackPanelReloader = stackPanelReloader;
		initCommentWidget(abstractLinkId, userName);
		initSubmitCommentMessages(stackPanelReloader.commentEntryWasSuccess,
				abstractLinkId);
		this.abstractLinkId = abstractLinkId;
		this.userName = userName;
		this.validated = validated;
	}

	private void initCommentWidget(String abstractLinkId, String userName) {
		CommentWidget commentWidget = new CommentWidget(stackPanelReloader,
				abstractLinkId, userName);
		this.commentWidgetPlaceholder.add(commentWidget);
		commentWidgetPlaceholder.setVisible(false);
	}

	private void initSubmitCommentMessages(Boolean commentEntryWasSuccess,
			String abstractLinkId) {
		successPanelPlaceholder.add(new Alert(
				"Der Kommentar wurde erfolgreich eingetragen!",
				AlertType.SUCCESS));
		errorPanelPlaceHolder.add(new Alert(
				"Es gab ein Problem, kontaktieren Sie einen Entwickler",
				AlertType.ERROR));

		if (commentEntryWasSuccess != null
				&& stackPanelReloader.commentEntryIdLastUpdated == abstractLinkId) {
			successPanelPlaceholder.setVisible(commentEntryWasSuccess);
			errorPanelPlaceHolder.setVisible(!commentEntryWasSuccess);
		}
	}

	private void initContent(String evidenceTitel, final String evidenceUrl,
			List<CommentEntry> list, Boolean validated) {
		this.textPlaceholder.add(new HTML(evidenceTitel));
		this.evidenceUrl = evidenceUrl;

		IconAnchor previewLabel = new IconAnchor();
		previewLabel.setText("Als Tab öffnen");
		previewLabel.setTarget(evidenceUrl);
		previewLabel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Window.open(evidenceUrl, "_blank", "");
			}
		});

		initHrLines(hrPanelContainer);

		previewAnchor = ".activity-preview";
		evidenceHref.add(previewLabel);

		for (CommentEntry commentEntry : list) {
			commentsVerticalPanel.add(new HTML("<b>"
					+ commentEntry.getUserName() + "</b>: "
					+ commentEntry.getCommentName()));
		}
		setButtonImage(validated);
		if (!Competence_webapp.contextFactory.getRole().equals("teacher")) {
			deleteButton.setVisible(false);
			validateButton.setEnabled(false);
		}
	}

	private void setButtonImage(Boolean validated) {
		if (validated) {
			validateButton.add(new HTML("<i class=\"icon-thumbs-up\"></i>"));
		} else {
			validateButton.add(new HTML("<i class=\"icon-thumbs-down\"></i>"));
		}
	}

	@UiHandler("validateButton")
	void onValidateButtonClick(ClickEvent event) {

		String shouldValidate = null;
		if (!validated) {
			shouldValidate = "validate";
		} else {
			shouldValidate = "invalidate";
		}
		Resource resource = new Resource(
				Competence_webapp.contextFactory.getServerURL()
						+ "/competences/json/link/" + shouldValidate + "/"
						+ abstractLinkId);
		try {
			resource.post().send(new RequestCallback() {

				@Override
				public void onResponseReceived(Request request,
						Response response) {
					stackPanelReloader.reload();
				}

				@Override
				public void onError(Request request, Throwable exception) {

				}
			});
		} catch (RequestException e) {

		}
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		Competence_webapp.showMoodlePreview(evidenceUrl, previewAnchor);
	}

	@UiHandler("createCommentButton")
	void onCreateCommentButtonClick(ClickEvent event) {
		this.commentWidgetPlaceholder.setVisible(true);
	}

	@UiHandler("errorPanelPlaceHolder")
	void onErrorPanelPlaceHolderClick(ClickEvent event) {
		errorPanelPlaceHolder.setVisible(false);
		stackPanelReloader.setCommentEntryWasSuccess(null);
	}

	@UiHandler("errorPanelPlaceHolder")
	void onErrorPanelPlaceHolderMouseOut(MouseOutEvent event) {
		errorPanelPlaceHolder.setVisible(false);
		stackPanelReloader.setCommentEntryWasSuccess(null);
	}

	@UiHandler("successPanelPlaceholder")
	void onSuccessPanelPlaceholderMouseOut(MouseOutEvent event) {
		successPanelPlaceholder.setVisible(false);
		stackPanelReloader.setCommentEntryWasSuccess(null);
	}

	@UiHandler("successPanelPlaceholder")
	void onSuccessPanelPlaceholderClick(ClickEvent event) {
		successPanelPlaceholder.setVisible(false);
		stackPanelReloader.setCommentEntryWasSuccess(null);
	}

	@UiHandler("deleteButton")
	void onDeleteButtonClick(ClickEvent event) {
		Resource resource = new Resource(
				Competence_webapp.contextFactory.getServerURL()
						+ "/competences/json/link/delete/" + abstractLinkId);
		try {
			resource.post().send(new RequestCallback() {

				@Override
				public void onResponseReceived(Request request,
						Response response) {
					stackPanelReloader.reload();
				}

				@Override
				public void onError(Request request, Throwable exception) {

				}
			});
		} catch (RequestException e) {

		}
	}
}
