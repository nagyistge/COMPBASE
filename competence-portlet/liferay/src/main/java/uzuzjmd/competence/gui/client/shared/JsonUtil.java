package uzuzjmd.competence.gui.client.shared;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
 * copied from
 * http://stackoverflow.com/questions/7278471/gwt-hashmap-to-from-json
 * 
 * @author julian
 * 
 */
public class JsonUtil {
	public static String toJson(Map<String, String> map) {
		String json = "";
		if (map != null && !map.isEmpty()) {
			JSONObject jsonObj = new JSONObject();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				jsonObj.put(entry.getKey(), new JSONString(entry.getValue()));
			}
			json = jsonObj.toString();
		}
		return json;
	}

	public static Map<String, String> toMap(String jsonStr) {
		JSONValue parsed = JSONParser.parseStrict(jsonStr);
		return toMap(parsed);
	}

	public static Map<String, String> toMap(JSONValue parsed) {
		Map<String, String> map = new HashMap<String, String>();
		JSONObject jsonObj = parsed.isObject();
		if (jsonObj != null) {
			for (String key : jsonObj.keySet()) {
				map.put(key, jsonObj.get(key).toString());
			}
		}
		return map;
	}

	public static Map<String, JSONValue> toJsonMap(JSONValue parsed) {
		Map<String, JSONValue> map = new HashMap<String, JSONValue>();
		JSONObject jsonObj = parsed.isObject();
		if (jsonObj != null) {
			for (String key : jsonObj.keySet()) {
				map.put(key, jsonObj.get(key));
			}
		}
		return map;
	}

}
