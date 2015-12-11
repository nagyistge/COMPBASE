package uzuzjmd.competence.persistence.neo4j;

import com.google.common.collect.Lists;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Statement;
import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;
import uzuzjmd.competence.persistence.ontology.CompOntClass;
import uzuzjmd.competence.persistence.owl.CompOntologyAccessScala;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dehne on 11.12.2015.
 */
public class Model2Neo4j {
    /**
     * persists the Jena API model in the neo4j database
     * @param model
     * @param manager
     * @throws Exception
     */
    public static void convertModel2Neo4jDB(OntModel model, CompOntologyManager manager) throws Exception {
        List<Individual> individuals = model.listIndividuals().toList();
        List<ObjectProperty> objectProperties = model.listObjectProperties().toList();
        List<OntClass> ontClasses = model.listClasses().toList();
        List<Statement> statements = model.listStatements().toList();
        Neo4JQueryManager neo4JQueryManager = new Neo4JQueryManager();


        for (Individual individual : individuals) {
            if (!filterSingletonIndividuals(individual)) {
                Neo4jIndividual neo4jIndividual = new Neo4jIndividual(individual.getLocalName(), CompOntologyAccessScala.getDataField("definition", manager, individual), individual.getOntClass());
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
                String definition = CompOntologyAccessScala.getDataFieldForClass("definition", manager, ontClass);
                Neo4jOntClass neo4jOntClass = new Neo4jOntClass(ontClass.getLocalName(), definition);
                neo4jOntClass.create();
            }
        }

        for (Statement statement : statements) {
            String subject = statement.getSubject().getLocalName();
            String edge = statement.getPredicate().getLocalName();
            String object = statement.getObject().asResource().getLocalName();
            if (edgesMapped.contains(edge)) {
                neo4JQueryManager.createRelationShip(subject, edge, object);
            }

            if (edge.equals("rdfs:subClassOf")) {
                neo4JQueryManager.createSuperClassRelationShip(subject, CompObjectProperties.subClassOf, object);
            }
        }

        // TODO implement restrictions


    }

    private static boolean filterSingletonIndividuals(Individual individual) {
        OntClass ontClass = individual.getOntClass();
        return filterSingletonOntClass(ontClass);
    }

    private static boolean filterSingletonOntClass(OntClass ontClass) {
        return getSingletonClassesForFilter().contains(CompOntClass.valueOf(ontClass.getLocalName()));
    }

    public static List<CompOntClass> getSingletonClassesForFilter() {
        CompOntClass[] resultElems = new CompOntClass[]{CompOntClass.Competence, CompOntClass.Catchword, CompOntClass.Operator};
        return Lists.newArrayList(resultElems);
    }

   /* public static List<CompObjectProperties> getCompObjectPropertiesForFilter() {
        CompObjectProperties[] resultElems = new CompObjectProperties[]{CompObjectProperties.subClassOf, CompObjectProperties.individualOf};
        return Lists.newArrayList(resultElems);
    }*/

}
