package uzuzjmd.competence.crawler.datatype;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uzuzjmd.competence.crawler.neo4j.Neo4JConnector;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by carl on 06.01.16.
 */
public class StichwortVar {
    private HashMap<String, String> elements;
    static private final Logger logger = LogManager.getLogger(StichwortVar.class.getName());

    public StichwortVar() {
        elements = new HashMap<>();
    }

    public HashMap<String, String> getElements() {
        return elements;
    }

    public void addElement(String key, String var) {
        if (elements.containsKey(key) ) {
            if (!elements.get(key).equals(var)) {
                logger.warn("Key " + key + "exists but there was written another var. Old: "
                        + elements.get(key) + " New: " + var + " Will overwrite to new");
            }
        }
        logger.info("addElement key:" + key + " var:" + var);
        elements.put(key, var);
    }

    public String toSolrQuery() {
        logger.debug("Entering toSolrQuery");
        String q = "";

        for (String key : elements.keySet()) {
            q += "\"" + key + "\"+" ;
        }
        q = q.substring(0, q.length() - 1);
        logger.debug("Leaving toSolrQuery with Query:" + q);
        return q;
    }

    public String toSolrQuery(String... keys) {
        logger.debug("Entering toSolrQuery with key:" + keys.length);
        String q = "";
        for (String key : keys) {
            q += "\"" + key + "\"+" ;
        }
        q = q.substring(0, q.length() - 1);
        logger.debug("Leaving toSolrQuery with Query:" + q);
        return q;
    }

    public String[] toNeo4JQuery() {
        logger.debug("Entering toNeo4JQuery");
        String[] result = new String[elements.size()];
        int i = 0;
        for (String key : elements.keySet()) {
            String str = Neo4JConnector.mergeRelation(new AbstractMap.SimpleEntry<String, String>("Stichwort", key),
                    new AbstractMap.SimpleEntry<String, String>("Variable", elements.get(key)),
                    "classTo");
            result[i] = str;
            i++;
        }
        logger.debug("Leaving toNeo4JQuery with query:" + Arrays.toString(result));
        return result;
    }

    public boolean containsKey(String key) {
        return elements.containsKey(key);
    }
    public void setVarList(HashMap<String, String> elements) {
        this.elements = elements;
    }
}
