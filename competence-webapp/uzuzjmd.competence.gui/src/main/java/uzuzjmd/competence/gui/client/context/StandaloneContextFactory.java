package uzuzjmd.competence.gui.client.context;

import java.util.HashMap;

import org.fusesource.restygwt.client.JsonEncoderDecoder;

import uzuzjmd.competence.service.rest.client.dto.UserCourseListResponse;

import com.google.gwt.core.client.GWT;

public class StandaloneContextFactory extends LmsContextFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public interface CourseListCodec extends
			JsonEncoderDecoder<UserCourseListResponse> {
	}

	private HashMap<String, Long> coursesHashmap;

	public StandaloneContextFactory(String serverUrl, String evidenceUrl) {
		super();
		this.serverUrl = serverUrl;
		this.evidenceServerUrl = evidenceUrl;
		this.coursesHashmap = new HashMap<String, Long>();
		this.coursesHashmap.put("university", 0l);
		setCourseContext("university");
		setRole("teacher");
	}

	public HashMap<String, Long> getCoursesHashmap() {
		return coursesHashmap;
	}

	public String getRoleFromContext() {
		return Role.teacher.toString();
	}

	public String getCompetenceServerURL() {
		return serverUrl;
	}

	@Override
	public Long getCourseId() {

		GWT.log("using standalone context factory");
		GWT.log("course context is: " + getCourseContext());
		GWT.log("course id is: " + coursesHashmap.get(getCourseContext()));
		return coursesHashmap.get(getCourseContext());
	}

	public Boolean getValidLoggedIn() {
		return super.validUserLoggedIn;
	}
}
