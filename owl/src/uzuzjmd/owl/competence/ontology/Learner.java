package uzuzjmd.owl.competence.ontology;

import thewebsemantic.Id;
import thewebsemantic.Namespace;
import thewebsemantic.binding.RdfBean;
import uzuzjmd.owl.util.MagicStrings;

@Namespace(value = MagicStrings.PREFIX)
public class Learner extends RdfBean<Learner> implements HasTitel{
	private String titel;

	@Id
	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

}
