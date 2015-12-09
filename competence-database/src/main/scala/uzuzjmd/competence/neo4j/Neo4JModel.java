package uzuzjmd.competence.neo4j;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.shared.Command;
import com.hp.hpl.jena.shared.Lock;
import com.hp.hpl.jena.shared.PrefixMapping;
import com.hp.hpl.jena.shared.ReificationStyle;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by dehne on 07.12.2015.
 */
public class Neo4JModel implements Model {
    @Override
    public long size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ResIterator listSubjects() {
        return null;
    }

    @Override
    public NsIterator listNameSpaces() {
        return null;
    }

    @Override
    public Resource getResource(String uri) {
        return null;
    }

    @Override
    public Property getProperty(String nameSpace, String localName) {
        return null;
    }

    @Override
    public Resource createResource() {
        return null;
    }

    @Override
    public Resource createResource(AnonId id) {
        return null;
    }

    @Override
    public Resource createResource(String uri) {
        return null;
    }

    @Override
    public Property createProperty(String nameSpace, String localName) {
        return null;
    }

    @Override
    public Literal createLiteral(String v, String language) {
        return null;
    }

    @Override
    public Literal createLiteral(String v, boolean wellFormed) {
        return null;
    }

    @Override
    public Literal createTypedLiteral(String lex, RDFDatatype dtype) {
        return null;
    }

    @Override
    public Literal createTypedLiteral(Object value, RDFDatatype dtype) {
        return null;
    }

    @Override
    public Resource getResource(String uri, ResourceF f) {
        return null;
    }

    @Override
    public Property getProperty(String uri) {
        return null;
    }

    @Override
    public Bag getBag(String uri) {
        return null;
    }

    @Override
    public Bag getBag(Resource r) {
        return null;
    }

    @Override
    public Alt getAlt(String uri) {
        return null;
    }

    @Override
    public Alt getAlt(Resource r) {
        return null;
    }

    @Override
    public Seq getSeq(String uri) {
        return null;
    }

    @Override
    public Seq getSeq(Resource r) {
        return null;
    }

    @Override
    public Resource createResource(Resource type) {
        return null;
    }

    @Override
    public RDFNode getRDFNode(Node n) {
        return null;
    }

    @Override
    public Resource createResource(String uri, Resource type) {
        return null;
    }

    @Override
    public Resource createResource(ResourceF f) {
        return null;
    }

    @Override
    public Resource createResource(String uri, ResourceF f) {
        return null;
    }

    @Override
    public Property createProperty(String uri) {
        return null;
    }

    @Override
    public Literal createLiteral(String v) {
        return null;
    }

    @Override
    public Literal createTypedLiteral(boolean v) {
        return null;
    }

    @Override
    public Literal createTypedLiteral(int v) {
        return null;
    }

    @Override
    public Literal createTypedLiteral(long v) {
        return null;
    }

    @Override
    public Literal createTypedLiteral(Calendar d) {
        return null;
    }

    @Override
    public Literal createTypedLiteral(char v) {
        return null;
    }

    @Override
    public Literal createTypedLiteral(float v) {
        return null;
    }

    @Override
    public Literal createTypedLiteral(double v) {
        return null;
    }

    @Override
    public Literal createTypedLiteral(String v) {
        return null;
    }

    @Override
    public Literal createTypedLiteral(Object value) {
        return null;
    }

    @Override
    public Literal createTypedLiteral(String lex, String typeURI) {
        return null;
    }

    @Override
    public Literal createTypedLiteral(Object value, String typeURI) {
        return null;
    }

    @Override
    public Statement createLiteralStatement(Resource s, Property p, boolean o) {
        return null;
    }

    @Override
    public Statement createLiteralStatement(Resource s, Property p, float o) {
        return null;
    }

    @Override
    public Statement createLiteralStatement(Resource s, Property p, double o) {
        return null;
    }

    @Override
    public Statement createLiteralStatement(Resource s, Property p, long o) {
        return null;
    }

    @Override
    public Statement createLiteralStatement(Resource s, Property p, int o) {
        return null;
    }

    @Override
    public Statement createLiteralStatement(Resource s, Property p, char o) {
        return null;
    }

    @Override
    public Statement createLiteralStatement(Resource s, Property p, Object o) {
        return null;
    }

    @Override
    public Statement createStatement(Resource s, Property p, String o) {
        return null;
    }

