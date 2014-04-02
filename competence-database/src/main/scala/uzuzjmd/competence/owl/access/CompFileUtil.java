package uzuzjmd.competence.owl.access;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.hp.hpl.jena.rdf.model.Model;

public class CompFileUtil {

	private Model m;

	public CompFileUtil(Model m) {
		this.m = m;
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
	 * 
	 * @param m
	 * @throws IOException
	 */
	public void writeOntologyout(Model m) throws IOException {
		writeOut(m);
	}

	private void writeOut(Model m) throws FileNotFoundException {
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

	/**
	 * 
	 * @param m
	 * @throws IOException
	 */
	public void writeOntologyout() throws IOException {
		writeOut(m);
	}

}
