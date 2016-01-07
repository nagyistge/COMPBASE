package uzuzjmd.competence.persistence.neo4j;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.Model;
import org.apache.commons.lang.NotImplementedException;
import uzuzjmd.competence.persistence.abstractlayer.CompOntologyAccessGenericImpl;
import uzuzjmd.competence.persistence.abstractlayer.CompOntologyManager;
import uzuzjmd.competence.persistence.owl.CompFileUtil;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;
import uzuzjmd.competence.persistence.ontology.CompOntClass;
import uzuzjmd.competence.persistence.abstractlayer.CompetenceQueries;
import java.util.List;

/**
 * Created by dehne on 09.12.2015.
 *
 *
 */
public class CompOntologyAccessNeo4jImpl extends CompOntologyAccessGenericImpl {

    public CompOntologyAccessNeo4jImpl() {
    }

    @Override
    public CompOntologyManager getManager() {
        return new CompOntologyManagerNeo4jImpl();
    }

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
        // TODO fix
        return null;
    }

    @Override
    public ObjectProperty createObjectPropertyWithIndividual(Individual domainIndividual, Individual rangeIndividual, CompObjectProperties compObjectProperties) {
        Neo4JQueryManagerImpl neo4JQueryManager = new Neo4JQueryManagerImpl();
        try {
            neo4JQueryManager.createRelationShip(domainIndividual.getLocalName(), compObjectProperties, rangeIndividual.getLocalName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Neo4JObjectProperty(domainIndividual, compObjectProperties, rangeIndividual);
    }

    @Override
    public ObjectProperty deleteObjectPropertyWithIndividual(Individual domainIndividual, Individual rangeIndividual, CompObjectProperties compObjectProperties) {
        Neo4JQueryManagerImpl neo4JQueryManager = new Neo4JQueryManagerImpl();
        try {
            neo4JQueryManager.deleteRelationShip(domainIndividual.getLocalName(), rangeIndividual.getLocalName(), compObjectProperties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Neo4JObjectProperty(domainIndividual, compObjectProperties, rangeIndividual);
    }

    @Override
    public Boolean existsObjectPropertyWithIndividual(Individual domainIndividual, Individual rangeIndividual, CompObjectProperties compObjectProperties) {
        Neo4JQueryManagerImpl neo4JQueryManager = new Neo4JQueryManagerImpl();
        try {
            return neo4JQueryManager.existsRelationShip(domainIndividual.getLocalName(), rangeIndividual.getLocalName(), compObjectProperties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean existsObjectPropertyWithOntClass(OntClass domainIndividual, Individual rangeIndividual, CompObjectProperties compObjectProperties) {
        Neo4JQueryManagerImpl neo4JQueryManager = new Neo4JQueryManagerImpl();
        try {
            neo4JQueryManager.existsRelationShipWithSuperClassGiven(domainIndividual.getLocalName(), rangeIndividual.getLocalName(), compObjectProperties);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
               return getOrCreateSingleTonIndividual(id, id, isRead);
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
        return new Neo4JQueryManagerImpl();
    }

    @Override
    public List<String> getAllInstanceDefinitions(CompOntClass clazz) {
        Neo4JQueryManagerImpl manager = new Neo4JQueryManagerImpl();
        try {
            return manager.getAllInstanceDefinitions(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getShortestSubClassPath(OntClass start, OntClass end) {
        Neo4JQueryManagerImpl manager = new Neo4JQueryManagerImpl();
        try {
            return manager.getShortestSubClassPath(start,end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean getDataFieldBoolean(String key, Individual individual) {
        // TODO Implement
        throw new NotImplementedException();


    }

    @Override
    public Long getDataFieldLong(String key, Individual individual) {
        // TODO Implement
        throw new NotImplementedException();

    }

    @Override
    public Integer getDataFieldInt(String key, Individual individual) {
        // TODO Implement
        throw new NotImplementedException();

    }

    @Override
    public String getDataField(String key, Individual individual) {
        Neo4JQueryManagerImpl neo4JQueryManager = new Neo4JQueryManagerImpl();
        try {
            return neo4JQueryManager.getPropertyInNode(individual.getLocalName(), key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean getDataFieldBooleanForClass(String key, OntClass ontClass) {
        // TODO Implement
        throw new NotImplementedException();

    }

    @Override
    public Integer getDataFieldIntForClass(String key, OntClass ontClass) {
        // TODO Implement
        throw new NotImplementedException();

    }

    @Override
    public String getDataFieldForClass(String key, OntClass ontClass) {
        // TODO Implement
        throw new NotImplementedException();

    }

    @Override
    public void deleteDataField(String key, Individual individual) {

    }

    private OntClass getOrCreateSingleTonIndividual(String id, String definition, Boolean isRead) {
        Neo4jIndividual neo4jIndividual = getOrCreateNeo4jSingletonIndividual(id, definition, isRead);
        if (neo4jIndividual == null) return null;
        return neo4jIndividual.getOntClass();
    }

    private Neo4jIndividual getOrCreateNeo4jSingletonIndividual(String id, String definition, Boolean isRead) {
        Neo4jIndividual neo4jIndividual = new Neo4jIndividual(id, definition, new Neo4jOntClass(definition), true);
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
