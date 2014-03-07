package uzuzjmd.test.reasoner;

import java.io.IOException;

import org.junit.Test;

import uzuzjmd.owl.competence.ontology.CompObjectProperties;
import uzuzjmd.owl.competence.ontology.CompOntClass;
import uzuzjmd.owl.util.CompOntologyManager;
import uzuzjmd.owl.util.CompOntologyUtil;
import uzuzjmd.owl.util.MagicStrings;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

public class RulesReasonerTest {

	@Test
	public void test() throws IOException {
		System.out.println("testing simple rules");
		CompOntologyManager manager = initOntology();		
		CompOntologyUtil util = manager.getUtil();
		OntClass learnerClass = util.createOntClass(CompOntClass.Learner);
		OntClass testCompetence = util.createOntClassForString("TestCompetence");
		testCompetence.addSuperClass(util.getClass(CompOntClass.Competence));
		Individual testindividual = util.createIndividualForString(learnerClass, "juliancominglate");
		util.createObjectPropertyWithIndividualAndClass(testindividual, testCompetence, CompObjectProperties.LearnerOf);		
	

		// Create a new query
		String queryString = 
			"PREFIX comp: <" + MagicStrings.PREFIX + ">"+
			"SELECT ?competence " +
			"WHERE {" +
			"      comp:juliancominglate comp:LearnerOf ?competence " +			
			"      }";

		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, manager.getM());
		ResultSet results = qe.execSelect();

		// Output query results	
		ResultSetFormatter.out(System.out, results, query);

		// Important - free up resources used running the query
		qe.close();
		
//		Set<Individual> julians = util.getRelatedIndividuals(testCompetence, CompObjectProperties.LearnerOfInverse);
//		for (Individual individual2 : julians) {
//			System.out.println(individual2);
//		}
	}
	
	

	private CompOntologyManager initOntology() {
		CompOntologyManager manager = new CompOntologyManager();
		manager.createBaseOntology();
		return manager;
	}

		
		

}
