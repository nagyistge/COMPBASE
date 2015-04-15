package uzuzjmd.competence.gui.client.login;

import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginView extends Composite {

	private static LoginViewUiBinder uiBinder = GWT
			.create(LoginViewUiBinder.class);
	@UiField
	TextBox loginTextBox;
	@UiField
	Button button;
	@UiField
	VerticalPanel errorHolder;
	@UiField
	Alert numberError;
	@UiField
	Alert emptyError;
	private PopupPanel parent;

	interface LoginViewUiBinder extends UiBinder<Widget, LoginView> {
	}

	public LoginView(PopupPanel parent) {
		initWidget(uiBinder.createAndBindUi(this));
		this.parent = parent;
	}

	public LoginView(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		RegExp regExp = RegExp.compile("^[1-10]");
		MatchResult matcher = regExp.exec(loginTextBox.getValue());
		boolean startsWithNumnber = matcher != null;
		if (startsWithNumnber) {
			numberError.setVisible(true);
		} else {
			if (loginTextBox.getValue().trim().equals("")) {
				emptyError.setVisible(true);
			} else {
				setUsername(loginTextBox.getValue());
				parent.hide();
			}
		}
	}

	public native void setUsername(String username)/*-{
		$wnd.user = username;
	}-*/;

}
