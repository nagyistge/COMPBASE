package uzuzjmd.competence.persistence.neo4j;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uzuzjmd.competence.config.MagicStrings;
import uzuzjmd.competence.monopersistence.daos.Dao;
import uzuzjmd.competence.persistence.neo4j.reasoning.RequestableImpl2;
import uzuzjmd.competence.shared.dto.UserTree;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by dehne on 07.01.2016.
 */
public abstract class Neo4JQueryManager  {
    static Logger logger = LogManager.getLogger(Neo4JQueryManager.class.getName());

    private static StatsHolder statsHolder = new StatsHolder();

    public static LoadingCache<String, ArrayList<String>> cacheImpl = CacheBuilder
            .newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(2l,TimeUnit.SECONDS)
            .build(new CacheLoader<String, ArrayList<String>>() {
                @Override
                public ArrayList<String> load(String key) throws Exception {
                    ArrayList<String> result = Neo4JQueryManager.issueSingleStatementRequest(new RequestableImpl<ArrayList<String>>(),key);
                    if (result == null) {
                        return new ArrayList<>();
                    }else {
                        return result;
                    }
                }
            });

    protected static <T> T issueSingleStatementRequest(Requestable<T> req, String query) throws Exception {
        statsHolder.requestHashMap.put(0, statsHolder.requestHashMap.get(0)+1);
        logger.debug(statsHolder.toString());
        logger.info(query);
        String payload = "{\"statements\" : [ {\"statement\" : \"" + query + "\"} ]}";
        return req.doRequest(payload);
    }

    private <T> T issueMultipleStatementRequest(Requestable<T> req, String... queries) throws Exception {
        statsHolder.requestHashMap.put(1, statsHolder.requestHashMap.get(1)+1);
        logger.debug(statsHolder.toString());
        String statements = "";
        for (int i = 0; i < queries.length; i++) {
            statements += "{\"statement\": \"" + queries[i] + "\"},";
        }
        statements = statements.substring(0, statements.length()-1);
        String payload = "{\"statements\" : [" + statements + "]}";
        return req.doRequest(payload);
    }

    protected ArrayList<String> issueNeo4JRequestStrings(final String payload) throws Exception {
        statsHolder.requestHashMap.put(2, statsHolder.requestHashMap.get(2)+1);
        logger.debug(statsHolder.toString());
        //return issueSingleStatementRequest(new RequestableImpl<ArrayList<String>>() , payload);
        return cacheImpl.get(payload);
    }

    protected ArrayList<HashMap<String, String>> issueNeo4JRequestHashMap(final String payload) throws Exception {
        statsHolder.requestHashMap.put(3, statsHolder.requestHashMap.get(3)+1);
        logger.debug(statsHolder.toString());
        return issueSingleStatementRequest(new RequestableImpl<ArrayList<HashMap<String, String>>>() , payload);
    }

    protected ArrayList<ArrayList<String>> issueNeo4JRequestArrayListArrayList(final String payload) throws Exception {
        statsHolder.requestHashMap.put(4, statsHolder.requestHashMap.get(4)+1);
        logger.debug(statsHolder.toString());
        return issueSingleStatementRequest(new RequestableImpl<ArrayList<ArrayList<String>>>() , payload);
    }

    protected ArrayList<String> issueNeo4JRequestStrings(final String... queries) throws Exception {
        statsHolder.requestHashMap.put(5, statsHolder.requestHashMap.get(5)+1);
        logger.debug(statsHolder.toString());
        return issueMultipleStatementRequest(new RequestableImpl<ArrayList<String>>() , queries);
    }

    protected ArrayList<HashMap<String, String>> issueNeo4JRequestArrayOfHashMap(final String... queries) throws Exception {
        statsHolder.requestHashMap.put(6, statsHolder.requestHashMap.get(6)+1);
        logger.debug(statsHolder.toString());
        return issueMultipleStatementRequest(new RequestableImpl2<ArrayList<HashMap<String, String>>>() , queries);
    }

    protected <T extends Dao> HashSet<T> getDaoList(Class<T> clazz, String query) throws Exception {
        ArrayList result = issueNeo4JRequestArrayOfHashMap(query);
        if (result == null || result.isEmpty()) {
            return new HashSet<>();
        }
        HashSet<T> resultDaos = new HashSet<>();
        getHashMap(clazz, result, resultDaos);
        return resultDaos;
    }

    public <T extends Dao> T getDao(String id, Class<T> clazz) throws Exception {
        statsHolder.requestHashMap.put(7, statsHolder.requestHashMap.get(7)+1);
        logger.debug(statsHolder.toString());
        String query = "MATCH (a{id:'"+id+"'}) return a";
        ArrayList result = issueNeo4JRequestHashMap(query);
        HashMap<String, String> hashMap = ((HashMap<String,String>)result.get(0));
        T tClass = clazz.getConstructor(String.class).newInstance(hashMap.get("id")).getFullDao(hashMap);
        return tClass;
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
        return getDaoList(competenceClass, query);
    }

    public <T extends Dao> Set<T> listSubClasses(Class<T> competenceClass, String id) throws Exception {
        String className = competenceClass.getSimpleName();
        String query = "MATCH z = (n:"+className+")-[r:subClassOf*]->(p:"+className+"{id:'"+id+"'}) return filter(x IN nodes(z) WHERE NOT(x.id = p.id)) ";
        return getDaoList(competenceClass, query);
    }
}
