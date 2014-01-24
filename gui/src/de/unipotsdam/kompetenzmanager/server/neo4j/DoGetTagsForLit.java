package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;
import de.unipotsdam.kompetenzmanager.shared.Literature;

public abstract class DoGetTagsForLit extends DoNeo implements Do {

	private Literature literature;

	public DoGetTagsForLit(GraphDatabaseService graphDB, Index<Node> nodeIndex,
			RelationshipIndex relIndex, Literature literature) {
		super(graphDB, nodeIndex, relIndex);
		this.literature = literature;	

	}

	@Override
	public synchronized Graph doit() {
		Graph graph = new Graph();
		getTheConnectedGraph(graph);
//		getTheRelationShipInTheGraph(graph);
		return graph;
	}

	protected abstract void getTheConnectedGraph(Graph graph);

	protected synchronized void getTheRelationShipInTheGraph(Graph graph) {
	for (GraphNode graphNode : graph.nodes) {
			for (GraphNode graphNode2 : graph.nodes) {
				if (!graphNode.equals(graphNode2)) {
					DoFindShortestPath doFindShortestPathBetweenResults = new DoFindShortestPath(
							this.graphDb, nodeIndex, this.relIndex,
							graphNode2.getLabel(), graphNode.getLabel());
					graph.mergeWith(doFindShortestPathBetweenResults.doit());
				}
			}
		}
	}

	

}
