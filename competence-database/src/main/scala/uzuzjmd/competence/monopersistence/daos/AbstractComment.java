package uzuzjmd.competence.monopersistence.daos;

/**
 * Created by fides-WHK on 13.01.2016.
 */
public class AbstractComment extends DaoAbstractImpl {
    public User creator;

    public AbstractComment(String id) {
        super(id);
    }

    public User getCreator() {
        return creator;
    }
}
