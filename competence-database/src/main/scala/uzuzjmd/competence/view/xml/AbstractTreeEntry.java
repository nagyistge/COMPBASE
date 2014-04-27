package uzuzjmd.competence.view.xml;

import javax.xml.bind.annotation.XmlAttribute;

import uzuzjmd.competence.evidence.service.rest.dto.UserTree;

/**
 * This is the mapping class for the GWT-Ext-Tree component produces xml fit for
 * display in the tree XMl-mappbare DTOs für den eigenen REST-Service
 * 
 * @author julian
 * 
 */
public class AbstractTreeEntry {
	private String name;
	private String qtip;
	private String icon;

	public AbstractTreeEntry() {

	}

	public AbstractTreeEntry(String name, String qtip, String icon) {
		this.name = name;
		this.qtip = qtip;
		this.icon = icon;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	@XmlAttribute
	public String getQtip() {
		return qtip;
	}

	public void setQtip(String qtip) {
		this.qtip = qtip;
	}

	@XmlAttribute
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public boolean equals(Object arg0) {
		return ((UserTree) arg0).getName().equals(this.getName());
	}

	@Override
	public int hashCode() {
		return getName().hashCode();
	}
}
