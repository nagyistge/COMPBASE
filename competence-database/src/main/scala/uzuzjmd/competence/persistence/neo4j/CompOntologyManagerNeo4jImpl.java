package uzuzjmd.competence.persistence.neo4j;

import com.hp.hpl.jena.ontology.OntModel;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uzuzjmd.competence.persistence.abstractlayer.CompOntologyAccess;
import uzuzjmd.competence.persistence.abstractlayer.CompOntologyAccessFactory;
import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager;
import uzuzjmd.competence.persistence.abstractlayer.CompetenceQueries;

/**
 * Created by dehne on 11.12.2015.
 */
public class CompOntologyManagerNeo4jImpl implements CompOntologyManager {


    private final CompOntologyAccess jenaUtil;
    private final CompOntologyManager jenaManager;

    public CompOntologyManagerNeo4jImpl() {
        jenaUtil = CompOntologyAccessFactory.createJenaInMemoryAccessPoint();
        this.jenaManager = jenaUtil.getManager();
    }

    @Override
    public void startReasoning(Boolean debugOn) {
            //TODO implement transferring the jena inf-Model to the neo4j-BASE
        /*throw new NotImplementedException();*/
    }

    @Override
    public OntModel getM() {
        return jenaManager.getM();
    }

    @Override
    public CompOntologyAccess getUtil() {
        return new CompOntologyAccessNeo4jImpl();
    }

    @Override
    public CompetenceQueries getQueries() {
        return getUtil().getQueries();
    }

    @Override
    public void close() {
        jenaManager.close();
    }

    @Override
    public void commit() {
        jenaManager.commit();
    }

    @Override
    public void end() {
        jenaManager.end();
    }

    @Override
    public void begin() {
        jenaManager.begin();
    }

    @Override
    public void beginRead() {
        jenaManager.beginRead();
    }
}
