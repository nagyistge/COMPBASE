package uzuzjmd.competence.persistence.neo4j;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Field;
import uzuzjmd.competence.config.MagicStrings;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;
import uzuzjmd.competence.persistence.ontology.CompOntClass;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dehne on 07.12.2015.
 */
public class Neo4jIndividual implements Individual, Fetchable<Neo4jIndividual> {

    private String id;
    private Boolean isSingleTonClass;
    private String definition;
    private Neo4JQueryManager qmanager;
    private CompOntClass clazz;
    private OntClass ontClass;
    static Logger logger = LogManager.getLogger(Neo4jIndividual.class.getName());


    public Neo4jIndividual(String id, String definition, OntClass ontClass) {
        logger.debug("Entering Neo4jIndividual Constructor with id:"
                + id + " definition:" + definition + " ontClass:" + ontClass.getLocalName());
        this.id = id;
        this.isSingleTonClass = false;
        this.definition = definition;
        qmanager = new Neo4JQueryManager();
        this.ontClass = ontClass;
        this.clazz = CompOntClass.valueOf(ontClass.getLocalName());
        logger.debug("Leaving Neo4jIndividual Constructor");
    }

    public Neo4jIndividual(String id, String definition, OntClass ontClass, Boolean isSingletonClass) {
        logger.debug("Entering Neo4jIndividual Constructor with id:"
                + id + " definition:" + definition + " ontClass:" + ontClass.getLocalName()
                + "isSinletonClass" + String.valueOf(isSingletonClass));
        this.id = id;
        this.isSingleTonClass = isSingletonClass;
        this.definition = definition;
        this.ontClass = ontClass;
        String str = ontClass.getLocalName();
        this.clazz = CompOntClass.valueOf(ontClass.getLocalName());
        qmanager = new Neo4JQueryManager();
        logger.debug("Leaving Neo4jIndividual Constructor");
    }

    @Override
    public boolean equals(Object o) {
        logger.debug("Entering equals with object:" + o.toString());
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            logger.debug("Leaving equals with " + String.valueOf(false));
            return false;
        }

        Neo4jIndividual that = (Neo4jIndividual) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (isSingleTonClass != null ?
                !isSingleTonClass.equals(that.isSingleTonClass) : that.isSingleTonClass != null) {
            logger.debug("Leaving equals with " + String.valueOf(false));
            return false;
        }
        Boolean ret = !(definition != null ? !definition.equals(that.definition) : that.definition != null);
        logger.debug("Leaving equals with " + String.valueOf(ret));

