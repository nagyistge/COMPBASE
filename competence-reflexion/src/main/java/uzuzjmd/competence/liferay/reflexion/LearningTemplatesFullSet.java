package uzuzjmd.competence.liferay.reflexion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

@ManagedBean(name="LearningTemplatesFullSet")
@ViewScoped
public class LearningTemplatesFullSet implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> learningTemplates;
	private String writtenText;

	public List<String> getLearningTemplates() {
		return learningTemplates;
	}

	public void setLearningTemplates(List<String> learningTemplates) {
		this.learningTemplates = learningTemplates;
	}
	
	public List<String> complete(String query) {
		learningTemplates = new ArrayList<String>();
		learningTemplates.add("template1");
		learningTemplates.add("template2");
		List<String> result = new ArrayList<String>();
		Collection<String> tmp = Collections2.filter(learningTemplates, Predicates.containsPattern("^"+query));
		result.addAll(tmp);
		return result;
	}
	
	@PostConstruct
	public void init() {
		learningTemplates = new ArrayList<String>();
		learningTemplates.add("template1");
		learningTemplates.add("template2");
	}

	public String getWrittenText() {
		return writtenText;
	}

	public void setWrittenText(String writtenText) {
		this.writtenText = writtenText;
	}
}
