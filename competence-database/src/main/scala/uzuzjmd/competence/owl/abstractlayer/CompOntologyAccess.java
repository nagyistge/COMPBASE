package uzuzjmd.competence.owl.abstractlayer;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.reasoner.ValidityReport;
import uzuzjmd.competence.owl.access.CompFileUtil;
import uzuzjmd.competence.owl.access.CompOntologyManager;
import uzuzjmd.competence.owl.access.OntResult;
import uzuzjmd.competence.owl.ontology.CompObjectProperties;
import uzuzjmd.competence.owl.ontology.CompOntClass;
import uzuzjmd.competence.owl.queries.CompetenceQueries;

import java.util.List;

/**
 * Created by dehne on 04.12.2015.
 */
public interface CompOntologyAccess {
	CompOntologyManager getManager();

	Individual createIndividualForString(
			OntClass ontClass, String individualName,
			Boolean isRead);

	Individual createIndividualForStringWithDefinition(
			OntClass ontClass, String individualName,
			String definition);

	ObjectProperty createObjectProperty(
			CompOntClass domain, CompOntClass range,
			CompObjectProperties propertyName);

	ObjectProperty createObjectPropertyWithIndividual(
			Individual domainIndividual,
			Individual rangeIndividual,
			CompObjectProperties compObjectProperties);

	ObjectProperty deleteObjectPropertyWithIndividual(
			Individual domainIndividual,
			Individual rangeIndividual,
			CompObjectProperties compObjectProperties);

	Boolean existsObjectPropertyWithIndividual(
			Individual domainIndividual,
			Individual rangeIndividual,
			CompObjectProperties compObjectProperties);

	OntClass createOntClass(CompOntClass ontClass,
							Boolean isRead);

	OntClass createOntClassForString(String string,
									 Boolean isRead, String... definitions);

	Individual createSingleTonIndividual(
			OntClass ontclass, Boolean isRead);

	OntClass createSingleTonIndividualWithClass(
			String classname, Boolean isRead,
			String... definitions);

	Individual createSingleTonIndividualWithClass2(
			String classname, Boolean isRead,
			String... definitions);

	OntResult accessSingletonResource(
			String classname, Boolean isRead,
			String... definitions);

	OntResult accessSingletonResourceWithClass(
			CompOntClass compOntClass, Boolean isRead);

	OntClass getClass(CompOntClass compOntClass,
					  Boolean isRead);

	CompFileUtil getFileUtil();

	Individual getIndividualForString(
			String indivString);

	OntClass getOntClassForString(String className);

	CompetenceQueries getQueries();

	List<String> getAllInstanceDefinitions(
			CompOntClass clazz);

	// TODO: Test
	List<String> getShortestSubClassPath(
			OntClass start, OntClass end);

	String validityReportTostring(
			ValidityReport report);
}
