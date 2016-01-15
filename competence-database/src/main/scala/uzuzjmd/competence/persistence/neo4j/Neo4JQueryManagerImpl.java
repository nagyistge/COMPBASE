package uzuzjmd.competence.persistence.neo4j;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uzuzjmd.competence.exceptions.DataFieldNotInitializedException;
import uzuzjmd.competence.monopersistence.daos.Dao;
import uzuzjmd.competence.monopersistence.daos.Competence;
import uzuzjmd.competence.monopersistence.daos.CourseContext;
import uzuzjmd.competence.monopersistence.daos.SelfAssessment;
import uzuzjmd.competence.monopersistence.daos.User;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;
import uzuzjmd.competence.persistence.ontology.CompOntClass;
import uzuzjmd.competence.shared.StringList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dehne on 04.12.2015.
 */
public class Neo4JQueryManagerImpl extends Neo4JQueryManager {

    public Neo4JQueryManagerImpl() {
    }

    public ArrayList<String> getLabelsForNode(String id) throws Exception {
        String query = "MATCH (e{id:'" + id + "'}) return labels(e)";
        ArrayList<ArrayList<String>> resultString = issueNeo4JRequestArrayListArrayList(query);
        if (resultString == null) {
            return new ArrayList<String>();
        }
        try {
            return resultString.iterator().next();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * This is used for standard daos who have normal label
     *
     * @param id
     * @param labelName
     * @throws Exception
     */
    public void setLabelForNode(String id, String labelName) throws Exception {
        String query = "MATCH (e{id:'" + id + "'}) set e:" + labelName + " return e";
        ArrayList<String> resultString = issueSingleStatementRequest(new RequestableImpl<ArrayList<String>>(), query);
    }

    public ArrayList<HashMap<String, String>> createOrUpdateUniqueNode(HashMap<String, String> props) throws Exception {
        logger.debug("Entering createOrUpdateUniqueNode with props:" + implode(props));
        List<Neo4jQueryStatement> states = new ArrayList<>();
        states.add(new Neo4jQueryStatement());
        states.get(states.size() - 1).setQueryType(Neo4jQuery.queryType.MERGE);
        states.get(states.size() - 1).setVar("n");
        if (props.containsKey("clazz") && props.get("clazz") != null) {
            states.get(states.size() - 1).setGroup(props.get("clazz"));
        }
        /*else {
            states.get(states.size() - 1).setGroup("unknown");
        }*/
        if (props.containsKey("id")) {
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

        logger.debug("Leaving createOrUpdateUniqueNode");
        return issueSingleStatementRequest(new RequestableImpl<ArrayList<HashMap<String, String>>>()
                , Neo4jQuery.statesToQuery(states));
    }


    private static String implode(Map<String, String> map) {

        boolean first = true;
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> e : map.entrySet()) {
            if (!first) sb.append(", ");
            sb.append(" " + e.getKey() + " : '" + e.getValue() + "' ");
            first = false;
        }

        return sb.toString();
    }


    public void createRelationShip(String domainId, CompObjectProperties edge, String rangeId) throws Exception {
        String query = "MATCH (n {id:'" + domainId + "'}), (n2{id:'" + rangeId + "'}) CREATE UNIQUE (n)-[r:" + edge.toString() + "]->(n2) return n,r,n2";
        issueNeo4JRequestStrings(query);
    }



    public void deleteNode(String id) throws Exception {
        String query = "MATCH (n {id:'" + id + "'}) DETACH DELETE n";
        issueNeo4JRequestStrings(query);
    }

    /**
     * delete Relationship between domainID and RangeID
     *
     * @param domainId
     * @param rangeId
     * @param compObjectProperties
     */
    public void deleteRelationShip(String domainId, String rangeId, CompObjectProperties compObjectProperties) throws Exception {
        String query = "MATCH (a{id:'" + domainId + "'})-[r:" + compObjectProperties.toString() + "]->(b{id:'" + rangeId + "'}) DELETE r";
        issueNeo4JRequestStrings(query);
    }


    /**
     * @param domainId
     * @param rangeId
     * @param compObjectProperties
     * @return
     */
    public Boolean existsRelationShip(String domainId, String rangeId, CompObjectProperties compObjectProperties) throws Exception {
        String query = "MATCH (a{id:'" + domainId + "'})-[r:" + compObjectProperties.toString() + "]->(b{id:'" + rangeId + "'}) return r";
        return existMatches(query);
    }

    private Boolean existMatches(String query) throws Exception {
        ArrayList<String> result = issueNeo4JRequestStrings(query);
        if (result == null) {
            return false;
        }
        return !result.isEmpty();
    }


    /**
     * checks if relationship exists but a singleton classNode is given instead of the individual
     *
     * @param domainClassNodeId
     * @param rangeId
     * @param compObjectProperties
     */
    public Boolean existsRelationShipWithSuperClassGiven(String domainClassNodeId, String rangeId, CompObjectProperties compObjectProperties) throws Exception {
        String query = "MATCH (a)-[r:individualOf]->(b{id:'" + domainClassNodeId + "'}), (a)-[r2:" + compObjectProperties.toString() + "]->(c{id:'" + rangeId + "'}) return a";
        return existMatches(query);
    }



    public List<String> getAssociatedNodeIdsAsRange(CompObjectProperties compObjectProperties, String rangeIndividualId) throws Exception {
        String query2 = "MATCH (b)-[r:" + compObjectProperties.toString() + "]->(a {id:'" + rangeIndividualId + "'}) RETURN b.id";
        return issueNeo4JRequestStrings(query2);
    }


    public List<String> getAssociatedNodeIdsAsDomain(String domainIndividual, CompObjectProperties compObjectProperties) throws Exception {
        String query2 = "MATCH (a {id:'" + domainIndividual + "'})-[r:" + compObjectProperties.toString() + "]->(b) RETURN b.id";
        return issueNeo4JRequestStrings(query2);
    }


    /**
     *
     * @param clazz
     * @return
     * @throws Exception
     */
    public List<String> getAllInstanceDefinitions(CompOntClass clazz) throws Exception {
        String query = "MATCH (a:" + clazz.name() + ") return a.name";
        ArrayList<String> result = issueNeo4JRequestStrings(query);
        return result;
    }

    /**
     *
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public List<String> getShortestSubClassPath(String start, String end) throws Exception {
        String query = "MATCH p=(a{id:'" + start + "'})-[r:subClassOf*]->(b{id:'" + end + "'}) return EXTRACT (n IN nodes(p)|n.definition) AS ids";
        return issueNeo4JRequestStrings(query);
    }

    /**
     *
     * @param start
     * @param end
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends Dao>  List<T> getShortestSubClassPath(String start, String end, Class<T> clazz) throws Exception {
        String query = "MATCH p=(a{id:'" + start + "'})-[r:subClassOf*]->(b{id:'" + end + "'}) return EXTRACT n IN nodes(p)";
        List<T> resultDaos = new ArrayList<T>();
        ArrayList<HashMap<String,String>> result = issueNeo4JRequestArrayOfHashMap(query);
        for (HashMap<String, String> stringStringHashMap : result) {
            resultDaos.add((T) clazz.newInstance().getFullDao(stringStringHashMap));
        }
        return resultDaos;
    }


    /**
     * @param subjectId
     * @param subjectDefinition
     * @param edge
     * @param objectId
     * @param objectDefinition
     */
    public void createRelationShip(String subjectId, String subjectDefinition, String edge, String objectId, String objectDefinition) throws Exception {
        String subjectQuery = "MERGE (a{id:'" + subjectId + "',subjectDefinition:'" + subjectDefinition + "'}) return a";
        String objectQuery = "MERGE (b{id:'" + objectId + "',subjectDefinition:'" + objectDefinition + "'}) return b";

        String relQuery = "MATCH (a{id:'" + subjectId + "',subjectDefinition:'" + subjectDefinition + "'})," +
                "MATCH (b{id:'" + objectId + "',subjectDefinition:'" + objectDefinition + "'})" +
                "CREATE UNIQUE (a)-[r:" + edge + "]->(b) " +
                "RETURN r";

        issueNeo4JRequestStrings(subjectQuery, objectQuery, relQuery);
    }

    /**
     * return the definition of a node if id is given
     *
     * @param id
     * @return
     */
    public String getDefinitionForClassForNode(String id) throws Exception {
        String query = "MATCH (a {id:'" + id + "'}) return a.definition";
        return issueNeo4JRequestStrings(query).iterator().next();
    }



    /**
     * removes a propety in a node
     *
     * @param id
     * @param prop
     * @throws Exception
     */
    public void removePropertyInNode(String id, String prop) throws Exception {
        String query = "MATCH (n { id: '" + id + "' }) REMOVE n." + prop + "  RETURN n";
        issueNeo4JRequestStrings(query);
    }

    /**
     * sets a propety in a node
     *
     * @param id
     * @param prop
     * @param value
     * @throws Exception
     */
    public void setPropertyInNode(String id, String prop, Object value) throws Exception {
        String query = "MATCH (n { id: '" + id + "' }) SET n." + prop + " = '" + value + "'  RETURN n";
        issueNeo4JRequestStrings(query);
    }

    public String getPropertyInNode(String id, String prop) throws Exception {
        String query = "MATCH (n { id: '" + id + "' })  RETURN n." + prop;
        if (issueNeo4JRequestStrings(query).isEmpty()) {
            throw new DataFieldNotInitializedException();
        }
        return issueNeo4JRequestStrings(query).get(0);
    }

    public Boolean exists(String id) throws Exception {
        String query = "MATCH (n{id:'" + id + "'}) return n";
        return !(issueNeo4JRequestStrings(query) == null || issueNeo4JRequestStrings(query).isEmpty());
    }

    public SelfAssessment getSelfAssessment(Competence competence, User user) throws Exception {
        String query = "MATCH (b:SelfCompetence)-[r1:AssessmentOfCompetence]->(a:Competence{id:'"+competence.getId()+"'}) MATCH (b)-[r2:AssessmentOfUser]->(c:User) return b";
        ArrayList<HashMap<String, String>> result = issueNeo4JRequestHashMap(query);
        HashMap<String, String> result2 = result.iterator().next();
        if (result2 == null) {
            return null;
        } else {
           return new SelfAssessment(result2.get("id")).getFullDao(result2);
        }
    }

    public StringList getAllSelectedLearningProjectTemplates(CourseContext courseContext, User user) {
        throw new NotImplementedException();
    }


    /**
     * returns a list in the form
     * 1 -> 2
     * 1 -> 3
     * 3 -> 4
     * 1 -> 3
     * []
     * []
     * @param label
     * @return
     */
    public List<ArrayList<String>> getSubClassTriples(String label) throws Exception {
        String query = "MATCH tree = (p:"+label+")-[:subClassOf*1..5]->(c:"+label+") return extract(n IN filter(x in nodes(tree) WHERE length(nodes(tree)) = 2)|n.id) ORDER BY length(tree)";
        return issueNeo4JRequestArrayListArrayList(query);
    }
}
