package uzuzjmd.competence.gui.client;

import java.util.HashMap;

import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

import uzuzjmd.competence.gui.client.viewcontroller.ContextSelectionController;
import uzuzjmd.competence.gui.client.viewcontroller.Controller;
import uzuzjmd.competence.service.rest.client.UserCourseListResponse;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONValue;

public class StandaloneContextFactory extends LmsContextFactory {

	public interface CourseListCodec extends
			JsonEncoderDecoder<UserCourseListResponse> {
	}

	private HashMap<String, String> coursesHashmap;

	public StandaloneContextFactory(String serverUrl, String evidenceUrl) {
		super();
		this.serverUrl = serverUrl;
		this.evidenceServerUrl = evidenceUrl;
		this.coursesHashmap = new HashMap<String, String>();

	}

	public HashMap<String, String> getCoursesHashmap() {
		return coursesHashmap;
	}

	public void updateCourses() {
		if (super.validUserLoggedIn) {
			Resource resource = new Resource(
					Controller.contextFactory.getEvidenceServerURL()
							+ "/lms/courses/moodle/" + getUser());
			resource.get().send(new JsonCallback() {

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
						String courseId = response.isArray().get(i).isObject()
								.get("courseid").toString()
								.replaceAll("\"", "");
						String name = response.isArray().get(i).isObject()
								.get("name").toString().replaceAll("\"", "");
						coursesHashmap.put(name, courseId);
					}

					ContextSelectionController
							.initContextController(coursesHashmap.keySet());
					Controller.reloadController.reload();

					setCourseContext(ContextSelectionController
							.getSelectedCourseContext());

				}

				@Override
				public void onFailure(Method method, Throwable exception) {

				}
			});
		}
	}

	public String getRoleFromContext() {
		return Role.teacher.toString();
	}

	public String getCompetenceServerURL() {
		return serverUrl;
	}

	@Override
	public String getRawCourseId() {
		return coursesHashmap.get(getCourseContext());
	}

}
