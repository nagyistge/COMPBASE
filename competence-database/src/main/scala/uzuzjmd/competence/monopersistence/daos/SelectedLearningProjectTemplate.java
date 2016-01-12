package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.monopersistence.Cascadable;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;

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


}
