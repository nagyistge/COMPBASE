package uzuzjmd.competence.crawler.Datatype;

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

    public int stichwortVarSize() {
        return stichwortVar.getElements().size();
    }

    public int varMetaSize() {
        return varMeta.getElements().size();
    }
}
