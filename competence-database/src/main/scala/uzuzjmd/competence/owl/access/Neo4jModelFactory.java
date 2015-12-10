package uzuzjmd.competence.owl.access;


import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import uzuzjmd.competence.neo4j.Neo4JQueryManager;
import uzuzjmd.competence.neo4j.SubClassRelation;
import uzuzjmd.competence.owl.access.CompOntologyAccessJenaImpl;
import uzuzjmd.competence.owl.access.CompOntologyManager;
import uzuzjmd.competence.owl.queries.CompetenceQueries;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dehne on 10.12.2015.
 */
public class Neo4jModelFactory {
    /**
     * No-one can make instances of this.
     */
    private Neo4jModelFactory() {
    }

    /**
     * TODO implement
     *
     * @return
     */
    public Model getModelFromNeo4j() {
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
        Neo4JQueryManager manager = new Neo4JQueryManager();
        CompetenceQueries competenceQueries = new CompetenceQueries(model);
        CompOntologyManager jenaManager = new CompOntologyManager();
        CompOntologyAccessJenaImpl util = new CompOntologyAccessJenaImpl(model, competenceQueries, jenaManager);
        jenaManager.setM(model);
        jenaManager.createBaseOntology();
        try {
            jenaManager.begin();
            jenaManager.getM().enterCriticalSection(false);
            HashMap<OntClass, String> singleTonDefinitions = manager.getAllSingletonDefinitions();
            for (OntClass ontClass : singleTonDefinitions.keySet()) {
                util.createSingleTonIndividualWithClass(ontClass.getLocalName(), false, singleTonDefinitions.get(ontClass));
            }
            List<SubClassRelation> subClassRelations = manager.getAllSingletonRelations(null);
            for (SubClassRelation subClassRelation: subClassRelations
                 ) {
                subClassRelation.getSubClass().getOntClass().setSuperClass(subClassRelation.getSuperClass());
            }
            List<ObjectProperty> objectProperties = manager.getAllObjectProperties();
            for (ObjectProperty objectProperty: objectProperties
                 ) {
                   model.createObjectProperty(objectProperty.getURI());
            }
            List<Individual> individuals = manager.getAllIndividuals();
            for (Individual individual : individuals) {
                model.createIndividual(individual);
            }
            List<OntClass> ontClasses = manager.getAllOntClasses();
            for (OntClass ontClass : ontClasses) {
                model.createClass(ontClass.getURI());
            }
            jenaManager.getM().leaveCriticalSection();
            jenaManager.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jenaManager.end();
        }
        return model;
    }


}
