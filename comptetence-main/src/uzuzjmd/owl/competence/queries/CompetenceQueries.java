package uzuzjmd.owl.competence.queries;

import java.util.HashSet;
import java.util.Set;

import uzuzjmd.owl.competence.ontology.CompObjectProperties;
import uzuzjmd.owl.util.MagicStrings;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

public class CompetenceQueries {
	
	private OntModel m;
	private String queryprefix;


	public CompetenceQueries(OntModel m) {
		this.m = m;
		queryprefix = "PREFIX comp: <" + MagicStrings.PREFIX + ">\n"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n";
	}
	
	/**
	 * 
	 * This only works if the ObjectProperty is registered within the Class specified by the String
	 * and the ObjectProperty is either point "Range" or "Domain" to the Class specified by the String 
	 * @param string Classname
	 * @param compObjectProperties ObjectPropertyClassifier
	 * @return relatedClasses
	 */
	public Set<OntClass> getRelatedClassesForOntClass(String domainClass, final CompObjectProperties compObjectProperties) {
		Set<OntClass> result = new HashSet<OntClass>();
		
		// Create a new query
		String queryString = queryprefix + 			
			"SELECT ?class " +
			"WHERE {" +
			"comp:"+compObjectProperties.name() + " rdfs:range ?class." + "\n"+
			"comp:"+compObjectProperties.name() + " rdfs:domain comp:"+domainClass +
			"}";

		Query query = QueryFactory.create(queryString); 

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, m);
		ResultSet results = qe.execSelect();
		   for ( ; results.hasNext() ; )
		    {
		      QuerySolution soln = results.nextSolution() ;
		      result.add(soln.getResource("?class").as(OntClass.class));
		    }

		// Important - free up resources used running the query
		qe.close();
		return result;
	}


	
	/**
	 * Iterates over all the object properties of Type specified and then iterates over all the individuals
	 * over all the individuals linked to them as range or domain except the individuals of Type
	 * OntClass specified
	 * @param compObjectProperties
	 * @param ontClass
	 * @return
	 */
	public Set<Individual> getRelatedIndividuals(OntClass rangeClass, final CompObjectProperties compObjectProperties) {
		Set<Individual> result = new HashSet<Individual>();		
		
		// Create a new query
		String queryString = queryprefix + 			
			"SELECT ?individual " +
			"WHERE {" +
			"?individual  comp:"+compObjectProperties.name() + " comp:" + rangeClass.getLocalName()+ 		
			"}";

		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, m);
		ResultSet results = qe.execSelect();
		   for ( ; results.hasNext() ; )
		    {
		      QuerySolution soln = results.nextSolution() ;
		      result.add(soln.getResource("?individual").as(Individual.class));
		    }

		// Important - free up resources used running the query
		qe.close();
		return result;
	}
}
