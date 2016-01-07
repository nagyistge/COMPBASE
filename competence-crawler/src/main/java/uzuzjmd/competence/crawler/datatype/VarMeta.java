package uzuzjmd.competence.crawler.datatype;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uzuzjmd.competence.crawler.neo4j.Neo4JConnector;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by carl on 06.01.16.
 */
public class VarMeta {
    private List<AbstractMap.SimpleEntry<String, DateScore>> elements;
    static private final Logger logger = LogManager.getLogger(VarMeta.class.getName());
    public VarMeta() {
        elements = new ArrayList<>();
    }

    public List<AbstractMap.SimpleEntry<String, DateScore>> getElements() {
        return elements;
    }

    public void addElement(String key, String var) {
        logger.info("addElement key:" + key + " var:" + var);
        elements.add(new AbstractMap.SimpleEntry<String, DateScore>(key, new DateScore(var)));
    }

    public void addElement(String key, DateScore ds) {
        logger.info("addElement key:" + key + " var:" + ds.toString());
        elements.add(new AbstractMap.SimpleEntry<String, DateScore>(key, ds));
    }



    public String[] toNeo4JQuery() {
        logger.debug("Entering toNeo4JQuery");
        String[] result = new String[elements.size()];
        int i = 0;
        for (AbstractMap.SimpleEntry key : elements) {
            DateScore ds = (DateScore) key.getValue();
            String str = Neo4JConnector.mergeRelation(new AbstractMap.SimpleEntry<String, String>("Variable", (String) key.getKey()),
                    new AbstractMap.SimpleEntry<String, String>("Meta", ds.metaVar),
                    "classOf");
            result[i] = str;
            i++;
        }
        logger.debug("Leaving toNeo4JQuery with query:" + Arrays.toString(result));
        return result;
    }
    public void setElements(List<AbstractMap.SimpleEntry<String, DateScore>> elements) {
        this.elements = elements;
    }
}
