package uzuzjmd.competence.gui.client.shared.dto;

import java.util.List;

public class CompetenceLinksView {
	private String abstractLinkId;
	private String evidenceTitel;
	private String evidenceUrl;
	private List<CommentData> comments;
	private Boolean validated;

	public CompetenceLinksView() {
		// TODO Auto-generated constructor stub
	}

	public CompetenceLinksView(String abstractLinkId, String evidenceTitel,
			String evidenceUrl, List<CommentData> comments, Boolean validated) {
		this.abstractLinkId = abstractLinkId;
		this.evidenceTitel = evidenceTitel;
		this.evidenceUrl = evidenceUrl;
		this.comments = comments;
		this.comments.addAll(comments);
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

	public List<CommentData> getComments() {
		return comments;
	}

	public void setComments(List<CommentData> comments) {
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

	public void setAbstractLinkId(String abstractLinkId) {
		this.abstractLinkId = abstractLinkId;
	}

}
