package uzuzjmd.competence.persistence.neo4j;


import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.Model;
import uzuzjmd.competence.persistence.owl.CompOntologyAccessJenaImpl;
import uzuzjmd.competence.persistence.abstractlayer.CompOntologyAccessFactory;
import uzuzjmd.competence.persistence.owl.CompOntologyManagerJenaImpl;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dehne on 10.12.2015.
 */
public class Neo4jModelFactory {

    /**
     * Creates an export from the neo4j database
     * @return
     */
    public static Model createModelFromNeo4j() {

        Neo4JQueryManagerImpl manager = new Neo4JQueryManagerImpl();
        CompOntologyAccessJenaImpl util = ((CompOntologyAccessJenaImpl) CompOntologyAccessFactory.createJenaInMemoryAccessPoint());
        CompOntologyManagerJenaImpl jenaManager = util.getManager();
        jenaManager.createBaseOntology();
        try {
            jenaManager.beginWrite();
            jenaManager.getM().enterCriticalSection(false);
            HashMap<OntClass, String> singleTonDefinitions = manager.getAllSingletonDefinitions();
            for (OntClass ontClass : singleTonDefinitions.keySet()) {
                util.createSingleTonIndividualWithClass(ontClass.getLocalName(), false, singleTonDefinitions.get(ontClass));
            }
            List<SubClassRelation> subClassRelations = manager.getAllSingletonRelations();
            for (SubClassRelation subClassRelation: subClassRelations
                 ) {
                subClassRelation.getSubClass().getOntClass().setSuperClass(subClassRelation.getSuperClass());
            }
            List<ObjectProperty> objectProperties = manager.getAllObjectProperties();
            for (ObjectProperty objectProperty: objectProperties
                 ) {
                   jenaManager.getM().createObjectProperty(objectProperty.getURI());
            }
            List<Individual> individuals = manager.getAllIndividuals();
            for (Individual individual : individuals) {
                jenaManager.getM().createIndividual(individual);
            }
            List<OntClass> ontClasses = manager.getAllOntClasses();
            for (OntClass ontClass : ontClasses) {
                jenaManager.getM().createClass(ontClass.getURI());
            }
            jenaManager.getM().leaveCriticalSection();
            jenaManager.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jenaManager.end();
        }
        return jenaManager.getM();
    }


}
