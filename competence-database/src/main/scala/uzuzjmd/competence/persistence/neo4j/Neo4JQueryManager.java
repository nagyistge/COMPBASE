package uzuzjmd.competence.persistence.neo4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import org.apache.commons.lang.NotImplementedException;
import uzuzjmd.competence.persistence.abstractlayer.CompOntologyAccess;
import uzuzjmd.competence.config.MagicStrings;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import uzuzjmd.competence.persistence.ontology.CompObjectProperties;
import uzuzjmd.competence.persistence.ontology.CompOntClass;
import uzuzjmd.competence.persistence.abstractlayer.CompetenceQueries;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by dehne on 04.12.2015.
 */
public class Neo4JQueryManager implements CompetenceQueries{

    final String txUri = MagicStrings.NEO4JURL + "/db/data/transaction/commit";

    static Logger logger = LogManager.getLogger(Neo4JQueryManager.class.getName());

    public ArrayList<String> getLabelForNode(String id) throws Exception {
        String query = "MATCH (e{id:'" + id + "'}) return labels(e)";
        ArrayList<String> resultString = issueSingleStatementRequest(query);
        return resultString;
    }

    private ArrayList<String> issueSingleStatementRequest(String query) throws Exception {
        logger.info(query);
        String payload = "{\"statements\" : [ {\"statement\" : \"" + query + "\"} ]}";
        return issueNeo4JRequest(payload);
    }

    private ArrayList<String> issueMultipleStatementRequest(String... queries) throws Exception {
        String statements = "";
        for (int i = 0; i < queries.length; i++) {
            statements += "{\"statement\": \"" + queries[i] + "\"}";
        }
        String payload = "{\"statements\" : [" + statements + "]}";
        return issueNeo4JRequest(payload);
    }

    public void executeReasoning(String... queries) throws Exception {
        issueMultipleStatementRequest(queries);
    }

    private ArrayList<String> issueNeo4JRequest(String payload) throws Exception {
        Client client2 = ClientBuilder.newClient();
        WebTarget target2 = client2.target(txUri);
        LinkedHashMap<String, ArrayList<LinkedHashMap<String, ArrayList<LinkedHashMap<String, ArrayList<String>>>>>> result = target2.request(
                MediaType.APPLICATION_JSON).post(
                Entity.entity(payload,
                        MediaType.APPLICATION_JSON), LinkedHashMap.class);

        if (!result.get("errors").isEmpty()) {
            throw new Exception(result.get("errors").get(0).toString());
        }
        return result.get("results").get(0).get("data").get(0).get("row");
    }


    /**
     * This is used for standard daos who have normal label
     * @param id
     * @param labelName
     * @throws Exception
     */
    public void setLabelForNode(String id, String labelName) throws Exception {
        String query = "MATCH (e{id:'" + id + "'}) set e:" + labelName + " return e";
        ArrayList<String> resultString = issueSingleStatementRequest(query);
    }

    public ArrayList<String> createUniqueNode(HashMap<String, String> props) throws Exception {
        logger.debug("Entering createUniqueNode with props:" + implode(props));
        String queryMerge = "MERGE (n:";
        if (props.containsKey("clazz")) {
            queryMerge +=  props.get("clazz");
        }  else {
            queryMerge += "unknown";
        }
        if (props.containsKey("id")){
            queryMerge += " {id: '" + props.get("id") + "'}";
        }
        queryMerge +=")";
        //res.add(queryMerge);
        for (Map.Entry<String, String> kvp :
                props.entrySet()) {
            if (kvp.getKey().contains("clazz") || kvp.getKey().contains("id")) {
                continue;
            } else {
                //res.add(" Set n." + kvp.getKey() + "='" + kvp.getValue() + "'");
                queryMerge += " Set n." + kvp.getKey() + "='" + kvp.getValue() + "'";
            }
        }
        return issueSingleStatementRequest(queryMerge + "return n");
    }

    /**
     * This is used for singleton DAOs who need and extra class node in order to reflect the double structure in owl
     * @param id
     * @param localName
     * @param superClassClass
     * @throws Exception
     */
    public void setClassForNode(String id, String localName, CompOntClass superClassClass) throws Exception {
        Boolean isClass = true;
        String createSuperNodeQuery = createUniqueNodeQuery(id, localName, isClass, superClassClass);
        String createIndividualOfRelationQuery = createIndividualOfRelation(id);
        issueMultipleStatementRequest(createSuperNodeQuery, createIndividualOfRelationQuery);
    }

    private String createIndividualOfRelation(String id) {
        String createIndividualOfRelation =
                "Match (n {id:\"" + id + "\", class:true}),(n2 {id:\"" + id + "\", class:false}) CREATE (n2)-[r:individualOf]->(n) return n, n2, r";
        return createIndividualOfRelation;
    }

    private String createUniqueNodeQuery(String id, String localName, Boolean isClass, CompOntClass ontClass) {
        String superNodeCreation = "MATCH (n:"+ontClass.toString()+" {id:\"" + id + "\", isClass:" + isClass + ", definition:\"" + localName + "\"})" +
                "CREATE UNIQUE (n) return n";
        return superNodeCreation;
    }


    private static String implode(Map<String, String> map){

        boolean first = true;
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<String, String> e : map.entrySet()){
            if (!first) sb.append(", ");
            sb.append(" " + e.getKey() + " : '" + e.getValue() + "' ");
            first = false;
        }

