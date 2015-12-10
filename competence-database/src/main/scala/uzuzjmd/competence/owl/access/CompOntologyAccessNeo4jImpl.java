package uzuzjmd.competence.owl.access;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.reasoner.ValidityReport;
import uzuzjmd.competence.neo4j.Neo4JObjectProperty;
import uzuzjmd.competence.neo4j.Neo4JQueryManager;
import uzuzjmd.competence.neo4j.Neo4jIndividual;
import uzuzjmd.competence.neo4j.Neo4jOntClass;
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
        Neo4jIndividual neo4jIndividual = new Neo4jIndividual(individualName, individualName, ontClass, false);
        try {
            if (isRead) {
                return neo4jIndividual.fetchIfExists();
            } else {
                neo4jIndividual.create();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return neo4jIndividual;
    }

    @Override
    public Individual createIndividualForStringWithDefinition(OntClass ontClass, String individualName, String definition) {
        Neo4jIndividual neo4jIndividual = new Neo4jIndividual(individualName, definition, ontClass, false);
        try {
            return neo4jIndividual.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObjectProperty createObjectProperty(CompOntClass domain, CompOntClass range, CompObjectProperties propertyName) {
        Neo4JObjectProperty neo4JObjectProperty = new Neo4JObjectProperty(domain,propertyName,range);
        try {
            return neo4JObjectProperty.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ObjectProperty createObjectPropertyWithIndividual(Individual domainIndividual, Individual rangeIndividual, CompObjectProperties compObjectProperties) {
        Neo4jIndividual neo4jIndividual = new Neo4jIndividual(domainIndividual.getLocalName(), null, domainIndividual.getOntClass());
        neo4jIndividual.createEdge(compObjectProperties, rangeIndividual);
        return new Neo4JObjectProperty(CompOntClass.valueOf(domainIndividual.getOntClass().getLocalName()), compObjectProperties, CompOntClass.valueOf(rangeIndividual.getOntClass().getLocalName()));
    }

    @Override
    public ObjectProperty deleteObjectPropertyWithIndividual(Individual domainIndividual, Individual rangeIndividual, CompObjectProperties compObjectProperties) {
        Neo4JQueryManager neo4JQueryManager = new Neo4JQueryManager();
        neo4JQueryManager.deleteRelationShip(domainIndividual.getLocalName(), rangeIndividual.getLocalName(), compObjectProperties);
        return new Neo4JObjectProperty(CompOntClass.valueOf(domainIndividual.getOntClass().getLocalName()), compObjectProperties, CompOntClass.valueOf(rangeIndividual.getOntClass().getLocalName()));
    }

    @Override
    public Boolean existsObjectPropertyWithIndividual(Individual domainIndividual, Individual rangeIndividual, CompObjectProperties compObjectProperties) {
        Neo4JQueryManager neo4JQueryManager = new Neo4JQueryManager();
        return neo4JQueryManager.existsRelationShip(domainIndividual.getLocalName(), rangeIndividual.getLocalName(), compObjectProperties);

    }

    @Override
    public Boolean existsObjectPropertyWithOntClass(OntClass domainIndividual, Individual rangeIndividual, CompObjectProperties compObjectProperties) {
        Neo4JQueryManager neo4JQueryManager = new Neo4JQueryManager();
        neo4JQueryManager.existsRelationShipWithSuperClassGiven(domainIndividual.getLocalName(), rangeIndividual.getLocalName(), compObjectProperties);
        return null;
    }

    /**
     * Creates SingleTonClass with root
     * @param ontClass
     * @param isRead
     * @return
     */
    @Override
    public OntClass createOntClass(CompOntClass ontClass, Boolean isRead) {
        return getOrCreateSingleTonIndividual(ontClass.toString(), ontClass.toString(), isRead);
    }



    @Override
    public OntClass createOntClassForString(String id, Boolean isRead, String... definitions) {
        if (definitions.length > 0 ) {
            return getOrCreateSingleTonIndividual(id, definitions[0], isRead);
        } else {
            // if no definitions are given then the Singleton must be a rootNode
            return createOntClass(CompOntClass.valueOf(id), isRead);
        }
    }

    @Override
    public Individual createSingleTonIndividual(OntClass ontclass, Boolean isRead) {
        return getOrCreateNeo4jSingletonIndividual(ontclass.getLocalName(), ontclass.getLocalName(), isRead);
    }

    @Override
    public OntClass createSingleTonIndividualWithClass(String classname, Boolean isRead, String... definitions) {
        if (definitions.length > 0 ) {
            return getOrCreateSingleTonIndividual(classname, definitions[0], isRead);
        } else {
            return getOrCreateSingleTonIndividual(classname, classname, isRead);
        }
    }



    @Override
    public CompFileUtil getFileUtil() {
        Model model = Neo4jModelFactory.createModelFromNeo4j();
        return new CompFileUtil(model);
    }

    @Override
    public Individual getIndividualForString(String indivString) {
        Neo4jIndividual neo4jIndividual = new Neo4jIndividual(indivString, indivString, null);
        try {
            return neo4jIndividual.fetchIfExists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OntClass getOntClassForString(String className) {
        try {
            OntClass result = new Neo4jOntClass(className).fetchIfExists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CompetenceQueries getQueries() {
        return new Neo4JQueryManager();
    }

    @Override
    public List<String> getAllInstanceDefinitions(CompOntClass clazz) {
        Neo4JQueryManager manager = new Neo4JQueryManager();
        return manager.getAllInstanceDefinitions(clazz);
    }

    @Override
    public List<String> getShortestSubClassPath(OntClass start, OntClass end) {
        Neo4JQueryManager manager = new Neo4JQueryManager();
        return manager.getShortestSubClassPath(start,end);
    }



    private OntClass getOrCreateSingleTonIndividual(String id, String definition, Boolean isRead) {
        Neo4jIndividual neo4jIndividual = getOrCreateNeo4jSingletonIndividual(id, definition, isRead);
        if (neo4jIndividual == null) return null;

        return neo4jIndividual.getOntClass();
    }

    private Neo4jIndividual getOrCreateNeo4jSingletonIndividual(String id, String definition, Boolean isRead) {
        Neo4jIndividual neo4jIndividual = new Neo4jIndividual(id, definition, new Neo4jOntClass(id), true);
        try {
            if (!isRead) {
                neo4jIndividual.create();
            } else {
                if (neo4jIndividual.fetchIfExists() == null) {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return neo4jIndividual;
    }
}
