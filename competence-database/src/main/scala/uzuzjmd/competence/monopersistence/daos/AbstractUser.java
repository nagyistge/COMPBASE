package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.persistence.ontology.CompObjectProperties;

import java.util.List;

/**
 * Created by dehne on 12.01.2016.
 */
public class AbstractUser extends DaoAbstractImpl {
    protected List<CourseContext> courseContexts;

    public AbstractUser(String id) {
        super(id);
    }

    public List<AbstractEvidenceLink> getAssociatedLinks() throws Exception {
        return getAssociatedDaosAsRange(CompObjectProperties.UserOfLink, AbstractEvidenceLink.class);
    }
}
