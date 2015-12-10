package uzuzjmd.competence.neo4j;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import org.apache.commons.lang.NotImplementedException;
import scala.NotImplementedError;
import uzuzjmd.competence.owl.ontology.CompObjectProperties;
import uzuzjmd.competence.owl.ontology.CompOntClass;

/**
 * Created by dehne on 04.12.2015.
 */
public class Neo4JObjectProperty implements ObjectProperty, Fetchable<Neo4JObjectProperty> {

    public Neo4JObjectProperty(CompOntClass domain, CompObjectProperties propertyName, CompOntClass range) {
    }

    @Override
    public void setSuperProperty(Property prop) {
        throw new NotImplementedError();
    }

    @Override
    public void addSuperProperty(Property prop) {
        throw new NotImplementedError();
    }

    @Override
    public OntProperty getSuperProperty() {
        throw new NotImplementedError();
    }

    @Override
    public ExtendedIterator<? extends OntProperty> listSuperProperties() {
        throw new NotImplementedError();
    }

    @Override
    public ExtendedIterator<? extends OntProperty> listSuperProperties(boolean direct) {
        throw new NotImplementedError();
    }

    @Override
    public boolean hasSuperProperty(Property prop, boolean direct) {
        throw new NotImplementedError();
    }

    @Override
    public void removeSuperProperty(Property prop) {
        throw new NotImplementedError();
    }

    @Override
    public void setSubProperty(Property prop) {
        throw new NotImplementedError();
    }

    @Override
    public void addSubProperty(Property prop) {
        throw new NotImplementedError();
    }

    @Override
    public OntProperty getSubProperty() {
        throw new NotImplementedError();
    }

    @Override
    public ExtendedIterator<? extends OntProperty> listSubProperties() {
        throw new NotImplementedError();
    }

    @Override
    public ExtendedIterator<? extends OntProperty> listSubProperties(boolean direct) {
        throw new NotImplementedError();
    }

    @Override
    public boolean hasSubProperty(Property prop, boolean direct) {
        throw new NotImplementedError();
    }

    @Override
    public void removeSubProperty(Property prop) {
        throw new NotImplementedError();
    }

    @Override
    public void setDomain(Resource res) {

    }

    @Override
    public void addDomain(Resource res) {

    }

    @Override
    public OntResource getDomain() {
        return null;
    }

    @Override
    public ExtendedIterator<? extends OntResource> listDomain() {
        return null;
    }

    @Override
    public boolean hasDomain(Resource res) {
        return false;
    }

    @Override
    public void removeDomain(Resource cls) {

    }

    @Override
    public void setRange(Resource res) {

    }

    @Override
    public void addRange(Resource res) {

    }

    @Override
    public OntResource getRange() {
        return null;
    }

    @Override
    public ExtendedIterator<? extends OntResource> listRange() {
        return null;
    }

    @Override
    public boolean hasRange(Resource res) {
        return false;
    }

    @Override
    public void removeRange(Resource cls) {

    }

    @Override
    public void setEquivalentProperty(Property prop) {

    }

    @Override
    public void addEquivalentProperty(Property prop) {

    }

    @Override
    public OntProperty getEquivalentProperty() {
        return null;
    }

    @Override
    public ExtendedIterator<? extends OntProperty> listEquivalentProperties() {
        return null;
    }

    @Override
    public boolean hasEquivalentProperty(Property prop) {
        return false;
    }

    @Override
    public void removeEquivalentProperty(Property prop) {

    }

    @Override
    public void setInverseOf(Property prop) {

    }

    @Override
    public void addInverseOf(Property prop) {

    }

    @Override
    public OntProperty getInverseOf() {
        return null;
    }

    @Override
    public ExtendedIterator<? extends OntProperty> listInverseOf() {
        return null;
    }

    @Override
    public boolean isInverseOf(Property prop) {
        return false;
    }

