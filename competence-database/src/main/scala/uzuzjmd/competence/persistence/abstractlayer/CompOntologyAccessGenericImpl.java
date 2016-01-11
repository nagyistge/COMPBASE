package uzuzjmd.competence.persistence.abstractlayer;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uzuzjmd.competence.persistence.ontology.CompOntClass;
import uzuzjmd.competence.persistence.owl.CompFileUtil;
import uzuzjmd.competence.persistence.owl.CompOntologyAccessScala;


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

    protected CompetenceQueries queries;





    /**
     * create id from definition string
     * @param string
     * @return
     */
    protected String encode(String string) {
        return CompOntologyAccessScala.encode(string);
    }


    @Override
    public OntClass getClass(CompOntClass compOntClass,
                             Boolean isRead) {
        return createOntClassForString(compOntClass.name(),
                isRead);
    }


    /**
     * SingletonInstances always have a class and a corresponding individual node.
     *
     * Here both are read or written simultaneously.
     * @param classname
     * @param isRead
     * @param definitions
     * @return
     */
    @Override
    public OntResult accessSingletonResource(
            String classname, Boolean isRead,
            String... definitions) {

        if (classname == null) {
            throw new Error("42");
        }

        if (classname.startsWith("I")) {
            logger.trace("trying to get SingletonRessource but Id given (including prefix I) instead of definition");
        }

        OntClass classOnt = createOntClassForString(
                classname, isRead, definitions);
        Individual individual = createSingleTonIndividual(
                classOnt, isRead);

        return new OntResult(individual, classOnt);
    }

    /**
     *
     * SingletonInstances always have a class and a corresponding individual node.
     *
     * Here both are read or written simultaneously.
     *
     * @param compOntClass
     * @param isRead
     * @return
     */
    @Override
    public OntResult accessSingletonResourceWithClass(
            CompOntClass compOntClass, Boolean isRead) {
        OntClass classOnt = createOntClass(compOntClass,
                isRead);
        Individual individual = createSingleTonIndividual(
                classOnt, isRead);
        return new OntResult(individual, classOnt);
    }
}
