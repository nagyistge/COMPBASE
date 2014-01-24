package de.unipotsdam.kompetenzmanager.client.viewcontroller;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import de.unipotsdam.kompetenzmanager.shared.Graph;
import de.unipotsdam.kompetenzmanager.shared.GraphNode;
import de.unipotsdam.kompetenzmanager.shared.GraphTriple;

public class JavascriptUtil {
	
	/**
	 * Converts Java Repräsentation to JSON
	 * for now the labels and the directions are ignored
	 * 
	 * @return
	 */
	public JSONObject toJSON(Graph graph) {
		JSONObject json = new JSONObject();
		JSONArray triples = new JSONArray();
		int i = 0;
		for (GraphTriple triple : graph.triples) {
			JSONObject obj = new JSONObject();
			JSONString fromNode = new JSONString(triple.fromNode);
			JSONString toNode = new JSONString(triple.toNode);
//			JSONString label = new JSONString(triple.label);
			JSONString label = new JSONString("");
			obj.put("node1", fromNode);
			obj.put("node2", toNode);
//			obj.put("directed", JSONBoolean.getInstance(triple.directed));
			obj.put("directed", JSONBoolean.getInstance(false));
			obj.put("label", label);			
			triples.set(i, obj);
			i++;
		}
		json.put("graph", triples);	
		JSONArray nodes = new JSONArray();
		int j = 0;
		for (GraphNode node : graph.nodes) {
			JSONString nodeLabel = new JSONString(node.getLabel());
			JSONObject obj = new JSONObject();
			obj.put("node", nodeLabel);
			nodes.set(j, obj);
			j++;
		}
		json.put("nodes", nodes);
		return json;
	}
	
}

