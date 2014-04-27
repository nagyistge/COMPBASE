package uzuzjmd.competence.service.rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import uzuzjmd.competence.view.xml.AbstractXMLTree;

@XmlRootElement(name = "catchword")
@XmlSeeAlso(AbstractXMLTree.class)
public class CatchwordXMLTree extends AbstractXMLTree<CatchwordXMLTree> {

	public CatchwordXMLTree() {

	}

	public CatchwordXMLTree(String name, String qtip, String icon,
			List<CatchwordXMLTree> children) {
		super(name, qtip, icon, children);

	}

}
