package uzuzjmd.competence.shared.activity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CommentData {
	private  Long created;
	private String commentId;
	private String commentedCommentId;
	private String linkId;
	private String user;
	private String text;
	private String courseContext;
	private String role;
	private String competenceId;

	public CommentData() {
	}

	public CommentData(String userId, String text, Long datecreated) {
		this.commentId = datecreated + text;
		this.user = userId;
		this.text = text;
	}

	public CommentData(Long datecreated, String linkId, String user, String text, String courseContext, String role) {
		super();
		this.commentId = datecreated + text;
		this.linkId = linkId;
		this.user = user;
		this.text = text;
		this.courseContext = courseContext;
		this.role = role;
		this.created = datecreated;
	}



	public CommentData(Long datecreated, String competenceId, String text, String userId, String courseContext, String role, String commentedCommentId) {
		this.commentId = datecreated + text;
		this.competenceId = competenceId;
		this.text = text;
		this.user = userId;
		this.courseContext = courseContext;
		this.role = role;
		this.commentedCommentId = commentedCommentId;
		this.created = datecreated;
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

	public String getCommentedCommentId() {
		return commentedCommentId;
	}

	public String getCompetenceId() {
		return competenceId;
	}

	public void setCompetenceId(String competenceId) {
		this.competenceId = competenceId;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}
}
