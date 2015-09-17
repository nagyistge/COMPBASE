package uzuzjmd.competence.service.rest.client.api;

import uzuzjmd.competence.gui.client.viewcontroller.Controller;

import com.google.gwt.core.shared.GWT;

public class RestUrlFactory {
	public static String getRequirementTextFieldUrl() {
		return Controller.contextFactory.getServerURL()
				+ "/competences/json/coursecontext/requirements/"
				+ Controller.contextFactory.getCourseId();
	}

	public static String getAddCompulsoryCompetencesUrl() {
		return Controller.contextFactory.getServerURL()
				+ "/competences/json/coursecontext/create/"
				+ Controller.contextFactory.getCourseId() + "/true";
	}

	public static String getAddNotCompulsoryCompetencesUrl() {
		return Controller.contextFactory.getServerURL()
				+ "/competences/json/coursecontext/create/"
				+ Controller.contextFactory.getCourseId() + "/false";
	}

	public static String getCompetenceTreeWithFilters(String compulsoryFilter,
			String query, Boolean isCourseContext) {
		String queryString = "";
		if (query != null) {
			queryString += query;
		}

		String context = Controller.contextFactory.getOrganization();
		if (isCourseContext) {
			context = "coursecontext/"
					+ Controller.contextFactory.getCourseId();
		}
		return Controller.contextFactory.getServerURL()
				+ "/competences/xml/competencetree/" + context + "/"
				+ compulsoryFilter + "/cached" + queryString;
	}

	public static String getProgressMapUrl() {
		return Controller.contextFactory.getServerURL()
				+ "/competences/json/link/progress/"
				+ Controller.contextFactory.getCourseId();
	}

	public static String computeActivityRestURL() {
		String moodleEvidenceUrl = Controller.contextFactory
				.getEvidenceServerURL()
				+ "/lms/activities/usertree/xml/crossdomain/"
				+ Controller.contextFactory.getCourseId()
				+ "?lmsSystem="
				+ Controller.contextFactory.getLMSsystem()
				+ "&user="
				+ Controller.contextFactory.getUser()
				+ "&password="
				+ Controller.contextFactory.getPassword();
		GWT.log("activity url queried is: " + moodleEvidenceUrl);
		return moodleEvidenceUrl;
	}

	public static String getProgressMapURL() {
		return Controller.contextFactory.getServerURL()
				+ "/competences/json/link/progress/"
				+ Controller.contextFactory.getCourseId();
	}
}
