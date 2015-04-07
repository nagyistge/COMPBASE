package uzuzjmd.competence.shared;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name="SuggestedCompetenceColumn" , eager = true)
public class SuggestedCompetenceColumn implements Serializable {
	
	public SuggestedCompetenceColumn() {
		// TODO Auto-generated constructor stub
	}
		
	
	@ManagedProperty("#{testOutput}")
	private String testOutput;
	
	@ManagedProperty("#{progressInPercent}")
	private int progressInPercent;
	
	public String getTestOutput() {
		return testOutput;
	}

	public void setTestOutput(String testOutput) {
		this.testOutput = testOutput;
	}

	public int getProgressInPercent() {
		return progressInPercent;
	}

	public void setProgressInPercent(int progressInPercent) {
		this.progressInPercent = progressInPercent;
	}

}
