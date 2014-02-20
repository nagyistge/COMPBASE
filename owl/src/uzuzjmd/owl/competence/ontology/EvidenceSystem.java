package uzuzjmd.owl.competence.ontology;

import java.util.HashSet;
import java.util.Set;

import thewebsemantic.Functional;
import thewebsemantic.Id;
import thewebsemantic.RdfProperty;
import thewebsemantic.binding.RdfBean;


	public class EvidenceSystem extends RdfBean<EvidenceSystem> {
		private String titel;
		private String commment;
		private Set<Evidence> evidences;

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

				
		@RdfProperty
		public Set<Evidence> getEvidences() {
			if (evidences == null) {
				evidences = new HashSet<Evidence>();
			}
			return evidences;
		}

		public void setEvidences(Set<Evidence> evidences) {
			this.evidences = evidences;
		}
}
