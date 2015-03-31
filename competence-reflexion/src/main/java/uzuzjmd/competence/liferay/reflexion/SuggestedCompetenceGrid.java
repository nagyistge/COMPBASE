package uzuzjmd.competence.liferay.reflexion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@ManagedBean(name = "SuggestedCompetenceGrid")
@ViewScoped
public class SuggestedCompetenceGrid implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{suggestedCompetenceRows}")
	private List<SuggestedCompetenceRow> suggestedCompetenceRows;

	private LearningTemplates learningTemplates;

	public SuggestedCompetenceGrid() {
		suggestedCompetenceRows = new ArrayList<SuggestedCompetenceRow>();
	}

	public void setLearningTemplates(LearningTemplates learningTemplates) {
		this.learningTemplates = learningTemplates;
	}

	public LearningTemplates getLearningTemplates() {
		return learningTemplates;
	}

	public List<SuggestedCompetenceRow> getSuggestedCompetenceRows() {
		return suggestedCompetenceRows;
	}

	public void setSuggestedCompetenceRows(
			List<SuggestedCompetenceRow> suggestedCompetenceRows) {
		this.suggestedCompetenceRows = suggestedCompetenceRows;
	}

	@PostConstruct
	public void init() {
		if (suggestedCompetenceRows == null) {
		suggestedCompetenceRows = new ArrayList<SuggestedCompetenceRow>();
		}
	}

}
