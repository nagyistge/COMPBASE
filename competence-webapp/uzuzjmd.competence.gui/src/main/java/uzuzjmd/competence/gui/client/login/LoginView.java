package uzuzjmd.competence.gui.client.login;

import uzuzjmd.competence.gui.client.viewcontroller.Controller;
import uzuzjmd.competence.service.rest.client.api.GetRequestManager;

import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.URL;
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
	TextBox passwordTextBox;
	@UiField
	Button button;

	@UiField
	Button debug;

	@UiField
	VerticalPanel errorHolder;
	@UiField
	Alert numberError;
	@UiField
	Alert emptyError;

	@UiField
	Alert notExistError;

	@UiField
	Alert otherError;

	private PopupPanel parent;

	interface LoginViewUiBinder extends
			UiBinder<Widget, LoginView> {
	}

	public LoginView(PopupPanel parent, boolean isDebug) {
		initWidget(uiBinder.createAndBindUi(this));
		this.parent = parent;
		debug.setVisible(isDebug);
		debug.setEnabled(isDebug);
		if (isDebug) {
			loginTextBox.setValue("test2@stuff.com");
			passwordTextBox.setValue("voyager1;A");
			button.click();
		}
	}

	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		RegExp regExp = RegExp.compile("^[1-10]");
		MatchResult matcher = regExp.exec(loginTextBox
				.getValue());
		boolean startsWithNumnber = matcher != null;
		if (startsWithNumnber) {
			numberError.setVisible(true);
		} else {
			if (loginTextBox.getValue().trim().equals("")) {
				emptyError.setVisible(true);
			} else {
				GetRequestManager manager = new GetRequestManager();
				manager.checkUserExists(
						loginTextBox.getValue(),
						passwordTextBox.getValue(), this);
			}
		}
	}

	@UiHandler("debug")
	void onButtonClick2(ClickEvent event) {
		parent.hide();
	}

	public void logUserIn() {
		setUsername(loginTextBox.getValue());
		Controller.contextFactory
				.setIsValidUserLoggedIn(true);
		String username = URL
				.encode(loginTextBox.getText());
		Controller.contextFactory.setUser(username);
		String password = URL.encode(passwordTextBox
				.getText());
		Controller.contextFactory.setPassword(password);

		// Window.alert("username is" + username);

		GetRequestManager manager = new GetRequestManager();
		manager.updateCourses();
		parent.hide();
	}

	public Alert getNotExistError() {
		return notExistError;
	}

	public Alert getOtherError() {
		return otherError;
	}

	public native void setUsername(String username)/*-{
		$wnd.user = username;
	}-*/;

}
