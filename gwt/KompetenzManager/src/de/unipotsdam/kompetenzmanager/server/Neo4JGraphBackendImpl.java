package de.unipotsdam.kompetenzmanager.server;

import java.util.Collection;

import com.google.gwt.user.server.rpc.UnexpectedException;

import de.unipotsdam.kompetenzmanager.client.GraphBackend;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoAddNode;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoConnectNodes;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoFindNeighbours;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoFindShortestPath;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoFullGraph;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoRemove;
import de.unipotsdam.kompetenzmanager.server.neo4j.Neo4JStarter;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;

public class Neo4JGraphBackendImpl implements GraphBackend {
	
	private Neo4JStarter neo;
	
	public Neo4JGraphBackendImpl() {
		this.neo = new Neo4JStarter();
	}

	@Override
	public synchronized Graph getFullGraph() {		
		Graph graph = null;
		try {
			graph = neo.doQuery(new DoFullGraph(neo.getGraphDB(), neo.getNodeIndex(),neo.getRelationshipIndex()));
		} catch(UnexpectedException e) {
			e.printStackTrace();
			shutdown();			
		}
		return graph;
		
	}

	@Override
	public synchronized Graph addNode(GraphNode sourceNode, GraphNode newNode,
			String kantenLabel) {		
		Graph graph = neo.doQuery(new DoAddNode(neo.getGraphDB(), neo.getNodeIndex(), neo.getRelationshipIndex(),
				sourceNode, newNode, kantenLabel));
		return getFullGraph();
	}

	@Override
	public synchronized Graph findShortestPath(String keyword) {		
		Graph graph =  neo.doQuery(new DoFindShortestPath(neo.getGraphDB(), neo.getNodeIndex(), neo.getRelationshipIndex(),"rootnode",keyword));		
		if (graph == null) {
			return getFullGraph();
		} else {
			return graph;
		}
	}

	@Override
	public synchronized Graph removeNode(GraphNode targetNode) {
		 this.neo.doQuery(new DoRemove(neo.getGraphDB(), neo.getNodeIndex(), neo.getRelationshipIndex(), targetNode.label));		 
		 return getFullGraph();
	}
	
	public void shutdown() {
		new Neo4JStarter().shutdown();
	}

	@Override
	public Graph findShortestPath(String fromNode, String toNode) {
		Graph graph =  neo.doQuery(new DoFindShortestPath(neo.getGraphDB(), neo.getNodeIndex(),  neo.getRelationshipIndex(),fromNode,toNode));		
		if (graph == null) {
			return getFullGraph();
		} else {
			return graph;
		}
	}

	@Override
	public Graph expandNode(String nodeName) {
		Graph graph = neo.doQuery(new DoFindNeighbours(neo.getGraphDB(), neo.getNodeIndex(),  neo.getRelationshipIndex(),nodeName));		
		graph.mergeWith(getFullGraph());
		System.out.println("neighbourgraph simple is " + graph);
		return graph;
	}

	@Override
	public Graph addNode(Graph graph, GraphNode sourceNode, GraphNode newNode,
			String kantenLabel) {
		Graph result = addNode(sourceNode, newNode, kantenLabel);
		result.intersectWith(graph);
		result.addTriple(sourceNode.label, newNode.label, kantenLabel, false);
		return result;
	}

	@Override
	public Graph findShortestPath(Graph graph, String keyword) {
		Graph result = findShortestPath(keyword);
		graph.intersectWith(result);
		return result;
	}

	@Override
	public Graph removeNode(Graph graph, GraphNode targetNode) {
		Graph result = removeNode(targetNode);
		result.intersectWith(graph);
		result.removeNode(targetNode);
		return result;
	}

	@Override
	public Graph findShortestPath(Graph graph, String fromNode, String toNode) {		
		Graph result = findShortestPath(fromNode, toNode);
		graph.intersectWith(result);
		return result;
	}

	@Override
	public Graph expandNode(Graph graph, String nodeName) {
		Graph result = neo.doQuery(new DoFindNeighbours(neo.getGraphDB(), neo.getNodeIndex(),  neo.getRelationshipIndex(),nodeName));			
		result.mergeWith(graph);
		System.out.println("neighbourgraph is " + result);
		return result;
	}

	@Override
	public Graph connectNodes(Collection<String> graphNodes, String toNode) {
		neo.doQuery(new DoConnectNodes(neo.getGraphDB(), neo.getNodeIndex(),  neo.getRelationshipIndex(), graphNodes,toNode));
		return getFullGraph();
	}

	@Override
	public Graph connectNodes(Graph graph, Collection<String> graphNodes,
			String toNode) {
		neo.doQuery(new DoConnectNodes(neo.getGraphDB(), neo.getNodeIndex(),  neo.getRelationshipIndex(), graphNodes,toNode));
		return getFullGraph().intersectWith(graph);
	}

}
