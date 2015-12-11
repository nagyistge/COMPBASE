package uzuzjmd.competence.persistence.owl;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.ontology.OntTools.Path;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.Filter;
import com.hp.hpl.jena.vocabulary.RDFS;
import uzuzjmd.competence.config.MagicStrings;
import uzuzjmd.competence.persistence.abstractlayer.CompOntologyAccessGenericImpl;
import uzuzjmd.competence.persistence.abstractlayer.CompetenceQueries;
import uzuzjmd.competence.persistence.dao.exceptions.OntClassForDaoNotInitializedException;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;
import uzuzjmd.competence.persistence.ontology.CompOntClass;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CompOntologyAccessJenaImpl extends CompOntologyAccessGenericImpl {

	private CompOntologyManagerJenaImpl manager;

	/**
	 * The QueriesObject and Model are dependencies
	 * 
	 * @param m
	 * @param queries
	 * @param compOntologyManager
	 */
	public CompOntologyAccessJenaImpl(OntModel m,
									  CompetenceQueries queries,
									  CompOntologyManagerJenaImpl compOntologyManager) {
		this.queries = queries;
		this.fileUtil = new CompFileUtil(m);
		this.manager = compOntologyManager;
	}

	public CompOntologyManagerJenaImpl getManager() {
		return manager;
	}

	/**
	 *
	 * @param domain
	 * @param range
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
	public Boolean getDataFieldBoolean(String key, Individual individual) {
		return CompOntologyAccessScala.getDataFieldBoolean(key, getManager(), individual);
	}

	@Override
	public java.lang.Long getDataFieldLong(String key, Individual individual) {
		return CompOntologyAccessScala.getDataFieldLong(key, getManager(), individual);
	}

	@Override
	public Integer getDataFieldInt(String key, Individual individual) {
		return CompOntologyAccessScala.getDataFieldInt(key, getManager(), individual);
	}

	@Override
	public String getDataField(String key, Individual individual) {
		try {
			return CompOntologyAccessScala.getDataField(key, getManager(), individual);
		} catch (OntClassForDaoNotInitializedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean getDataFieldBooleanForClass(String key, OntClass individual) {
		return null;
	}

	@Override
	public Integer getDataFieldIntForClass(String key, OntClass individual) {
		return null;
	}

	@Override
	public String getDataFieldForClass(String key, OntClass ontClass) {
		try {
			return CompOntologyAccessScala.getDataFieldForClass(key, getManager(), ontClass);
		} catch (OntClassForDaoNotInitializedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteDataField(String key, Individual individual) {
		CompOntologyAccessScala.deleteDataField(key, getManager(), individual);
	}


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
	public Boolean existsObjectPropertyWithOntClass(OntClass domainClass, Individual rangeIndividual, CompObjectProperties compObjectProperties) {
		return existsObjectPropertyWithIndividual(domainClass.listInstances().toList().get(0).asIndividual(), rangeIndividual, compObjectProperties);
	}



}
