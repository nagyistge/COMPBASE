package uzuzjmd.competence.owl.access;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.reasoner.ValidityReport;
import uzuzjmd.competence.owl.abstractlayer.CompOntologyAccess;
import uzuzjmd.competence.owl.ontology.CompObjectProperties;
import uzuzjmd.competence.owl.ontology.CompOntClass;
import uzuzjmd.competence.owl.queries.CompetenceQueries;

import java.util.List;

/**
 * Created by dehne on 09.12.2015.
 */
public class CompOntologyAccessNeo4jImpl extends CompOntologyAccessGenericImpl {
    @Override
    public Individual createIndividualForString(OntClass ontClass, String individualName, Boolean isRead) {
        return null;
    }

    @Override
    public Individual createIndividualForStringWithDefinition(OntClass ontClass, String individualName, String definition) {
        return null;
    }

    @Override
    public ObjectProperty createObjectProperty(CompOntClass domain, CompOntClass range, CompObjectProperties propertyName) {
        return null;
    }

    @Override
    public ObjectProperty createObjectPropertyWithIndividual(Individual domainIndividual, Individual rangeIndividual, CompObjectProperties compObjectProperties) {
        return null;
    }

    @Override
    public ObjectProperty deleteObjectPropertyWithIndividual(Individual domainIndividual, Individual rangeIndividual, CompObjectProperties compObjectProperties) {
        return null;
    }

    @Override
    public Boolean existsObjectPropertyWithIndividual(Individual domainIndividual, Individual rangeIndividual, CompObjectProperties compObjectProperties) {
        return null;
    }

    @Override
    public OntClass createOntClass(CompOntClass ontClass, Boolean isRead) {
        return null;
    }

    @Override
    public OntClass createOntClassForString(String string, Boolean isRead, String... definitions) {
        return null;
    }

    @Override
    public Individual createSingleTonIndividual(OntClass ontclass, Boolean isRead) {
        return null;
    }

    @Override
    public OntClass createSingleTonIndividualWithClass(String classname, Boolean isRead, String... definitions) {
        return null;
    }

    @Override
    public Individual createSingleTonIndividualWithClass2(String classname, Boolean isRead, String... definitions) {
        return null;
    }

    @Override
    public OntResult accessSingletonResource(String classname, Boolean isRead, String... definitions) {
        return null;
    }

    @Override
    public OntResult accessSingletonResourceWithClass(CompOntClass compOntClass, Boolean isRead) {
        return null;
    }

    @Override
    public OntClass getClass(CompOntClass compOntClass, Boolean isRead) {
        return null;
    }

    @Override
    public CompFileUtil getFileUtil() {
        return null;
    }

    @Override
    public Individual getIndividualForString(String indivString) {
        return null;
    }

    @Override
    public OntClass getOntClassForString(String className) {
        return null;
    }

    @Override
    public CompetenceQueries getQueries() {
        return null;
    }

    @Override
    public List<String> getAllInstanceDefinitions(CompOntClass clazz) {
        return null;
    }

    @Override
    public List<String> getShortestSubClassPath(OntClass start, OntClass end) {
        return null;
    }

    @Override
    public String validityReportTostring(ValidityReport report) {
        return null;
    }
}
