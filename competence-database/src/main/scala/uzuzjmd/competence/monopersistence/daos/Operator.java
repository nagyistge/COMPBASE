package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.persistence.ontology.CompObjectProperties;

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

    @Override
    public Dao persist() throws Exception {
        super.persist();
        createEdgeWith(CompObjectProperties.subClassOf, new Operator(DBInitializer.OPERATORROOT));
        return this;
    }
}
