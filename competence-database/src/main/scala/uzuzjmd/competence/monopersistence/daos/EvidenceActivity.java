package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.monopersistence.DaoAbstractImpl;
import uzuzjmd.competence.monopersistence.HasDefinition;

/**
 * Created by dehne on 11.01.2016.
 */
public class EvidenceActivity extends DaoAbstractImpl {

    private String printableName;

    public EvidenceActivity(String id) {
        super(id);
    }

    public EvidenceActivity(String id, String printableName) {
        super(id);
        this.printableName = printableName;
    }

    public String getUrl() {
        return this.getId();
    }

    public String getPrintableName() {
        return printableName;
    }


}
