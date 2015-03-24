package uzuzjmd.competence.liferay.reflexion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
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

	public SuggestedCompetenceGrid() {	
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
		
		suggestedCompetenceRows = new ArrayList<SuggestedCompetenceRow>();
		
		SuggestedCompetenceRow columntest = new SuggestedCompetenceRow();
		columntest.setSuggestedCompetenceRowHeader("Hörverstehen");
		List<SuggestedCompetenceColumn> suggestedCompetenceColumns = new ArrayList<SuggestedCompetenceColumn>();
		SuggestedCompetenceColumn column = new SuggestedCompetenceColumn();
		column.setTestOutput("hello Zeile 1.0");
		column.setProgressInPercent(90);
		suggestedCompetenceColumns.add(column);
		columntest.setSuggestedCompetenceColumns(suggestedCompetenceColumns);
		suggestedCompetenceRows.add(columntest);

		// zweite Testzeile

		SuggestedCompetenceRow columntest2 = new SuggestedCompetenceRow();	
		columntest2.setSuggestedCompetenceRowHeader("Lesen können");
		List<SuggestedCompetenceColumn> suggestedCompetenceColumns2 = new ArrayList<SuggestedCompetenceColumn>();
		SuggestedCompetenceColumn column2 = new SuggestedCompetenceColumn();
		column2.setTestOutput("hello Zeile 2.1");
		column2.setProgressInPercent(10);
		SuggestedCompetenceColumn column3 = new SuggestedCompetenceColumn();
		column3.setTestOutput("hello Zeile 2.2");
		column3.setProgressInPercent(40);
		suggestedCompetenceColumns2.add(column2);
		suggestedCompetenceColumns2.add(column3);
		columntest2.setSuggestedCompetenceColumns(suggestedCompetenceColumns2);
		suggestedCompetenceRows.add(columntest2);
	}
}
