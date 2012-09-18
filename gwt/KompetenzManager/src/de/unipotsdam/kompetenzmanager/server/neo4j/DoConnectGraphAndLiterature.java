package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;

import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;
import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class DoConnectGraphAndLiterature extends DoNeoLit {

	private DoNeoGraph doNeoGraph;
	private Literature literature;
	private Graph graph;

	public DoConnectGraphAndLiterature(GraphDatabaseService graphDB,
			Index<Node> nodeIndex, RelationshipIndex relIndex,
			Literature literature, Graph graph) {
		super(graphDB, nodeIndex, relIndex);
		this.doNeoGraph = new DoNeoGraph(graphDB, nodeIndex, relIndex);
		this.literature = literature;
		this.graph = graph;
		compute();
	}

	private void compute() {
		for (GraphNode graphNode : graph.nodes) {
			Iterable<Node> nodeIt = this.nodeIndex.get(LIT_NODE_KEY,
					graphNode.label);
			if (nodeIt.iterator().hasNext()) {
				for (LiteratureEntry literatureEntry : literature.literatureEntries) {
					Node litNode = getLitNode(literatureEntry);
					if (litNode != null) {
						nodeIt.iterator()
								.next()
								.createRelationshipTo(litNode, RelTypes.isTagOf);
					}
				}
			}
		}

	}

	@Override
	public Literature dolit() {
		return this.literature;
	}

}
