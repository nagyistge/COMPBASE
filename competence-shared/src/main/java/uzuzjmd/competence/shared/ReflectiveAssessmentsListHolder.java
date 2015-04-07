package uzuzjmd.competence.shared;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="ReflectiveAssessmentsListHolder")
@ViewScoped
public class ReflectiveAssessmentsListHolder implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{reflectiveAssessmentList}")
	private List<ReflectiveAssessment> reflectiveAssessmentList;
	private String suggestedMetaCompetence;
	private Assessment assessment;
	
	public ReflectiveAssessmentsListHolder() {
		init();
	}

	public List<ReflectiveAssessment> getReflectiveAssessmentList() {
		return reflectiveAssessmentList;
	}

	public void setReflectiveAssessmentList(List<ReflectiveAssessment> reflectiveAssessmentList) {
		this.reflectiveAssessmentList = reflectiveAssessmentList;
	}

	public String getSuggestedMetaCompetence() {
		return suggestedMetaCompetence;
	}

	public void setSuggestedMetaCompetence(String suggestedMetaCompetence) {
		this.suggestedMetaCompetence = suggestedMetaCompetence;
	}
	
	@PostConstruct	
	public void init() {
		assessment = new Assessment();
		assessment.init();
		
		reflectiveAssessmentList = new LinkedList<ReflectiveAssessment>();
		
//		ReflectiveAssessment assessment = new ReflectiveAssessment();
//		assessment.setIsLearningGoal(true);
//		HashMap<String, Integer> reflexion = new HashMap<String, Integer>();
//		reflexion.put("Ich kann viele tolle Dinge", 2);
////		assessment.setReflexion(reflexion);
//		assessment.setCompetenceDescription("some stuff, I don't know");
//		assessment.setAssessmentIndex(0);
//		reflectiveAssessmentList.add(assessment);
//				
//		this.setSuggestedMetaCompetence("Hörbuch hören können");
		
	}

	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}
}