    @Override
    public Statement createStatement(Resource s, Property p, String o, String l) {
        return null;
    }

    @Override
    public Statement createStatement(Resource s, Property p, String o, boolean wellFormed) {
        return null;
    }

    @Override
    public Statement createStatement(Resource s, Property p, String o, String l, boolean wellFormed) {
        return null;
    }

    @Override
    public Bag createBag() {
        return null;
    }

    @Override
    public Bag createBag(String uri) {
        return null;
    }

    @Override
    public Alt createAlt() {
        return null;
    }

    @Override
    public Alt createAlt(String uri) {
        return null;
    }

    @Override
    public Seq createSeq() {
        return null;
    }

    @Override
    public Seq createSeq(String uri) {
        return null;
    }

    @Override
    public Model add(Resource s, Property p, RDFNode o) {
        return null;
    }

    @Override
    public Model addLiteral(Resource s, Property p, boolean o) {
        return null;
    }

    @Override
    public Model addLiteral(Resource s, Property p, long o) {
        return null;
    }

    @Override
    public Model addLiteral(Resource s, Property p, int o) {
        return null;
    }

    @Override
    public Model addLiteral(Resource s, Property p, char o) {
        return null;
    }

    @Override
    public Model addLiteral(Resource s, Property p, float o) {
        return null;
    }

    @Override
    public Model addLiteral(Resource s, Property p, double o) {
        return null;
    }

    @Override
    public Model addLiteral(Resource s, Property p, Object o) {
        return null;
    }

    @Override
    public Model addLiteral(Resource s, Property p, Literal o) {
        return null;
    }

    @Override
    public Model add(Resource s, Property p, String o) {
        return null;
    }

    @Override
    public Model add(Resource s, Property p, String lex, RDFDatatype datatype) {
        return null;
    }

    @Override
    public Model add(Resource s, Property p, String o, boolean wellFormed) {
        return null;
    }

    @Override
    public Model add(Resource s, Property p, String o, String l) {
        return null;
    }

    @Override
    public Model remove(Resource s, Property p, RDFNode o) {
        return null;
    }

    @Override
    public Model remove(StmtIterator iter) {
        return null;
    }

    @Override
    public Model remove(Model m) {
        return null;
    }

    @Override
    public Model remove(Model m, boolean suppressReifications) {
        return null;
    }

    @Override
    public StmtIterator listLiteralStatements(Resource subject, Property predicate, boolean object) {
        return null;
    }

    @Override
    public StmtIterator listLiteralStatements(Resource subject, Property predicate, char object) {
        return null;
    }

    @Override
    public StmtIterator listLiteralStatements(Resource subject, Property predicate, long object) {
        return null;
    }

    @Override
    public StmtIterator listLiteralStatements(Resource subject, Property predicate, float object) {
        return null;
    }

    @Override
    public StmtIterator listLiteralStatements(Resource subject, Property predicate, double object) {
        return null;
    }

    @Override
    public StmtIterator listStatements(Resource subject, Property predicate, String object) {
        return null;
    }

    @Override
    public StmtIterator listStatements(Resource subject, Property predicate, String object, String lang) {
        return null;
    }

    @Override
    public ResIterator listResourcesWithProperty(Property p, boolean o) {
        return null;
    }

    @Override
    public ResIterator listResourcesWithProperty(Property p, long o) {
        return null;
    }

    @Override
    public ResIterator listResourcesWithProperty(Property p, char o) {
        return null;
    }

    @Override
    public ResIterator listResourcesWithProperty(Property p, float o) {
        return null;
    }

    @Override
    public ResIterator listResourcesWithProperty(Property p, double o) {
        return null;
    }

    @Override
    public ResIterator listResourcesWithProperty(Property p, Object o) {
        return null;
    }

    @Override
    public ResIterator listSubjectsWithProperty(Property p, String o) {
        return null;
    }

    @Override
    public ResIterator listSubjectsWithProperty(Property p, String o, String l) {
        return null;
    }

    @Override
    public boolean containsLiteral(Resource s, Property p, boolean o) {
        return false;
    }

    @Override
    public boolean containsLiteral(Resource s, Property p, long o) {
        return false;
    }

    @Override
    public boolean containsLiteral(Resource s, Property p, int o) {
        return false;
    }

    @Override
    public boolean containsLiteral(Resource s, Property p, char o) {
        return false;
    }

