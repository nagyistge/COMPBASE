package uzuzjmd.service.competence.rest;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TestPojo {

	private String name;

	private Map jsonmap;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map getJsonmap() {
		return jsonmap;
	}

	public void setJsonmap(Map jsonmap) {
		this.jsonmap = jsonmap;
	}
}
