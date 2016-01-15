package uzuzjmd.competence.persistence.neo4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uzuzjmd.competence.config.MagicStrings;
import uzuzjmd.competence.monopersistence.daos.Dao;
import uzuzjmd.competence.persistence.neo4j.reasoning.RequestableImpl2;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * Created by dehne on 07.01.2016.
 */
public abstract class Neo4JQueryManager  {
    static Logger logger = LogManager.getLogger(Neo4JQueryManager.class.getName());
    final String txUri = MagicStrings.NEO4JURL + "/db/data/transaction/commit";

    protected <T> T issueSingleStatementRequest(Requestable<T> req, String query) throws Exception {
        logger.info(query);
        String payload = "{\"statements\" : [ {\"statement\" : \"" + query + "\"} ]}";
        return req.doRequest(payload);
    }

    private <T> T issueMultipleStatementRequest(Requestable<T> req, String... queries) throws Exception {
        String statements = "";
        for (int i = 0; i < queries.length; i++) {
            statements += "{\"statement\": \"" + queries[i] + "\"},";
        }
        statements = statements.substring(0, statements.length()-1);
        String payload = "{\"statements\" : [" + statements + "]}";
        return req.doRequest(payload);
    }

    protected ArrayList<String> issueNeo4JRequestStrings(final String payload) throws Exception {
        return issueSingleStatementRequest(new RequestableImpl<ArrayList<String>>() , payload);
    }

    protected ArrayList<HashMap<String, String>> issueNeo4JRequestHashMap(final String payload) throws Exception {
        return issueSingleStatementRequest(new RequestableImpl<ArrayList<HashMap<String, String>>>() , payload);
    }

    protected ArrayList<ArrayList<String>> issueNeo4JRequestArrayListArrayList(final String payload) throws Exception {
        return issueSingleStatementRequest(new RequestableImpl<ArrayList<ArrayList<String>>>() , payload);
    }

    protected ArrayList<String> issueNeo4JRequestStrings(final String... queries) throws Exception {
        return issueMultipleStatementRequest(new RequestableImpl<ArrayList<String>>() , queries);
    }

  /*  protected ArrayList<HashMap<String, String>> issueNeo4JRequestArrayOfHashMap(final String... queries) throws Exception {
        return issueMultipleStatementRequest(new RequestableImpl<ArrayList<HashMap<String, String>>>() , queries);
    }*/

    protected ArrayList<HashMap<String, String>> issueNeo4JRequestArrayOfHashMap(final String... queries) throws Exception {
        return issueMultipleStatementRequest(new RequestableImpl2<ArrayList<HashMap<String, String>>>() , queries);
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


    protected <T extends Dao> HashSet<T> getDaoList(Class<T> clazz, String id, String query) throws Exception {
        ArrayList result = issueNeo4JRequestArrayOfHashMap(query);
        if (result == null || result.isEmpty()) {
            return new HashSet<>();
        }
        HashSet<T> resultDaos = new HashSet<>();
        getHashMap(clazz, result, resultDaos);
        return resultDaos;
    }

    public <T extends Dao> T getDao(String id, Class<T> clazz) throws Exception {
        String query = "MATCH (a{id:'"+id+"'}) return a";
        ArrayList result = issueNeo4JRequestHashMap(query);
        HashSet<T> resultDaos = new HashSet<>();
        getHashMap(clazz, result, resultDaos);
        return resultDaos.iterator().next();
    }

    private <T extends Dao> void getHashMap(Class<T> clazz, ArrayList<ArrayList<HashMap<String, String>>> result, HashSet<T> resultDaos) throws InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        for (ArrayList<HashMap<String, String>> hashMaps : result) {
            for (HashMap<String, String> hashMap : hashMaps) {
                if (hashMap != null) {
                    T tClass = clazz.getConstructor(String.class).newInstance(hashMap.get("id"));
                    resultDaos.add((T) tClass.getFullDao(hashMap));
                }
            }
        }
    }

    public <T extends Dao> Set<T> listSuperClasses(Class<T> competenceClass, String id) throws Exception {
        String className = competenceClass.getSimpleName();
        String query = "MATCH z = (n:"+className+"{id:'"+id+"'})-[r:subClassOf*]->(p:"+className+") return filter(x IN nodes(z) WHERE NOT(x.id = n.id)) ";
        return getDaoList(competenceClass, id, query);
    }

    public <T extends Dao> HashSet<T> listSubClasses(Class<T> competenceClass, String id) throws Exception {
        String className = competenceClass.getSimpleName();
        String query = "MATCH z = (n:"+className+")-[r:subClassOf*]->(p:"+className+"{id:'"+id+"'}) return filter(x IN nodes(z) WHERE NOT(x.id = p.id)) ";
        return getDaoList(competenceClass, id, query);
    }
}
