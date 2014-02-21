package uzuzjmd.owl.util;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.OWLEntityRemover;

import thewebsemantic.binding.Jenabean;
import thewebsemantic.binding.RdfBean;
import uzuzjmd.owl.competence.ontology.Catchword;
import uzuzjmd.owl.competence.ontology.Competence;
import uzuzjmd.owl.competence.ontology.CompetenceArea;
import uzuzjmd.owl.competence.ontology.CompetenceDescription;
import uzuzjmd.owl.competence.ontology.DescriptionElement;
import uzuzjmd.owl.competence.ontology.Evidence;
import uzuzjmd.owl.competence.ontology.Learner;
import uzuzjmd.owl.competence.ontology.Operator;
import uzuzjmd.owl.persistence.JenaToOwlConvert;

import com.hp.hpl.jena.ontology.OntModel;

public class OntologyManager {
	
	private OntologyUtil util;
	private OntModel m;

	public OntologyManager() {
		this.util = new OntologyUtil();
	}
	
	public void loadOntology() throws IOException {
		// create the base model
		
	}
	
	public void createBaseOntology() {
		m = this.util.initializeOntologyModel();
		initJenaBeans(m);		
		initClasses();
		
		try {
			util.writeOntologyout(m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Classes underneath will be mapped to RDF-Classes.
	 */
	private void initClasses() {
		RdfBean[] collection = new RdfBean[] {new Catchword(), new Competence(), new CompetenceArea(), new CompetenceDescription(), new DescriptionElement(), new Evidence(), new Learner(), new Operator()};
		for (RdfBean bean: collection) {
			bean.save();
		}
				
		// removing null-individuals created : not hacky at all :-)
		removeNullIndividuals();				
	}

	private void removeNullIndividuals() {
		// individuals are not found because of jenabeans shortcomings
		JenaToOwlConvert convert = new JenaToOwlConvert();
		OWLOntology owlontolgy = convert.ModelJenaToOwlConvert(m, "RDF/XML");
		OWLOntologyManager manager = owlontolgy.getOWLOntologyManager();			
		OWLEntityRemover entityRemover = new OWLEntityRemover(manager, Collections.singleton(owlontolgy));
		Set<OWLNamedIndividual> individuals = owlontolgy.getIndividualsInSignature();
		for (OWLNamedIndividual owlNamedIndividual : individuals) {
			System.out.println("to be removed" + owlNamedIndividual.toString());			
			owlNamedIndividual.accept(entityRemover);
		}		
		manager.applyChanges(entityRemover.getChanges());						
		// final ontology is stored as jena-model because triple store....
		m = convert.ModelOwlToJenaConvert(owlontolgy, "TURTLE");
		
	}

	private void initJenaBeans(OntModel m) {
		Jenabean jenabean = Jenabean.instance();
		jenabean.bind(m);
	}

	
}
