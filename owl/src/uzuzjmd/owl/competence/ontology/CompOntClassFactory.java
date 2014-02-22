package uzuzjmd.owl.competence.ontology;

import uzuzjmd.owl.util.CompOntologyUtil;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;

public class CompOntClassFactory {

	private OntModel m;
	private CompOntologyUtil util;

	public CompOntClassFactory(OntModel m, CompOntologyUtil util) {
		this.m = m;
		this.util = util;
	}

	public OntClass getCatchwordClass() {
		return util.getOntClassForString(m, "Catchword");
	}

	public OntClass getCompetenceClass() {
		return util.getOntClassForString(m, "Competence");
	}

	public OntClass getCompetenceAreaClass() {
		return util.getOntClassForString(m, "CompetenceArea");
	}

	public OntClass getCompetenceDescriptionClass() {
		return util.getOntClassForString(m, "CompetenceDescription");
	}

	public OntClass getDescriptionElementClass() {
		return util.getOntClassForString(m, "DescriptionElement");
	}

	public OntClass getEvidenceClass() {
		return util.getOntClassForString(m, "Evidence");
	}

	public OntClass getLearnerClass() {
		return util.getOntClassForString(m, "Learner");
	}

	public OntClass getOperatorClass() {
		return util.getOntClassForString(m, "Operator");
	}
}
