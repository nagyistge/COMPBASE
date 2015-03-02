package uzuzjmd.competence.liferay.reflexion;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name="SuggestedCompetenceColumn" , eager = true)
public class SuggestedCompetenceColumn {
	
	@ManagedProperty("#{testOutput}")
	private String testOutput;
	
	public String getTestOutput() {
		return testOutput;
	}

	public void setTestOutput(String testOutput) {
		this.testOutput = testOutput;
	}

}
