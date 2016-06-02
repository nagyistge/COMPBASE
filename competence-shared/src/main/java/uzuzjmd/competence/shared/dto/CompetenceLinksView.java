package uzuzjmd.competence.shared.dto;

import java.util.LinkedList;
import java.util.List;

public class CompetenceLinksView {
	private String abstractLinkId;
	private String evidenceTitel;
	private String evidenceUrl;
	private List<CommentEntry> comments;
	private Boolean validated;

	public CompetenceLinksView() {
		// TODO Auto-generated constructor stub
		this.comments = new LinkedList<CommentEntry>();
	}

	public CompetenceLinksView(String abstractLinkId, String evidenceTitel,
			String evidenceUrl, List<CommentEntry> comments, Boolean validated) {
		this.abstractLinkId = abstractLinkId;
		this.evidenceTitel = evidenceTitel;
		this.evidenceUrl = evidenceUrl;
		this.comments = new LinkedList<CommentEntry>();
		if (comments != null) {
			this.comments.addAll(comments);
		}

		// for (CommentEntry commentEntry : comments) {
		// comments.add(commentEntry);
		// }
		this.validated = validated;
	}

	public String getEvidenceTitel() {
		return evidenceTitel;
	}

	public void setEvidenceTitel(String evidenceTitel) {
		this.evidenceTitel = evidenceTitel;
	}

	public String getEvidenceUrl() {
		return evidenceUrl;
	}

	public void setEvidenceUrl(String evidenceUrl) {
		this.evidenceUrl = evidenceUrl;
	}

	public List<CommentEntry> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntry> comments) {
		this.comments = comments;
	}

	public Boolean getValidated() {
		return validated;
	}

	public void setValidated(Boolean validated) {
		this.validated = validated;
	}

	public String getAbstractLinkId() {
		return abstractLinkId;
	}



	public int compareTo(Object arg0) {
		CompetenceLinksView toCompare = (CompetenceLinksView) arg0;
		if (toCompare.getEvidenceTitel().equals(this.getEvidenceTitel())) {
			return 0;
		}
		return toCompare.getEvidenceTitel().hashCode() > this
				.getEvidenceTitel().hashCode() ? 1 : -1;
	}

}
