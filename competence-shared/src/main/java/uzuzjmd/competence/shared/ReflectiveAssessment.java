package uzuzjmd.competence.shared;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="ReflectiveAssessment")
@ViewScoped
public class ReflectiveAssessment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Boolean isLearningGoal;
	private String competenceDescription;
	private Integer assessmentIndex;
	
	
	public Boolean getIsLearningGoal() {
		return isLearningGoal;
	}
	public void setIsLearningGoal(Boolean isLearningGoal) {
		this.isLearningGoal = isLearningGoal;
	}
	public String getCompetenceDescription() {
		return competenceDescription;
	}
	public void setCompetenceDescription(String competenceDescription) {
		this.competenceDescription = competenceDescription;
	}
	public Integer getAssessmentIndex() {
		return assessmentIndex;
	}
	public void setAssessmentIndex(Integer assessmentIndex) {
		this.assessmentIndex = assessmentIndex;
	}
	
	
}
