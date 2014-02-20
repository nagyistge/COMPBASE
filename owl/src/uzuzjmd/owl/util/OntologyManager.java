package uzuzjmd.owl.util;

import java.util.Iterator;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class OntologyManager {
	public static final String prefix = "http://www.uzuzjmd.de/proof-of-concept.owl";
	
	public void loadOntology() {
		// create the base model
				
		String NS = prefix + "#";
		OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF );
//		OntDocumentManager dm = m.getDocumentManager();
//		dm.addAltEntry( prefix,
//		                "file:" + "/proof-of-concept.owl");		
		m.read( this.getClass().getResourceAsStream("/proof-of-conceptrdf.owl"), NS );

		// create a dummy paper for this example
		OntClass paper = m.getOntClass( NS + "Competence" );
		Individual p1 = m.createIndividual( NS + "competence1", paper );

		// list the asserted types
		for (Iterator<Resource> i = p1.listRDFTypes(false); i.hasNext(); ) {
		    System.out.println( p1.getURI() + " is asserted in class " + i.next() );
		}

		// list the inferred types
//		p1 = inf.getIndividual( NS + "competence1" );
//		for (Iterator<Resource> i = p1.listRDFTypes(false); i.hasNext(); ) {
//		    System.out.println( p1.getURI() + " is inferred to be in class " + i.next() );
//		}
		
	}
}
