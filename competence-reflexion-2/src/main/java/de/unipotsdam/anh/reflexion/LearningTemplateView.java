package de.unipotsdam.anh.reflexion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

@ManagedBean(name = "learningTemplateView")
public class LearningTemplateView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{templateCompetenceView}")
	private TemplateCompetenceView templateCompetenceView;
	
	private String newLearningTemplate;
	private String selectedLearningTemplate;
	
	private List<String> learningTemplates;
	
	@PostConstruct
	public void init() {
//		learningTemplates = LearningTemplateDao.findAll().getData();
	}
	
	public List<String> complete(String query) {
        final List<String> results = new ArrayList<String>();
        final Collection<String> tmp = Collections2.filter(learningTemplates, Predicates.containsPattern(query));	
		results.addAll(tmp);
		
        return results;
    }
	
	public void createLearningTemplate(ActionEvent e) {
//		LearningTemplateDao.createTemplate(newLearningTemplate);
		this.selectedLearningTemplate = this.newLearningTemplate;
		templateCompetenceView.setDescriptorsetName(selectedLearningTemplate);
		System.out.println(selectedLearningTemplate);
	}
	
	public void selectLearningTemplate(ActionEvent e) {
		System.out.println(selectedLearningTemplate);
	}

	public String getNewLearningTemplate() {
		return newLearningTemplate;
	}

	public void setNewLearningTemplate(String newLearningTemplate) {
		this.newLearningTemplate = newLearningTemplate;
	}

	public String getSelectedLearningTemplate() {
		return selectedLearningTemplate;
	}

	public void setSelectedLearningTemplate(String selectedLearningTemplate) {
		this.selectedLearningTemplate = selectedLearningTemplate;
	}

	public List<String> getLearningTemplates() {
		return learningTemplates;
	}

	public void setLearningTemplates(List<String> learningTemplates) {
		this.learningTemplates = learningTemplates;
	}

	public TemplateCompetenceView getTemplateCompetenceView() {
		return templateCompetenceView;
	}

	public void setTemplateCompetenceView(
			TemplateCompetenceView templateCompetenceView) {
		this.templateCompetenceView = templateCompetenceView;
	}
}
