package uzuzjmd.competence.service.rest.dto;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * This class wraps a list to be easily accessible by service framworks
 * @param <T>
 */
@XmlRootElement(name = "list")
public class JaxbList<T> extends LinkedList<T> {
	protected List<T> list;

	public JaxbList() {
	}

	public JaxbList(List<T> list) {
		this.list = list;
	}

	@XmlElement(name = "item")
	public List<T> getList() {
		return this;
	}

}