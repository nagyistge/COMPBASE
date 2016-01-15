package uzuzjmd.competence.monopersistence.daos;

import uzuzjmd.competence.persistence.ontology.CompObjectProperties;

/**
 * Created by dehne on 11.01.2016.
 */
public class Catchword extends DaoAbstractImpl implements HasDefinition {

    public Catchword(String id) {
        super(id);
    }

    @Override
    public String getDefinition() {
        return this.getId();
    }

    @Override
    public void persist() throws Exception {
        super.persist();
        createEdgeWith(CompObjectProperties.subClassOf, new Catchword(DBInitializer.CATCHWORDROOT));
    }
}
