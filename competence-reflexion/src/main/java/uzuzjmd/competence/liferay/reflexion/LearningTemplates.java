package uzuzjmd.competence.liferay.reflexion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
	private String selectedCompetence; // the one to persist in lernprojekte tab
	private String selectedLearningTemplate; // the one to select the grid for

	//@ManagedProperty("#{SuggestedCompetenceGrid}")
	private SuggestedCompetenceGrid suggestedCompetenceGrid;

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
			if (!selectedTemplates.isEmpty()) {
				SuggestedCompetenceGrid data = SuggestedCompetenceGridDAO.getGrid(selectedTemplates.get(0));
				System.out.println(data.getSuggestedCompetenceRows().size());
				setSuggestedCompetenceGrid(data);
			}
		}

	}

	public void setSuggestedCompetenceGrid(
			SuggestedCompetenceGrid suggestedCompetenceGrid) {
		this.suggestedCompetenceGrid = suggestedCompetenceGrid;
	}

	public SuggestedCompetenceGrid getSuggestedCompetenceGrid() {
		return suggestedCompetenceGrid;
	}

	public void addTemplate(ActionEvent e) {
		SelectedLearningTemplateDAO.persist(getSelectedCompetence());
	}

	// public void deleteTemplate(ActionEvent e) {
	// System.out.println(e.getComponent().getId());
	// }

	public void deleteTemplate1(String todelete) {
		SelectedLearningTemplateDAO.delete(todelete);
	}

	public String getSelectedCompetence() {
		return selectedCompetence;
	}

	public void setSelectedCompetence(String selectedCompetence) {
		this.selectedCompetence = selectedCompetence;
	}

	public void setSelectedLearningTemplate(String selectedLearningTemplate) {
		this.selectedLearningTemplate = selectedLearningTemplate;
	}

	public String getSelectedLearningTemplate() {
		return selectedLearningTemplate;
	}
}
