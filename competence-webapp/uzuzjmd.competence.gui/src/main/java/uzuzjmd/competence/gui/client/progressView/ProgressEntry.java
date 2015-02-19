package uzuzjmd.competence.gui.client.progressView;

import uzuzjmd.competence.gui.client.LmsContextFactory;
import uzuzjmd.competence.gui.client.evidenceView.EvidenceStackPanel;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Label;
import com.github.gwtbootstrap.client.ui.ProgressBar;
import com.github.gwtbootstrap.client.ui.base.ProgressBarBase.Color;
import com.github.gwtbootstrap.client.ui.base.ProgressBarBase.Style;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class ProgressEntry extends Composite {

	private static ProgressEntryUiBinder uiBinder = GWT
			.create(ProgressEntryUiBinder.class);
	@UiField
	SimplePanel progressBarPlaceholder;
	@UiField
	Button showEvidencesButton;
	@UiField
	Label userNameLabel;
	private ProgressBar progressBar;
	private String userName;
	private PopupPanel popup;
	private LmsContextFactory contextFactory;

	interface ProgressEntryUiBinder extends UiBinder<Widget, ProgressEntry> {
	}

	public ProgressEntry(String userName, int progress,
			LmsContextFactory contextFactory) {
		initWidget(uiBinder.createAndBindUi(this));
		this.contextFactory = contextFactory;
		this.userName = userName;
		this.userNameLabel.setText(userName);
		initProgressBar(progress);
		initPopupPanel(this.userName);
	}

	private void initPopupPanel(String userName) {
		popup = new PopupPanel();
		EvidenceStackPanel evidenceStackPanel = new EvidenceStackPanel(popup,
				userName, contextFactory);
		popup.add(evidenceStackPanel);
		popup.setAnimationEnabled(true);
		popup.setGlassEnabled(true);
		// popup.center();
		popup.setVisible(false);
		// popup.setWidth("725px");
		// popup.setHeight("400px");
		// popup.setStyleName("evidencePopup");
	}

	private void initProgressBar(int progress) {
		this.progressBar = new ProgressBar(Style.ANIMATED);
		this.progressBar.setPercent(progress);
		if (progress < 25) {
			progressBar.setColor(Color.DANGER);
		} else if (progress < 50) {
			progressBar.setColor(Color.WARNING);
		} else if (progress < 75) {
			progressBar.setColor(Color.DEFAULT);
		} else {
			progressBar.setColor(Color.SUCCESS);
		}
		progressBar.setVisible(true);
		this.progressBarPlaceholder.add(progressBar);
	}

	@UiHandler("showEvidencesButton")
	void onShowEvidencesButtonClick(ClickEvent event) {
		popup.setVisible(true);
		popup.show();
	}
}
