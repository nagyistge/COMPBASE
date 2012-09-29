package de.unipotsdam.kompetenzmanager.server.neo4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.RelationshipIndex;
import org.neo4j.kernel.Traversal;

import com.google.gwt.dev.util.collect.Lists;

import de.unipotsdam.kompetenzmanager.shared.Graph;

public class DoFindShortestPath extends DoNeoGraph {

	private String fromNode;
	private String toNode;

	public DoFindShortestPath(GraphDatabaseService graphDB,
			Index<Node> nodeIndex, RelationshipIndex relationshipIndex, String fromNode, String toNode) {
		super(graphDB, nodeIndex, relationshipIndex);
		this.fromNode = fromNode;
		this.toNode = toNode;		 
	}

	@Override
	public synchronized Graph doit() {
		RelTypes assoc = RelTypes.subclassOf;
		Node startNode = this.nodeIndex.get(NODE_KEY, fromNode).getSingle();
//		Node endNode = this.nodeIndex.get(NODE_KEY, toNode).getSingle();
		Node endNode = findEndNode(toNode);
		if (endNode == null) {
			return null;
		}
		Graph graph = findShortestPath(startNode, endNode, assoc);		
		return  graph;
	}

	private Graph findShortestPath(Node startNode, Node endNode, RelTypes relTypes) {
		PathFinder<Path> finder = GraphAlgoFactory.shortestPath(
		        Traversal.expanderForTypes(relTypes, Direction.BOTH ), maxDepth );				
		//todo implement like by using a node table and a like iterator in queryutil
		Path paths = finder.findSinglePath(startNode, endNode);
		Graph graph = convertPathToGraph(paths);
		return graph;
	}

	private Graph convertPathToGraph(Path paths) {
		return convertRelationShipsToGraph(convertIteratorToList(paths.relationships()));
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
