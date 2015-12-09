package uzuzjmd.competence.owl.access;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uzuzjmd.competence.owl.abstractlayer.CompOntologyAccess;
import uzuzjmd.competence.owl.queries.CompetenceQueries;

/**
 * Created by carl on 09.12.15.
 */
abstract public class CompOntologyAccessGenericImpl  implements CompOntologyAccess {

    /**
     * init Logger
     */
    public static final Logger logger = LogManager
            .getLogger(CompOntologyAccess.class.getName());
    protected CompFileUtil fileUtil;

    protected CompOntologyManager manager;
    protected CompetenceQueries queries;

    @Override
    public CompOntologyManager getManager() {
        return manager;
    }

    public abstract Model getModel();
}
