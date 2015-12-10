package uzuzjmd.competence.neo4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uzuzjmd.competence.owl.access.MagicStrings;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import uzuzjmd.competence.owl.ontology.CompObjectProperties;
import uzuzjmd.competence.owl.ontology.CompOntClass;

import java.util.*;

/**
 * Created by dehne on 04.12.2015.
 */
public class Neo4JQueryManager {

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


    /*public  getClassForNode(String id) {
    }
    */
}
