package uzuzjmd.competence.liferay.reflexion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "LearningTemplates")
@ViewScoped
public class LearningTemplates implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> learningTemplates;
	private String selectedCompetence;

	public List<String> getLearningTemplates() {
		return learningTemplates;
	}

	public void setLearningTemplates(List<String> learningTemplates) {
		this.learningTemplates = learningTemplates;
	}

	@PostConstruct
	public void init() {
		learningTemplates = new ArrayList<String>();
		List<String> selectedTemplates = SelectedLearningTemplateDAO.findAll()
				.getData();
		if (selectedTemplates != null) {
			learningTemplates.addAll(selectedTemplates);
		}
	}

	public void addTemplate(ActionEvent e) {
		SelectedLearningTemplateDAO.persist(getSelectedCompetence());
	}

	public String getSelectedCompetence() {
		return selectedCompetence;
	}

	public void setSelectedCompetence(String selectedCompetence) {
		this.selectedCompetence = selectedCompetence;
	}
}
