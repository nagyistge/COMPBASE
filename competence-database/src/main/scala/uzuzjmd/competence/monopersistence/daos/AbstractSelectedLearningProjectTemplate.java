package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.persistence.ontology.CompObjectProperties;

import java.util.List;

/**
 * Created by dehne on 12.01.2016.
 */
public class AbstractSelectedLearningProjectTemplate extends DaoAbstractImpl {
    protected User associatedUser;
    protected CourseContext associatedCourse;
    protected LearningProjectTemplate associatedTemplate;

    public AbstractSelectedLearningProjectTemplate(String id) {
        super(id);
    }

    public LearningProjectTemplate getAssociatedTemplate() {
        return associatedTemplate;
    }

    public CourseContext getAssociatedCourse() throws Exception {
        if (this.associatedCourse == null) {
            return getAssociatedDaoAsDomain(CompObjectProperties.CourseContextOfSelectedLearningProjectTemplate, CourseContext.class);
        } else {
            return this.associatedCourse;
        }
    }

    public User getAssociatedUser() throws Exception {
        return getAssociatedDaoAsDomain(CompObjectProperties.UserOfSelectedLearningProjectTemplate, User.class);
    }

    public List<LearningProjectTemplate> getAssociatedTemplates() throws Exception {
        return getAssociatedDaosAsRange(CompObjectProperties.SelectedTemplateOfLearningTemplate, LearningProjectTemplate.class);
    }
}
