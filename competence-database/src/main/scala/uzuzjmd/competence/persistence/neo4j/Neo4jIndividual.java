package uzuzjmd.competence.persistence.neo4j;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uzuzjmd.competence.config.MagicStrings;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;
import uzuzjmd.competence.persistence.ontology.CompOntClass;

import java.util.HashMap;

/**
 * Created by dehne on 07.12.2015.
 */
public class Neo4jIndividual implements Individual, Fetchable<Neo4jIndividual> {

    private final String id;
    private final Boolean isSingleTonClass;
    private final String definition;
    private final OntClass ontClass;

    public Neo4jIndividual(String id, String definition, OntClass ontClass) {
        this.id = id;
        this.ontClass = ontClass;
        this.isSingleTonClass = false;
        this.definition = definition;
    }


    public Neo4jIndividual(String id, String definition, OntClass ontClass, Boolean isSingletonClass) {
        this.id = id;
        this.isSingleTonClass = isSingletonClass;
        this.definition = definition;
        this.ontClass = ontClass;
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
        Neo4JQueryManager neo4JQueryManager = new Neo4JQueryManager();

        try {
            if (!isSingleTonClass) {
                neo4JQueryManager.setLabelForNode(id, cls.getLocalName());
            } else {
                neo4JQueryManager.setClassForNode(id, definition, CompOntClass.valueOf(cls.getLocalName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOntClass(Resource cls) {
        setOntClass(cls);
    }

    @Override
    public OntClass getOntClass() {
        Neo4JQueryManager neo4JQueryManager = new Neo4JQueryManager();
        HashMap<String, String> idDefinition = new HashMap<String, String>();
        try {
            if (!isSingleTonClass) {
                return new Neo4jOntClass(neo4JQueryManager.getLabelForNode(id).get(0));
            } else {
                idDefinition.put(neo4JQueryManager.getClassForNode(id), neo4JQueryManager.getDefinitionForClassForNode(id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Neo4jOntClass(idDefinition.keySet().iterator().next(), idDefinition.values().iterator().next());
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
        Neo4JQueryManager neo4JQueryManager = new Neo4JQueryManager();
        try {
            neo4JQueryManager.deleteNode(this.id);
        } catch (Exception e) {
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
        return null;
    }

    @Override
    public Neo4jIndividual update() throws Exception {
        return null;
    }

    @Override
    public void delete() throws Exception {

    }

    public void createEdge(CompObjectProperties edgeName, Individual individual) {

    }
}
