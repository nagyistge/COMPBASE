package uzuzjmd.competence.persistence.neo4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uzuzjmd.competence.config.MagicStrings;
import uzuzjmd.competence.monopersistence.daos.Dao;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

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

    protected ArrayList<HashMap<String, String>> issueNeo4JRequestArrayOfHashMap(final String... queries) throws Exception {
        return issueMultipleStatementRequest(new RequestableImpl<ArrayList<HashMap<String, String>>>() , queries);
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


    protected <T extends Dao> List<T> getDaoList(Class<T> clazz, String id, String query) throws Exception {
        ArrayList<HashMap<String, String>> result = issueNeo4JRequestHashMap(query);
        if (result == null || result.isEmpty()) {
            return new ArrayList<>();
        }
        ArrayList<T> resultDaos = new ArrayList<T>();
        getHashMap(id, clazz, result, resultDaos);
        return resultDaos;
    }

    public <T extends Dao> T getDao(String id, Class<T> clazz) throws Exception {
        String query = "MATCH (a{id:'"+id+"'}) return a";
        ArrayList<HashMap<String, String>> result = issueNeo4JRequestHashMap(query);
        ArrayList<T> resultDaos = new ArrayList<T>();
        getHashMap(id, clazz, result, resultDaos);
        return resultDaos.iterator().next();
    }

    private <T extends Dao> void getHashMap(String id, Class<T> clazz, ArrayList<HashMap<String, String>> result, ArrayList<T> resultDaos) throws InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        for (HashMap<String, String> stringStringHashMap : result) {
            HashMap<String, String> result2 = stringStringHashMap;
            if (result2 != null) {
                T tClass = clazz.getConstructor(String.class).newInstance(id);
                resultDaos.add((T) tClass.getFullDao(stringStringHashMap));
            }
        }
    }

    public <T extends Dao> List<T> listSuperClasses(Class<T> competenceClass, String id) throws Exception {
        String className = competenceClass.getSimpleName();
        String query = "MATCH z = (n:"+className+"{id:'"+id+"'})-[r:subClassOf*]->(p:"+className+") return filter(x IN nodes(z) WHERE NOT(x.id = n.id)) ";
        return getDaoList(competenceClass, id, query);
    }

    public <T extends Dao> List<T> listSubClasses(Class<T> competenceClass, String id) throws Exception {
        String className = competenceClass.getSimpleName();
        String query = "MATCH z = (n:"+className+")-[r:subClassOf*]->(p:"+className+"{id:'"+id+"'}) return filter(x IN nodes(z) WHERE NOT(x.id = p.id)) ";
        return getDaoList(competenceClass, id, query);
    }
}
