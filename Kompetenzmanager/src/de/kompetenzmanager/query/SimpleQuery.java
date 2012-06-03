package de.kompetenzmanager.query;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import de.kompetenzmanager.model.LabeledConnection;
import de.kompetenzmanager.model.LabeledElement;
import de.kompetenzmanager.model.Thing;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Graphs;

public class SimpleQuery implements Queryable {

	private final DirectedGraph<LabeledElement, LabeledConnection> fullGraph;

	public SimpleQuery(DirectedGraph<LabeledElement, LabeledConnection> fullGraph) {
		this.fullGraph = Graphs.unmodifiableDirectedGraph(fullGraph);
	}

	@Override
	public Graph<LabeledElement, LabeledConnection> searchForKeyword(String keyword) throws InvalidGraphException {
		// Die Elemente im Graph finden, die der Keywordsuche entsprechen
		Collection<LabeledElement> matchingIndividuals = searchIndividualsWithLabel(keyword, fullGraph);

		// Top-Element finden
		Thing top = findTop(fullGraph);

		// Für jedes dieser Elemente den kürzesten Pfad vom Top finden
		DijkstraShortestPath<LabeledElement, LabeledConnection> pathSearch = new DijkstraShortestPath<LabeledElement, LabeledConnection>(fullGraph);
		Set<LabeledConnection> necessaryConnections = new LinkedHashSet<LabeledConnection>();
		for (LabeledElement element : matchingIndividuals) {
			List<LabeledConnection> path = pathSearch.getPath(top, element);
			necessaryConnections.addAll(path);
		}

		// Alle Pfade zu einem neuen Graphen zusammenfügen
		DirectedSparseGraph<LabeledElement, LabeledConnection> result = new DirectedSparseGraph<LabeledElement, LabeledConnection>();
		for (LabeledConnection connection : necessaryConnections) {
			LabeledElement source = fullGraph.getSource(connection);
			LabeledElement dest = fullGraph.getDest(connection);
			result.addEdge(connection, source, dest, EdgeType.DIRECTED);
		}

		return fullGraph;
	}

	Thing findTop(Graph<LabeledElement, LabeledConnection> fullGraph) throws InvalidGraphException {
		for (LabeledElement element : fullGraph.getVertices()) {
			if (element instanceof Thing) {
				return (Thing) element;
			}
		}
		throw new InvalidGraphException("Graph must have a top element");
	}

	Collection<LabeledElement> searchIndividualsWithLabel(String label, Graph<LabeledElement, LabeledConnection> fullGraph) {
		Collection<LabeledElement> resultLabels = new HashSet<LabeledElement>();
		for (LabeledElement element : fullGraph.getVertices()) {
			if (element.getLabel().toLowerCase().contains(label.toLowerCase())) {
				resultLabels.add(element);
			}
		}
		return resultLabels;
	}
}
