package uzuzjmd.competence.service.rest.model.dto;

import java.util.List;

public class PrerequisiteData {
	private String course;
	private String linkedCompetence;
	private List<String> selectedCompetences;
	
	public PrerequisiteData(String course, String linkedCompetence, List<String> selectedCompetences) {
		this.setCourse(course);
		this.setLinkedCompetence(linkedCompetence);
		this.setSelectedCompetences(selectedCompetences);
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getLinkedCompetence() {
		return linkedCompetence;
	}

	public void setLinkedCompetence(String linkedCompetence) {
		this.linkedCompetence = linkedCompetence;
	}

	public List<String> getSelectedCompetences() {
		return selectedCompetences;
	}

	public void setSelectedCompetences(List<String> selectedCompetences) {
		this.selectedCompetences = selectedCompetences;
	}
	
	public void addElementSelectedCompetences(String element) {
		this.selectedCompetences.add(element);
	}

}