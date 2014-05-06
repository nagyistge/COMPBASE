package uzuzjmd.competence.owl.reasoning;

import java.util.HashSet;
import java.util.Set;

import uzuzjmd.competence.owl.ontology.CompObjectProperties;

public class RuleFactory {

	private HashSet<String> result;

	public RuleFactory() {
		result = new HashSet<String>();
		// generateInverseRules();
		// result.add(catchwordOfConclusions());
		// result.add(getCompulsoryInheritance());
	}

	private void generateInverseRules() {
		for (CompObjectProperties prop : CompObjectProperties.values()) {
			if (!prop.name().endsWith("Inverse")) {
				result.add("[operatorInverse" + prop.hashCode() + ": (?a comp:" + prop.name() + " ?b) -> (?b comp:" + prop.name() + "Inverse ?a)]");
			} else if (prop.name().endsWith("Inverse")) {
				result.add("[operatorInverse" + prop.hashCode() + ": (?a comp:" + prop.name() + "Inverse ?b) -> (?b comp:" + prop.name() + " ?a)]");
			}
		}
	}

	public String getCompulsoryInheritance() {
		return "[compulsoryInheritance: (?a comp:CompulsoryOf ?b) (?b rdf:type ?d) (?d rdfs:subClassOf comp:Competence) (?d rdfs:subClassOf ?e) (?f rdf:type ?e) -> (?a comp:CompulsoryOf ?f)]";
	}

	public Set<String> getRuleStringss() {
		return result;
	}

	public String getOperatorInverse() {
		return "[operatorInverse: (?a comp:LearnerOf ?b) -> (?b comp:LearnerOfInverse ?a)]";
	}

	public String catchwordOfConclusions() {
		return "[catchwordTransition: (?a rdfs:subClassOf comp:Catchword)" + " (?a rdfs:subClassOf ?b) " + "-> (?b rdfs:subClassOf comp:Catchword)]";
	}

}

// create inverse relationships

// [operatorInverse2: (?a comp:CatchwordOf ?b) -> (?b comp:CatchwordOfInverse
// ?a)]

// tests
// [opinheritanceClassUP2: (?a rdfs:range comp:Competence) -> ]

// inheritance for classes
// [opinheritanceClassUP: (?a rdfs:range comp:Competence)
// (?a rdfs:domain comp:Learner)
// (?competence rdfs:subClassOf comp:Competence)
// (?learnerOf rdfs:subClassOf comp:Learner)
// ->
// (?a rdfs:range ?competence)
// (?a rdfs:domain ?learnerOf)]

// [opinheritanceClassDOWN: (?a comp:CatchwordOf ?b) -> (?b
// comp:CatchwordOfInverse ?a)]

// inheritance for indviduals
// [opinheritenceUp: (?a comp:LearnerOf ?competence) (?competence
// rdfs:subClassOf ?c) notEqual(?c, owl:Thing) notEqual(?c, rdfs:Resource)
// -> (?a comp:LearnerOf ?c)]
// [opinheritenceDOWN: (?a comp:LearnerOf ?competence) (?c rdfs:subClassOf
// ?competence) -> (?a comp:LearnerOf ?c)]