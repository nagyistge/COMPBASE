package uzuzjmd.competence.gui.client.tabs;

import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;

public class CompetenceTab extends Composite {

	protected Alert fillInfoTab(String infoText, SimplePanel panel) {
		Alert info = new Alert(infoText, AlertType.INFO, false);
		info.setWidth("625px");
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
