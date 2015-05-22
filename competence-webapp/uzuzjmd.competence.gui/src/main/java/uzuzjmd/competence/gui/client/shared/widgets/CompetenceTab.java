package uzuzjmd.competence.gui.client.shared.widgets;

import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;

public class CompetenceTab extends Composite {

	private String alertWidth = "625px";

	protected Alert fillInfoTab(String infoText, SimplePanel panel) {
		Alert info = new Alert(infoText, AlertType.INFO, false);
		info.setWidth(alertWidth);
		info.getElement();
		panel.add(info);
		return info;
	}

	protected Alert createAlert(String infoText, FocusPanel panel,
			AlertType alertType) {
		Alert info = new Alert(infoText, alertType);
		info.setWidth(alertWidth);
		panel.add(info);
		return info;
	}

	protected void initHrLines(SimplePanel... hrPanels) {
		HTML html = new HTML(
				"<hr class=\"fancy-line\" style=\"width:100%;\" />");
		for (SimplePanel simplePanel : hrPanels) {
			simplePanel.add(html);
		}
	}

}
