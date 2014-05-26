package uzuzjmd.competence.gui.client.evidenceView;

import java.util.List;

import uzuzjmd.competence.gui.client.Competence_webapp;
import uzuzjmd.competence.gui.shared.dto.CommentEntry;

import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Label;
import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.github.gwtbootstrap.client.ui.constants.LabelType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EvidenceLinkEntry extends Composite {

	private static EvidenceLinkEntryUiBinder uiBinder = GWT
			.create(EvidenceLinkEntryUiBinder.class);
	@UiField
	Label activityTyp;
	@UiField
	SimplePanel evidenceHref;
	@UiField
	VerticalPanel commentsVerticalPanel;
	@UiField
	Button createCommentButton;
	@UiField
	ToggleButton validateButton;
	@UiField
	Button deleteButton;
	@UiField
	ScrollPanel commentWidgetPlaceholder;
	@UiField
	FocusPanel errorPanelPlaceHolder;
	@UiField
	FocusPanel successPanelPlaceholder;
	private String previewAnchor;
	private String evidenceUrl;
	private StackPanelReloader stackPanelReloader;

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
				"Der Kommentar wurde erfolgreich eingetragen",
				AlertType.SUCCESS));
		errorPanelPlaceHolder.add(new Alert(
				"Es gab ein Problem, kontaktien Sie einen Entwickler",
				AlertType.ERROR));

		if (commentEntryWasSuccess != null
				&& stackPanelReloader.commentEntryIdLastUpdated == abstractLinkId) {
			successPanelPlaceholder.setVisible(commentEntryWasSuccess);
			errorPanelPlaceHolder.setVisible(!commentEntryWasSuccess);
		}
	}

	private void initContent(String evidenceTitel, String evidenceUrl,
			List<CommentEntry> list, Boolean validated) {
		this.activityTyp.setText(evidenceTitel);
		this.evidenceUrl = evidenceUrl;

		Label previewLabel = new Label("Preview");
		previewLabel.setType(LabelType.IMPORTANT);
		previewAnchor = ".activity-preview";
		evidenceHref.add(previewLabel);

		for (CommentEntry commentEntry : list) {
			commentsVerticalPanel.add(new HTML("<b>"
					+ commentEntry.getUserName() + "</b>: "
					+ commentEntry.getCommentName()));
		}
		validateButton.setValue(validated);

		if (validated) {
			validateButton.setHTML("<i class=\"icon-thumbs-up\"></i>");
		} else {
			validateButton.setHTML("<i class=\"icon-thumbs-down\"></i>");
		}
	}

	@UiHandler("validateButton")
	void onValidateButtonClick(ClickEvent event) {
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
}
