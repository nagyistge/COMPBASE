package de.unipotsdam.kompetenzmanager.server;

import java.util.Collection;

import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;
import org.neo4j.kernel.Traversal;

import de.unipotsdam.kompetenzmanager.server.neo4j.DoNeo;
import de.unipotsdam.kompetenzmanager.server.neo4j.RelTypes;
import de.unipotsdam.kompetenzmanager.shared.Graph;

public class DoFindShortestPath extends DoNeo {

	private String fromNode;
	private String toNode;

	public DoFindShortestPath(GraphDatabaseService graphDB,
			Index<Node> nodeIndex, String fromNode, String toNode) {
		super(graphDB, nodeIndex);
		this.fromNode = fromNode;
		this.toNode = toNode;		 
	}

	@Override
	public Graph doit() {
		PathFinder<Path> finder = GraphAlgoFactory.shortestPath(
		        Traversal.expanderForTypes( RelTypes.assoziatedWith, Direction.BOTH ), maxDepth );				
		Node startNode = this.nodeIndex.get(NODE_KEY, fromNode).getSingle();
//		Node endNode = this.nodeIndex.get(NODE_KEY, toNode).getSingle();
		Node endNode = findEndNode(toNode);
		if (endNode == null) {
			return null;
		}
		//todo implement like by using a node table and a like iterator in queryutil
		Path paths = finder.findSinglePath(startNode, endNode);
		return convertPathToGraph(paths);
	}

	private Graph convertPathToGraph(Path paths) {
		return convertRelationShipsToGraph(paths.relationships());
	}
	
	private Node findEndNode(String toFind) {
		Iterable<String> nodeKeys = getTableNode().getPropertyKeys();
		for (String label : nodeKeys) {				
			if (queryUtil.myLike(label, toFind)) {
				return this.nodeIndex.get(NODE_KEY, label).getSingle();
			}
		}
		return null;
	}

	

}
