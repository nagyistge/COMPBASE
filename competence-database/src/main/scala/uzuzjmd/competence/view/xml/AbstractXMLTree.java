package uzuzjmd.competence.view.xml;

import java.util.LinkedList;
import java.util.List;

/**
 * This class is usefull because it can be mapped by jersey (whereas mapping the
 * scala trees I am not so sure)
 * 
 * @author julian
 * 
 */
public class AbstractXMLTree<T extends AbstractXMLTree<T>> extends
		AbstractTreeEntry {

	private List<T> children;

	public AbstractXMLTree() {
		children = new LinkedList<T>();
	}

	public AbstractXMLTree(String name, String qtip, String icon,
			List<T> children) {
		super(name, qtip, icon);
		this.setChildren(children);
	}

	public List<T> getChildren() {
		return children;
	}

	public void setChildren(List<T> children) {
		this.children = children;
	}

}
