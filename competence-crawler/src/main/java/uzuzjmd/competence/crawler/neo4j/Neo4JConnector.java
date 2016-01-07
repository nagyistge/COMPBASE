package uzuzjmd.competence.crawler.neo4j;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import uzuzjmd.competence.persistence.neo4j.Neo4JQueryManager;
import uzuzjmd.competence.persistence.neo4j.Requestable;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;

import java.util.AbstractMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by carl on 07.01.16.
 */
public class Neo4JConnector extends Neo4JQueryManager {
    @Override
    public ConcurrentLinkedQueue<OntClass> getRelatedClassesForOntClass(String domainClass, CompObjectProperties compObjectProperties) {
        return null;
    }

    @Override
    public ConcurrentLinkedQueue<Individual> getRelatedIndividuals(CompObjectProperties compObjectProperties, String rangeIndividualName) {
        return null;
    }

    @Override
    public ConcurrentLinkedQueue<Individual> getRelatedIndividualsDomainGiven(String domainIndividual, CompObjectProperties compObjectProperties) {
        return null;
    }

    public void queryMyStatements(String... statements) throws Exception {
        issueNeo4JRequestStrings(statements);
    }

    static public String mergeRelation(AbstractMap.SimpleEntry<String, String> id1,
                                       AbstractMap.SimpleEntry<String, String> id2,
                                       String relation) {
        return "MERGE (n:" + id1.getKey() + "{id:'" + id1.getValue() + "'}) "
                    + "MERGE (m:" + id2.getKey() + "{id:'" + id2.getValue() + "'})"
                    + "CREATE UNIQUE (m) -[t:" + relation + "]-> (n) ";

    }
}
