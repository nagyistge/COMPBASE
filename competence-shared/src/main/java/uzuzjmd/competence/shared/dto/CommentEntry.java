package uzuzjmd.competence.shared.dto;

public class CommentEntry {
	private String userName;
	private String commentName;
	private Long created;

	public CommentEntry() {
	}

	public CommentEntry(String userName, String commentName, Long created) {
		this.userName = userName;
		this.commentName = commentName;
		this.created = created;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCommentName() {
		return commentName;
	}

	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}
}
