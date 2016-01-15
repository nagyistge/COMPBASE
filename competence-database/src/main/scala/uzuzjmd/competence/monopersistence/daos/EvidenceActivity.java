package uzuzjmd.competence.monopersistence.daos;

/**
 * Created by dehne on 11.01.2016.
 */
public class EvidenceActivity extends DaoAbstractImpl {

    public String printableName;

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
