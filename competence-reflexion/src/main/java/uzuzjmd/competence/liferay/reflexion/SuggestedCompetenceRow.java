package uzuzjmd.competence.liferay.reflexion;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="SuggestedCompetenceRow" , eager = true)
@ViewScoped
public class SuggestedCompetenceRow {
	@ManagedProperty("#{suggestedCompetenceColumns}")
	private List<SuggestedCompetenceColumn> suggestedCompetenceColumns;
	
	
	@ManagedProperty("#{suggestedCompetenceRowHeader}")
	private String suggestedCompetenceRowHeader;
		
	
	public SuggestedCompetenceRow() {
		suggestedCompetenceColumns = new ArrayList<SuggestedCompetenceColumn>();
	}

	public List<SuggestedCompetenceColumn> getSuggestedCompetenceColumns() {
		return suggestedCompetenceColumns;
	}

	public void setSuggestedCompetenceColumns(List<SuggestedCompetenceColumn> suggestedCompetenceColumns) {
		this.suggestedCompetenceColumns = suggestedCompetenceColumns;
	}

	public String getSuggestedCompetenceRowHeader() {
		return suggestedCompetenceRowHeader;
	}

	public void setSuggestedCompetenceRowHeader(
			String suggestedCompetenceRowHeader) {
		this.suggestedCompetenceRowHeader = suggestedCompetenceRowHeader;
	}

	


}
