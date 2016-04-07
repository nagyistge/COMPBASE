package uzuzjmd.competence.persistence.dao;

import uzuzjmd.competence.persistence.ontology.Edge;

import java.util.List;

/**
 * Created by dehne on 12.01.2016.
 */
public class AbstractUser extends DaoAbstractImpl {
    protected List<CourseContext> courseContexts;
    protected List<Competence> competencesLearned;



    public AbstractUser(String id) {
        super(id);
    }

    public List<AbstractEvidenceLink> getAssociatedLinks() throws Exception {
        return getAssociatedDaosAsRange(Edge.UserOfLink, AbstractEvidenceLink.class);
    }

    public List<Competence> getCompetencesLearned() throws Exception {
        if (competencesLearned == null) {
            competencesLearned =  getAssociatedDaosAsDomain(Edge.UserHasPerformed, Competence.class);
        }
        return competencesLearned;
    }
}
