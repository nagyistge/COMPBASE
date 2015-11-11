package uzuzjmd.competence.service.rest.client.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "competenceRoot")
@XmlSeeAlso(AbstractXMLTree.class)
public class CompetenceXMLTree extends
		AbstractXMLTree<CompetenceXMLTree> {

	private Boolean isCompulsory = false;

	public CompetenceXMLTree() {

	}

	public CompetenceXMLTree(String name, String qtip,
			String icon, List<CompetenceXMLTree> children) {
		super(name, qtip, icon, children);
		SortedList<CompetenceXMLTree> sortedCompetences = new SortedList<CompetenceXMLTree>(
				new CompetenceXMLTreeComparator());
		sortedCompetences.addAll(children);
		setChildren(sortedCompetences);
	}

	@XmlElement(name = "competence")
	@Override
	public SortedList<CompetenceXMLTree> getChildren() {
		return super.getChildren();
	}

	public Boolean getIsCompulsory() {
		return isCompulsory;
	}

	public void setIsCompulsory(Boolean isCompulsory) {
		this.isCompulsory = isCompulsory;
	}

	@Override
	public String toString() {
		return "CompetenceXMLTree [isCompulsory="
				+ isCompulsory + ", getChildren()="
				+ getChildren() + ", getIsCompulsory()="
				+ getIsCompulsory() + ", toString()="
				+ super.toString() + ", getName()="
				+ getName() + ", getQtip()=" + getQtip()
				+ ", getIcon()=" + getIcon()
				+ ", hashCode()=" + hashCode()
				+ ", getClass()=" + getClass() + "]";
	}

}
