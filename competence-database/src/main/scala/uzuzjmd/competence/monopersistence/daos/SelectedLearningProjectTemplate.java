package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.monopersistence.DaoAbstractImpl;

/**
 * Created by dehne on 11.01.2016.
 */
public class SelectedLearningProjectTemplate extends DaoAbstractImpl{

    private User associatedUser;
    private CourseContext associatedCourse;


    public SelectedLearningProjectTemplate(String id) {
        super(id);
    }
}
