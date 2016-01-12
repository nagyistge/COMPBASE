package uzuzjmd.competence.persistence.neo4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uzuzjmd.competence.config.MagicStrings;
import uzuzjmd.competence.monopersistence.Dao;
import uzuzjmd.competence.persistence.abstractlayer.CompetenceQueries;

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


    protected <T extends Dao> List<T> getDaoList(Class<T> competenceClass, String id, String query) throws Exception {
        ArrayList<HashMap<String, String>> result = issueNeo4JRequestHashMap(query);
        ArrayList<T> resultDaos = new ArrayList<T>();
        for (HashMap<String, String> stringStringHashMap : result) {
            HashMap<String, String> result2 = stringStringHashMap;
            if (result2 != null) {
                T tClass = competenceClass.getConstructor(String.class).newInstance(id);
                resultDaos.add((T) tClass.getFullDao(stringStringHashMap));
            }
        }
        return resultDaos;
    }
}
