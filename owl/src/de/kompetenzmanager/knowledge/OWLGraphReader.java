package de.kompetenzmanager.knowledge;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

public class OWLGraphReader {

	private final OWLDataFactory dataFactory;
	private final OWLReasoner reasoner;
	private final PrefixManager pm;

	public OWLGraphReader(OWLDataFactory dataFactory, OWLReasoner reasoner, PrefixManager pm) {
		this.dataFactory = dataFactory;
		this.reasoner = reasoner;
		this.pm = pm;
	}

	Collection<OWLIndividual> searchIndividualsWithLabel(String label) {
		OWLDataProperty labelProperty = dataFactory.getOWLDataProperty("label", pm);
		OWLClass thingClass = dataFactory.getOWLThing();
		// Alle Indidividuen auslesen
		NodeSet<OWLNamedIndividual> labelIndividuals = reasoner.getInstances(thingClass, false);
		// Alle geholten Individuen auf Ähnlichkeit mit dem Suchbegriff vergleichen und Treffer sichern
		Collection<OWLIndividual> resultLabels = new HashSet<OWLIndividual>();
		for (Node<OWLNamedIndividual> labelIndividual : labelIndividuals) {
			for (OWLNamedIndividual oneIndividual : labelIndividual.getEntities()) {
				Set<OWLLiteral> values = reasoner.getDataPropertyValues(oneIndividual, labelProperty);
				for (OWLLiteral value : values) {
					if (value.toString().toLowerCase().contains(label.toLowerCase())) {
						resultLabels.add(oneIndividual);
					}
				}
			}
		}
		// Gefunde Individuen zurückgeben
		return resultLabels;
	}
}