    @Override
    public boolean containsLiteral(Resource s, Property p, float o) {
        return false;
    }

    @Override
    public boolean containsLiteral(Resource s, Property p, double o) {
        return false;
    }

    @Override
    public boolean containsLiteral(Resource s, Property p, Object o) {
        return false;
    }

    @Override
    public boolean contains(Resource s, Property p, String o) {
        return false;
    }

    @Override
    public boolean contains(Resource s, Property p, String o, String l) {
        return false;
    }

    @Override
    public Statement createStatement(Resource s, Property p, RDFNode o) {
        return null;
    }

    @Override
    public RDFList createList() {
        return null;
    }

    @Override
    public RDFList createList(Iterator<? extends RDFNode> members) {
        return null;
    }

    @Override
    public RDFList createList(RDFNode[] members) {
        return null;
    }

    @Override
    public Model add(Statement s) {
        return null;
    }

    @Override
    public Model add(Statement[] statements) {
        return null;
    }

    @Override
    public Model remove(Statement[] statements) {
        return null;
    }

    @Override
    public Model add(List<Statement> statements) {
        return null;
    }

    @Override
    public Model remove(List<Statement> statements) {
        return null;
    }

    @Override
    public Model add(StmtIterator iter) {
        return null;
    }

    @Override
    public Model add(Model m) {
        return null;
    }

    @Override
    public Model add(Model m, boolean suppressReifications) {
        return null;
    }

    @Override
    public Model read(String url) {
        return null;
    }

    @Override
    public Model read(InputStream in, String base) {
        return null;
    }

    @Override
    public Model read(InputStream in, String base, String lang) {
        return null;
    }

    @Override
    public Model read(Reader reader, String base) {
        return null;
    }

    @Override
    public Model read(String url, String lang) {
        return null;
    }

    @Override
    public Model read(Reader reader, String base, String lang) {
        return null;
    }

    @Override
    public Model read(String url, String base, String lang) {
        return null;
    }

    @Override
    public Model write(Writer writer) {
        return null;
    }

    @Override
    public Model write(Writer writer, String lang) {
        return null;
    }

    @Override
    public Model write(Writer writer, String lang, String base) {
        return null;
    }

    @Override
    public Model write(OutputStream out) {
        return null;
    }

    @Override
    public Model write(OutputStream out, String lang) {
        return null;
    }

    @Override
    public Model write(OutputStream out, String lang, String base) {
        return null;
    }

    @Override
    public Model remove(Statement s) {
        return null;
    }

    @Override
    public Statement getRequiredProperty(Resource s, Property p) {
        return null;
    }

    @Override
    public Statement getProperty(Resource s, Property p) {
        return null;
    }

    @Override
    public ResIterator listSubjectsWithProperty(Property p) {
        return null;
    }

    @Override
    public ResIterator listResourcesWithProperty(Property p) {
        return null;
    }

    @Override
    public ResIterator listSubjectsWithProperty(Property p, RDFNode o) {
        return null;
    }

    @Override
    public ResIterator listResourcesWithProperty(Property p, RDFNode o) {
        return null;
    }

    @Override
    public NodeIterator listObjects() {
        return null;
    }

    @Override
    public NodeIterator listObjectsOfProperty(Property p) {
        return null;
    }

    @Override
    public NodeIterator listObjectsOfProperty(Resource s, Property p) {
        return null;
    }

    @Override
    public boolean contains(Resource s, Property p) {
        return false;
    }

    @Override
    public boolean containsResource(RDFNode r) {
        return false;
    }

    @Override
    public boolean contains(Resource s, Property p, RDFNode o) {
        return false;
    }

    @Override
    public boolean contains(Statement s) {
        return false;
    }

    @Override
    public boolean containsAny(StmtIterator iter) {
        return false;
    }

    @Override
    public boolean containsAll(StmtIterator iter) {
        return false;
    }

    @Override
    public boolean containsAny(Model model) {
        return false;
    }

    @Override
    public boolean containsAll(Model model) {
        return false;
    }

    @Override
    public boolean isReified(Statement s) {
        return false;
    }

    @Override
    public Resource getAnyReifiedStatement(Statement s) {
        return null;
    }

    @Override
    public void removeAllReifications(Statement s) {

    }

    @Override
    public void removeReification(ReifiedStatement rs) {

    }

