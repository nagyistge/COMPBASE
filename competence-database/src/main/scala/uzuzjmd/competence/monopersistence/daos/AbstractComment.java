package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.monopersistence.DaoAbstractImpl;

/**
 * Created by fides-WHK on 13.01.2016.
 */
public class AbstractComment extends DaoAbstractImpl {
    protected User creator;

    public AbstractComment(String id) {
        super(id);
    }

    public User getCreator() {
        return creator;
    }
}
