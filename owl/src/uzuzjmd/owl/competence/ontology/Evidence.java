package uzuzjmd.owl.competence.ontology;

import thewebsemantic.Id;
import thewebsemantic.binding.RdfBean;

public class Evidence extends RdfBean<Evidence> {
	private String titel;

	public void setTitel(String titel) {
		this.titel = titel;
	}

	@Id
	public String getTitle() {
		return titel;
	}

}
