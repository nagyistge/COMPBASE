package de.unipotsdam.kompetenzmanager.server;

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
		Node endNode = this.nodeIndex.get(NODE_KEY, toNode).getSingle();
		//todo implement like by using a node table and a like iterator in queryutil
		Path paths = finder.findSinglePath(startNode, endNode);
		return convertPathToGraph(paths);
	}

	private Graph convertPathToGraph(Path paths) {
		return convertRelationShipsToGraph(paths.relationships());
	}

	

}