    @Override
    public void removeInverseProperty(Property prop) {

    }

    @Override
    public FunctionalProperty asFunctionalProperty() {
        return null;
    }

    @Override
    public DatatypeProperty asDatatypeProperty() {
        return null;
    }

    @Override
    public Individual asIndividual() {
        return null;
    }

    @Override
    public OntClass asClass() {
        return null;
    }

    @Override
    public Ontology asOntology() {
        return null;
    }

    @Override
    public DataRange asDataRange() {
        return null;
    }

    @Override
    public AllDifferent asAllDifferent() {
        return null;
    }

    @Override
    public boolean isProperty() {
        return false;
    }

    @Override
    public AnonId getId() {
        return null;
    }

    @Override
    public boolean isAnon() {
        return false;
    }

    @Override
    public boolean isLiteral() {
        return false;
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
        return null;
    }

    @Override
    public <T extends RDFNode> boolean canAs(Class<T> view) {
        return false;
    }

    @Override
    public Model getModel() {
        return null;
    }

    @Override
    public Property inModel(Model m) {
        return null;
    }

    @Override
    public Object visitWith(RDFVisitor rv) {
        return null;
    }

    @Override
    public Resource asResource() {
        return null;
    }

    @Override
    public Literal asLiteral() {
        return null;
    }

    @Override
    public boolean hasURI(String uri) {
        return false;
    }

    @Override
    public String getURI() {
        return null;
    }

    @Override
    public String getNameSpace() {
        return null;
    }

    @Override
    public String getLocalName() {
        return null;
    }

    @Override
    public Statement getRequiredProperty(Property p) {
        return null;
    }

    @Override
    public Statement getProperty(Property p) {
        return null;
    }

    @Override
    public StmtIterator listProperties(Property p) {
        return null;
    }

    @Override
    public StmtIterator listProperties() {
        return null;
    }

    @Override
    public Resource addLiteral(Property p, boolean o) {
        return null;
    }

    @Override
    public Resource addLiteral(Property p, long o) {
        return null;
    }

    @Override
    public Resource addLiteral(Property p, char o) {
        return null;
    }

    @Override
    public Resource addLiteral(Property value, double d) {
        return null;
    }

    @Override
    public Resource addLiteral(Property value, float d) {
        return null;
    }

    @Override
    public Resource addLiteral(Property p, Object o) {
        return null;
    }

    @Override
    public Resource addLiteral(Property p, Literal o) {
        return null;
    }

    @Override
    public Resource addProperty(Property p, String o) {
        return null;
    }

    @Override
    public Resource addProperty(Property p, String o, String l) {
        return null;
    }

    @Override
    public Resource addProperty(Property p, String lexicalForm, RDFDatatype datatype) {
        return null;
    }

    @Override
    public Resource addProperty(Property p, RDFNode o) {
        return null;
    }

    @Override
    public boolean hasProperty(Property p) {
        return false;
    }

    @Override
    public boolean hasLiteral(Property p, boolean o) {
        return false;
    }

    @Override
    public boolean hasLiteral(Property p, long o) {
        return false;
    }

    @Override
    public boolean hasLiteral(Property p, char o) {
        return false;
    }

    @Override
    public boolean hasLiteral(Property p, double o) {
        return false;
    }

    @Override
    public boolean hasLiteral(Property p, float o) {
        return false;
    }

    @Override
    public boolean hasLiteral(Property p, Object o) {
        return false;
    }

    @Override
    public boolean hasProperty(Property p, String o) {
        return false;
    }

    @Override
    public boolean hasProperty(Property p, String o, String l) {
        return false;
    }

    @Override
    public boolean hasProperty(Property p, RDFNode o) {
        return false;
    }

    @Override
    public Resource removeProperties() {
        return null;
    }

    @Override
    public Resource removeAll(Property p) {
        return null;
    }

    @Override
    public Resource begin() {
        return null;
    }