        return sb.toString();
    }
    /**
     *
     * @param id
     * @param edge
     * @param superClassId
     * @throws Exception
     */
    public void createSuperClassRelationShip(String id, CompObjectProperties edge, String superClassId) throws Exception {
        Boolean classRelationShip = true;
        createRelationShip(id, edge, superClassId, classRelationShip);
    }

    private void createRelationShip(String domainId, CompObjectProperties edge, String rangeId, Boolean classRelationShip) throws Exception {
        String query = "MATCH (n {id:\"" + domainId + "\", isClass="+classRelationShip+"}), (n2{id:\"" + rangeId + "\", isClass="+classRelationShip+"}) CREATE (n)-[r:" + edge.toString() + "]->(n2) return n,r,n2";
        issueSingleStatementRequest(query);
    }

    /**
     * used for Singletons
     * @param id
     * @return
     */
    public String getClassForNode(String id) throws Exception {
        String query = "MATCH (n {id=\""+id+"\"}), (n)-[r:individualOf]->(n2) return n2";
        ArrayList<String> result = issueSingleStatementRequest(query);
        return result.get(0);
    }

    public void deleteNode(String id) throws Exception {
        String query = "MATCH (n {id:\""+id+"\"}) DETACH DELETE n";
        issueSingleStatementRequest(query);
    }
    /**
     * delete Relationship between domainID and RangeID
     * @param domainId
     * @param rangeId
     * @param compObjectProperties
     */
    public void deleteRelationShip(String domainId, String rangeId, CompObjectProperties compObjectProperties) {
        // TODO implement
        throw new NotImplementedException();
    }


    /**
     *
     * @param domainId
     * @param rangeId
     * @param compObjectProperties
     * @return
     */
    public Boolean existsRelationShip(String domainId, String rangeId, CompObjectProperties compObjectProperties) {
        //TODO implement
        throw new NotImplementedException();
    }


    /**
     * checks if relationship exists but a singleton classNode is given instead of the individual
     * @param domainClassNodeId
     * @param rangeId
     * @param compObjectProperties
     */
    public void existsRelationShipWithSuperClassGiven(String domainClassNodeId, String rangeId, CompObjectProperties compObjectProperties) {
        // TODO implement
        throw new NotImplementedException();
    }

    /**
     * Get All edges in graph EXCEPT the subClass and individualOf relations
     * @return
     */
    public List<ObjectProperty> getAllObjectProperties() {
        // TODO implement
        throw new NotImplementedException();
    }

    /**
     * GET ALL Individuals in graph EXCEPT the SingletonIndividuals
     * @return
     */
    public List<Individual> getAllIndividuals() {
        // TODO implement
        throw new NotImplementedException();
    }

    /**
     * GET a map of all singletonClassIds and their definition attribute
     * @return
     */
    public HashMap<OntClass,String> getAllSingletonDefinitions() {

        // TODO implement
        throw new NotImplementedException();
    }

    /**
     * GET a list of all subClassRelations between the classNodes for a given label
     * @return
     */
    public List<SubClassRelation> getAllSingletonRelations(CompOntClass compOntClass) {

        // TODO implement
        throw new NotImplementedException();
    }

    /**
     * GET a list of all labels EXCEPT the singletonLables like Competence or Operator
     * @return
     */
    public List<OntClass> getAllOntClasses() {
        // TODO implement
        throw new NotImplementedException();
    }

    /**
     * GET the SingletonClass that are related by the given ObjectProperty (edge) to the domain given
     * @param domainClass
     * @param compObjectProperties
     * @return
     */
    @Override
    public ConcurrentLinkedQueue<OntClass> getRelatedClassesForOntClass(String domainClass, CompObjectProperties compObjectProperties) {
        // TODO implement
        throw new NotImplementedException();
    }


    /**
     * GET the individuals/nodes that are linked to the rangeIndividual like (domain)-[compObjectProperty]->(rangeIndividual)
     * @param compObjectProperties
     * @param rangeIndividualId
     * @return
     */
    @Override
    public ConcurrentLinkedQueue<Individual> getRelatedIndividuals(CompObjectProperties compObjectProperties, String rangeIndividualId) {
        // TODO implement
        throw new NotImplementedException();

    }

    /**
     * GET the individuals/nodes that are linked to the rangeIndividual like (domain)-[compObjectProperty]->(rangeIndividual)
     * @param domainIndividual
     * @param compObjectProperties
     * @return
     */
    @Override
    public ConcurrentLinkedQueue<Individual> getRelatedIndividualsDomainGiven(String domainIndividual, CompObjectProperties compObjectProperties) {
        // TODO implement
        throw new NotImplementedException();

    }

    /**
     * GET all nodes for a label identified by clazz
     * ASSERT that clazz is not a singletonClass
     * @see CompOntologyAccess
     * @param clazz
     * @return
     */
    public List<String> getAllInstanceDefinitions(CompOntClass clazz) {
        // TODO implement
        throw new NotImplementedException();
    }

    /**
     * Should return the shortestSubClassPath between the 2 singletonClasses given
     * @see CompOntologyAccess
     * @param start
     * @param end
     */
    public List<String>  getShortestSubClassPath(OntClass start, OntClass end) {
        // TODO implement
        throw new NotImplementedException();
    }


    /**
     *
     * @param subjectId
     * @param subjectDefinition
     * @param edge
     * @param objectId
     * @param objectDefinition
     */
    public void createRelationShip(String subjectId, String subjectDefinition, String edge, String objectId, String objectDefinition) {
        // TODO implement
        throw new NotImplementedException();
    }

    /**
     * return the definition of a node if id is given
     * @param id
     * @return
     */
    public String getDefinitionForClassForNode(String id) {
        // TODO implement
        throw new NotImplementedException();
    }
}
