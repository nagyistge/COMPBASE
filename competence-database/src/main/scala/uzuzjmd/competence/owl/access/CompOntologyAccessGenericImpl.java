package uzuzjmd.competence.owl.access;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uzuzjmd.competence.owl.abstractlayer.CompOntologyAccess;
import uzuzjmd.competence.owl.ontology.CompObjectProperties;
import uzuzjmd.competence.owl.ontology.CompOntClass;
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


    /**
     * @param domain
     * @param range
     * @param propertyName
     * @return
     */
    @Override
    public ObjectProperty createObjectProperty(
            CompOntClass domain, CompOntClass range,
            CompObjectProperties propertyName) {

        OntClass ontClass1 = getClass(domain, false);
        OntClass ontclass2 = getClass(range, false);
        return createObjectProperty(ontClass1, ontclass2,
                propertyName.name());
    }

    /**
     *
     * @param domain
     * @param range
     * @param propertyName
     * @return
     */
    private ObjectProperty createObjectProperty(
            OntClass domain, OntClass range,
            String propertyName) {

        ObjectProperty property = manager.getM()
                .createObjectProperty(encode(propertyName));
        property.setDomain(domain);
        property.setRange(range);
        return property;
    }

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


    @Override
    public OntResult accessSingletonResource(
            String classname, Boolean isRead,
            String... definitions) {
        if (classname.startsWith("I")) {
            logger.trace("trying to get SingletonRessource but Id given (including prefix I) instead of definition");
        }

        OntClass classOnt = createOntClassForString(
                classname, isRead, definitions);
        Individual individual = createSingleTonIndividual(
                classOnt, isRead);

        return new OntResult(individual, classOnt);
    }


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
