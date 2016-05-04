package uzuzjmd.competence.service.rest.dto;

import java.util.LinkedList;
import java.util.List;

public class CompetenceFilterData {
	private List<String> selectedCatchwordArray = new LinkedList<>();
	private List<String> selectedOperatorsArray = new LinkedList<>();
	private String course;
	private Boolean compulsory;
	private String textFilter;

	public Boolean getResultAsTree() {
		return resultAsTree;
	}

	public void setResultAsTree(Boolean resultAsTree) {
		this.resultAsTree = resultAsTree;
	}

	private Boolean resultAsTree;

	public void setRootCompetence(String rootCompetence) {
		this.rootCompetence = rootCompetence;
	}

	private String rootCompetence;

	public CompetenceFilterData(
			List<String> selectedCatchwordArray,
			List<String> selectedOperatorsArray,
			String course, Boolean compulsory,
			String textFilter, Boolean resultAsTree, String rootCompetence) {
		super();
		this.selectedCatchwordArray = selectedCatchwordArray;
		this.selectedOperatorsArray = selectedOperatorsArray;
		this.course = course;
		this.compulsory = compulsory;
		this.textFilter = textFilter;
		this.resultAsTree = resultAsTree;
		this.rootCompetence = rootCompetence;
	}

	public CompetenceFilterData(String course, Boolean resultAsTree) {
		this.course = course;
		this.resultAsTree = resultAsTree;
	}

	public List<String> getSelectedCatchwordArray() {
		return selectedCatchwordArray;
	}

	public void setSelectedCatchwordArray(
			List<String> selectedCatchwordArray) {
		this.selectedCatchwordArray = selectedCatchwordArray;
	}

	public List<String> getSelectedOperatorsArray() {
		return selectedOperatorsArray;
	}

	public void setSelectedOperatorsArray(
			List<String> selectedOperatorsArray) {
		this.selectedOperatorsArray = selectedOperatorsArray;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Boolean getCompulsory() {
		return compulsory;
	}

	public void setCompulsory(Boolean compulsory) {
		this.compulsory = compulsory;
	}

	public String getTextFilter() {
		return textFilter;
	}

	public void setTextFilter(String textFilter) {
		this.textFilter = textFilter;
	}

	public String getRootCompetence() {
		return rootCompetence;
	}
}
