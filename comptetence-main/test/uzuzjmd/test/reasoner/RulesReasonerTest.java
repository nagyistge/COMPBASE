package uzuzjmd.test.reasoner;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uzuzjmd.owl.competence.ontology.CompObjectProperties;
import uzuzjmd.owl.competence.ontology.CompOntClass;
import uzuzjmd.owl.reasoning.jena.RuleFactory;
import uzuzjmd.owl.reasoning.jena.SimpleRulesReasoner;
import uzuzjmd.owl.util.CompOntologyManager;
import uzuzjmd.owl.util.CompOntologyUtil;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

public class RulesReasonerTest {

	private CompOntologyManager manager;
	private CompOntologyUtil util;
	private OntClass learnerClass;
	private OntClass testCompetence;	

	@Before
	public void setupTestSzenario() throws IOException {
		manager = initOntology();
		util = manager.getUtil();
		
		manager.getRulesReasoner().getReasoner().setParameter(ReasonerVocabulary.PROPtraceOn, true);		
		initRulesReasoning(manager.getRulesReasoner());
	
		initTestData(util); // fügt ein paar TestIndividuals hinzu
		
		//transitivReasonerInit();		
		//initOWLReasoner();
				
		manager.switchOffDebugg();
		//manager.switchOnDebug();
	}
	
	@After
	public void showDataAsXML() throws IOException {
		util.writeOntologyout(manager.getM());
	}

	private void initOWLReasoner() {
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();		
		InfModel infmodel = ModelFactory.createInfModel(reasoner, manager.getM());
		infmodel.validate();
		manager.getM().add(infmodel);
	}

	private void transitivReasonerInit() {
		Reasoner reasoner = ReasonerRegistry.getTransitiveReasoner();		
		InfModel infmodel = ModelFactory.createInfModel(reasoner, manager.getM());
		infmodel.validate();
		manager.getM().add(infmodel);
	}

	@Test
	public void test() throws IOException {
		System.out.println("testing simple rules");
	}

	@Test
	public void testGetRelatedIndiduals() {
		Individual testindividual = util.createIndividualForString(
				learnerClass, "juliancominglate");
		Individual testindividual2 = util.createIndividualForString(testCompetence, "myTestkompetence");
		util.createObjectPropertyWithIndividual(testindividual, testindividual2, CompObjectProperties.LearnerOf);				
		Set<Individual> individuals = util.getQueries().getRelatedIndividuals(CompObjectProperties.LearnerOf,testindividual2.getLocalName());			
		
		System.out.println("found related individuals for TestCompetence+ LearnerOf");
		for (Individual individual : individuals) {			
			System.out.println(individual);
		}
		assertTrue(individuals.contains(testindividual));
		
	}

	@Test
	public void testGetRelatedClasses() throws IOException {
		Set<OntClass> classes = util.getQueries().getRelatedClassesForOntClass(
				CompOntClass.Learner.name(),
				CompObjectProperties.LearnerOfInverse);
		Set<OntClass> classes2 = util.getQueries()
				.getRelatedClassesForOntClass(CompOntClass.Learner.name(),
						CompObjectProperties.LearnerOf);		
		System.out.println("found related classes for Learner+ LearnerOf/LearnerOfInverse");
		for (OntClass ontClass : classes2) {
			System.out.println(ontClass);
		}
		for (OntClass ontClass : classes) {
			System.out.println(ontClass);
		}
		
		util.writeOntologyout(manager.getM());
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

	private void initTestData(CompOntologyUtil util) {
		// Erstelle Klassen
		learnerClass = util.createOntClass(CompOntClass.Learner);
		testCompetence = util.createOntClassForString("TestCompetence");
		OntClass catchwordClass = util.createOntClass(CompOntClass.Catchword);		
		

		// Erstelle Klassenhierarchie
		testCompetence.addSuperClass(util.getClass(CompOntClass.Competence));

		// Erstelle Individuals
		Individual individual = util.createIndividualForString(learnerClass,
				"julian");
		Individual catchwordIndividual = util.createIndividualForString(
				catchwordClass, "mycachtword");
		util.createIndividualForString(learnerClass, "juliancominglate");
			
	}

	private SimpleRulesReasoner initRulesReasoning(
			SimpleRulesReasoner rulesReasoner) {
		RuleFactory factory = new RuleFactory();
		for (String ruleString : factory.getRuleStringss()) {
			rulesReasoner.addRuleAsString(ruleString);
		}
		return rulesReasoner;
	}

}
