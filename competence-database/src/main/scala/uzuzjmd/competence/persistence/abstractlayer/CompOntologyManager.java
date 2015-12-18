package uzuzjmd.competence.persistence.abstractlayer;

import com.hp.hpl.jena.ontology.OntModel;

/**
 * Created by dehne on 10.12.2015.
 */
public interface CompOntologyManager {
    void startReasoning(Boolean debugOn);

    OntModel getM();

    CompOntologyAccess getUtil();

    CompetenceQueries getQueries();

    @Deprecated
    void close();

    void commit();

    void end();

    void beginWrite();

    void beginRead();
}
