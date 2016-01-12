package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.monopersistence.DaoAbstractImpl;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;

import java.util.List;

/**
 * Created by dehne on 11.01.2016.
 */
public class CourseContext extends DaoAbstractImpl {
    public CourseContext(String id) {
        super(id);
    }

    public List<Competence> getLinkedCompetences() throws Exception {
        return getAssociatedDaosAsRange(CompObjectProperties.CourseContextOf, Competence.class);
    }

    public List<User> getLinkedUser() throws Exception {
        return getAssociatedDaosAsDomain(CompObjectProperties.belongsToCourseContext, User.class);
    }
}
