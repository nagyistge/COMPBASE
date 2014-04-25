package uzuzjmd.competence.service.rest.dto;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import uzuzjmd.competence.evidence.service.rest.dto.AbstractTreeEntry;

@XmlRootElement(name = "competence")
@XmlSeeAlso(AbstractTreeEntry.class)
public class CompetenceTree extends AbstractTreeEntry {

	private List<CompetenceTree> children;

	public CompetenceTree() {
		children = new LinkedList<CompetenceTree>();
	}

	public CompetenceTree(String name, String qtip, String icon,
			List<CompetenceTree> children) {
		super(name, qtip, icon);
		this.setChildren(children);
	}

	public List<CompetenceTree> getChildren() {
		return children;
	}

	public void setChildren(List<CompetenceTree> children) {
		this.children = children;
	}

}
