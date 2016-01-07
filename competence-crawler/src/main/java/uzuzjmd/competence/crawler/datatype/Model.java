package uzuzjmd.competence.crawler.datatype;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Created by carl on 06.01.16.
 */
public class Model {
    static private final Logger logger = LogManager.getLogger(Model.class.getName());
    private StichwortVar stichwortVar;
    private VarMeta varMeta;

   public Model() {
       stichwortVar = new StichwortVar();
       varMeta = new VarMeta();
   }

    public void addDate(String stichwort, String variable, String[] metas){
        logger.debug("Entering addDate with stichwort " + stichwort + " variable " + variable
            + " metas " + Arrays.toString(metas));
        if (stichwort.length() > 0) {
            stichwortVar.addElement(stichwort, variable);
        }
        for (int i = 0; i < metas.length; i++) {
            varMeta.addElement(variable, metas[i]);
        }
        logger.debug("Leaving addDate");
    }

    public String[] toNeo4JQuery() {
        logger.debug("Entering toNeo4JQuery");
        String[] result = (String[]) ArrayUtils.addAll(stichwortVar.toNeo4JQuery(), varMeta.toNeo4JQuery());
        logger.debug("Leaving toNeo4JQuery");
        return result;
    }

    public String[] toNeo4JQueryStichVar() {
        return stichwortVar.toNeo4JQuery();
    }
    public String[] toNeo4JQueryVarMeta() {
        return varMeta.toNeo4JQuery();
    }

    public String deleteModelInNeo4J() {
        return "MATCH (n:Stichwort),(m:Meta),(o:Variable) DETACH DELETE m,n,o";
    }

    public int stichwortVarSize() {
        return stichwortVar.getElements().size();
    }

    public int varMetaSize() {
        return varMeta.getElements().size();
    }
}
