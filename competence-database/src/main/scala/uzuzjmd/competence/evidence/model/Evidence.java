package uzuzjmd.competence.evidence.model;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso(uzuzjmd.competence.evidence.model.MoodleEvidence.class)
public class Evidence {

	public Evidence() {
		// TODO Auto-generated constructor stub
	}

	public Evidence(String shortname, String string, String userId,
			String changed) {
		this.shortname = shortname;
		this.url = string;
		this.userId = userId;
		this.changed = changed;
	}

	private String shortname;
	private String url;
	private String userId;
	private String username;
	private String changed;

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getChanged() {
		return changed;
	}

	public void setChanged(String changed) {
		this.changed = changed;
	}
}
