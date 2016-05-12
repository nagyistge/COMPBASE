package uzuzjmd.competence.service.rest.dto;

import java.util.List;

/**
 * This wraps a pair of course and competences in order to exchange competences linked to a course
 */
public class CourseData {
	private String course;
	private String printableName;

	public CourseData(String course, String printableName, List<String> competences) {
		this.course = course;
		this.printableName = printableName;
		this.competences = competences;
	}

	private List<String> competences;
	
	public CourseData(String course, List<String> competences) {
		this.course = course;
		this.competences = competences;
	}

	public CourseData(String course, String printableName) {
		this.course = course;
		this.printableName = printableName;
	}

	public String getCourseId() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public List<String> getCompetences() {
		return competences;
	}

	public void setCompetences(List<String> competences) {
		this.competences = competences;
	}

	public String getPrintableName() {
		return printableName;
	}

	public void setPrintableName(String printableName) {
		this.printableName = printableName;
	}
}
