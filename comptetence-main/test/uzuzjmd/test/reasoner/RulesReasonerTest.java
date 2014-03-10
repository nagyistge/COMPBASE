package uzuzjmd.test.reasoner;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import uzuzjmd.owl.competence.ontology.CompObjectProperties;
import uzuzjmd.owl.competence.ontology.CompOntClass;
import uzuzjmd.owl.reasoning.jena.SimpleRulesReasoner;
import uzuzjmd.owl.util.CompOntologyManager;
import uzuzjmd.owl.util.CompOntologyUtil;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;

public class RulesReasonerTest {

	private CompOntologyManager manager;
	private CompOntologyUtil util;
	private OntClass learnerClass;
	private OntClass testCompetence;

	@Before
	public void setupTestSzenario() {
		manager = initOntology();
		util = manager.getUtil();
		
		initIndividuals(util); // fügt ein paar TestIndividuals hinzu

		try {
			initRulesReasoning(new SimpleRulesReasoner(manager.getM()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		manager.switchOffDebugg();
	}

	@Test
	public void test() throws IOException {
		System.out.println("testing simple rules");
	}

	@Test
	public void testGetRelatedIndiduals() {
		Individual testindividual = util.createIndividualForString(
				learnerClass, "juliancominglate");
		Set<Individual> individuals = util.getQueries().getRelatedIndividuals(
				testCompetence, CompObjectProperties.LearnerOf);
		Set<Individual> individuals2 = util.getQueries().getRelatedIndividuals(
				testCompetence, CompObjectProperties.LearnerOfInverse);
		assertTrue(individuals.contains(testindividual.getLocalName()));
		assertTrue(individuals2.contains(testindividual.getLocalName()));

		System.out
				.println("found related individuals for TestCompetence+ LearnerOfInverse");
		for (Individual individual : individuals2) {
			System.out.println(individual);
		}
		System.out
				.println("found related individuals for TestCompetence+ LearnerOf");
		for (Individual individual : individuals) {
			System.out.println(individual);
		}
	}

	@Test
	public void testGetRelatedClasses() {
		Set<OntClass> classes = util.getQueries().getRelatedClassesForOntClass(
				CompOntClass.Learner.name(),
				CompObjectProperties.LearnerOfInverse);
		Set<OntClass> classes2 = util.getQueries()
				.getRelatedClassesForOntClass(CompOntClass.Learner.name(),
						CompObjectProperties.LearnerOf);		
		System.out
				.println("found related classes for Learner+ LearnerOf/LearnerOfInverse");
		for (OntClass ontClass : classes2) {
			System.out.println(ontClass);
		}
		for (OntClass ontClass : classes) {
			System.out.println(ontClass);
		}
		
		assertTrue(classes.contains(testCompetence.getSuperClass()));
		assertTrue(classes2.contains(testCompetence.getSuperClass()));
		assertTrue(classes.contains(testCompetence));
		assertTrue(classes2.contains(testCompetence));

	}

	private CompOntologyManager initOntology() {
		CompOntologyManager manager = new CompOntologyManager();
		manager.createBaseOntology();
		return manager;
	}

	private void initIndividuals(CompOntologyUtil util) {
		// Erstelle Klassen
		learnerClass = util.createOntClass(CompOntClass.Learner);
		testCompetence = util
				.createOntClassForString("TestCompetence");
		OntClass catchwordClass = util.createOntClass(CompOntClass.Catchword);
		testCompetence.addSuperClass(util.getClass(CompOntClass.Competence));
		Individual testindividual = util.createIndividualForString(
				learnerClass, "juliancominglate");

		// Erstelle Klassenhierarchie
		testCompetence.addSuperClass(util.getClass(CompOntClass.Competence));

		// Erstelle Individuals
		Individual individual = util.createIndividualForString(learnerClass,
				"julian");
		Individual catchwordIndividual = util.createIndividualForString(
				catchwordClass, "mycachtword");

		// create Object Properties
		util.createObjectPropertyWithIndividualAndClass(individual,
				testCompetence, CompObjectProperties.LearnerOf);
		util.createObjectPropertyWithIndividualAndClass(catchwordIndividual,
				testCompetence, CompObjectProperties.CatchwordOf);

	}

	private SimpleRulesReasoner initRulesReasoning(
			SimpleRulesReasoner rulesReasoner) {
		rulesReasoner.addRuleAsString(
				"(?a comp:LearnerOf ?b) -> (?b comp:LearnerOfInverse ?a)",
				"operatorInverse");
		rulesReasoner.reason();
		return rulesReasoner;
	}

}
