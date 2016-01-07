package uzuzjmd.competence.service.rest.dto;

import java.util.List;

/**
 * This class provides a wrapper for the service
 * to exchange data necessary to like a competence to a user who has performed it.
 */
public class CompetenceLinkData {
	public CompetenceLinkData(String course, String creator, String role, String linkedUser, List<String> competences, List<String> evidences) {
		super();
		this.course = course;
		this.creator = creator;
		this.role = role;
		this.linkedUser = linkedUser;
		this.competences = competences;
		this.evidences = evidences;
	}

	String course;

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLinkedUser() {
		return linkedUser;
	}

	public void setLinkedUser(String linkedUser) {
		this.linkedUser = linkedUser;
	}

	public List<String> getCompetences() {
		return competences;
	}

	public void setCompetences(List<String> competences) {
		this.competences = competences;
	}

	public List<String> getEvidences() {
		return evidences;
	}

	public void setEvidences(List<String> evidences) {
		this.evidences = evidences;
	}

	private String creator;
	private String role;
	private String linkedUser;
	private List<String> competences;
	private List<String> evidences;
}
