package de.kompetenzmanager.query;

import de.kompetenzmanager.model.LabeledConnection;
import de.kompetenzmanager.model.LabeledElement;
import edu.uci.ics.jung.graph.Graph;

public interface Queryable {

	public Graph<LabeledElement, LabeledConnection> searchForKeyword(String keyword) throws InvalidGraphException;
}
