package uzuzjmd.owl.competence.ontology;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import uzuzjmd.owl.util.CompFileUtil;
import uzuzjmd.owl.util.CompOntologyManager;
import uzuzjmd.owl.util.MagicStrings;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;

public class OntologyManagerTest {

	// @Test
	public void testBaseOntology() throws IOException {
		CompOntologyManager manager = new CompOntologyManager();
		manager.createBaseOntology();
		CompFileUtil compFileUtil = new CompFileUtil(manager.getM());
		compFileUtil.writeOntologyout(manager.getM());
	}

	@Test
	public void createIndividuals() throws IOException {
		CompOntologyManager manager = new CompOntologyManager();
		manager.createBaseOntology();
		manager.getUtil().createIndividualForString(
				manager.getUtil().getClass(CompOntClass.Competence),
				"meineTestkompetenz");
		manager.getUtil().createIndividualForString(
				manager.getUtil().getClass(CompOntClass.Competence),
				"meineTestkompetenz2");

		String ruleSrc = "[rule1: (?a rdf:type http://www.uzuzjmd.de/proof-of-concept.owl#Competence) (?b rdf:type http://www.uzuzjmd.de/proof-of-concept.owl#Competence) -> (?a owl:sameAs ?b)]";
		List<Rule> rules = Rule.parseRules(ruleSrc);
		GenericRuleReasoner reasoner = new GenericRuleReasoner(rules);
		reasoner.bindSchema(manager.getM());
		reasoner.setOWLTranslation(true); // not needed in RDFS case
		// reasoner.setTransitiveClosureCaching(true);
		InfModel inf = ModelFactory.createInfModel(reasoner, manager.getM());
		System.out.println("A * * =>");
		// inf.write(System.out);
		manager.getM().add(inf);
		CompFileUtil compFileUtil = new CompFileUtil(manager.getM());
		compFileUtil.writeOntologyout(manager.getM());
		System.out.println(manager
				.getM()
				.getResource(
						MagicStrings.PREFIX + CompOntClass.Competence.name())
				.as(OntClass.class).listInstances().next().getSameAs());

	}

}
