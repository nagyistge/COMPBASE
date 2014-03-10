package uzuzjmd.owl.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import uzuzjmd.owl.competence.ontology.CompObjectProperties;
import uzuzjmd.owl.competence.ontology.CompOntClass;
import uzuzjmd.owl.competence.queries.CompetenceQueries;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.URIref;

public class CompOntologyUtil {

	static final Logger logger = LogManager.getLogger(CompOntologyUtil.class.getName());
	private OntModel m;
	private CompetenceQueries queries;
	

		
	public CompOntologyUtil(OntModel m, CompetenceQueries queries) {
		this.m = m;
		this.queries = queries;
	}
	
	public CompetenceQueries getQueries() {
		return queries;
	}
	
	
	public OntModel getM() {
		return m;
	}

	/**
	 * creates the individual, if not exists
	 * @param paper
	 * @param individualName
	 * @return
	 */
	public Individual createIndividualForString(OntClass paper,
			String individualName) {		
		return m.createIndividual(encode(MagicStrings.PREFIX + individualName), paper);				
	}
	
	/**
	 * creates the individual, if not exists
	 * @param paper
	 * @param individualName
	 * @return
	 */
	public Individual createIndividualForStringWithDefinition(OntClass ontClass,
			String individualName,String definition) {		
		Individual individual = m.createIndividual(encode(MagicStrings.PREFIX + individualName), ontClass);
		individual.addLiteral(m.createProperty(MagicStrings.PREFIX+"definition"), definition);
		return individual;
	}
	
	
	/**
	 * returns null if individual does not exist
	 * @param indivString
	 * @return
	 */
	public Individual getIndividualForString(String indivString) {
		return m.getIndividual(encode(MagicStrings.PREFIX + indivString));
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
	public ObjectProperty createObjectPropertyWithIndividual(Individual domainIndividual, Individual rangeIndividual, CompObjectProperties compObjectProperties) {	  
		ObjectProperty result =  getObjectPropertyForString(compObjectProperties.name());				
		domainIndividual.addProperty(result.asObjectProperty(), rangeIndividual);			
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
	public OntClass createOntClassForString(String string, String ... definitions) {
		OntClass paper = m.createClass(encode(MagicStrings.PREFIX + string));
		if (definitions != null && definitions.length > 0) {
			paper.addLiteral(m.createProperty(MagicStrings.PREFIX+"definition"), definitions[0]);
		}
		return paper;		
	}

	/**
	 * returns null if class does not exist
	 * @param className
	 * @return
	 */
	public OntClass getOntClassForString(String className) {
		OntClass paper = m.getOntClass(encode(MagicStrings.PREFIX + className));				
		return paper;
	}
	
	
	
	public ObjectProperty getObjectPropertyForString(String objectProperty) {
		return m.createObjectProperty(encode(MagicStrings.PREFIX + objectProperty));
	}
	
	private String encode(String string) {
	//	return string;
		return URIref.encode(string);
	}
	
	public Individual createSingleTonIndividual(OntClass ontclass) {
		return createIndividualForString(ontclass, "I"+ontclass.getLocalName());
	}
	
	
}
