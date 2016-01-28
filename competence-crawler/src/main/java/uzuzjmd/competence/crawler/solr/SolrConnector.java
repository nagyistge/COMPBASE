package uzuzjmd.competence.crawler.solr;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by carl on 07.01.16.
 */
public class SolrConnector {
    static private final Logger logger = LogManager.getLogger(SolrConnector.class.getName());
    private SolrClient client ;
    private final String serverUrl;

    public SolrConnector(String serverUrl) {
        logger.debug("Entering SolrConnector Constructor with serverUrl:" + serverUrl);
        this.serverUrl = serverUrl;
        client = new HttpSolrClient(serverUrl);
        logger.debug("Leaving SolrConnector Constructor");
    }
    public QueryResponse connectToSolr(String query) throws IOException, SolrServerException {
        logger.debug("Entering connectToSolr with query:" + query);
        SolrQuery solrQuery = new SolrQuery(query);
        solrQuery.set("indent", "true");
        solrQuery.set("rows", 10000);
        solrQuery.setFields("id", "content", "title", "score", "url", "pageDepth");
        solrQuery.set("wt", "json");

        QueryResponse response = client.query(solrQuery);
        SolrDocumentList docs = response.getResults();
        logger.info("Quantitiv Result:" + String.valueOf(docs.getNumFound()));
        logger.debug("Leaving connectToSolr");
        return response;

    }

    public String getServerUrl(){
        return serverUrl;
    }
    /*
    private LinkedHashMap<String, ArrayList<LinkedHashMap<String, ArrayList<LinkedHashMap<String, ArrayList<String>>>>>> issueAbstractRequest(String payload) {
        Client client2 = ClientBuilder.newClient();
        WebTarget target2 = client2.target(txUri);
        return target2.request(
                MediaType.APPLICATION_JSON).post(
                Entity.entity(payload,
                        MediaType.APPLICATION_JSON), LinkedHashMap.class);
    }
    */
}