        return ret;

    }

    @Override
    public int hashCode() {
        logger.debug("Entering hashcode");
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (isSingleTonClass != null ? isSingleTonClass.hashCode() : 0);
        result = 31 * result + (definition != null ? definition.hashCode() : 0);
        logger.debug("Leaving hashCode with " + result);
        return result;
    }


    /**
     * adding a class to an individual is like adding a label to a node in Neo4J
     * In order to adhere to owl standards, inheritance must be persisted
     * redundant with an extra class node
     *
     * @param cls
     */
    @Override
    public void setOntClass(Resource cls) {
        createClass(cls.getLocalName());
    }

    private void createClass(String str) {
        try {
            if (!isSingleTonClass) {
                qmanager.setLabelForNode(id, str);
            } else {
                qmanager.setClassForNode(id, definition, CompOntClass.valueOf(str));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void addOntClass(Resource cls) {
        setOntClass(cls);
    }

    @Override
    public OntClass getOntClass() {
        logger.debug("Entering getOntClass");
        Neo4JQueryManager neo4JQueryManager = new Neo4JQueryManager();
        HashMap<String, String> idDefinition = new HashMap<String, String>();
        Neo4jOntClass myont;
        try {
            if (!isSingleTonClass) {
                myont = new Neo4jOntClass(neo4JQueryManager.getLabelForNode(id).get(0));
                logger.debug("Leaving getOntClass with Neo4jOntClass:" + myont.getLocalName());
                return myont;
            } else {
                idDefinition.put(neo4JQueryManager.getClassForNode(id), neo4JQueryManager.getDefinitionForClassForNode(id));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        myont = new Neo4jOntClass(idDefinition.keySet().iterator().next(), idDefinition.values().iterator().next());
        logger.debug("Leaving getOntClass with Neo4jOntClass:" + myont.getLocalName());
        return  myont;
    }

    @Override
    public OntClass getOntClass(boolean direct) {
        return getOntClass();
    }

    @Override
    public <T extends OntClass> ExtendedIterator<T> listOntClasses(boolean direct) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasOntClass(Resource ontClass, boolean direct) {

        return getOntClass().equals((OntClass) ontClass);
    }

    @Override
    public boolean hasOntClass(Resource ontClass) {
        return hasOntClass(ontClass, false);
    }

    @Override
    public boolean hasOntClass(String uri) {
        return getOntClass().getURI().equals(uri);
    }

    @Override
    public void removeOntClass(Resource ontClass) {
        throw new NotImplementedException();
    }

    @Override
    public OntModel getOntModel() {
        throw new NotImplementedException();
    }

    @Override
    public Profile getProfile() {
        throw new NotImplementedException();
    }

    @Override
    public boolean isOntLanguageTerm() {
        throw new NotImplementedException();
    }

    @Override
    public void setSameAs(Resource res) {
        throw new NotImplementedException();
    }

    @Override
    public void addSameAs(Resource res) {
        throw new NotImplementedException();
    }

    @Override
    public OntResource getSameAs() {
        throw new NotImplementedException();
    }

    @Override
    public ExtendedIterator<? extends Resource> listSameAs() {
        throw new NotImplementedException();
    }

    @Override
    public boolean isSameAs(Resource res) {
        throw new NotImplementedException();
    }

    @Override
    public void removeSameAs(Resource res) {
        throw new NotImplementedException();
    }

    @Override
    public void setDifferentFrom(Resource res) {
        throw new NotImplementedException();
    }

    @Override
    public void addDifferentFrom(Resource res) {
        throw new NotImplementedException();
    }

    @Override
    public OntResource getDifferentFrom() {
        throw new NotImplementedException();
    }

    @Override
    public ExtendedIterator<? extends Resource> listDifferentFrom() {
        throw new NotImplementedException();
    }

    @Override
    public boolean isDifferentFrom(Resource res) {
        throw new NotImplementedException();
    }

    @Override
    public void removeDifferentFrom(Resource res) {

        throw new NotImplementedException();
    }

    @Override
    public void setSeeAlso(Resource res) {
        throw new NotImplementedException();
    }

    @Override
    public void addSeeAlso(Resource res) {
        throw new NotImplementedException();
    }

    @Override
    public Resource getSeeAlso() {
        throw new NotImplementedException();
    }

    @Override
    public ExtendedIterator<RDFNode> listSeeAlso() {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasSeeAlso(Resource res) {
        throw new NotImplementedException();
    }

    @Override
    public void removeSeeAlso(Resource res) {
        throw new NotImplementedException();
    }

    @Override
    public void setIsDefinedBy(Resource res) {
        throw new NotImplementedException();
    }

    @Override
    public void addIsDefinedBy(Resource res) {
        throw new NotImplementedException();
    }

    @Override
    public Resource getIsDefinedBy() {
        throw new NotImplementedException();
    }

    @Override
    public ExtendedIterator<RDFNode> listIsDefinedBy() {
        throw new NotImplementedException();
    }

    @Override
    public boolean isDefinedBy(Resource res) {
        throw new NotImplementedException();
    }

    @Override
    public void removeDefinedBy(Resource res) {
        throw new NotImplementedException();
    }

    @Override
    public void setVersionInfo(String info) {
        throw new NotImplementedException();
    }

    @Override
    public void addVersionInfo(String info) {
        throw new NotImplementedException();
    }

    @Override
    public String getVersionInfo() {
        throw new NotImplementedException();
    }

    @Override
    public ExtendedIterator<String> listVersionInfo() {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasVersionInfo(String info) {
        throw new NotImplementedException();
    }

    @Override
    public void removeVersionInfo(String info) {
        throw new NotImplementedException();
    }

    @Override
    public void setLabel(String label, String lang) {
        throw new NotImplementedException();
    }

    @Override
    public void addLabel(String label, String lang) {
        throw new NotImplementedException();
    }

    @Override
    public void addLabel(Literal label) {
        throw new NotImplementedException();
    }

    @Override
    public String getLabel(String lang) {
        throw new NotImplementedException();
    }

    @Override
    public ExtendedIterator<RDFNode> listLabels(String lang) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasLabel(String label, String lang) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasLabel(Literal label) {
        throw new NotImplementedException();
    }

    @Override
    public void removeLabel(String label, String lang) {
        throw new NotImplementedException();
    }

    @Override
    public void removeLabel(Literal label) {
        throw new NotImplementedException();
    }

    @Override
    public void setComment(String comment, String lang) {
        throw new NotImplementedException();
    }

    @Override
    public void addComment(String comment, String lang) {
        throw new NotImplementedException();
    }

    @Override
    public void addComment(Literal comment) {
        throw new NotImplementedException();
    }

    @Override
    public String getComment(String lang) {
        throw new NotImplementedException();
    }

    @Override
    public ExtendedIterator<RDFNode> listComments(String lang) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasComment(String comment, String lang) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasComment(Literal comment) {
        throw new NotImplementedException();
    }

    @Override
    public void removeComment(String comment, String lang) {
        throw new NotImplementedException();
    }

    @Override
    public void removeComment(Literal comment) {
        throw new NotImplementedException();
    }

    @Override
    public void setRDFType(Resource cls) {
        throw new NotImplementedException();
    }

    @Override
    public void addRDFType(Resource cls) {
        throw new NotImplementedException();
    }

    @Override
    public Resource getRDFType() {
        throw new NotImplementedException();
    }

    @Override
    public Resource getRDFType(boolean direct) {
        throw new NotImplementedException();
    }

    @Override
    public ExtendedIterator<Resource> listRDFTypes(boolean direct) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasRDFType(Resource ontClass, boolean direct) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasRDFType(Resource ontClass) {
        throw new NotImplementedException();
    }

    @Override
    public void removeRDFType(Resource cls) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasRDFType(String uri) {
        throw new NotImplementedException();
    }

    @Override
    public int getCardinality(Property p) {
        throw new NotImplementedException();
    }

    @Override
    public void setPropertyValue(Property property, RDFNode value) {
        throw new NotImplementedException();
    }

    @Override
    public RDFNode getPropertyValue(Property property) {
        throw new NotImplementedException();
    }

    @Override
    public NodeIterator listPropertyValues(Property property) {
        throw new NotImplementedException();
    }

    @Override
    public void removeProperty(Property property, RDFNode value) {
        throw new NotImplementedException();
    }

    @Override
    public void remove() {
        try {
            qmanager.deleteNode(this.id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public OntProperty asProperty() {
        throw new NotImplementedException();
    }

    @Override
    public AnnotationProperty asAnnotationProperty() {
        throw new NotImplementedException();

    }

    @Override
    public ObjectProperty asObjectProperty() {
        throw new NotImplementedException();

    }

    @Override
    public DatatypeProperty asDatatypeProperty() {
        throw new NotImplementedException();

    }

    @Override
    public Individual asIndividual() {
        return this;
    }

    @Override
    public OntClass asClass() {
        return getOntClass();
    }

    @Override
    public Ontology asOntology() {
        throw new NotImplementedException();

    }

    @Override
    public DataRange asDataRange() {
        throw new NotImplementedException();

    }

    @Override
    public AllDifferent asAllDifferent() {
        throw new NotImplementedException();

    }

    @Override
    public boolean isProperty() {
        throw new NotImplementedException();

    }

    @Override
    public boolean isAnnotationProperty() {
        throw new NotImplementedException();

    }

    @Override
    public boolean isObjectProperty() {
        throw new NotImplementedException();

    }

    @Override
    public boolean isDatatypeProperty() {
        throw new NotImplementedException();

    }

    @Override
    public boolean isIndividual() {
        return true;
    }

    @Override
    public boolean isClass() {
        return false;
    }

    @Override
    public boolean isOntology() {
        return false;
    }

    @Override
    public boolean isDataRange() {
        return false;
    }

    @Override
    public boolean isAllDifferent() {
        return false;
    }

    @Override
    public AnonId getId() {
        throw new NotImplementedException();

    }

    @Override
    public boolean isAnon() {
        throw new NotImplementedException();

    }

    @Override
    public boolean isLiteral() {
        throw new NotImplementedException();

    }

    @Override
    public boolean isURIResource() {
        return false;
    }

    @Override
    public boolean isResource() {
        return false;
    }

    @Override
    public <T extends RDFNode> T as(Class<T> view) {
        throw new NotImplementedException();

    }

    @Override
    public <T extends RDFNode> boolean canAs(Class<T> view) {
        throw new NotImplementedException();

    }

    @Override
    public Model getModel() {
        throw new NotImplementedException();
    }

    @Override
    public Resource inModel(Model m) {
        throw new NotImplementedException();
    }

    @Override
    public Object visitWith(RDFVisitor rv) {
        throw new NotImplementedException();
    }

    @Override
    public Resource asResource() {
        return new Neo4jResource(this.id);
    }

    @Override
    public Literal asLiteral() {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasURI(String uri) {
        throw new NotImplementedException();
    }

    @Override
    public String getURI() {
        return MagicStrings.PREFIX + this.id;
    }

    @Override
    public String getNameSpace() {
        return MagicStrings.PREFIX;
    }

    @Override
    public String getLocalName() {
        return this.id;
    }

    @Override
    public Statement getRequiredProperty(Property p) {
        throw new NotImplementedException();
    }

    @Override
    public Statement getProperty(Property p) {
        throw new NotImplementedException();
    }

    @Override
    public StmtIterator listProperties(Property p) {
        throw new NotImplementedException();
    }

    @Override
    public StmtIterator listProperties() {
        throw new NotImplementedException();
    }

    @Override
    public Resource addLiteral(Property p, boolean o) {
        throw new NotImplementedException();
    }

    @Override
    public Resource addLiteral(Property p, long o) {
        throw new NotImplementedException();
    }

    @Override
    public Resource addLiteral(Property p, char o) {
        throw new NotImplementedException();
    }

    @Override
    public Resource addLiteral(Property value, double d) {
        throw new NotImplementedException();
    }

    @Override
    public Resource addLiteral(Property value, float d) {
        throw new NotImplementedException();
    }

    @Override
    public Resource addLiteral(Property p, Object o) {
        throw new NotImplementedException();
    }

    @Override
    public Resource addLiteral(Property p, Literal o) {
        throw new NotImplementedException();
    }

    @Override
    public Resource addProperty(Property p, String o) {
        throw new NotImplementedException();
    }

    @Override
    public Resource addProperty(Property p, String o, String l) {
        throw new NotImplementedException();
    }

    @Override
    public Resource addProperty(Property p, String lexicalForm, RDFDatatype datatype) {
        throw new NotImplementedException();
    }

    @Override
    public Resource addProperty(Property p, RDFNode o) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasProperty(Property p) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasLiteral(Property p, boolean o) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasLiteral(Property p, long o) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasLiteral(Property p, char o) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasLiteral(Property p, double o) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasLiteral(Property p, float o) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasLiteral(Property p, Object o) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasProperty(Property p, String o) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasProperty(Property p, String o, String l) {
        throw new NotImplementedException();
    }

    @Override
    public boolean hasProperty(Property p, RDFNode o) {
        throw new NotImplementedException();
    }

    @Override
    public Resource removeProperties() {
        throw new NotImplementedException();
    }

    @Override
    public Resource removeAll(Property p) {
        throw new NotImplementedException();
    }

    @Override
    public Resource begin() {
        throw new NotImplementedException();
    }

    @Override
    public Resource abort() {
        throw new NotImplementedException();
    }

    @Override
    public Resource commit() {
        throw new NotImplementedException();
    }

    @Override
    public Resource getPropertyResourceValue(Property p) {
        throw new NotImplementedException();
    }

    @Override
    public Node asNode() {
        throw new NotImplementedException();
    }


    @Override
    public Neo4jIndividual fetchIfExists() throws Exception {
        return null;
    }

    @Override
    public Neo4jIndividual create() throws Exception {
        logger.debug("Entering create");
        ArrayList<HashMap<String, String>> returnStrings = qmanager.createUniqueNode(this.toHashmap());
        if (returnStrings.size() > 0) {
            if (returnStrings.size() > 1) {
                logger.warn("Getting more Individuals than expected. Count:"
                        + String.valueOf(returnStrings.size()));
            }
            for (HashMap<String, String> hm : returnStrings) {
                hashMapToIndividual(hm);
            }
            logger.debug("Leaving create with Neo4jIndividual:" + this.getLocalName());
            return this;
        } else {
            logger.debug("Leaving create with null");
            return null;
        }

    }

    @Override
    public Neo4jIndividual update() throws Exception {
        // TODO implement
        return this;
    }

    @Override
    public void delete() throws Exception {

    }

    private HashMap<String,String> toHashmap() {
        logger.debug("Entering toHashmap");
        HashMap<String, String> result = new HashMap<String, String>();
        String logMes = "{";
        for (Field prop :
                Neo4jIndividual.class.getDeclaredFields()) {
            logMes += prop.getName() + ":";
            try {
                if (! ((prop.get(this).getClass().getName().contains("Neo4J")) || (prop.get(this).getClass().getName().contains("Logger")))) {
                    result.put(prop.getName(), prop.get(this).toString());
                    logMes += prop.get(this).toString() + ";";
                }
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        logger.debug("Leaving toHasmap with Hasmap:" + logMes + "}");
        return result;
    }
    public void createEdge(CompObjectProperties edgeName, Individual individual) {

    }
    private void hashMapToIndividual(HashMap<String, String> properties) throws IllegalAccessException, NoSuchFieldException {
        logger.debug("Entering hashMapToIndivudual with properties");
        String logMessage = "Created/Updated Individual {";
        for (String key :
                properties.keySet()) {
           logMessage += key + ":" + properties.get(key) + "; ";
            Field f = Neo4jIndividual.class.getDeclaredField(key);
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
