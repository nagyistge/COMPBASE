package uzuzjmd.owl.competence.ontology;

import thewebsemantic.Id;
import thewebsemantic.Namespace;
import thewebsemantic.binding.RdfBean;
import uzuzjmd.owl.util.MagicStrings;

@Namespace(value = MagicStrings.PREFIX)
public class Competence extends RdfBean<Competence> implements HasTitel {
	private String titel;

	@Override
	public void setTitel(String string) {
		this.titel = string;		
	}
	
	@Id
	@Override
	public String getTitel() {
		return this.titel;
	}

}
