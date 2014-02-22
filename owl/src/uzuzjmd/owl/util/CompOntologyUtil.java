package uzuzjmd.owl.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import uzuzjmd.owl.competence.ontology.CompOntClass;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDBFactory;

public class CompOntologyUtil {
	public Individual createIndividualForString(OntModel m, OntClass paper,
			String individualName) {
		return m.createIndividual(MagicStrings.PREFIX + individualName, paper);
	}

	public OntClass getOntClassForString(OntModel m, String className) {
		OntClass paper = m.getOntClass(MagicStrings.PREFIX + className);
		return paper;
	}

	/**
	 * reads relativ from src dir
	 * 
	 * @param m
	 * @param path
	 */
	public void readFileOntology(OntModel m, String path) {
		m.read(this.getClass().getResourceAsStream("/" + path),
				MagicStrings.PREFIX);
	}

	/**
	 * Also creates a database, if it does not exist already If there already
	 * exist one, Nullpointer is thrown
	 * 
	 * @return
	 */
	public OntModel initializeOntologyModel() {
		Dataset dataset = TDBFactory.createDataset(MagicStrings.TDBLocation);
		Model tdb = dataset.getDefaultModel();
		OntModel m = ModelFactory.createOntologyModel(
				OntModelSpec.OWL_MEM_MICRO_RULE_INF, tdb);
		return m;
	}

	public OntModel initializeOntologyModelInMemory() {
		OntModel m = ModelFactory
				.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		return m;
	}

	public ObjectProperty createObjectProperty(OntModel m, OntClass Domain,
			OntClass Range, String propertyName) {
		ObjectProperty property = m.createObjectProperty(MagicStrings.PREFIX
				+ propertyName);
		property.setDomain(Range);
		property.setRange(Domain);
		return property;
	}

	/**
	 * 
	 * @param m
	 * @throws IOException
	 */
	public void writeOntologyout(OntModel m) throws IOException {
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
	
	public void createOntClass(OntModel model, CompOntClass ontClass) {
		model.createClass(MagicStrings.PREFIX+ontClass.name());
	}

}
