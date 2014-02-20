package uzuzjmd.owl.competence.ontology;

import thewebsemantic.Id;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import thewebsemantic.binding.RdfBean;

public class Evidence extends RdfBean<Evidence> {
	private String titel;	
	private String commment;
	private int number;

	public void setTitel(String titel) {
		this.titel = titel;
	}

	@Id
	public String getTitle() {
		return titel;
	}

		
	public String getCommment() {
		return commment;
	}
	
	public void setCommment(String commment) {
		this.commment = commment;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
