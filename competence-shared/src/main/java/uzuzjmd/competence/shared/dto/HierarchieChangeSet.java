package uzuzjmd.competence.shared.dto;

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

	public List<String> convertToListString() {
		LinkedList<String> result = new LinkedList<String>();
		for (HierarchieChange changeelement : getElements()) {
			result.add(changeelement.getOldClass() + ":"
					+ changeelement.getNodeSelected() + ":"
					+ changeelement.getNewClass());
		}

		return result;
	}

	public HierarchieChangeSet convertListToModel(List<String> encodedSet) {
		HierarchieChangeSet result = new HierarchieChangeSet();
		for (String string : encodedSet) {
			String[] splitted = string.split(":");
			result.getElements()
					.add(new HierarchieChange(splitted[0], splitted[2],
							splitted[1]));
		}
		return result;
	}
}
