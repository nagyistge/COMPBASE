package uzuzjmd.competence.service.rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import uzuzjmd.competence.view.xml.AbstractXMLTree;
import uzuzjmd.java.collections.SortedList;

@XmlRootElement(name = "catchwordRoot")
@XmlSeeAlso(AbstractXMLTree.class)
public class CatchwordXMLTree extends AbstractXMLTree<CatchwordXMLTree> {

	public CatchwordXMLTree() {

	}

	public CatchwordXMLTree(String name, String qtip, String icon, List<CatchwordXMLTree> children) {
		super(name, qtip, icon, children);

	}

	@XmlElement(name = "catchword")
	@Override
	public SortedList<CatchwordXMLTree> getChildren() {
		return super.getChildren();
	}

}
