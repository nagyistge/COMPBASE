package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.persistence.ontology.CompObjectProperties;
import uzuzjmd.competence.persistence.ontology.Contexts;

import java.util.List;

/**
 * Created by dehne on 11.01.2016.
 */
public class CourseContext extends DaoAbstractImpl {

    public String requirement;

    public CourseContext(String id) {
        super(id);
        this. requirement = "";
    }

    public CourseContext(String id, String requirement) {
        super(id);
        this.requirement = requirement;
    }

    public CourseContext(Contexts context) {
        super(context.name());
    }

    public List<Competence> getLinkedCompetences() throws Exception {
        return getAssociatedDaosAsDomain(CompObjectProperties.CourseContextOf, Competence.class);
    }


    public List<User> getLinkedUser() throws Exception {
        return getAssociatedDaosAsDomain(CompObjectProperties.belongsToCourseContext, User.class);
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    @Override
    public CourseContext getFullDao() throws Exception {
        return super.getFullDao();
    }
}
