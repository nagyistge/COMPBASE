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
        ArrayList<String> resultString = issueNeo4JRequestStrings(query);
        return resultString;
    }

    private <T> T issueSingleStatementRequest( Requestable<T> req, String query) throws Exception {
        logger.info(query);
        String payload = "{\"statements\" : [ {\"statement\" : \"" + query + "\"} ]}";
        return req.doRequest(payload);
    }

    private <T> T issueMultipleStatementRequest(Requestable<T> req, String... queries) throws Exception {
        String statements = "";
        for (int i = 0; i < queries.length; i++) {
            statements += "{\"statement\": \"" + queries[i] + "\"}";
        }
        String payload = "{\"statements\" : [" + statements + "]}";
        return req.doRequest(payload);
    }

    private ArrayList<String> issueNeo4JRequestStrings(final String payload) throws Exception {
        return issueSingleStatementRequest(new RequestableImpl<ArrayList<String>>() , payload);
    }


    private ArrayList<HashMap<String, String>> issueNeo4JRequestHashMap(final String payload) throws Exception {
        return issueSingleStatementRequest(new RequestableImpl<ArrayList<HashMap<String, String>>>() , payload);
    }

    private ArrayList<String> issueNeo4JRequestStrings(final String... queries) throws Exception {
        return issueMultipleStatementRequest(new RequestableImpl<ArrayList<String>>() , queries);
    }

    private LinkedHashMap<String, ArrayList<LinkedHashMap<String, ArrayList<LinkedHashMap<String, ArrayList<String>>>>>> issueAbstractRequest(String payload) {
    public void executeReasoning(String... queries) throws Exception {
        issueMultipleStatementRequest(queries);
    }

        Client client2 = ClientBuilder.newClient();
        WebTarget target2 = client2.target(txUri);
        return target2.request(
                MediaType.APPLICATION_JSON).post(
                Entity.entity(payload,
                        MediaType.APPLICATION_JSON), LinkedHashMap.class);
    }
    /**
     * This is used for standard daos who have normal label
     * @param id
     * @param labelName
     * @throws Exception
     */
    public void setLabelForNode(String id, String labelName) throws Exception {
        String query = "MATCH (e{id:'" + id + "'}) set e:" + labelName + " return e";
        ArrayList<String> resultString = issueSingleStatementRequest(new RequestableImpl<ArrayList<String>>()
                , query);
    }

    public ArrayList<HashMap<String, String>> createUniqueNode(HashMap<String, String> props) throws Exception {
        logger.debug("Entering createUniqueNode with props:" + implode(props));
        List<Neo4jQueryStatement> states = new ArrayList<>();
        states.add(new Neo4jQueryStatement());
        states.get(states.size() - 1).setQueryType(Neo4jQuery.queryType.MERGE);
        states.get(states.size() - 1).setVar("n");
        if (props.containsKey("clazz")) {
            states.get(states.size() - 1).setGroup(props.get("clazz"));
        }  else {
            states.get(states.size() - 1).setGroup("unknown");
        }
        if (props.containsKey("id")){
            states.get(states.size() - 1).addArgument("id", props.get("id"));
        }
        for (Map.Entry<String, String> kvp :
                props.entrySet()) {
            if (kvp.getKey().contains("clazz") || kvp.getKey().contains("id")) {
                continue;
            } else {
                states.add(new Neo4jQueryStatement());
                states.get(states.size() - 1).setQueryType(Neo4jQuery.queryType.SET);
                states.get(states.size() - 1).setVar("n");
                states.get(states.size() - 1).addArgument(kvp.getKey(), kvp.getValue());
            }
        }
        states.add(new Neo4jQueryStatement());
        states.get(states.size() - 1).setQueryType(Neo4jQuery.queryType.RETURN);
        states.get(states.size() - 1).setVar("n");

        logger.debug("Leaving createUniqueNode");
        return issueSingleStatementRequest(new RequestableImpl<ArrayList<HashMap<String, String>>>()
                , Neo4jQuery.statesToQuery(states));
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
        HashMap<String, String> hm = new HashMap<>();
        hm.put("id", id);
        hm.put("clazz", superClassClass.toString());
        hm.put("definition", localName);
        hm.put("isClass", String.valueOf(isClass));
        createUniqueNode(hm);
        String createIndividualOfRelationQuery = createIndividualOfRelation(id);
        issueSingleStatementRequest(new RequestableImpl<ArrayList<String>>() , createIndividualOfRelationQuery);
    }

    //TODO set back to private
    public String createIndividualOfRelation(String id) {
        List<Neo4jQueryStatement> states = new ArrayList<Neo4jQueryStatement>();
        states.add(new Neo4jQueryStatement());
        states.get(states.size() - 1).setQueryType(Neo4jQuery.queryType.MATCHMULTI);

        Neo4jQueryStatement tempState = new Neo4jQueryStatement();
        tempState.setQueryType(Neo4jQuery.queryType.MATCHNOGROUP);
        tempState.setVar("n");
        tempState.addArgument("id", id);
        tempState.addArgument("class", "false");

        states.get(states.size() - 1).addMultiState(tempState);

        tempState = new Neo4jQueryStatement();
        tempState.setQueryType(Neo4jQuery.queryType.MATCHNOGROUP);
        tempState.setVar("n2");
        tempState.addArgument("id", id);
        tempState.addArgument("class", "true");

        states.get(states.size() - 1).addMultiState(tempState);

        tempState = new Neo4jQueryStatement();
        tempState.setQueryType(Neo4jQuery.queryType.CREATEREL);
        tempState.setVar("r");
        tempState.setRelEntry("n2", "n");
        tempState.setGroup("individualOf");

        states.add(tempState);

        //String createIndividualOfRelation =
        //        "Match (n {id:\"" + id + "\", class:true}),(n2 {id:\"" + id + "\", class:false}) CREATE (n2)-[r:individualOf]->(n) return n, n2, r";
        return Neo4jQuery.statesToQuery(states) + "return n, n2, r";
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
        issueNeo4JRequestStrings(query);
    }

    /**
     * used for Singletons
     * @param id
     * @return
     */
    public String getClassForNode(String id) throws Exception {
        String query = "MATCH (n {id=\""+id+"\"}), (n)-[r:individualOf]->(n2) return n2";
        ArrayList<String> result = issueNeo4JRequestStrings(query);
        return result.get(0);
    }

    public void deleteNode(String id) throws Exception {

        String query = "MATCH (n {id:\""+id+"\"}) DETACH DELETE n";
        issueNeo4JRequestStrings(query);
    }
    /**
     * delete Relationship between domainID and RangeID
     * @param domainId
     * @param rangeId
     * @param compObjectProperties
     */
    public void deleteRelationShip(String domainId, String rangeId, CompObjectProperties compObjectProperties) throws Exception {
        String query = "MATCH (a{id:\""+domainId+"\"})-[r:"+compObjectProperties.toString()+"]->(b{id:\""+rangeId+"\"}) DELETE r";
        issueSingleStatementRequest(query);
    }


    /**
     *
     * @param domainId
     * @param rangeId
     * @param compObjectProperties
     * @return
     */
    public Boolean existsRelationShip(String domainId, String rangeId, CompObjectProperties compObjectProperties) throws Exception {
        String query = "MATCH (a{id:\""+domainId+"\"})-[r:"+compObjectProperties.toString()+"]->(b{id:\""+rangeId+"\"}) return r";
        return existMatches(query);
    }

    private Boolean existMatches(String query) throws Exception {
        ArrayList<String> result = issueSingleStatementRequest(query);
        return !result.isEmpty();
    }


    /**
     * checks if relationship exists but a singleton classNode is given instead of the individual
     * @param domainClassNodeId
     * @param rangeId
     * @param compObjectProperties
     */
    public Boolean existsRelationShipWithSuperClassGiven(String domainClassNodeId, String rangeId, CompObjectProperties compObjectProperties) throws Exception {
        String query = "MATCH (a)-[r:individualOf]->(b{id:\""+domainClassNodeId+"\"}), (a)-[r2:"+compObjectProperties.toString()+"]->(c{id:\""+rangeId+"\"}) return a";
        return existMatches(query);
    }

    /**
     * Get All edges in graph EXCEPT the subClass and individualOf relations
     * @return
     */
    public List<ObjectProperty> getAllObjectProperties() {


        String query = "MATCH (a)-[r]->(b) RETURN a,r,b";

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
    public String getDefinitionForClassForNode(String id) throws Exception {
        String query = "MATCH (a {id:\""+id+"\"}) return a.definition";
        return issueSingleStatementRequest(query).iterator().next();
    }
}