    @Override
    public Resource abort() {
        return null;
    }

    @Override
    public Resource commit() {
        return null;
    }

    @Override
    public Resource getPropertyResourceValue(Property p) {
        return null;
    }

    @Override
    public int getOrdinal() {
        return 0;
    }

    @Override
    public boolean isAnnotationProperty() {
        return false;
    }

    @Override
    public OntModel getOntModel() {
        return null;
    }

    @Override
    public Profile getProfile() {
        return null;
    }

    @Override
    public boolean isOntLanguageTerm() {
        return false;
    }

    @Override
    public void setSameAs(Resource res) {

    }

    @Override
    public void addSameAs(Resource res) {

    }

    @Override
    public OntResource getSameAs() {
        return null;
    }

    @Override
    public ExtendedIterator<? extends Resource> listSameAs() {
        return null;
    }

    @Override
    public boolean isSameAs(Resource res) {
        return false;
    }

    @Override
    public void removeSameAs(Resource res) {

    }

    @Override
    public void setDifferentFrom(Resource res) {

    }

    @Override
    public void addDifferentFrom(Resource res) {

    }

    @Override
    public OntResource getDifferentFrom() {
        return null;
    }

    @Override
    public ExtendedIterator<? extends Resource> listDifferentFrom() {
        return null;
    }

    @Override
    public boolean isDifferentFrom(Resource res) {
        return false;
    }

    @Override
    public void removeDifferentFrom(Resource res) {

    }

    @Override
    public void setSeeAlso(Resource res) {

    }

    @Override
    public void addSeeAlso(Resource res) {

    }

    @Override
    public Resource getSeeAlso() {
        return null;
    }

    @Override
    public ExtendedIterator<RDFNode> listSeeAlso() {
        return null;
    }

    @Override
    public boolean hasSeeAlso(Resource res) {
        return false;
    }

    @Override
    public void removeSeeAlso(Resource res) {

    }

    @Override
    public void setIsDefinedBy(Resource res) {

    }

    @Override
    public void addIsDefinedBy(Resource res) {

    }

    @Override
    public Resource getIsDefinedBy() {
        return null;
    }

    @Override
    public ExtendedIterator<RDFNode> listIsDefinedBy() {
        return null;
    }

    @Override
    public boolean isDefinedBy(Resource res) {
        return false;
    }

    @Override
    public void removeDefinedBy(Resource res) {

    }

    @Override
    public void setVersionInfo(String info) {

    }

    @Override
    public void addVersionInfo(String info) {

    }

    @Override
    public String getVersionInfo() {
        return null;
    }

    @Override
    public ExtendedIterator<String> listVersionInfo() {
        return null;
    }

    @Override
    public boolean hasVersionInfo(String info) {
        return false;
    }

    @Override
    public void removeVersionInfo(String info) {

    }

    @Override
    public void setLabel(String label, String lang) {

    }

    @Override
    public void addLabel(String label, String lang) {

    }

    @Override
    public void addLabel(Literal label) {

    }

    @Override
    public String getLabel(String lang) {
        return null;
    }

    @Override
    public ExtendedIterator<RDFNode> listLabels(String lang) {
        return null;
    }

    @Override
    public boolean hasLabel(String label, String lang) {
        return false;
    }

    @Override
    public boolean hasLabel(Literal label) {
        return false;
    }

    @Override
    public void removeLabel(String label, String lang) {

    }

    @Override
    public void removeLabel(Literal label) {

    }

    @Override
    public void setComment(String comment, String lang) {

    }

    @Override
    public void addComment(String comment, String lang) {

    }

    @Override
    public void addComment(Literal comment) {

    }

    @Override
    public String getComment(String lang) {
        return null;
    }

    @Override
    public ExtendedIterator<RDFNode> listComments(String lang) {
        return null;
    }

    @Override
    public boolean hasComment(String comment, String lang) {
        return false;
    }

