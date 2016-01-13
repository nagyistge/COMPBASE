package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.monopersistence.DaoAbstractImpl;
import uzuzjmd.competence.monopersistence.HasDefinition;

/**
 * Created by dehne on 11.01.2016.
 */
public class Operator extends DaoAbstractImpl implements HasDefinition {
    public Operator(String id) {
        super(id);
    }

    @Override
    public String getDefinition() {
        return this.getId();
    }
}
