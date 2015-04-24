package uzuzjmd.competence.gui.client.shared.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HierarchieChangeSet implements Serializable {

	List<HierarchieChange> elements;

	public HierarchieChangeSet() {
		elements = new LinkedList<HierarchieChange>();
	}

	public List<HierarchieChange> getElements() {
		return elements;
	}

	public void setElements(List<HierarchieChange> elements) {
		this.elements = elements;
	}

}
