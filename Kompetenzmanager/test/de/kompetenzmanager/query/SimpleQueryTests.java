package de.kompetenzmanager.query;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.kompetenzmanager.model.LabeledConnection;
import de.kompetenzmanager.model.LabeledElement;
import de.kompetenzmanager.model.Thing;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class SimpleQueryTests {

	@Test
	public void shouldFindTwoPaths() throws InvalidGraphException {
		DirectedGraph<LabeledElement, LabeledConnection> graph = new DirectedSparseGraph<LabeledElement, LabeledConnection>();
		Thing top = new Thing();
		graph.addEdge(new LabeledConnection("label"), top, new LabeledElement("individual"), EdgeType.DIRECTED);
		graph.addEdge(new LabeledConnection("label"), top, new LabeledElement("individual"), EdgeType.DIRECTED);

		SimpleQuery sut = new SimpleQuery(graph);
		Graph<LabeledElement, LabeledConnection> result = sut.searchForKeyword("individual");

		assertEquals(3, result.getVertexCount());
		assertEquals(2, result.getEdgeCount());
	}

	@Test
	public void shouldFindNoPaths() throws InvalidGraphException {
		DirectedGraph<LabeledElement, LabeledConnection> graph = new DirectedSparseGraph<LabeledElement, LabeledConnection>();
		Thing top = new Thing();
		graph.addEdge(new LabeledConnection("label"), top, new LabeledElement("individual"), EdgeType.DIRECTED);
		graph.addEdge(new LabeledConnection("label"), top, new LabeledElement("individual"), EdgeType.DIRECTED);

		SimpleQuery sut = new SimpleQuery(graph);
		Graph<LabeledElement, LabeledConnection> result = sut.searchForKeyword("not here");

		assertEquals(3, result.getVertexCount());
		assertEquals(2, result.getEdgeCount());
	}
}
