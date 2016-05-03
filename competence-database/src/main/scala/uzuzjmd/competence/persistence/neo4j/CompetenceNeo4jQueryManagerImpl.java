package uzuzjmd.competence.persistence.neo4j;

import uzuzjmd.competence.exceptions.DataFieldNotInitializedException;
import uzuzjmd.competence.persistence.dao.*;
import uzuzjmd.competence.persistence.ontology.Edge;
import uzuzjmd.competence.persistence.ontology.Label;
import uzuzjmd.competence.service.rest.dto.CompetenceFilterData;

import java.util.*;

/**
 * Created by dehne on 24.02.2016.
 */
public class CompetenceNeo4jQueryManagerImpl extends CompetenceNeo4JQueryManager {

    public void createRelationShip(String domainId, Edge edge, String rangeId) throws Exception {
        String query = "MATCH (n {id:'" + domainId + "'}), (n2{id:'" + rangeId + "'}) CREATE UNIQUE (n)-[r:" + edge.toString() + "]->(n2) return n,r,n2";
        issueNeo4JRequestStrings(query);

    }

    public void createRelationShipWithWeight(String domainId, Edge edge, String rangeId, Double weight) throws Exception {
        logger.info("calling create relationship with" + domainId + " " + edge + " " + rangeId+ weight);
        String query = "MATCH (n {id:'" + domainId + "'}), (n2{id:'" + rangeId + "'}) CREATE UNIQUE (n)-[r:" + edge.toString() + "{weight:'"+weight+"'}]->(n2) return n,r,n2";
        issueNeo4JRequestStrings(query);
    }

