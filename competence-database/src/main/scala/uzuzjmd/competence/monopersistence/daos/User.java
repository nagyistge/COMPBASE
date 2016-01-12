package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.persistence.ontology.CompObjectProperties;

import java.util.List;

/**
 * Created by dehne on 11.01.2016.
 */
public class User extends AbstractUser {
    private Role role;
    

    public User(String id) {
        super(id);
    }

    public User(String id, Role role, List<CourseContext> courseContexts) {
        super(id);
        this.role = role;
        this.courseContexts = courseContexts;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return getId();
    }

    public Boolean hasCourseContext(CourseContext courseContext) throws Exception {
        return hasEdge(CompObjectProperties.belongsToCourseContext, courseContext);
    }

}
