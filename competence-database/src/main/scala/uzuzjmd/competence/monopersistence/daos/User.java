package uzuzjmd.competence.monopersistence.daos;

import com.google.common.collect.Lists;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;

import java.util.List;

/**
 * Created by dehne on 11.01.2016.
 */
public class User extends AbstractUser {
    public Role role;
    public User(String id) {
        super(id);
    }

    public User(String id, Role role, CourseContext ... courseContexts) {
        super(id);
        this.role = role;
        this.courseContexts = Lists.newArrayList(courseContexts);
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
