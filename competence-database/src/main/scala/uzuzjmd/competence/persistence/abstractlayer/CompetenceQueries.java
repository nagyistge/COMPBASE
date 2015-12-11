package uzuzjmd.competence.persistence.abstractlayer;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by dehne on 10.12.2015.
 */
public interface CompetenceQueries {
	ConcurrentLinkedQueue<OntClass> getRelatedClassesForOntClass(String domainClass, CompObjectProperties compObjectProperties);

	ConcurrentLinkedQueue<Individual> getRelatedIndividuals(CompObjectProperties compObjectProperties, String rangeIndividualName);

	ConcurrentLinkedQueue<Individual> getRelatedIndividualsDomainGiven(String domainIndividual, CompObjectProperties compObjectProperties);
}
