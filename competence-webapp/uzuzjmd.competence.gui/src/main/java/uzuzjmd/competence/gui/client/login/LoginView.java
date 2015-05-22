package uzuzjmd.competence.gui.client.login;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.context.StandaloneContextFactory;
import uzuzjmd.competence.gui.client.viewcontroller.Controller;

import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.json.client.JSONValue;
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

	interface LoginViewUiBinder extends UiBinder<Widget, LoginView> {
	}

	public LoginView(PopupPanel parent, boolean isDebug) {
		initWidget(uiBinder.createAndBindUi(this));
		this.parent = parent;
		debug.setVisible(isDebug);
		debug.setEnabled(isDebug);
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
				checkUserExists(loginTextBox.getValue());
			}
		}
	}

	@UiHandler("debug")
	void onButtonClick2(ClickEvent event) {
		parent.hide();
	}

	private void logUserIn() {
		setUsername(loginTextBox.getValue());
		Controller.contextFactory.setIsValidUserLoggedIn(true);
		Controller.contextFactory.setUser(loginTextBox.getValue());
		((StandaloneContextFactory) Controller.contextFactory).updateCourses();
		parent.hide();
	}

	private void checkUserExists(String userEmail) {
		String moodleEvidenceUrl = Controller.contextFactory
				.getEvidenceServerURL() + "/lms/user/exists";
		Resource resource = new Resource(moodleEvidenceUrl);
		resource.addQueryParam("user", userEmail).get()
				.send(new JsonCallback() {

					@Override
					public void onSuccess(Method method, JSONValue response) {
						if (response.isBoolean().booleanValue()) {
							logUserIn();
						} else {
							notExistError.setVisible(true);
						}
					}

					@Override
					public void onFailure(Method method, Throwable exception) {
						otherError.setText("hello" + exception.getMessage()
								+ method.toString());
						otherError.setHTML("hello" + exception.getMessage()
								+ method.toString());
						otherError.setVisible(true);
					}
				});
	}

	public native void setUsername(String username)/*-{
		$wnd.user = username;
	}-*/;

}
