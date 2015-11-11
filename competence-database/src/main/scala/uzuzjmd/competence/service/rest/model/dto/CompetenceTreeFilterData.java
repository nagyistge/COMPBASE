package uzuzjmd.competence.service.rest.model.dto;

import java.util.List;

public class CompetenceTreeFilterData {
	private List<String> selectedCatchwordArray;
	private List<String> selectedOperatorsArray;
	private String course;
	private Boolean compulsory;
	private String textFilter;

	public CompetenceTreeFilterData(
			List<String> selectedCatchwordArray,
			List<String> selectedOperatorsArray,
			String course, Boolean compulsory,
			String textFilter) {
		super();
		this.selectedCatchwordArray = selectedCatchwordArray;
		this.selectedOperatorsArray = selectedOperatorsArray;
		this.course = course;
		this.compulsory = compulsory;
		this.textFilter = textFilter;
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

}
