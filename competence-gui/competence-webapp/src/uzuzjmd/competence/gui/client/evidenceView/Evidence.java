package uzuzjmd.competence.gui.client.evidenceView;

public class Evidence {
	private String printableName;
	private String url;
	private String user;

	public Evidence(String printableName, String url, String user) {
		this.printableName = printableName;
		this.url = url;
		this.user = user;
	}

	public String getPrintableName() {
		return printableName;
	}

	public void setPrintableName(String printableName) {
		this.printableName = printableName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
