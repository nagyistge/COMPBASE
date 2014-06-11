package uzuzjmd.competence.gui.shared;

import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.gwtext.client.widgets.Panel;

public class ErrorWidget extends Panel {

	private String alertWidth;
	private Alert alert;

	public ErrorWidget(String alertWidth, String infoText, AlertType alertType) {
		this.alertWidth = alertWidth;
		this.alert = createAlert(infoText, alertType);
		this.add(alert);
	}

	public ErrorWidget(String infoText, AlertType alertType) {
		this.alertWidth = "625px";
		this.alert = createAlert(infoText, alertType);
		this.add(alert);
	}

	protected Alert createAlert(String infoText, AlertType alertType) {
		Alert info = new Alert(infoText);
		info.setType(alertType);
		info.setWidth(alertWidth);
		return info;
	}

	public Alert getAlert() {
		return alert;
	}
}
