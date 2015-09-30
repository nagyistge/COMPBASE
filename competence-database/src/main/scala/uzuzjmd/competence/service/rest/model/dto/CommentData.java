package uzuzjmd.competence.service.rest.model.dto;

public class CommentData {
	private String linkId;
	private String user;
	private String text;
	private String courseContext;
	private String role;

	public CommentData(String linkId, String user, String text, String courseContext, String role) {
		super();
		this.linkId = linkId;
		this.user = user;
		this.text = text;
		this.courseContext = courseContext;
		this.role = role;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCourseContext() {
		return courseContext;
	}

	public void setCourseContext(String courseContext) {
		this.courseContext = courseContext;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
