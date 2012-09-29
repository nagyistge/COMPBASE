package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;
import de.unipotsdam.kompetenzmanager.shared.Literature;

public class DoGetLitForTags extends DoNeoLit {

	private Graph graph;

	public DoGetLitForTags(GraphDatabaseService graphDB, Index<Node> nodeIndex,
			RelationshipIndex relIndex, Graph graph) {
		super(graphDB, nodeIndex, relIndex);
		this.graph = graph;
	}

	@Override
	public synchronized Literature dolit() {
		Literature literature = new Literature();
		for (GraphNode graphNode : graph.nodes) {
			Node node = this.nodeIndex.get(NODE_KEY, graphNode.label).getSingle();
			for (Relationship rel : node.getRelationships(RelTypes.isTagOf)) {
//				literature.literatureEntries.add(convertLitNodeToLitEntry(rel.getEndNode()));
				literature.literatureEntries.add(convertLitNodeToLitEntry(rel.getOtherNode(node)));
			}
		}
		return literature;
	}

}
