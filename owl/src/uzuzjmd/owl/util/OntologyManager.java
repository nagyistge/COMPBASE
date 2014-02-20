package uzuzjmd.owl.util;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import thewebsemantic.binding.Jenabean;
import uzuzjmd.owl.competence.ontology.Evidence;
import uzuzjmd.owl.competence.ontology.EvidenceSystem;

import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Alt;
import com.hp.hpl.jena.rdf.model.Bag;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.RSIterator;
import com.hp.hpl.jena.rdf.model.ReifiedStatement;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceF;
import com.hp.hpl.jena.rdf.model.Seq;
import com.hp.hpl.jena.rdf.model.Statement;

public class OntologyManager {
	public static final String prefix = "http://www.uzuzjmd.de/proof-of-concept.owl";

	public void loadOntology() throws IOException {
		// create the base model

		String NS = prefix + "#";
		OntModel m = ModelFactory
				.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		m.read(this.getClass().getResourceAsStream("/proof-of-conceptrdf.owl"),
				NS);

		// create a dummy paper for this example
		OntClass paper = m.getOntClass(NS + "Competence");
		Individual p1 = m.createIndividual(NS + "competence1", paper);

		// // list the asserted types
		// for (Iterator<Resource> i = p1.listRDFTypes(false); i.hasNext(); ) {
		// System.out.println( p1.getURI() + " is asserted in class " + i.next()
		// );
		// }
		//
		// // list the inferred types
		// p1 = m.getIndividual( NS + "competence1" );
		// for (Iterator<Resource> i = p1.listRDFTypes(false); i.hasNext(); ) {
		// System.out.println( p1.getURI() + " is inferred to be in class " +
		// i.next() );
		// }

		Jenabean.instance().bind(m);

		Evidence evidence = new Evidence();
		evidence.setTitel("hello");
		evidence.setCommment("that is my personal comment");
		evidence.setNumber(88);
		evidence.save();

		EvidenceSystem evidenceSystem = new EvidenceSystem();
		evidenceSystem.setTitel("Moodle");
		evidenceSystem.getEvidences().add(evidence);
		evidenceSystem.save();
		
		
		//m.write(System.out);

		NS = "http://uzuzjmd.owl.competence.ontology/";
		// create a dummy paper for this example
		OntClass evidenceClass = m.getOntClass(NS + "Evidence");
		System.out.println("Evidence saved as " + evidenceClass.getURI());
		Individual evidence1 = m.createIndividual(NS + "evidence1",
				evidenceClass);

		// // list the asserted types
		for (Iterator<Resource> i = evidence1.listRDFTypes(false); i.hasNext();) {
			System.out.println(evidence1.getURI() + " is asserted in class "
					+ i.next());
		}

		ObjectProperty property = m.createObjectProperty(NS + "testProperty");
		property.setDomain(evidenceClass);
		property.setRange(evidenceClass);

		m.addLiteral(evidenceClass, property, false);

		// list the inferred types
		p1 = m.getIndividual(NS + "evidence1");
		for (Iterator<Statement> i = evidence1.listProperties(); i.hasNext();) {
			System.out.println(evidence1.getURI()
					+ " is inferred to have properties " + i.next().asTriple());
		}
		
		Individual individual = m.getIndividual(NS + "Evidence/"+ "hello");		
		System.out.println("hello individual number: " + individual.listProperties().next().getLiteral().getInt());
		Iterator<Statement> properties = individual.listProperties();
		properties.next();
		System.out.println("hello individual: comment"+ properties.next().getLiteral().getString());

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
}
