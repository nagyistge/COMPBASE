package uzuzjmd.competence.service.rest;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "list")
public class JaxbList<T> extends LinkedList<T> {
	protected List<T> list;

	public JaxbList() {
		// TODO Auto-generated constructor stub
	}

	public JaxbList(List<T> list) {
		this.list = list;
	}

	@XmlElement(name = "item")
	public List<T> getList() {
		return this;
	}

}