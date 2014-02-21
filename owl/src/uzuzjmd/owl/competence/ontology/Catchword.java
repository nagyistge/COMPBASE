package uzuzjmd.owl.competence.ontology;

import thewebsemantic.Id;
import thewebsemantic.Namespace;
import thewebsemantic.binding.RdfBean;
import uzuzjmd.owl.util.MagicStrings;

@Namespace(value = MagicStrings.PREFIX)
public class Catchword extends RdfBean<Catchword> implements HasTitel {
	private String titel;

	@Id
	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}	
		
	
}
