package uzuzjmd.competence.owl.access;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.hp.hpl.jena.rdf.model.Model;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import uzuzjmd.competence.owl.abstractlayer.CompOntologyAccess;
import uzuzjmd.competence.owl.dao.exceptions.OntClassForDaoNotInitializedException;
import uzuzjmd.competence.owl.ontology.CompObjectProperties;
import uzuzjmd.competence.owl.ontology.CompOntClass;
import uzuzjmd.competence.owl.queries.CompetenceQueries;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.OntTools;
import com.hp.hpl.jena.ontology.OntTools.Path;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.Filter;
import com.hp.hpl.jena.vocabulary.RDFS;

public class CompOntologyAccessJenaImpl extends CompOntologyAccessGenericImpl {


	/**
	 * The QueriesObject and Model are dependencies
	 * 
	 * @param m
	 * @param queries
	 * @param compOntologyManager
	 */
	public CompOntologyAccessJenaImpl(OntModel m,
									  CompetenceQueries queries,
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
	@Override
	public Individual createIndividualForString(
			OntClass ontClass, String individualName,
			Boolean isRead) {
		if (individualName == null) {
			throw new Error(
					"individual name should not be null");
		}
		if (isRead) {
			return manager.getM().getIndividual(
					encode(individualName));
		} else {
			return manager.getM().createIndividual(
					encode(individualName), ontClass);
		}
	}

	/**
	 * creates the individual, if not exists
	 * 
	 * @param paper
	 * @param individualName
	 * @return
	 */
	@Override
	public Individual createIndividualForStringWithDefinition(
			OntClass ontClass, String individualName,
			String definition) {

		Individual individual = manager.getM()
				.createIndividual(encode(individualName),
						ontClass);
		individual.addLiteral(manager.getM()
				.createProperty("definition"), definition);
		return individual;
	}

	/**
	 * @param domain
	 * @param range
	 * @param propertyName
	 * @return
	 */
	@Override
	public ObjectProperty createObjectProperty(
			CompOntClass domain, CompOntClass range,
			CompObjectProperties propertyName) {

		OntClass ontClass1 = getClass(domain, false);
		OntClass ontclass2 = getClass(range, false);
		return createObjectProperty(ontClass1, ontclass2,
				propertyName.name());
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
	private ObjectProperty createObjectProperty(
			OntClass domain, OntClass range,
			String propertyName) {

		ObjectProperty property = manager.getM()
				.createObjectProperty(encode(propertyName));
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
	@Override
	public ObjectProperty createObjectPropertyWithIndividual(
			Individual domainIndividual,
			Individual rangeIndividual,
			CompObjectProperties compObjectProperties) {

		ObjectProperty result = createObjectPropertyForString(compObjectProperties
				.name());
		domainIndividual.addProperty(
				result.asObjectProperty(), rangeIndividual);
		return result;
	}

	/**
	 * delete the link
	 * 
	 * @param individual
	 * @param individual2
	 * @param compObjectProperties
	 */
	@Override
	public ObjectProperty deleteObjectPropertyWithIndividual(
			Individual domainIndividual,
			Individual rangeIndividual,
			CompObjectProperties compObjectProperties) {
		ObjectProperty result = getObjectPropertyForString(compObjectProperties
				.name());
		domainIndividual.removeProperty(
				result.asObjectProperty(), rangeIndividual);
		return result;
	}

	/**
	 * checks if relationship exists
	 * 
	 * @param individual
	 * @param individual2
	 * @param compObjectProperties
	 */
	@Override
	public Boolean existsObjectPropertyWithIndividual(
			Individual domainIndividual,
			final Individual rangeIndividual,
			CompObjectProperties compObjectProperties) {
		if ((domainIndividual == null)
				|| (rangeIndividual == null)
				|| (compObjectProperties == null)) {
			return false;
		}

		ObjectProperty result = getObjectPropertyForString(compObjectProperties
				.name());
		ExtendedIterator<Statement> linkedIndividuals = domainIndividual
				.listProperties(result.asProperty())
				.filterKeep(new Filter<Statement>() {
					@Override
					public boolean accept(Statement o) {
						return o.getResource()
								.getLocalName()
								.equals(rangeIndividual
										.asResource()
										.getLocalName());
					}
				});
		Boolean exists = !linkedIndividuals.toSet()
				.isEmpty();
		return exists;
	}

	/**
	 * creates class or returns class if exists
	 * 
	 * @param model
	 * @param ontClass
	 */
	@Override
	public OntClass createOntClass(CompOntClass ontClass,
								   Boolean isRead) {
		return createOntClassForString(ontClass.name(),
				isRead);
	}

	/**
	 * creates class or returns class if exists
	 * 
	 * @param string
	 * @param isRead
	 */
	@Override
	public OntClass createOntClassForString(String string,
											Boolean isRead, String... definitions) {
		if (string.equals("")) {
			return null;
		}
		OntClass paper = null;
		try {
			if (isRead) {
				paper = manager.getM().getOntClass(
						encode(string));
			} else {
				paper = manager.getM().createClass(
						encode(string));
			}
		} catch (NullPointerException e) {
			System.out.println("und deine mudda");
		}
		if (definitions != null && definitions.length > 0) {
			paper.addLiteral(
					manager.getM().createProperty(
							encode("definition")),
					definitions[0]);
		}
		return paper;
	}

	private String encode(String string) {
		return CompOntologyAccessScala.encode(string);
	}

	/**
	 * convenienceMethod for adding the Prefix that specifies the individual for
	 * singleton classes
	 * 
	 * @param ontclass
	 * @return
	 */
	@Override
	public Individual createSingleTonIndividual(
			OntClass ontclass, Boolean isRead) {
		if (ontclass == null) {
			return null;
		}
		String singletonstring = MagicStrings.SINGLETONPREFIX
				+ ontclass.getURI().substring(
						MagicStrings.PREFIX.length(),
						ontclass.getURI().length());
		return createIndividualForString(ontclass,
				singletonstring, isRead);
	}

	@Override
	public OntClass createSingleTonIndividualWithClass(
			String classname, Boolean isRead,
			String... definitions) {
		OntClass classOnt = createOntClassForString(
				classname, isRead, definitions);
		createSingleTonIndividual(classOnt, isRead);
		return classOnt;
	}

	@Override
	public Individual createSingleTonIndividualWithClass2(
			String classname, Boolean isRead,
			String... definitions) {
		OntClass classOnt = createOntClassForString(
				classname, isRead, definitions);
		return createSingleTonIndividual(classOnt, isRead);
	}

	@Override
	public OntResult accessSingletonResource(
			String classname, Boolean isRead,
			String... definitions) {
		if (classname.startsWith("I")) {
			logger.trace("trying to get SingletonRessource but Id given (including prefix I) instead of definition");
		}

		OntClass classOnt = createOntClassForString(
				classname, isRead, definitions);
		Individual individual = createSingleTonIndividual(
				classOnt, isRead);

		return new OntResult(individual, classOnt);
	}

	@Override
	public OntResult accessSingletonResourceWithClass(
			CompOntClass compOntClass, Boolean isRead) {
		OntClass classOnt = createOntClass(compOntClass,
				isRead);
		Individual individual = createSingleTonIndividual(
				classOnt, isRead);
		return new OntResult(individual, classOnt);
	}

	@Override
	public OntClass getClass(CompOntClass compOntClass,
							 Boolean isRead) {
		return createOntClassForString(compOntClass.name(),
				isRead);
	}



	@Override
	public CompFileUtil getFileUtil() {
		return fileUtil;
	}

	/**
	 * returns null if individual does not exist
	 * 
	 * @param indivString
	 * @return
	 */
	@Override
	public Individual getIndividualForString(
			String indivString) {
		return manager.getM().getIndividual(
				encode(indivString));
	}

	private ObjectProperty getObjectPropertyForString(
			String objectProperty) {
		// manager.getM().getObjectProperty(uri)
		return manager.getM().getObjectProperty(
				encode(objectProperty));

	}

	private ObjectProperty createObjectPropertyForString(
			String objectProperty) {
		// manager.getM().getObjectProperty(uri)
		return manager.getM().createObjectProperty(
				encode(objectProperty));
	}

	/**
	 * returns null if class does not exist
	 * 
	 * @param className
	 * @return
	 */
	@Override
	public OntClass getOntClassForString(String className) {
		manager.sync();
		String encoded = encode(className);
		OntClass paper = manager.getM()
				.getOntClass(encoded);
		return paper;
	}

	/**
	 * In order to use the queries directly
	 * 
	 * @return
	 */
	@Override
	public CompetenceQueries getQueries() {

		return queries;
	}

	@Override
	public List<String> getAllInstanceDefinitions(
			CompOntClass clazz) {

		List<String> result = new LinkedList<String>();
		OntClass learningProjectTemplateClass = getOntClassForString(clazz
				.name());
		if (learningProjectTemplateClass == null) {
			try {
				throw new OntClassForDaoNotInitializedException();
			} catch (OntClassForDaoNotInitializedException e) {
				logger.error(e);
				e.printStackTrace();
			}
		}
		ExtendedIterator<? extends OntResource> instances = learningProjectTemplateClass
				.listInstances();
		while (instances.hasNext()) {
			OntResource res = instances.next();
			OntProperty iProperty = manager.getM()
					.getOntProperty(
							MagicStrings.PREFIX
									+ "definition");
			RDFNode value = res.getPropertyValue(iProperty);
			if (value != null) {
				String definitionValue = value.asNode()
						.getLiteralValue().toString()
						.replaceAll("[\n\r]", "")
						.replaceAll("[\n]", "");
				result.add(definitionValue);
			}
		}
		return result;
	}

	// TODO: Test
	@Override
	public List<String> getShortestSubClassPath(
			OntClass start, OntClass end) {
		Filter<Statement> onPath = new Filter<Statement>() {

			@Override
			public boolean accept(Statement o) {
				return o.getPredicate().equals(
						RDFS.subClassOf);
			}
		};
		List<String> result = new LinkedList<String>();
		Path resultPath = OntTools.findShortestPath(
				manager.getM(), start, end, onPath);
		Iterator<Statement> statementIt = resultPath
				.iterator();
		while (statementIt.hasNext()) {
			result.add(statementIt.next().getSubject()
					.getLocalName());
		}
		return result;
	}

	@Override
	public String validityReportTostring(
			ValidityReport report) {
		String result = "";
		for (Iterator<Report> i = report.getReports(); i
				.hasNext();) {
			result += (" - " + i.next());
		}
		return result;
	}

	@Override
	public Model getModel() {
		return null;
	}
}
