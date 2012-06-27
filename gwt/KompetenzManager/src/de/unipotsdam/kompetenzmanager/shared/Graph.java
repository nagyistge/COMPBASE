package de.unipotsdam.kompetenzmanager.shared;

import java.util.HashSet;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Graph implements IsSerializable {
	public HashSet<GraphTriple> triples;
	public HashSet<GraphNode> nodes;

	public Graph() {
		this.triples = new HashSet<GraphTriple>();
		this.nodes = new HashSet<GraphNode>();
	}

	/**
	 * add new Connection on Graph
	 * @param fromNode
	 * @param toNode
	 * @param label
	 * @param directed
	 */
	public void addTriple(String fromNode, String toNode, String label,
			Boolean directed) {
		GraphTriple graphTriple = new GraphTriple(fromNode, toNode, label,
				directed);
		GraphNode fromNode1 = new GraphNode(fromNode);
		GraphNode toNode1 = new GraphNode(toNode);
		this.triples.add(graphTriple);
		this.nodes.add(fromNode1);
		this.nodes.add(toNode1);
	}	

}
