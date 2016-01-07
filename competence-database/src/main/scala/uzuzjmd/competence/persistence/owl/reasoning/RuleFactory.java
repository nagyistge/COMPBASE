package uzuzjmd.competence.persistence.owl.reasoning;

import java.util.HashSet;
import java.util.Set;

/**
 * implements the reasoning with the jena backend
 */
public class RuleFactory {

	private HashSet<String> result;

	public RuleFactory() {
		result = new HashSet<String>();
		result.add(getNotSelfReferencedCreationRule());
		result.add(getNotSelfReferencedCreationRule2());
		result.add(getPrerequisiteConclusion());
		result.add(getNotRequireTransition1());
		result.add(getNotRequireTransition2());
		result.add(getNotRequireTransition3());
		result.add(getNotAllowedCreationRule());
		result.add(noNothingArtefacts());
		result.add(noNothingArtefacts2());
		result.add(createPerformanceLinks());
	}

	public Set<String> getRuleStrings() {
		return result;
	}

	public String getPrerequisiteConclusion() {
		return "[requireTransition: (?a comp:PrerequisiteOf ?b) (?b comp:PrerequisiteOf ?c) -> (?a comp:PrerequisiteOf ?c)]";
	}

	public String getNotRequireTransition1() {
		return "[notrequireTransition1: (?a comp:NotPrerequisiteOf ?b) (?b comp:PrerequisiteOf ?c) (?a comp:PrerequisiteOf ?c) -> remove(2)]";
	}

	public String getNotRequireTransition2() {
		return "[notrequireTransition1: (?a comp:PrerequisiteOf ?b) (?b comp:NotPrerequisiteOf ?c) (?a comp:PrerequisiteOf ?c) -> remove(0)]";
	}

	public String getNotRequireTransition3() {
		return "[notrequireTransition1: (?a comp:PrerequisiteOf ?b) (?b comp:PrerequisiteOf ?c) (?a comp:NotPrerequisiteOf ?c) -> remove(1)]";
	}

	public String getNotAllowedCreationRule() {
		return "[notallowedcreation: (?a comp:PrerequisiteOf ?b) (?user rdf:type comp:User) -> (?user comp:NotAllowedToView ?a)]";
	}

	public String getNotSelfReferencedCreationRule() {
		return "[notselfreferenced: (?a comp:PrerequisiteOf ?a)  -> remove(0)]";
	}

	public String getNotSelfReferencedCreationRule2() {
		return "[notselfreferenced2: (?a comp:NotAllowedToView ?a)  -> remove(0)]";
	}

	public String noNothingArtefacts() {
		return "[noNothing1: equal(?a, http://comp#I3org227owlNothing) -> remove(?a)]";
	}

	public String noNothingArtefacts2() {
		return "[noNothing2: equal(?a, owl:Nothing) -> remove(?a)]";
	}

	public String createPerformanceLinks() {
		return "[userCompetenceLink: "
				+ "(?user comp:UserOfLink ?abstractEvidenceLink)"
				+ "(?abstractEvidenceLink comp:linksCompetence ?competence) "
				+ "-> (?user comp:UserHasPerformed ?competence)]";
	}
}

