package uzuzjmd.competence.evidence.service.rest.dto;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso(AbstractTreeEntry.class)
public class ActivityEntry extends AbstractTreeEntry {

	private String url;

	public ActivityEntry() {
	}

	public ActivityEntry(String name, String qtip, String icon, String url) {
		super(name, qtip, icon);
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
