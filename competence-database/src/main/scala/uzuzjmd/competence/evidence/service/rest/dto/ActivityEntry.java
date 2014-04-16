package uzuzjmd.competence.evidence.service.rest.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.sun.xml.internal.txw2.annotation.XmlAttribute;

@XmlSeeAlso(AbstractTreeEntry.class)
public class ActivityEntry extends AbstractTreeEntry {

	private String url;

	public ActivityEntry() {
	}

	public ActivityEntry(String name, String qtip, String icon, String url) {
		super(name, qtip, icon);
		this.url = url;
	}

	@XmlAttribute
	@XmlElement(name = "moodleUrl")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
