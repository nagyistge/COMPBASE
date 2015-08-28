package uzuzjmd.competence.service.rest;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MapWrapper<KEY, VALUE> {
	private HashMap<KEY, VALUE> map;

	public HashMap<KEY, VALUE> getMap() {
		return map;
	}

	public void setMap(HashMap<KEY, VALUE> map) {
		this.map = map;
	}
}