    @Override
    public boolean hasComment(Literal comment) {
        return false;
    }

    @Override
    public void removeComment(String comment, String lang) {

    }

    @Override
    public void removeComment(Literal comment) {

    }

    @Override
    public void setRDFType(Resource cls) {

    }

    @Override
    public void addRDFType(Resource cls) {

    }

    @Override
    public Resource getRDFType() {
        return null;
    }

    @Override
    public Resource getRDFType(boolean direct) {
        return null;
    }

    @Override
    public ExtendedIterator<Resource> listRDFTypes(boolean direct) {
        return null;
    }

    @Override
    public boolean hasRDFType(Resource ontClass, boolean direct) {
        return false;
    }

    @Override
    public boolean hasRDFType(Resource ontClass) {
        return false;
    }

    @Override
    public void removeRDFType(Resource cls) {

    }

    @Override
    public boolean hasRDFType(String uri) {
        return false;
    }

    @Override
    public int getCardinality(Property p) {
        return 0;
    }

    @Override
    public void setPropertyValue(Property property, RDFNode value) {

    }

    @Override
    public RDFNode getPropertyValue(Property property) {
        return null;
    }

    @Override
    public NodeIterator listPropertyValues(Property property) {
        return null;
    }

    @Override
    public void removeProperty(Property property, RDFNode value) {

    }

    @Override
    public void remove() {

    }

    @Override
    public OntProperty asProperty() {
        return null;
    }

    @Override
    public AnnotationProperty asAnnotationProperty() {
        return null;
    }

    @Override
    public ObjectProperty asObjectProperty() {
        return null;
    }

    @Override
    public TransitiveProperty asTransitiveProperty() {
        return null;
    }

    @Override
    public InverseFunctionalProperty asInverseFunctionalProperty() {
        return null;
    }

    @Override
    public SymmetricProperty asSymmetricProperty() {
        return null;
    }

    @Override
    public FunctionalProperty convertToFunctionalProperty() {
        return null;
    }

    @Override
    public DatatypeProperty convertToDatatypeProperty() {
        return null;
    }

    @Override
    public ObjectProperty convertToObjectProperty() {
        return null;
    }

    @Override
    public TransitiveProperty convertToTransitiveProperty() {
        return null;
    }

    @Override
    public InverseFunctionalProperty convertToInverseFunctionalProperty() {
        return null;
    }

    @Override
    public SymmetricProperty convertToSymmetricProperty() {
        return null;
    }

    @Override
    public boolean isFunctionalProperty() {
        return false;
    }

    @Override
    public boolean isDatatypeProperty() {
        return false;
    }

    @Override
    public boolean isIndividual() {
        return false;
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
    public boolean isObjectProperty() {
        return false;
    }

    @Override
    public boolean isTransitiveProperty() {
        return false;
    }

    @Override
    public boolean isInverseFunctionalProperty() {
        return false;
    }

    @Override
    public boolean isSymmetricProperty() {
        return false;
    }

    @Override
    public OntProperty getInverse() {
        return null;
    }

    @Override
    public ExtendedIterator<? extends OntProperty> listInverse() {
        return null;
    }

    @Override
    public boolean hasInverse() {
        return false;
    }

    @Override
    public ExtendedIterator<? extends OntClass> listDeclaringClasses() {
        return null;
    }

    @Override
    public ExtendedIterator<? extends OntClass> listDeclaringClasses(boolean direct) {
        return null;
    }

    @Override
    public ExtendedIterator<Restriction> listReferringRestrictions() {
        return null;
    }

    @Override
    public Node asNode() {
        return null;
    }

    @Override
    public Neo4JObjectProperty fetchIfExists() {
        return null;
    }

    @Override
    public Neo4JObjectProperty create() throws Exception {
        return null;
    }

    @Override
    public Neo4JObjectProperty update() throws Exception {
        return null;
    }

    @Override
    public void delete() throws Exception {

    }


}
