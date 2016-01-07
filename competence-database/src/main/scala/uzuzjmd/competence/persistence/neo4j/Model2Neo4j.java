package uzuzjmd.competence.persistence.neo4j;

import com.google.common.collect.Lists;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Statement;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;
import uzuzjmd.competence.persistence.ontology.CompOntClass;
import uzuzjmd.competence.persistence.owl.CompOntologyAccessScala;
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dehne on 11.12.2015.
 */
public class Model2Neo4j {

    /**
     * @param manager
     * @throws Exception
     */
    public static void convertModel2Neo4jDB(CompOntologyManagerJenaImpl manager) throws Exception {
        manager.beginRead();
        OntModel model = manager.getM();
        List<Individual> individuals = model.listIndividuals().toList();
        List<OntClass> ontClasses = model.listClasses().toList();
        List<Statement> statements = model.listStatements().toList();
        Neo4JQueryManagerImpl neo4JQueryManager = new Neo4JQueryManagerImpl();


        for (Individual individual : individuals) {
            if (!filterSingletonIndividuals(individual)) {

                String definition = null;
                try {
                    definition = CompOntologyAccessScala.getDataField("definition", manager, individual);
                } catch (Exception e) {
                }

                Neo4jIndividual neo4jIndividual = new Neo4jIndividual(individual.getLocalName(), definition, individual.getOntClass());
                neo4jIndividual.create();
            }
        }


        ArrayList<CompObjectProperties> edges = Lists.newArrayList(CompObjectProperties.values());
        List<String> edgesMapped = new LinkedList<String>();

        for (CompObjectProperties compObjectProperty : edges) {
            edgesMapped.add(compObjectProperty.toString());
        }

        for (OntClass ontClass : ontClasses) {
            if (filterSingletonOntClass(ontClass)) {
                String definition = null;
                try {
                    definition = CompOntologyAccessScala.getDataFieldForClass("definition", manager, ontClass);
                } catch (Exception e) {

                }

                Neo4jOntClass neo4jOntClass = new Neo4jOntClass(ontClass.getLocalName(), definition);
                neo4jOntClass.create();
            }
        }

        for (Statement statement : statements) {
            String subject = statement.getSubject().getLocalName();
            String subjectDefinition = null;
            try {
                 subjectDefinition = CompOntologyAccessScala.getDataField("definition", manager, model.getIndividual(statement.getSubject().getURI()));
            } catch (Exception e) {

            }

            String edge = statement.getPredicate().getLocalName();

            String object = statement.getObject().asResource().getLocalName();
            String objectDefinition = null;
            try {
                objectDefinition = CompOntologyAccessScala.getDataField("definition", manager, model.getIndividual(statement.getSubject().getURI()));
            } catch (Exception e) {

            }

            if (!subject.equals("Nothing") && !object.equals("Thing") && !object.equals("Resource") && !subject.equals(object)) {
                if (edgesMapped.contains(edge)) {
                    neo4JQueryManager.createRelationShip(subject, subjectDefinition, edge, object, objectDefinition);
                }
                if (edge.equals("rdfs:subClassOf")) {
                    neo4JQueryManager.createRelationShip(subject, CompObjectProperties.subClassOf, object);
                }
            }
        }

        // TODO implement restrictions

        manager.end();

    }

    private static boolean filterSingletonIndividuals(Individual individual) {
        OntClass ontClass = individual.getOntClass();
        return filterSingletonOntClass(ontClass);
    }

    private static boolean filterSingletonOntClass(OntClass ontClass) {
        try {
            return getSingletonClassesForFilter().contains(CompOntClass.valueOf(ontClass.getLocalName()));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static List<CompOntClass> getSingletonClassesForFilter() {
        CompOntClass[] resultElems = new CompOntClass[]{CompOntClass.Competence, CompOntClass.Catchword, CompOntClass.Operator};
        return Lists.newArrayList(resultElems);
    }

}
