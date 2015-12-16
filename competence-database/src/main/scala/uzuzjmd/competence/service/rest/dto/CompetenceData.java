package uzuzjmd.competence.service.rest.dto;

import java.util.LinkedList;
import java.util.List;

public class CompetenceData {

	private String operator;
	private List<String> catchwords;
	private List<String> superCompetences;
	private List<String> subCompetences;
	private String learningProjectName;
	private String forCompetence;

	public CompetenceData(String operator,
			List<String> catchwords,
			List<String> superCompetences,
			List<String> subCompetences,
			String learningProjectName, String forCompetence) {
		super();
		this.operator = operator;
		this.catchwords = catchwords;
		this.superCompetences = superCompetences;
		this.subCompetences = subCompetences;
		this.learningProjectName = learningProjectName;
		this.forCompetence = forCompetence;

		if (superCompetences == null) {
			this.superCompetences = new LinkedList<String>();
		}
		if (subCompetences == null) {
			this.subCompetences = new LinkedList<String>();
		}
	}

	public String getForCompetence() {
		return forCompetence;
	}

	public void setForCompetence(String forCompetence) {
		this.forCompetence = forCompetence;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public List<String> getCatchwords() {
		return catchwords;
	}

	public void setCatchwords(List<String> catchwords) {
		this.catchwords = catchwords;
	}

	public List<String> getSuperCompetences() {
		return superCompetences;
	}

	public void setSuperCompetences(
			List<String> superCompetences) {
		this.superCompetences = superCompetences;
	}

	public List<String> getSubCompetences() {
		return subCompetences;
	}

	public void setSubCompetences(
			List<String> subCompetences) {
		this.subCompetences = subCompetences;
	}

	public String getLearningProjectName() {
		return learningProjectName;
	}

	public void setLearningProjectName(
			String learningProjectName) {
		this.learningProjectName = learningProjectName;
	}
}
