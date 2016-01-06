package uzuzjmd.competence.crawler.Datatype;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
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

    public void setElements(List<AbstractMap.SimpleEntry<String, DateScore>> elements) {
        this.elements = elements;
    }
}
