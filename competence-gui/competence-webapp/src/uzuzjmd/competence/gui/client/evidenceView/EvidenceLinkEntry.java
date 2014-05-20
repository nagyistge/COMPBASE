package uzuzjmd.competence.gui.client.evidenceView;

import java.util.List;

import uzuzjmd.competence.gui.shared.dto.CommentEntry;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Label;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
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

	interface EvidenceLinkEntryUiBinder extends
			UiBinder<Widget, EvidenceLinkEntry> {
	}

	public EvidenceLinkEntry(String abstractLinkId, String evidenceTitel,
			String evidenceUrl, List<CommentEntry> list, Boolean validated) {
		initWidget(uiBinder.createAndBindUi(this));
		this.activityTyp.setText(evidenceTitel);
		this.evidenceHref.add(new Anchor(evidenceUrl));
		for (CommentEntry commentEntry : list) {
			commentsVerticalPanel.add(new Label(commentEntry.getUserName()
					+ ":" + commentEntry.getCommentName()));
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
}
