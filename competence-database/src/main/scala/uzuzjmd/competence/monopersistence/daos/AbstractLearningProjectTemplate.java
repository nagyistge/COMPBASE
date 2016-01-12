package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.monopersistence.DaoAbstractImpl;

import java.util.List;

/**
 * Created by dehne on 12.01.2016.
 */
public class AbstractLearningProjectTemplate extends DaoAbstractImpl {
    protected List<Competence> associatedCompetences;

    public AbstractLearningProjectTemplate(String id) {
        super(id);
    }
}
