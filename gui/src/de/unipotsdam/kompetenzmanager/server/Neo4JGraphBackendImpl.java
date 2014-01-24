package de.unipotsdam.kompetenzmanager.server;

import java.io.IOException;
import java.util.Collection;

import com.google.gwt.user.server.rpc.UnexpectedException;

import de.unipotsdam.kompetenzmanager.client.viewcontroller.GraphBackend;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoAddNode;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoAddOrUpdateLiteratureEntry;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoConnectNodes;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoFindNeighbours;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoFindShortestPath;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoFullGraph;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoGetTagsForLabels;
import de.unipotsdam.kompetenzmanager.server.neo4j.DoRemove;
import de.unipotsdam.kompetenzmanager.server.neo4j.Neo4JStarter;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphLiteraturePair;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;
import de.unipotsdam.kompetenzmanager.shared.Literature;
import de.unipotsdam.kompetenzmanager.shared.LiteratureEntry;

public class Neo4JGraphBackendImpl implements GraphBackend {
	
	private Neo4JStarter neo;
	
	public Neo4JGraphBackendImpl() throws IOException {
		this.neo = new Neo4JStarter();
	}

	@Override
	public synchronized Graph getFullGraph()  {		
		Graph graph = null;
		try {
			graph = neo.doQuery(new DoFullGraph(neo.getGraphDB(), neo.getNodeIndex(),neo.getRelationshipIndex()));
		} catch(UnexpectedException e) {
			e.printStackTrace();
			try {
				shutdown();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
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
		 this.neo.doQuery(new DoRemove(neo.getGraphDB(), neo.getNodeIndex(), neo.getRelationshipIndex(), targetNode.getLabel()));		 
		 return getFullGraph();
	}
	
	public synchronized void shutdown() throws IOException {
		new Neo4JStarter().shutdown();
	}

	@Override
	public synchronized Graph findShortestPath(String fromNode, String toNode) {
		Graph graph =  neo.doQuery(new DoFindShortestPath(neo.getGraphDB(), neo.getNodeIndex(),  neo.getRelationshipIndex(),fromNode,toNode));		
		if (graph == null) {
			return getFullGraph();
		} else {
			return graph;
		}
	}

	@Override
	public synchronized Graph expandNode(String nodeName) {
		Graph graph = neo.doQuery(new DoFindNeighbours(neo.getGraphDB(), neo.getNodeIndex(),  neo.getRelationshipIndex(),nodeName));		
		graph.mergeWith(getFullGraph());
		System.out.println("neighbourgraph simple is " + graph);
		return graph;
	}

	@Override
	public synchronized Graph addNode(Graph graph, GraphNode sourceNode, GraphNode newNode,
			String kantenLabel) {
		Graph result = addNode(sourceNode, newNode, kantenLabel);
		result.intersectWith(graph);
		result.addTriple(sourceNode.getLabel(), newNode.getLabel(), kantenLabel, false);
		return result;
	}

	@Override
	public synchronized Graph findShortestPath(Graph graph, String keyword) {
		Graph result = findShortestPath(keyword);
		graph.intersectWith(result);
		return result;
	}

	@Override
	public synchronized Graph removeNode(Graph graph, GraphNode targetNode) {
		Graph result = removeNode(targetNode);
		result.intersectWith(graph);
		result.removeNode(targetNode);
		return result;
	}

	@Override
	public synchronized Graph findShortestPath(Graph graph, String fromNode, String toNode) {		
		Graph result = findShortestPath(fromNode, toNode);
		graph.intersectWith(result);
		return result;
	}

	@Override
	public synchronized Graph expandNode(Graph graph, String nodeName) {
		Graph result = neo.doQuery(new DoFindNeighbours(neo.getGraphDB(), neo.getNodeIndex(),  neo.getRelationshipIndex(),nodeName));			
		result.mergeWith(graph);
		System.out.println("neighbourgraph is " + result);
		return result;
	}

	@Override
	public synchronized Graph connectNodes(Collection<String> graphNodes, String toNode) {
		neo.doQuery(new DoConnectNodes(neo.getGraphDB(), neo.getNodeIndex(),  neo.getRelationshipIndex(), graphNodes,toNode));
		return getFullGraph();
	}

	@Override
	public synchronized Graph connectNodes(Graph graph, Collection<String> graphNodes,
			String toNode) {
		neo.doQuery(new DoConnectNodes(neo.getGraphDB(), neo.getNodeIndex(),  neo.getRelationshipIndex(), graphNodes,toNode));
		return getFullGraph().intersectWith(graph);
	}

	@Override
	@Deprecated
	public synchronized Literature getFullLiterature() {		
		return null;
	}

	@Override
	@Deprecated
	public synchronized Literature getLiteratureForTags(Graph graph) {
		return null;
	}

	@Override
	@Deprecated
	public  synchronized Graph getTagsforLiterature(Literature literature) {
		return null;
	}

	@Override
	public  synchronized Literature addOrUpdateLiteratureEntry(Literature literatureStored, LiteratureEntry literatureEntry) {
		this.neo.doQueryLit(new DoAddOrUpdateLiteratureEntry(neo.getGraphDB(), neo.getNodeIndex(), neo.getRelationshipIndex(), literatureEntry));
		return new Literature(literatureEntry).mergeWith(literatureStored).intersectStrong(getFullLiterature());
//		return getFullLiterature();
	}

	@Override
	@Deprecated
	public  synchronized Literature removeLiteratureEntry(Literature literatureStored, LiteratureEntry literatureEntry) {
		return null;
	}

	@Override
	@Deprecated
	public  synchronized GraphLiteraturePair connectLiteratureToGraph(Literature literature,
			Graph graph) {
		return null;		
	}

	public Graph getTagsforLiterature2(Literature literature) {		
		DoGetTagsForLabels doget = new DoGetTagsForLabels(neo.getGraphDB(),neo.getNodeIndex(), neo.getRelationshipIndex(), literature);
		return doget.doit();
	}





}
