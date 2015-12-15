package uzuzjmd.competence.persistence.abstractlayer;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import uzuzjmd.competence.persistence.owl.CompFileUtil;
import uzuzjmd.competence.persistence.ontology.CompObjectProperties;
import uzuzjmd.competence.persistence.ontology.CompOntClass;

import java.util.List;

/**
 * Created by dehne on 04.12.2015.
 *
 * This class provides a utility in order to access the persistenceLayer.
 *
 * It is currently implemented by a TDB (jena ontology) persistence
 * AND a NEO4j query based persistence.
 */
public interface CompOntologyAccess {

	CompOntologyManager getManager();

	Individual createIndividualForString(
			OntClass ontClass, String individualName,
			Boolean isRead);

	Individual createIndividualForStringWithDefinition(
			OntClass ontClass, String individualName,
			String definition);

	/**
	 * Creates a restriction with the given domains and range for the given edge (ObjectProperty)
	 * @param domain
	 * @param range
	 * @param propertyName
     * @return
     */
	ObjectProperty createObjectProperty(
			CompOntClass domain, CompOntClass range,
			CompObjectProperties propertyName);

	/**
	 * Creates an edge between the two individuals
	 * @param domainIndividual
	 * @param rangeIndividual
	 * @param compObjectProperties
     * @return
     */
	ObjectProperty createObjectPropertyWithIndividual(
			Individual domainIndividual,
			Individual rangeIndividual,
			CompObjectProperties compObjectProperties);

	/**
	 * deletes an edge between two individuals
	 * @param domainIndividual
	 * @param rangeIndividual
	 * @param compObjectProperties
     * @return
     */
	ObjectProperty deleteObjectPropertyWithIndividual(
			Individual domainIndividual,
			Individual rangeIndividual,
			CompObjectProperties compObjectProperties);

	/**
	 * checks if edge between two individuals exists
	 * @param domainIndividual
	 * @param rangeIndividual
	 * @param compObjectProperties
	 * @return
	 */
	Boolean existsObjectPropertyWithIndividual(
			Individual domainIndividual,
			Individual rangeIndividual,
			CompObjectProperties compObjectProperties);

	/**
	 * checks if edge between two individuals one of them a singleton exists
	 * @param domainIndividual
	 * @param rangeIndividual
	 * @param compObjectProperties
	 * @return
	 */
	Boolean existsObjectPropertyWithOntClass(
			OntClass domainIndividual,
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

	OntResult accessSingletonResource(
			String classname, Boolean isRead,
			String... definitions);

	/**
	 * No id might be given if the root of singleton hierarchy is accessed
	 * Should only return nodes that are classes not individuals
	 * @param compOntClass
	 * @param isRead
     * @return
     */
	OntResult accessSingletonResourceWithClass(
			CompOntClass compOntClass, Boolean isRead);

	OntClass getClass(CompOntClass compOntClass,
					  Boolean isRead);

	CompFileUtil getFileUtil();

	Individual getIndividualForString(
			String indivString);

	OntClass getOntClassForString(String className);

	CompetenceQueries getQueries();

	/**
	 * returns the definition of all instances of a (non-singleton) class / label
	 * @param clazz
	 * @return
     */
	List<String> getAllInstanceDefinitions(
			CompOntClass clazz);


	/**
	 * returns the shortest subpath form startClass to endClass (both singletons)
	 * @param start
	 * @param end
     * @return
     */
	List<String> getShortestSubClassPath(
			OntClass start, OntClass end);


	/**
	 * get Boolean attributes for normal Nodes / Individuals
	 * @param key
	 * @param individual
     * @return
     */
	Boolean getDataFieldBoolean(String key, Individual individual);


	/**
	 * get Boolean attributes for normal Nodes / Individuals
	 * @param key
	 * @param individual
	 * @return
	 */
	java.lang.Long getDataFieldLong(String key, Individual individual);

	/**
	 * get Integer attributes for normal Nodes / Individuals
	 * @param key
	 * @param individual
     * @return
     */
	Integer getDataFieldInt(String key, Individual individual);

	/**
	 * get String attributes for normal Nodes / Individuals
	 * @param key
	 * @param individual
     * @return
     */
	String getDataField(String key, Individual individual);

	/**
	 * get Boolean attributes for singletonClasses from the connected
	 * individual node
	 * @param key
	 * @param ontClass
     * @return
     */
	Boolean getDataFieldBooleanForClass(String key, OntClass ontClass);

	/**
	 * get integer attributes for singletonClasses from the connected
	 * individual node
	 * @param key
	 * @param ontClass
     * @return
     */
	Integer getDataFieldIntForClass(String key, OntClass ontClass);

	/**
	 * get String attributes for singletonClasses from the connected
	 * individual node
	 * @param key
	 * @param ontClass
     * @return
     */
	String getDataFieldForClass(String key, OntClass ontClass);


	/**
	 * delete DataField in normal Node/Individual
	 * @param key
	 * @return
     */
	void deleteDataField(String key, Individual individual);

}
