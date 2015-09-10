package uzuzjmd.competence.service.rest.client.api;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.TextCallback;

import uzuzjmd.competence.gui.client.context.StandaloneContextFactory;
import uzuzjmd.competence.gui.client.login.LoginView;
import uzuzjmd.competence.gui.client.viewcontroller.ContextSelectionController;
import uzuzjmd.competence.gui.client.viewcontroller.Controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.HasText;

public class GetRequestManager extends RestClientManager {
	public void updateRequirementTextFieldContent(final HasText widget) {
		GWT.log("Initiating requirement textfield");
		Resource resource = new Resource(
				RestUrlFactory.getRequirementTextFieldUrl());
		resource.get().send(new TextCallback() {

			@Override
			public void onSuccess(Method arg0, String arg1) {
				widget.setText(arg1);
			}

			@Override
			public void onFailure(Method arg0, Throwable arg1) {
				GWT.log("could not get requirements for course");
			}
		});
		GWT.log("Initiated requirement textfield");
	}

	public void checkUserExists(String userEmail, String password,
			final LoginView view) {
		String moodleEvidenceUrl = Controller.contextFactory
				.getEvidenceServerURL() + "/lms/user/exists";
		Resource resource = new Resource(moodleEvidenceUrl);
		resource.addQueryParam("user", userEmail)
				.addQueryParam("password", password)
				.addQueryParam("lmsSystem",
						Controller.contextFactory.getLMSsystem()).get()
				.send(new JsonCallback() {

					@Override
					public void onSuccess(Method method, JSONValue response) {
						if (response.isBoolean().booleanValue()) {
							view.logUserIn();
						} else {
							view.getNotExistError().setVisible(true);
						}
					}

					@Override
					public void onFailure(Method method, Throwable exception) {
						view.getOtherError().setText(
								"hello" + exception.getMessage()
										+ method.toString());
						view.getOtherError().setHTML(
								"hello" + exception.getMessage()
										+ method.toString());
						view.getOtherError().setVisible(true);
					}
				});
	}

	public void updateCourses() {

		if (Controller.contextFactory.getIsValidUserLoggedIn()) {
			Resource resource = new Resource(
					Controller.contextFactory.getEvidenceServerURL()
							+ "/lms/courses/moodle/"
							+ Controller.contextFactory.getUser());
			resource.addQueryParam("password",
					Controller.contextFactory.getPassword()).get()
					.send(new JsonCallback() {

						@Override
						public void onSuccess(Method method, JSONValue response) {
							GWT.log("received coursesList for User"
									+ response.toString());
							// CourseListCodec codec =
							// GWT.create(CourseListCodec.class);
							// GWT.log(codec.toString());
							// UserCourseListResponse courseListResponse = codec
							// .decode(response);

							// for (UserCourseListItem userCourseListItem :
							// courseListResponse) {
							// coursesHashmap.put(userCourseListItem.getName(),
							// userCourseListItem.getCourseid());
							// }

							for (int i = 0; i < response.isArray().size(); i++) {
								Long courseId = Math.round(response.isArray()
										.get(i).isObject().get("courseid")
										.isNumber().doubleValue());
								String name = response.isArray().get(i)
										.isObject().get("name").toString()
										.replaceAll("\"", "");
								((StandaloneContextFactory) Controller.contextFactory)
										.getCoursesHashmap()
										.put(name, courseId);
							}

							ContextSelectionController
									.initContextController(((StandaloneContextFactory) Controller.contextFactory)
											.getCoursesHashmap().keySet());
							Controller.reloadController.reload();

							Controller.contextFactory
									.setCourseContext(ContextSelectionController
											.getSelectedCourseContext());

						}

						@Override
						public void onFailure(Method method, Throwable exception) {

						}
					});
		}
	}
}
