package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;
import de.unipotsdam.kompetenzmanager.shared.Literature;

public class DoGetTagsForLabels extends DoGetTagsForLit {

	public DoGetTagsForLabels(GraphDatabaseService graphDB,
			Index<Node> nodeIndex, RelationshipIndex relIndex,
			Literature literature) {
		super(graphDB, nodeIndex, relIndex, literature);

	}

	@Override
	protected synchronized void getTheConnectedGraph(Graph graph) {
		for (GraphNode node : graph.nodes) {
			DoFindShortestPath doFindShortestPath = new DoFindShortestPath(
					this.graphDb, nodeIndex, this.relIndex, "rootnode",
					node.getLabel());
			graph.mergeWith(doFindShortestPath.doit());
		}
	}

}
