package uzuzjmd.competence.service.rest.client.api;

import uzuzjmd.competence.gui.client.viewcontroller.Controller;

public class RestUrlFactory {
	public static String getRequirementTextFieldUrl() {
		return Controller.contextFactory.getServerURL()
				+ "/competences/json/coursecontext/requirements/"
				+ Controller.contextFactory.getCourseContext();
	}

	public static String getAddCompulsoryCompetencesUrl() {
		return Controller.contextFactory.getServerURL()
				+ "/competences/json/coursecontext/create/"
				+ Controller.contextFactory.getCourseContext() + "/true";
	}

	public static String getAddNotCompulsoryCompetencesUrl() {
		return Controller.contextFactory.getServerURL()
				+ "/competences/json/coursecontext/create/"
				+ Controller.contextFactory.getCourseContext() + "/false";
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
					+ Controller.contextFactory.getCourseContext();
		}
		return Controller.contextFactory.getServerURL()
				+ "/competences/xml/competencetree/" + context + "/"
				+ compulsoryFilter + "/cached" + queryString;
	}
}
