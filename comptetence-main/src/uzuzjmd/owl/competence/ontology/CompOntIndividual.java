package uzuzjmd.owl.competence.ontology;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;

import uk.ac.manchester.cs.owl.owlapi.OWLNamedIndividualImpl;

public class CompOntIndividual<T extends CompOntClass> extends OWLNamedIndividualImpl {

	public CompOntIndividual(OWLDataFactory dataFactory, IRI iri) {
		super(dataFactory, iri);
		// TODO Auto-generated constructor stub
	}

}
