package uzuzjmd.owl.competence.ontology;

import thewebsemantic.Id;
import thewebsemantic.Namespace;
import thewebsemantic.binding.RdfBean;
import uzuzjmd.owl.util.MagicStrings;

@Namespace(value = MagicStrings.PREFIX)
public class Operator extends RdfBean<Operator> implements HasTitel{

	private String titel;

	@Id
	@Override
	public String getTitel() {
		return this.titel;
	}

	@Override
	public void setTitel(String string) {
		this.titel = string;		
	}

}