    public ArrayList<String> getClosestEdges(String domainId, Edge edge) throws Exception {
        String query = "MATCH (n {id:'" + domainId + "'})-[r:" + edge.toString()+ "]->(n2)  return n2.id ORDER BY (r.weight) LIMIT 10";
        return issueNeo4JRequestStrings(query);
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
     * @param edge
     */
    public void deleteRelationShip(String domainId, String rangeId, Edge edge) throws Exception {
        String query = "MATCH (a{id:'" + domainId + "'})-[r:" + edge.toString() + "]->(b{id:'" + rangeId + "'}) DELETE r";
        issueNeo4JRequestStrings(query);
    }


    /**
     * @param domainId
     * @param rangeId
     * @param edge
     * @return
     */
    public Boolean existsRelationShip(String domainId, String rangeId, Edge edge) throws Exception {
        String query = "MATCH (a{id:'" + domainId + "'})-[r:" + edge.toString() + "]->(b{id:'" + rangeId + "'}) return r";
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
     * @param edge
     */
    public Boolean existsRelationShipWithSuperClassGiven(String domainClassNodeId, String rangeId, Edge edge) throws Exception {
        String query = "MATCH (a)-[r:individualOf]->(b{id:'" + domainClassNodeId + "'}), (a)-[r2:" + edge.toString() + "]->(c{id:'" + rangeId + "'}) return a";
        return existMatches(query);
    }


    public List<String> getAssociatedNodeIdsAsRange(Edge edge, String rangeIndividualId) throws Exception {
        String query2 = "MATCH (b)-[r:" + edge.toString() + "]->(a {id:'" + rangeIndividualId + "'}) RETURN b.id";
        return issueNeo4JRequestStrings(query2);
    }


    public List<String> getAssociatedNodeIdsAsDomain(String domainIndividual, Edge edge) throws Exception {
        String query2 = "MATCH (a {id:'" + domainIndividual + "'})-[r:" + edge.toString() + "]->(b) RETURN b.id";
        return issueNeo4JRequestStrings(query2);
    }


    /**
     * @param clazz
     * @return
     * @throws Exception
     */
    public List<String> getAllInstanceDefinitions(Label clazz) throws Exception {
        String query = "MATCH (a:" + clazz.name() + ") return a.id";
        ArrayList<String> result = issueNeo4JRequestStrings(query);
        return result;
    }

    /**
     * @param clazz
     * @return
     * @throws Exception
     */
    public <T extends Dao> Set<T> getAllCompetenceDaos(Label clazzLabel, Class<T> clazz) throws Exception {
        String query = "MATCH (a:" + clazzLabel.name() + ") return a.id";
        ArrayList<String> result = issueNeo4JRequestStrings(query);
        Set<T> result2 = new HashSet<>();
        for (String s : result) {
            HashMap<String, String> props = new HashMap<String, String>();
            props.put("id", s);
            result2.add((T) new Competence(s));
        }
        return result2;
    }

    /**
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
     * @param start
     * @param end
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends Dao> List<T> getShortestSubClassPath(String start, String end, Class<T> clazz) throws Exception {
        String query = "MATCH p=(a{id:'" + start + "'})-[r:subClassOf*]->(b{id:'" + end + "'}) return EXTRACT n IN nodes(p)";
        List<T> resultDaos = new ArrayList<T>();
        ArrayList<HashMap<String, String>> result = issueNeo4JRequestArrayOfHashMap(query);
        for (HashMap<String, String> stringStringHashMap : result) {
            resultDaos.add((T) clazz.newInstance().getFullDao(stringStringHashMap));
        }
        return resultDaos;
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
        String query = "MATCH (c:User{id:'" + user.getId() + "'}) MATCH (b:SelfAssessment)-[r1:AssessmentOfCompetence]->(a:Competence{id:'" + competence.getId() + "'}) MATCH (b)-[r2:AssessmentOfUser]->(c:User) return b";
        ArrayList<HashMap<String, String>> result = issueNeo4JRequestHashMap(query);
        if (result == null) {
            return new SelfAssessment(competence, user, 0, false);
        }
        HashMap<String, String> result2 = result.iterator().next();
        if (result2 == null) {
            return null;
        } else {
            return new SelfAssessment(result2.get("id")).getFullDao(result2);
        }
    }


    /**
     * returns a list in the form
     * 1 -> 2
     * 1 -> 3
     * 3 -> 4
     * 1 -> 3
     * []
     * []
     *
     * @param label
     * @return
     */
    public List<ArrayList<String>> getSubClassTriples(String label, CompetenceFilterData filterData) throws Exception {
        String courseId = filterData.getCourse();
        List<String> operators = filterData.getSelectedOperatorsArray();
        List<String> catchwords = filterData.getSelectedCatchwordArray();
        String futherMatches = "";
        for (String catchword : catchwords) {
            futherMatches += "MATCH (c:Catchword{id:'" + catchword + "'})-[r33:CatchwordOf]->(p)";
        }
        for (String operator : operators) {
            futherMatches += "MATCH (c:Operator{id:'" + operator + "'})-[r44:OperatorOf]->(p)";
        }
        if (filterData.getRootCompetence() != null) {
            futherMatches += "MATCH (p:" + label + ")-[:subClassOf*1..5]->(z:" + label + "{id:'" + filterData.getRootCompetence() + "'})";
        }
        String query = "MATCH tree = (p:" + label + ")-[:subClassOf*1..5]->(c:" + label + ")" + futherMatches + "MATCH (x:CourseContext{id:'" + courseId + "'})-[r33:CourseContextOfCompetence]->(p) return extract(n IN filter(x in nodes(tree) WHERE length(nodes(tree)) = 2)|n.id) ORDER BY length(tree) ";
        return issueNeo4JRequestArrayListArrayList(query);
    }

    public HashSet<Competence> getSuggestedCompetenceRequirements(String competenceId, Class<Competence> competenceClass, LearningProjectTemplate learningProject) throws Exception {
        String query = "MATCH (b:LearningProjectTemplate{id:'" + learningProject.getId() + "'})-[r1:LearningProjectTemplateOf]->(c) MATCH (c:Competence)-[r2:SuggestedCompetencePrerequisiteOf]->(a:Competence{id:'" + competenceId + "'}) return c.id";
        ArrayList<String> result = issueNeo4JRequestStrings(query);
        if (result == null || result.isEmpty()) {
            return new HashSet<>();
        }
        HashSet<Competence> result2 = new HashSet<>();
        for (String s : result) {
            result2.add(new Competence(s, learningProject));
        }
        return result2;
    }

    /**
     * a->b and all catchwords c where c->a and c->b
     *
     * @param learningTemplate
     * @return
     * @throws Exception
     */
    public List<ArrayList<String>> getPrerequisiteTriples(LearningProjectTemplate learningTemplate) throws Exception {
        String query = "MATCH (l:LearningProjectTemplate{id:'" + learningTemplate.getDefinition() + "'})-[r4:LearningProjectTemplateOf]->(a) MATCH (l)-[r5:LearningProjectTemplateOf]->(b) MATCH (a:Competence)-[r1:SuggestedCompetencePrerequisiteOf]->(b:Competence) MATCH (c)-[x22:CatchwordOf]->(a) MATCH (c)-[x33:CatchwordOf]->(b) return a.id, b.id, c.id";
        return issueNeo4JRequestArrayListArrayList(query);
    }


    /**
     * TODO: check if order is preserved
     *
     * @param fromNode
     * @param toNode
     * @param suggestedCompetencePrerequisiteOf
     * @param returnType
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends Dao> HashSet<T> getShortestPath(String fromNode, String toNode, Edge suggestedCompetencePrerequisiteOf, Class<T> returnType) throws Exception {
        String query =  "MATCH p = shortestPath((a:"+returnType.getSimpleName()+"{id:'"+fromNode+"'})-[:"+suggestedCompetencePrerequisiteOf+"*]->(b:"+returnType.getSimpleName()+"{id:'"+toNode+"'})) return nodes(p)";
        return getDaoList(returnType, query);
    }
}
