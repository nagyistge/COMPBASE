package uzuzjmd.competence.gui.shared.widgets;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class JavascriptUtil {

	/**
	 * Converts Java Repr�sentation to JSON for now the labels and the
	 * directions are ignored
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
			JSONString label = new JSONString(triple.label);
			obj.put("node1", fromNode);
			obj.put("node2", toNode);
			obj.put("directed", JSONBoolean.getInstance(triple.directed));
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
