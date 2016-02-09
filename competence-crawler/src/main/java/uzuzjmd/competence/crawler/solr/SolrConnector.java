package uzuzjmd.competence.crawler.solr;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.PhraseQuery;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by carl on 07.01.16.
 */
public class SolrConnector {
    static private final Logger logger = LogManager.getLogger(SolrConnector.class.getName());
    private SolrClient client ;
    private final String serverUrl;
    private static final int LIMIT=1000000;

    public SolrConnector(String serverUrl) {
        logger.debug("Entering SolrConnector Constructor with serverUrl:" + serverUrl);
        this.serverUrl = serverUrl;
        client = new HttpSolrClient(serverUrl);
        logger.debug("Leaving SolrConnector Constructor");
    }

    static public int getLimit() {
        return LIMIT;
    }
    public QueryResponse connectToSolr(String query) throws IOException, SolrServerException {
        //query = "content\"" + StringUtils.join(query.split(" "), "\" AND \"") + "\"";
        logger.debug("Entering connectToSolr with query:" + query);
        SolrQuery solrQuery = new SolrQuery("content:\"" + query + "\"");
        solrQuery.set("indent", "true");
        solrQuery.set("rows", LIMIT);
        solrQuery.setFields("id", "content", "title", "score", "url", "pageDepth");
        solrQuery.set("wt", "json");
        if (query.toLowerCase().contains("forschendes lernen")) {
            solrQuery.set("defType", "edismax");
            solrQuery.set("qs", "10");
        }

        QueryResponse response = client.query(solrQuery);
        SolrDocumentList docs = response.getResults();
        logger.info("Quantitiv Result from \"" + query + "\":" + String.valueOf(docs.getNumFound()));
        if (docs.getNumFound() > LIMIT) {
            logger.warn("Limit Exceeded. Found more Docs in solr than queried. Increase the LIMIT if you want to get more Docs");
        }
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
