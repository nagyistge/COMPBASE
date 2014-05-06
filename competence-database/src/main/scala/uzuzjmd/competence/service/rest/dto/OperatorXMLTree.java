package uzuzjmd.competence.service.rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import uzuzjmd.competence.view.xml.AbstractXMLTree;

@XmlRootElement(name = "operatorRoot")
@XmlSeeAlso(AbstractXMLTree.class)
public class OperatorXMLTree extends AbstractXMLTree<OperatorXMLTree> {

	public OperatorXMLTree() {

	}

	public OperatorXMLTree(String name, String qtip, String icon, List<OperatorXMLTree> children) {
		super(name, qtip, icon, children);

	}

	@XmlElement(name = "operator")
	@Override
	public List<OperatorXMLTree> getChildren() {
		return super.getChildren();
	}

}