    @Override
    public StmtIterator listStatements() {
        return null;
    }

    @Override
    public StmtIterator listStatements(Selector s) {
        return null;
    }

    @Override
    public StmtIterator listStatements(Resource s, Property p, RDFNode o) {
        return null;
    }

    @Override
    public ReifiedStatement createReifiedStatement(Statement s) {
        return null;
    }

    @Override
    public ReifiedStatement createReifiedStatement(String uri, Statement s) {
        return null;
    }

    @Override
    public RSIterator listReifiedStatements() {
        return null;
    }

    @Override
    public RSIterator listReifiedStatements(Statement st) {
        return null;
    }

    @Override
    public ReificationStyle getReificationStyle() {
        return null;
    }

    @Override
    public Model query(Selector s) {
        return null;
    }

    @Override
    public Model union(Model model) {
        return null;
    }

    @Override
    public Model intersection(Model model) {
        return null;
    }

    @Override
    public Model difference(Model model) {
        return null;
    }

    @Override
    public Model begin() {
        return null;
    }

    @Override
    public Model abort() {
        return null;
    }

    @Override
    public Model commit() {
        return null;
    }

    @Override
    public Object executeInTransaction(Command cmd) {
        return null;
    }

    @Override
    public boolean independent() {
        return false;
    }

    @Override
    public boolean supportsTransactions() {
        return false;
    }

    @Override
    public boolean supportsSetOperations() {
        return false;
    }

    @Override
    public boolean isIsomorphicWith(Model g) {
        return false;
    }

    @Override
    public void close() {

    }

    @Override
    public Lock getLock() {
        return null;
    }

    @Override
    public Model register(ModelChangedListener listener) {
        return null;
    }

    @Override
    public Model unregister(ModelChangedListener listener) {
        return null;
    }

    @Override
    public Model notifyEvent(Object e) {
        return null;
    }

    @Override
    public Model removeAll() {
        return null;
    }

    @Override
    public Model removeAll(Resource s, Property p, RDFNode r) {
        return null;
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public void enterCriticalSection(boolean readLockRequested) {

    }

    @Override
    public void leaveCriticalSection() {

    }

    @Override
    public Statement asStatement(Triple t) {
        return null;
    }

    @Override
    public Graph getGraph() {
        return null;
    }

    @Override
    public RDFNode asRDFNode(Node n) {
        return null;
    }

    @Override
    public Resource wrapAsResource(Node n) {
        return null;
    }

    @Override
    public PrefixMapping setNsPrefix(String prefix, String uri) {
        return null;
    }

    @Override
    public PrefixMapping removeNsPrefix(String prefix) {
        return null;
    }

    @Override
    public PrefixMapping setNsPrefixes(PrefixMapping other) {
        return null;
    }

    @Override
    public PrefixMapping setNsPrefixes(Map<String, String> map) {
        return null;
    }

    @Override
    public PrefixMapping withDefaultMappings(PrefixMapping map) {
        return null;
    }

    @Override
    public String getNsPrefixURI(String prefix) {
        return null;
    }

    @Override
    public String getNsURIPrefix(String uri) {
        return null;
    }

    @Override
    public Map<String, String> getNsPrefixMap() {
        return null;
    }

    @Override
    public String expandPrefix(String prefixed) {
        return null;
    }

    @Override
    public String shortForm(String uri) {
        return null;
    }

    @Override
    public String qnameFor(String uri) {
        return null;
    }

    @Override
    public PrefixMapping lock() {
        return null;
    }

    @Override
    public boolean samePrefixMappingAs(PrefixMapping other) {
        return false;
    }

    @Override
    public RDFReader getReader() {
        return null;
    }

    @Override
    public RDFReader getReader(String lang) {
        return null;
    }

    @Override
    public String setReaderClassName(String lang, String className) {
        return null;
    }

    @Override
    public void resetRDFReaderF() {

    }

    @Override
    public String removeReader(String lang) throws IllegalArgumentException {
        return null;
    }

    @Override
    public RDFWriter getWriter() {
        return null;
    }

    @Override
    public RDFWriter getWriter(String lang) {
        return null;
    }

    @Override
    public String setWriterClassName(String lang, String className) {
        return null;
    }

    @Override
    public void resetRDFWriterF() {

    }

    @Override
    public String removeWriter(String lang) throws IllegalArgumentException {
        return null;
    }
}
