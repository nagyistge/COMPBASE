package uzuzjmd.competence.shared.activity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement
public class CommentData {
	// the date when the comment was created
	private  Long created;
	// the id of the comment
	private String commentId;
	// the id of the comment this comment is linked to
	private String commentedCommentId;
	// the id of the abstract link this comment is attached to
	private String linkId;
	// the id of the user
	private String user;
	// the plain text of the comment
	private String text;
	// the id of the course the link is attached to
	private String courseContext;
	// the role of the creator (teacher or student) -> should be refactored
	private String role;
	// the id of the competence the comment is related to
	private String competenceId;

	public CommentData() {
	}

	public CommentData(String userId, String text, Long dateCreated) {
		this.commentId = dateCreated + text;
		this.user = userId;
		this.text = text;
	}

	public CommentData(Long dateCreated, String linkId, String user, String text, String courseContext, String role) {
		super();
		this.commentId = dateCreated + text;
		this.linkId = linkId;
		this.user = user;
		this.text = text;
		this.courseContext = courseContext;
		this.role = role;
		this.created = dateCreated;
	}



	public CommentData(Long dateCreated, String competenceId, String text, String userId, String courseContext, String role, String commentedCommentId) {
		this.commentId = dateCreated + text;
		this.competenceId = competenceId;
		this.text = text;
		this.user = userId;
		this.courseContext = courseContext;
		this.role = role;
		this.commentedCommentId = commentedCommentId;
		this.created = dateCreated;
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
