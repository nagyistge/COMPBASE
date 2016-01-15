package uzuzjmd.competence.persistence.neo4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uzuzjmd.competence.config.MagicStrings;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.transform.Result;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by carl on 16.12.15.
 */
public class RequestableImpl<T> implements Requestable<T>{

    final String txUri = MagicStrings.NEO4JURL + "/db/data/transaction/commit";
    static Logger logger = LogManager.getLogger(RequestableImpl.class.getName());

    @Override
    public T doRequest(String queryType) throws Exception {
        logger.debug("Entering doRequest with queryType:" + queryType);
         LinkedHashMap<String, ArrayList<LinkedHashMap<String, ArrayList<LinkedHashMap<String, T>>>>> result = getLinkedHashMapFromRest(queryType);

        if (!result.get("errors").isEmpty()) {
            logger.error(result.get("errors").get(0).toString());
            throw new Exception(result.get("errors").get(0).toString());
        }
        logger.debug("Leaving doRequest");
        if (result.get("results").get(0).get("data").isEmpty()) {
            return null;
        } else {
            return extractValues(result);
        }
    }

    protected T extractValues(LinkedHashMap<String, ArrayList<LinkedHashMap<String, ArrayList<LinkedHashMap<String, T>>>>> result) {
        return result.get("results").get(0).get("data").get(0).get("row");
    }

    protected LinkedHashMap<String, ArrayList<LinkedHashMap<String, ArrayList<LinkedHashMap<String, T>>>>> getLinkedHashMapFromRest(String payload) {
        Client client2 = ClientBuilder.newClient();
        WebTarget target2 = client2.target(txUri);
        return target2.request(
                MediaType.APPLICATION_JSON).post(
                Entity.entity(payload,
                        MediaType.APPLICATION_JSON), LinkedHashMap.class);
    }
}
