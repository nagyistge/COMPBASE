package de.unipotsdam.kompetenzmanager.server.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
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
	}

	private void compute() {
		for (GraphNode graphNode : graph.nodes) {
			Node gnode = this.nodeIndex.get(NODE_KEY, graphNode.label)
					.getSingle();
			for (LiteratureEntry literatureEntry : literature.literatureEntries) {
				Node litNode = getLitNode(literatureEntry);
				if (litNode != null && gnode != null) {
					// for presentation needs to work
					// for (Relationship formerRel :
					// gnode.getRelationships(RelTypes.isTagOf)) {
					// formerRel.delete();
					// }
					Relationship rel = gnode.createRelationshipTo(litNode,
							RelTypes.isTagOf);
					this.relIndex
							.add(rel,
									REL_KEY,
									createRelIndex(gnode, litNode, "",
											RelTypes.isTagOf));
				} else {
					System.err.println("could not find both nodes to connnect");
				}
			}

		}

	}

	@Override
	public Literature dolit() {
		compute();
		return this.literature;
	}

}
