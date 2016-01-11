package uzuzjmd.competence.monopersistence;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uzuzjmd.competence.persistence.neo4j.Neo4JQueryManagerImpl;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;
import uzuzjmd.competence.persistence.ontology.CompOntClass;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dehne on 11.01.2016.
 */
public abstract class DaoAbstractImpl implements Dao {

    private final String id;
    protected final Neo4JQueryManagerImpl queryManager = new Neo4JQueryManagerImpl();
    static Logger logger = LogManager.getLogger(DaoAbstractImpl.class.getName());
    public DaoAbstractImpl(String id) {
        this.id = id;
    }

    @Override
    public void setFullDao(HashMap<String, String> props) {
        for (String s : props.keySet()) {
            try {
                Field field = this.getClass().getField(s);
                field.set(this, props.get(s));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public <T extends Dao> T getFullDao(HashMap<String, String> props) {
        setFullDao(props);
        return (T) this;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public CompOntClass getLabel() {
        return CompOntClass.valueOf(this.getClass().getName());
    }

    @Override
    public void createEdgeWith(CompObjectProperties edge, Dao range) throws Exception {
        queryManager.createRelationShip(this.getId(), edge, range.getId());
    }

    @Override
    public void createEdgeWith(Dao domain, CompObjectProperties edge) throws Exception {
        queryManager.createRelationShip(domain.getId(), edge, this.getId());
    }

    @Override
    public void deleteEdgeWith(Dao otherNode, CompObjectProperties edge) throws Exception {
        queryManager.deleteRelationShip(otherNode.getId(), this.getId(), edge);
        queryManager.deleteRelationShip(this.getId(), otherNode.getId(), edge);
    }

    @Override
    public Boolean hasEdge(Dao domain, CompObjectProperties edge) throws Exception {
        return queryManager.existsRelationShip(domain.getId(), this.getId(), edge);
    }

    @Override
    public Boolean hasEdge(CompObjectProperties edge, Dao range) throws Exception {
        return queryManager.existsRelationShip(this.getId(), range.getId(), edge);
    }

    @Override
    public void persist() throws Exception {
        HashMap<String, String> propHashMap = this.toHashMap();
        propHashMap.put("id", getId());
        propHashMap.put("clazz", getLabel().toString());
        queryManager.createOrUpdateUniqueNode(propHashMap);
    }

    @Override
    public void delete() throws Exception {
        queryManager.deleteNode(getId());
    }

    @Override
    public <T extends Dao> List<T> getAssociatedDaosAsDomain(CompObjectProperties edge, Class<T> clazz) throws Exception {
        List<String> nodeIds = queryManager.getAssociatedNodeIdsAsDomain(getId(), edge);
        return instantiateDaos(clazz, nodeIds);
    }

    @Override
    public <T extends Dao> List<T> getAssociatedDaosAsRange(CompObjectProperties edge, Class<T> clazz) throws Exception {
        List<String> nodeIds = queryManager.getAssociatedNodeIdsAsRange(edge, getId());
        return instantiateDaos(clazz, nodeIds);
    }

    private <T extends Dao> List<T> instantiateDaos(Class<T> clazz, List<String> nodeIds) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        List<T> resultList = new ArrayList<T>();
        for (String nodeId : nodeIds) {
            T result = clazz.getDeclaredConstructor(String.class).newInstance(nodeId);
            resultList.add(result);
        }
        return resultList;
    }

    protected HashMap<String, String> toHashMap() {
        logger.debug("Entering toHashMap");
        HashMap<String, String> result = new HashMap<String, String>();
        String logMes = "{";
        for (Field prop :
                getClass().getDeclaredFields()) {
            logMes += prop.getName() + ":";
            try {
                if (!(prop.get(this) == null)) {
                    if (!((prop.get(this).getClass().getName().contains("Neo4J")) || (prop.get(this).getClass().getName().contains("Logger")))) {
                        result.put(prop.getName(), prop.get(this).toString());
                        logMes += prop.get(this).toString() + ";";
                    }
                }
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        logger.debug("Leaving toHashMap with HashMap:" + logMes + "}");
        return result;
    }


    protected void hashMapToIndividual(HashMap<String, String> properties) throws IllegalAccessException, NoSuchFieldException {
        logger.debug("Entering hashMapToIndividual with properties");
        String logMessage = "Created/Updated Individual {";
        for (String key :
                properties.keySet()) {
            logMessage += key + ":" + properties.get(key) + "; ";
            try {
                Field f = getClass().getDeclaredField(key);
                if (f.get(this).getClass().getName().equals(String.class.getName())) {
                    f.set(this, properties.get(f.getName()));
                } else {
                    try {
                        f.set(this, convert(f.get(this).getClass(), properties.get(key)));
                    } catch (IllegalAccessException e) {
                        logger.warn("Can't convert a field from database to Individual");
                        logger.warn("fieldClass: " + f.get(this).getClass().getName() + " Property:" + properties.get(key));
                    }
                }
            } catch (NoSuchFieldException e) {}
        }
        logger.info(logMessage + "}");
        logger.debug("Leaving hashMapToIndividual");
    }

    static <T> T convert(Class<T> klazz, String arg) {
        logger.debug("Entering static convert with klazz:" + klazz.getName() + " arg:" + arg);
        Exception cause = null;
        T ret = null;
        try {
            ret = klazz.cast(
                    klazz.getDeclaredMethod("valueOf", String.class)
                            .invoke(null, arg)
            );
        } catch (NoSuchMethodException e) {
            cause = e;
        } catch (IllegalAccessException e) {
            cause = e;
        } catch (InvocationTargetException e) {
            cause = e;
        }
        if (cause == null) {
            return ret;
        } else {
            logger.error(cause.getMessage());
            throw new IllegalArgumentException(cause);
        }
    }
}
