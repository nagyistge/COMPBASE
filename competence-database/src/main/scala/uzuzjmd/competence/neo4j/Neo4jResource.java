package uzuzjmd.competence.neo4j;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.*;
import uzuzjmd.competence.config.MagicStrings;

/**
 * Created by dehne on 09.12.2015.
 */
public class Neo4jResource implements Resource {

    private String id;

    public Neo4jResource(String id) {
        this.id = id;
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
    public Resource inModel(Model m) {
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
        return MagicStrings.PREFIX;
    }

    @Override
    public String getLocalName() {
        return this.id;
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
    public Node asNode() {
        return null;
    }
}
