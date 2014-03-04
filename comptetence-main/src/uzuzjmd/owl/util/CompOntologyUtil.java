package uzuzjmd.owl.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uzuzjmd.owl.competence.ontology.CompObjectProperties;
import uzuzjmd.owl.competence.ontology.CompOntClass;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.Filter;

public class CompOntologyUtil {

	private OntModel m;

	public CompOntologyUtil(OntModel m) {
		this.m = m;
	}

	/**
	 * creates the individual, if not exists
	 * @param paper
	 * @param individualName
	 * @return
	 */
	public Individual createIndividualForString(OntClass paper,
			String individualName) {
		return m.createIndividual(MagicStrings.PREFIX + individualName, paper);
	}
	
	/**
	 * returns null if individual does not exist
	 * @param indivString
	 * @return
	 */
	public Individual getIndividualForString(String indivString) {
		return m.getIndividual(MagicStrings.PREFIX + indivString);
	}
	


	/**
	 * reads relativ from src dir
	 * 
	 * @param m
	 * @param path
	 */
	public void readFileOntology(String path) {
		m.read(this.getClass().getResourceAsStream("/" + path),
				MagicStrings.PREFIX);
	}

	/**
	 * creates and object porperty or reuses one	 
	 * @param m
	 * @param Domain
	 * @param Range
	 * @param propertyName
	 * @return
	 */	
	public ObjectProperty createObjectProperty(OntClass domain, OntClass range,
			String propertyName) {
		ObjectProperty property = m.createObjectProperty(MagicStrings.PREFIX
				+ propertyName);
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
	public ObjectProperty createObjectProperty(CompOntClass domain,
			CompOntClass range, CompObjectProperties propertyName) {
		OntClass ontClass1 = getClass(domain);
		OntClass ontclass2 = getClass(range);
		return createObjectProperty(ontClass1, ontclass2, propertyName.name());
	}
	
	/**
	 * links the objectProperty to  
	 * @param individual
	 * @param individual2
	 * @param compObjectProperties
	 */
	public ObjectProperty createObjectPropertyWithIndividual(Individual individual, Individual individual2, CompObjectProperties compObjectProperties) {	  
		ObjectProperty result =  createObjectProperty(individual.getOntClass(), individual2.getOntClass(), compObjectProperties.name());
		Resource resourceTyp = result.getRDFType();		
		individual.addProperty(result.asProperty(), resourceTyp);       
		individual2.addProperty(result.asProperty(), resourceTyp);			       		
		return result;
	}
	
	

	/**
	 * 
	 * @param m
	 * @throws IOException
	 */
	public void writeOntologyout(Model m) throws IOException {
		OutputStream out = null;
		try {
			// // XML format - long and verbose
			out = new FileOutputStream(MagicStrings.ONTOLOGYFILE);
			// String preamble =
			// "<?xml version=\"1.0\"?>\r\n\r\n\r\n<!DOCTYPE rdf:RDF [\r\n    <!ENTITY owl \"http://www.w3.org/2002/07/owl#\" >\r\n    <!ENTITY xsd \"http://www.w3.org/2001/XMLSchema#\" >\r\n    <!ENTITY rdfs \"http://www.w3.org/2000/01/rdf-schema#\" >\r\n    <!ENTITY rdf \"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" >\r\n]>\r\n";
			// out.write(preamble.getBytes());
			m.write(out, MagicStrings.ONTOLOGYFORMAT);

			System.out.println("Written Ontology to "
					+ MagicStrings.ONTOLOGYFILE);

			// OR Turtle format - compact and more readable
			// use this variant if you're not sure which to use!
			// out = new FileWriter("mymodel.ttl");
			// m.write(out, "Turtle");
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException ignore) {
					System.out.println(ignore);
					ignore.printStackTrace();
				}
			}
		}
	}

	public OntClass getClass(CompOntClass compOntClass) {
		return createOntClassForString(compOntClass.name());
	}

	/**
	 * creates class or returns class if exists
	 * @param model
	 * @param ontClass
	 */
	public OntClass createOntClass(CompOntClass ontClass) {
		return createOntClassForString(ontClass.name());
	}
	

	/**
	 * creates class or returns class if exists
	 * @param model
	 * @param ontClass
	 */
	public OntClass createOntClassForString(String string) {
		return m.createClass(MagicStrings.PREFIX + string);
	}

	/**
	 * returns null if class does not exist
	 * @param className
	 * @return
	 */
	public OntClass getOntClassForString(String className) {
		OntClass paper = m.getOntClass(MagicStrings.PREFIX + className);
		return paper;
	}
	
	public Set<OntClass> getOperatorsForOntClass(String string) {
		return getRelatedClassesForOntClass(string,  CompObjectProperties.OperatorOf);
	} 
	
	/**
	 * 
	 * This only works if the ObjectProperty is registered within the Class specified by the String
	 * and the ObjectProperty is either point "Range" or "Domain" to the Class specified by the String 
	 * @param string Classname
	 * @param compObjectProperties ObjectPropertyClassifier
	 * @return relatedClasses
	 */
	public Set<OntClass> getRelatedClassesForOntClass(String competenceClassString, final CompObjectProperties compObjectProperties) {
		OntClass competenceClass = m.getOntClass(competenceClassString);		
		List<OntProperty> properties = getRelatedProperties(
				compObjectProperties, competenceClass);
		Set<OntClass> result = new HashSet<OntClass>();
		for (OntProperty ontClass : properties) {			
			result.add(ontClass.getDomain().asClass());
			result.add(ontClass.getRange().asClass());
		}
		result.remove(competenceClass);
		return result;
	}

	private List<OntProperty> getRelatedProperties(
			final CompObjectProperties compObjectProperties,
			OntClass competenceClass) {
		List<OntProperty> properties = competenceClass.listDeclaredProperties().filterKeep(new Filter<OntProperty>() {			
			@Override
			public boolean accept(OntProperty o) {
				return o.equals(m.getObjectProperty(MagicStrings.PREFIX + compObjectProperties.name()).asProperty());
			}
		}).toList();
		return properties;
	} 
	
	/**
	 * Iterates over all the object properties of Type specified and then iterates over all the individuals
	 * over all the individuals linked to them as range or domain except the individuals of Type
	 * OntClass specified
	 * @param compObjectProperties
	 * @param ontClass
	 * @return
	 */
	public Set<Individual> getRelatedIndividuals(OntClass ontClass, final CompObjectProperties compObjectProperties) {
		Set<Individual> individuals = new HashSet<Individual>();
		List<OntProperty> relatedProps = getRelatedProperties(compObjectProperties, ontClass);
		for (OntProperty ontProperty : relatedProps) {
				Set<? extends OntResource> ontRange = ontProperty.listRange().toSet();
				for (OntResource ontResource : ontRange) {
					individuals.add(ontResource.asIndividual());
				}
				Set<? extends OntResource> ontDomain = ontProperty.listDomain().toSet();
				for (OntResource ontResource : ontDomain) {
					if (!ontResource.asIndividual().getOntClass().equals(ontClass)){
						individuals.add(ontResource.asIndividual());
					}
				}
		}		
		return individuals;
	}
	
	public ObjectProperty getObjectPropertyForString(String objectProperty) {
		return m.createObjectProperty(MagicStrings.PREFIX + objectProperty);
	}
	
	
}
