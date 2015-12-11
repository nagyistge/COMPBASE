package uzuzjmd.competence.persistence.abstractlayer;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import uzuzjmd.competence.neo4j.CompOntologyAccessNeo4jImpl;
import uzuzjmd.competence.persistence.owl.CompOntologyAccessJenaImpl;
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl;
import uzuzjmd.competence.persistence.owl.CompetenceQueriesJenaImpl;

/**
 * Created by dehne on 10.12.2015.
 */
public class CompOntologyAccessFactory {
    public static CompOntologyAccess createNeo4jAccessPoint() {
        return new CompOntologyAccessNeo4jImpl();
    }

    public static CompOntologyAccess createJenaTDBAccessPoint() {
        CompOntologyManagerJenaImpl manager = new CompOntologyManagerJenaImpl();
        CompOntologyAccessJenaImpl ontologyAccessJena = new CompOntologyAccessJenaImpl(manager.getM(), new CompetenceQueriesJenaImpl(manager.getM()),manager);
        return ontologyAccessJena;
    }

    public static CompOntologyAccess createJenaInMemoryAccessPoint() {
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
        CompetenceQueries competenceQueries = new CompetenceQueriesJenaImpl(model);
        CompOntologyManagerJenaImpl jenaManager = new CompOntologyManagerJenaImpl();
        CompOntologyAccessJenaImpl util = new CompOntologyAccessJenaImpl(model, competenceQueries, jenaManager);
        jenaManager.setM(model);
        return util;
    }
}
