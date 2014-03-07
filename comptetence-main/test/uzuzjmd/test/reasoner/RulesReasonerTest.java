package uzuzjmd.test.reasoner;

import java.io.IOException;
import java.util.Set;

import org.junit.Test;

import uzuzjmd.owl.competence.ontology.CompObjectProperties;
import uzuzjmd.owl.competence.ontology.CompOntClass;
import uzuzjmd.owl.util.CompOntologyManager;
import uzuzjmd.owl.util.CompOntologyUtil;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;

public class RulesReasonerTest {

	@Test
	public void test() throws IOException {
		System.out.println("testing simple rules");
		CompOntologyManager manager = initOntology();				
		CompOntologyUtil util = manager.getUtil();		
		
		// tesstdaten anlegen
		OntClass learnerClass = util.createOntClass(CompOntClass.Learner);
		OntClass testCompetence = util.createOntClassForString("TestCompetence");
		testCompetence.addSuperClass(util.getClass(CompOntClass.Competence));
		Individual testindividual = util.createIndividualForString(learnerClass, "juliancominglate");
		util.createObjectPropertyWithIndividualAndClass(testindividual, testCompetence, CompObjectProperties.LearnerOf);		
	
		Set<OntClass> classes = util.getQueries().getRelatedClassesForOntClass(CompOntClass.Learner.name(), CompObjectProperties.LearnerOf);
		assert classes.contains(testCompetence.getSuperClass());
		
		Set<Individual> individuals = util.getQueries().getRelatedIndividuals(testCompetence, CompObjectProperties.LearnerOf);
		assert (individuals.contains(testindividual));
		
	}
	
	

	private CompOntologyManager initOntology() {
		CompOntologyManager manager = new CompOntologyManager();
		manager.createBaseOntology();
		return manager;
	}

		
		

}
