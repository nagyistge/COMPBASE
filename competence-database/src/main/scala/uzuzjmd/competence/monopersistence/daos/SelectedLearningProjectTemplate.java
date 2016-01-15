package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.persistence.neo4j.Neo4JQueryManagerImpl;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;
import uzuzjmd.competence.shared.StringList;

/**
 * Created by dehne on 11.01.2016.
 */
public class SelectedLearningProjectTemplate extends AbstractSelectedLearningProjectTemplate implements Cascadable{


    public SelectedLearningProjectTemplate(String id) {
        super(id);
    }

    public SelectedLearningProjectTemplate(User associatedUser, CourseContext associatedCourse, LearningProjectTemplate selectedLearningProjectTemplate) {
        super(associatedUser.getId() + associatedCourse.getId() + selectedLearningProjectTemplate.getId());
        this.associatedUser = associatedUser;
        this.associatedCourse = associatedCourse;
        this.associatedTemplate = selectedLearningProjectTemplate;
    }

    public SelectedLearningProjectTemplate(User associatedUser, CourseContext associatedCourse, String selectedLearningProjectTemplate) {
        super(associatedUser.getId() + associatedCourse.getId() + selectedLearningProjectTemplate);
        this.associatedUser = associatedUser;
        this.associatedCourse = associatedCourse;
        this.associatedTemplate = new LearningProjectTemplate(selectedLearningProjectTemplate);
    }


    @Override
    public void persistMore() throws Exception {
        this.persist();

        if (associatedCourse != null) {
            createEdgeWith(associatedCourse, CompObjectProperties.CourseContextOfSelectedLearningProjectTemplate);
        }

        if (associatedUser != null) {
            createEdgeWith(associatedUser, CompObjectProperties.UserOfSelectedLearningProjectTemplate);
        }

        if (associatedTemplate != null ) {
            createEdgeWith(CompObjectProperties.SelectedTemplateOfLearningTemplate, associatedTemplate);
        }
    }

    public static StringList getAllSelectedLearningProjectTemplates(User user, CourseContext courseContext) {
        Neo4JQueryManagerImpl manager = new Neo4JQueryManagerImpl();
        return manager.getAllSelectedLearningProjectTemplates(courseContext, user);
    }


}
