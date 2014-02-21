package uzuzjmd.owl.util;

import java.io.FileWriter;
import java.io.IOException;

import org.semanticweb.owlapi.model.OWLOntology;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class OntologyUtil {
	public Individual createIndividualForString(OntModel m, OntClass paper,
			String individualName) {
		return m.createIndividual(MagicStrings.PREFIX + individualName , paper);
	}

	public OntClass getOntClassForString(OntModel m, String className) {
		OntClass paper = m.getOntClass(MagicStrings.PREFIX + className);
		return paper;
	}

	public void readFileOntology(OntModel m) {
		m.read(this.getClass().getResourceAsStream("/proof-of-conceptrdf.owl"),
				MagicStrings.PREFIX);
	}

	public OntModel initializeOntologyModel() {
		OntModel m = ModelFactory
				.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		return m;
	}
	
	public ObjectProperty createObjectProperty(OntModel m, OntClass Domain,
			OntClass Range, String propertyName) {
		ObjectProperty property = m.createObjectProperty(MagicStrings.PREFIX + propertyName);
		property.setDomain(Range);
		property.setRange(Domain);
		return property;
	}
	
	public void writeOntologyout(OntModel m) throws IOException {
		FileWriter out = null;
		try {
			// // XML format - long and verbose
			// out = new FileWriter( "mymodel.xml" );
			// m.write( out, "RDF/XML-ABBREV" );

			// OR Turtle format - compact and more readable
			// use this variant if you're not sure which to use!
			out = new FileWriter("mymodel.ttl");
			m.write(out, "Turtle");
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException ignore) {
				}
			}
		}
	}
	
	public OntModel convertOWLAPIToJena(OWLOntology owlOntology) {
		
		return null;
		
	}

}
