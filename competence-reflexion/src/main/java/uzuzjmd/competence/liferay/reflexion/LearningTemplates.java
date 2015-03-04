package uzuzjmd.competence.liferay.reflexion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.ResourceDependency;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

//@ResourceDependency(name="js/main.js")
@ManagedBean(name="LearningTemplates")
@ViewScoped
public class LearningTemplates implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> learningTemplates;

	public List<String> getLearningTemplates() {
		return learningTemplates;
	}

	public void setLearningTemplates(List<String> learningTemplates) {
		this.learningTemplates = learningTemplates;
	}
	
	@PostConstruct
	public void init() {
		learningTemplates = new ArrayList<String>();
		learningTemplates.add("template1");
		learningTemplates.add("template2");
	}
	
	public Boolean dummyAction() {
		return null;
	}
}
