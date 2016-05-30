package uzuzjmd.competence.persistence.dao;

import uzuzjmd.competence.persistence.ontology.Edge;
import uzuzjmd.competence.persistence.ontology.Contexts;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dehne on 11.01.2016.
 */
public class CourseContext extends DaoAbstractImpl {

    public String requirement;
    public String printableName;

    public CourseContext(String id) {
        super(id);
        this. requirement = "";
    }

    public CourseContext(String id, String printableName) {
        super(id);
        this.requirement = "";
        this.printableName = printableName;
    }

    public CourseContext(String id, String printableName, String requirement) {
        super(id);
        this.printableName = printableName;
        this.requirement = requirement;
    }

    public CourseContext(Contexts context) {
        super(context.name());
    }

    public List<Competence> getLinkedCompetences() throws Exception {
        List<Competence> result = getAssociatedDaosAsDomain(Edge.CourseContextOfCompetence, Competence.class);
        if (result == null) {
            return new LinkedList<>();
        }
        return result;
    }


    public List<User> getLinkedUser() throws Exception {
        return getAssociatedDaosAsDomain(Edge.CourseContextOfUser, User.class);
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

    public String getPrintableName() {
        return this.printableName;
    }
}
