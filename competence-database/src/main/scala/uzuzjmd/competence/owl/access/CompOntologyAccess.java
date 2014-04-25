package uzuzjmd.competence.owl.access;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import uzuzjmd.competence.owl.ontology.CompObjectProperties;
import uzuzjmd.competence.owl.ontology.CompOntClass;
import uzuzjmd.competence.owl.queries.CompetenceQueries;

import com.hp.hpl.jena.iri.IRI;
import com.hp.hpl.jena.iri.IRIFactory;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;

public class CompOntologyAccess {

	/**
	 * init Logger
	 */
	static final Logger logger = LogManager.getLogger(CompOntologyAccess.class
			.getName());
	private CompFileUtil fileUtil;

	private CompOntologyManager manager;
	private CompetenceQueries queries;

	/**
	 * The QueriesObject and Model are dependencies
	 * 
	 * @param m
	 * @param queries
	 * @param compOntologyManager
	 */
	public CompOntologyAccess(OntModel m, CompetenceQueries queries,
			CompOntologyManager compOntologyManager) {
		this.queries = queries;
		this.fileUtil = new CompFileUtil(m);
		this.manager = compOntologyManager;
	}

	/**
	 * creates the individual, if not exists
	 * 
	 * @param classNameWithoutPrefix
	 * @param individualName
	 * @return
	 */
	public Individual createIndividualForString(
			OntClass classNameWithoutPrefix, String individualName) {

		return manager.getM().createIndividual(encode(individualName),
				classNameWithoutPrefix);
	}

	/**
	 * creates the individual, if not exists
	 * 
	 * @param paper
	 * @param individualName
	 * @return
	 */
	public Individual createIndividualForStringWithDefinition(
			OntClass ontClass, String individualName, String definition) {

		Individual individual = manager.getM().createIndividual(
				encode(individualName), ontClass);
		individual.addLiteral(manager.getM().createProperty("definition"),
				definition);
		return individual;
	}

	/**
	 * @param domain
	 * @param range
	 * @param propertyName
	 * @return
	 */
	public ObjectProperty createObjectProperty(CompOntClass domain,
			CompOntClass range, CompObjectProperties propertyName) {

		OntClass ontClass1 = getClass(domain);
		OntClass ontclass2 = getClass(range);
		return createObjectProperty(ontClass1, ontclass2, propertyName.name());
	}

	/**
	 * creates and object porperty or reuses one
	 * 
	 * @param m
	 * @param Domain
	 * @param Range
	 * @param propertyName
	 * @return
	 */
	public ObjectProperty createObjectProperty(OntClass domain, OntClass range,
			String propertyName) {

		ObjectProperty property = manager.getM().createObjectProperty(
				propertyName);
		property.setDomain(domain);
		property.setRange(range);
		return property;
	}

	/**
	 * links the objectProperty to
	 * 
	 * @param individual
	 * @param individual2
	 * @param compObjectProperties
	 */
	public ObjectProperty createObjectPropertyWithIndividual(
			Individual domainIndividual, Individual rangeIndividual,
			CompObjectProperties compObjectProperties) {

		ObjectProperty result = getObjectPropertyForString(compObjectProperties
				.name());
		domainIndividual
				.addProperty(result.asObjectProperty(), rangeIndividual);
		return result;
	}

	/**
	 * creates class or returns class if exists
	 * 
	 * @param model
	 * @param ontClass
	 */
	public OntClass createOntClass(CompOntClass ontClass) {
		return createOntClassForString(ontClass.name());
	}

	/**
	 * creates class or returns class if exists
	 * 
	 * @param model
	 * @param ontClass
	 */
	public OntClass createOntClassForString(String string,
			String... definitions) {
		OntClass paper = manager.getM().createClass(encode(string));
		if (definitions != null && definitions.length > 0) {
			paper.addLiteral(manager.getM()
					.createProperty(encode("definition")), definitions[0]);
		}
		return paper;
	}

	/**
	 * convenienceMethod for adding the Prefix that specifies the individual for
	 * singleton classes
	 * 
	 * @param ontclass
	 * @return
	 */
	public Individual createSingleTonIndividual(OntClass ontclass) {
		return createIndividualForString(ontclass, MagicStrings.SINGLETONPREFIX
				+ ontclass.getLocalName());
	}

	/**
	 * convenience method for accessing the underlying individual but only
	 * String given
	 * 
	 * @param ontclass
	 * @return
	 */
	public Individual createSingleTonIndividual(String ontclass) {
		return createIndividualForString(getOntClassForString(ontclass),
				MagicStrings.SINGLETONPREFIX + ontclass);
	}

	public OntClass createSingleTonIndividualWithClass(String classname,
			String... definitions) {
		OntClass classOnt = createOntClassForString(classname, definitions);
		createSingleTonIndividual(classOnt);
		return classOnt;
	}

	public Individual createSingleTonIndividualWithClass2(String classname,
			String... definitions) {
		OntClass classOnt = createOntClassForString(classname, definitions);
		return createSingleTonIndividual(classOnt);
	}

	private String encode(String string) {
		if (string.startsWith(MagicStrings.PREFIX)) {
			System.out.println("das ist nicht ok");
		}
		string = string.replaceAll(" ", "_").replaceAll("[\u0000-\u001f]", "");
		IRIFactory factory = IRIFactory.uriImplementation();
		IRI iri = factory.construct(string);
		return MagicStrings.PREFIX + iri.toString();
	}

	public OntClass getClass(CompOntClass compOntClass) {
		return createOntClassForString(compOntClass.name());
	}

	public CompFileUtil getFileUtil() {
		return fileUtil;
	}

	/**
	 * returns null if individual does not exist
	 * 
	 * @param indivString
	 * @return
	 */
	public Individual getIndividualForString(String indivString) {
		return manager.getM().getIndividual(encode(indivString));
	}

	public ObjectProperty getObjectPropertyForString(String objectProperty) {
		return manager.getM().createObjectProperty(encode(objectProperty));
	}

	/**
	 * returns null if class does not exist
	 * 
	 * @param className
	 * @return
	 */
	public OntClass getOntClassForString(String className) {
		OntClass paper = manager.getM().getOntClass(encode(className));
		return paper;
	}

	/**
	 * In order to use the queries directly
	 * 
	 * @return
	 */
	public CompetenceQueries getQueries() {
		return queries;
	}

}
