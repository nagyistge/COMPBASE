package uzuzjmd.owl.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import uzuzjmd.owl.competence.ontology.CompOntClass;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;

public class CompOntologyUtil {
	
	private OntModel m;

	public CompOntologyUtil(OntModel m) {
		this.m = m;
	}
	
	
	public Individual createIndividualForString(OntClass paper,
			String individualName) {
		return m.createIndividual(MagicStrings.PREFIX + individualName, paper);
	}

	public OntClass getOntClassForString(String className) {
		OntClass paper = m.getOntClass(MagicStrings.PREFIX + className);
		return paper;
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
	 * should not be used directly anymore. Use Enums instead!
	 * @param m
	 * @param Domain
	 * @param Range
	 * @param propertyName
	 * @return
	 */
	@Deprecated
	public ObjectProperty createObjectProperty(OntClass domain,
			OntClass range, String propertyName) {
		ObjectProperty property = m.createObjectProperty(MagicStrings.PREFIX
				+ propertyName);
		property.setDomain(domain);
		property.setRange(range);
		return property;
	}
	
	public ObjectProperty createObjectProperty(CompOntClass domain,
			CompOntClass range, String propertyName) {		
		OntClass ontClass1 = getClass(domain);
		OntClass ontclass2 = getClass(range);
		return createObjectProperty(ontClass1,ontclass2, propertyName);		
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
			//String preamble = "<?xml version=\"1.0\"?>\r\n\r\n\r\n<!DOCTYPE rdf:RDF [\r\n    <!ENTITY owl \"http://www.w3.org/2002/07/owl#\" >\r\n    <!ENTITY xsd \"http://www.w3.org/2001/XMLSchema#\" >\r\n    <!ENTITY rdfs \"http://www.w3.org/2000/01/rdf-schema#\" >\r\n    <!ENTITY rdf \"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" >\r\n]>\r\n";			
			//out.write(preamble.getBytes());
			m.write(out, MagicStrings.ONTOLOGYFORMAT);

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
		return getOntClassForString(compOntClass.name());
	}
	
	public void createOntClass(OntModel model, CompOntClass ontClass) {
		model.createClass(MagicStrings.PREFIX+ontClass.name());
	}

}
