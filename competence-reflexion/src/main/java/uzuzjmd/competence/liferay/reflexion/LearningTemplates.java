package uzuzjmd.competence.liferay.reflexion;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="LearningTemplates")
@RequestScoped
public class LearningTemplates {	
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
}
