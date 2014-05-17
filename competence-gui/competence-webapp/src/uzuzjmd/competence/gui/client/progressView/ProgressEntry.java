package uzuzjmd.competence.gui.client.progressView;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Label;
import com.github.gwtbootstrap.client.ui.ProgressBar;
import com.github.gwtbootstrap.client.ui.base.ProgressBarBase.Color;
import com.github.gwtbootstrap.client.ui.base.ProgressBarBase.Style;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
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

	interface ProgressEntryUiBinder extends UiBinder<Widget, ProgressEntry> {
	}

	public ProgressEntry(String userName, Double progress) {
		initWidget(uiBinder.createAndBindUi(this));
		this.userNameLabel.setText(userName);
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
}
