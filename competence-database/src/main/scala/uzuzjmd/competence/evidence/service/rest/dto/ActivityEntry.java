package uzuzjmd.competence.evidence.service.rest.dto;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso(AbstractTreeEntry.class)
public class ActivityEntry extends AbstractTreeEntry {

	public ActivityEntry() {
	}

	public ActivityEntry(String name, String qtip, String icon) {
		super(name, qtip, icon);
	}

}
