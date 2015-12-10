package uzuzjmd.competence.neo4j;


import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dehne on 10.12.2015.
 */
public class Neo4jModelFactory  {
    /**
     * No-one can make instances of this.
     */
    private Neo4jModelFactory() {
    }

    /**
     * TODO implement
     * @return
     */
    public Model getModelFromNeo4j(){
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
        Neo4JQueryManager manager = new Neo4JQueryManager();

        List<HashMap<OntClass, String>> singleTonDefinitions = manager.getAllSingletonDefinitions();

        List<SubClassRelation> subClassRelations = manager.getAllSingletonRelations(null);
        List<ObjectProperty> objectProperties = manager.getAllObjectProperties();
        List<Individual> individuals = manager.getAllIndividuals();
        List<OntClass> ontClasses = manager.getAllOntClasses();

        return model;
    }




}
