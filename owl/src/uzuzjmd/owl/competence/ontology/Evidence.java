package uzuzjmd.owl.competence.ontology;

import thewebsemantic.Id;
import thewebsemantic.Namespace;
import thewebsemantic.binding.RdfBean;
import uzuzjmd.owl.util.MagicStrings;

@Namespace(value = MagicStrings.PREFIX)
public class Evidence extends RdfBean<Evidence> implements HasTitel{
	private String titel;	
	private String commment;
	private int number;

	public void setTitel(String titel) {
		this.titel = titel;
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
	
	@Id
	@Override
	public String getTitel() {
		return titel;
	}
	

}
