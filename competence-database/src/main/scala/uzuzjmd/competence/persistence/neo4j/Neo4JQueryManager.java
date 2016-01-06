package uzuzjmd.competence.persistence.neo4j;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uzuzjmd.competence.config.MagicStrings;
import uzuzjmd.competence.persistence.abstractlayer.CompOntologyAccess;
import uzuzjmd.competence.persistence.abstractlayer.CompetenceQueries;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;
import uzuzjmd.competence.persistence.ontology.CompOntClass;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
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

    private ArrayList<ArrayList<String>> issueNeo4JRequestArrayListArrayList(final String payload) throws Exception {
        return issueSingleStatementRequest(new RequestableImpl<ArrayList<ArrayList<String>>>() , payload);
    }

    private ArrayList<String> issueNeo4JRequestStrings(final String... queries) throws Exception {
        return issueMultipleStatementRequest(new RequestableImpl<ArrayList<String>>() , queries);
    }

    private LinkedHashMap<String, ArrayList<LinkedHashMap<String, ArrayList<LinkedHashMap<String, ArrayList<String>>>>>> issueAbstractRequest(String payload) {
        Client client2 = ClientBuilder.newClient();
        WebTarget target2 = client2.target(txUri);
        return target2.request(
                MediaType.APPLICATION_JSON).post(
                Entity.entity(payload,
                        MediaType.APPLICATION_JSON), LinkedHashMap.class);
    }

    public void executeReasoning(String... queries) throws Exception {
        issueNeo4JRequestStrings(queries);
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
        issueNeo4JRequestStrings(query);
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
        ArrayList<String> result = issueNeo4JRequestStrings(query);
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
    public List<ObjectProperty> getAllObjectProperties() throws Exception {
        String query = "MATCH (a)-[r]->(b) RETURN a,r,b";
        ArrayList<ArrayList<String>> lists = issueNeo4JRequestArrayListArrayList(query);
        ArrayList<ObjectProperty> result = new ArrayList<ObjectProperty>();

        for (ArrayList<String> list : lists) {
            Neo4jIndividual domainIndividual = new Neo4jIndividual(list.get(0), list.get(0), null);
            Neo4jIndividual rangeIndividual = new Neo4jIndividual(list.get(2), list.get(2), null);
            CompObjectProperties edge = CompObjectProperties.valueOf(list.get(1));
            Neo4JObjectProperty prop = new Neo4JObjectProperty(domainIndividual,edge, rangeIndividual);
            result.add(prop);
        }
        return result;
    }

    /**
     * GET ALL Individuals in graph EXCEPT the SingletonIndividuals
     * @return
     */
    public List<Individual> getAllIndividuals() throws Exception {

        String query = "MATCH (a) WHERE ALL (x IN labels(a) WHERE NOT (x = \"Competence\") AND NOT (x=\"Operator\") AND NOT (x=\"Catchword\")) return a.id, labels(a)";
        ArrayList<HashMap<String, String>> results = issueNeo4JRequestHashMap(query);
        // TODO implement
        List<Individual> result = new LinkedList<Individual>();
        for (HashMap<String, String> stringStringHashMap : results) {
            Neo4jOntClass ontClass = new Neo4jOntClass(stringStringHashMap.values().iterator().next());
            Individual individual = new Neo4jIndividual(stringStringHashMap.keySet().iterator().next(), stringStringHashMap.keySet().iterator().next(), ontClass);
            result.add(individual);
        }
        return result;
    }

    /**
     * GET a map of all singletonClassIds and their definition attribute
     * @return
     */
    public HashMap<OntClass,String> getAllSingletonDefinitions() throws Exception {
        String query = "MATCH (a)-[r:individualOf]->(b) return b.definition";
        ArrayList<String> result = issueNeo4JRequestStrings(query);

        HashMap<OntClass,String> resultFinal = new HashMap<OntClass, String>();

        for (String s : result) {
            OntClass correspondingClass = new Neo4jIndividual(s,s,null, true).getOntClass();
            resultFinal.put(correspondingClass, s);
        }

        return resultFinal;
    }

    /**
     * GET a list of all subClassRelations between the classNodes for a given label
     * @return
     */
    public List<SubClassRelation> getAllSingletonRelations() throws Exception {
        ArrayList<SubClassRelation> resultFinal = new ArrayList<SubClassRelation>();
        String query = "MATCH (a)-[r:subClassOf]->(b) return a,r,b";
        ArrayList<ArrayList<String>> result = issueNeo4JRequestArrayListArrayList(query);
        for (ArrayList<String> strings : result) {
            Neo4jIndividual domainIndividual = new Neo4jIndividual(strings.get(0), strings.get(0), null);
            Neo4jIndividual rangeIndividual = new Neo4jIndividual(strings.get(2), strings.get(2), null);
            SubClassRelation rel = new SubClassRelation(domainIndividual, rangeIndividual, CompOntClass.valueOf(domainIndividual.getOntClass().getLocalName()));
            resultFinal.add(rel);
        }
        return resultFinal;
    }

    /**
     * GET a list of all labels EXCEPT the singletonLables like Competence or Operator
     * @return
     */
    public List<OntClass> getAllOntClasses() {
        List<OntClass> result = new ArrayList<OntClass>();
        for (CompOntClass compOntClass: CompOntClass.values()) {
            if (!compOntClass.equals(CompOntClass.Competence) || compOntClass.equals(CompOntClass.Catchword) || compOntClass.equals(CompOntClass.Operator)) {
                result.add(new Neo4jOntClass(compOntClass.name()));
            }
        }
        String competenceQuery = "MATCH (a:Competence) return a";
        String operatorQuery = "Match (b:Operator) return b";
        String catchwordQuery = "Match (c:Operator) return c";
        try {
            ArrayList<String> operatorClasses = issueNeo4JRequestStrings(operatorQuery);
            for (String operator : operatorClasses) {
                result.add(new Neo4jOntClass(operator, CompOntClass.Operator));
            }
            ArrayList<String> catchwordClasses = issueNeo4JRequestStrings(catchwordQuery);
            for (String catchword : catchwordClasses) {
                result.add(new Neo4jOntClass(catchword, CompOntClass.Catchword));
            }
            ArrayList<String> competenceClasses = issueNeo4JRequestStrings(competenceQuery);
            for (String competence : competenceClasses) {
                result.add(new Neo4jOntClass(competence, CompOntClass.Competence));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * GET the SingletonClass that are related by the given ObjectProperty (edge) to the domain given
     * @param domainClass
     * @param compObjectProperties
     * @return
     */
    @Override
    public ConcurrentLinkedQueue<OntClass> getRelatedClassesForOntClass(String domainClass, CompObjectProperties compObjectProperties) {
        String query = "MATCH (a)-[r:individualOf]->(b{id:\""+domainClass+"\"})-[r2:"+compObjectProperties.toString()+"]->(c) RETURN c";
        return createOntclassQueue(query);
    }


    /**
     * GET the individuals/nodes that are linked to the rangeIndividual like (domain)-[compObjectProperty]->(rangeIndividual)
     * @param compObjectProperties
     * @param rangeIndividualId
     * @return
     */
    @Override
    public ConcurrentLinkedQueue<Individual> getRelatedIndividuals(CompObjectProperties compObjectProperties, String rangeIndividualId) {
        String query = "MATCH (b)-[r:"+compObjectProperties.toString()+"]->(a {id:\""+rangeIndividualId+"\"}) RETURN b";
        return createIndividualQueue(query);
    }

    /**
     * GET the individuals/nodes that are linked to the rangeIndividual like (domain)-[compObjectProperty]->(rangeIndividual)
     * @param domainIndividual
     * @param compObjectProperties
     * @return
     */
    @Override
    public ConcurrentLinkedQueue<Individual> getRelatedIndividualsDomainGiven(String domainIndividual, CompObjectProperties compObjectProperties) {
        String query = "MATCH (a {id:\""+domainIndividual+"\"})-[r:"+compObjectProperties.toString()+"]->(b) RETURN b";
        return createIndividualQueue(query);
    }

    private ConcurrentLinkedQueue<Individual> createIndividualQueue(String query) {
        try {
            ArrayList<String> result = issueNeo4JRequestStrings(query);
            List<Individual> individuals = new LinkedList<Individual>();
            for (String resultString:result
                    ) {
                individuals.add(new Neo4jIndividual(resultString, resultString, null).fetchIfExists());
            }
            ConcurrentLinkedQueue<Individual> resultQueue = new ConcurrentLinkedQueue<Individual>();
            resultQueue.addAll(individuals);
            return resultQueue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private ConcurrentLinkedQueue<OntClass> createOntclassQueue(String query) {
        try {
            ArrayList<String> result = issueNeo4JRequestStrings(query);
            List<OntClass> ontClasses = new LinkedList<OntClass>();
            for (String resultString:result
                    ) {
                ontClasses.add(new Neo4jOntClass(resultString).fetchIfExists());
            }
            ConcurrentLinkedQueue<OntClass> resultQueue = new ConcurrentLinkedQueue<OntClass>();
            resultQueue.addAll(ontClasses);
            return resultQueue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * GET all nodes for a label identified by clazz
     * ASSERT that clazz is not a singletonClass
     * @see CompOntologyAccess
     * @param clazz
     * @return
     */
    public List<String> getAllInstanceDefinitions(CompOntClass clazz) throws Exception {
        String query = "MATCH (a:"+clazz.name()+") return a.name";
        ArrayList<String> result = issueNeo4JRequestStrings(query);
        return result;
    }

    /**
     * Should return the shortestSubClassPath between the 2 singletonClasses given
     * @see CompOntologyAccess
     * @param start
     * @param end
     */
    public List<String>  getShortestSubClassPath(OntClass start, OntClass end) throws Exception {
        String query = "MATCH p=(a{id:\""+start.getLocalName()+"\"})-[r:subClassOf*]->(b{id:\""+end.getLocalName()+"\"}) return EXTRACT (n IN nodes(p)|n.definition) AS ids";
        return issueNeo4JRequestStrings(query);
    }


    /**
     *
     * @param subjectId
     * @param subjectDefinition
     * @param edge
     * @param objectId
     * @param objectDefinition
     */
    public void createRelationShip(String subjectId, String subjectDefinition, String edge, String objectId, String objectDefinition) throws Exception {
        String subjectQuery = "MERGE (a{id:\""+subjectId+"\",subjectDefinition:\""+subjectDefinition+"\"}) return a";
        String objectQuery = "MERGE (b{id:\""+objectId+"\",subjectDefinition:\""+objectDefinition+"\"}) return b";

        String relQuery = "MATCH (a{id:\""+subjectId+"\",subjectDefinition:\""+subjectDefinition+"\"})," +
                          "MATCH (b{id:\""+objectId+"\",subjectDefinition:\""+objectDefinition+"\"})" +
                            "CREATE UNIQUE (a)-[r:"+edge+"]->(b) " +
                            "RETURN r";

        issueNeo4JRequestStrings(subjectQuery, objectQuery, relQuery);
    }

    /**
     * return the definition of a node if id is given
     * @param id
     * @return
     */
    public String getDefinitionForClassForNode(String id) throws Exception {
        String query = "MATCH (a {id:\""+id+"\"}) return a.definition";
        return issueNeo4JRequestStrings(query).iterator().next();
    }

    /**
     * return the singletonSuperClass
     * @param neo4jOntClass
     * @return
     */
    public OntClass getSuperClass(Neo4jOntClass neo4jOntClass) {
        String query = "MATCH (a {id:\""+neo4jOntClass.getLocalName()+"\"})-[r:subClassOf]->(b) return b";
        try {
            ArrayList<String> results = issueNeo4JRequestStrings(query);
            if (!results.isEmpty()) {
                return new Neo4jOntClass(results.iterator().next());
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
