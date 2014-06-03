package uzuzjmd.competence.service.rest.dto;

import java.util.List;

import uzuzjmd.competence.service.rest.client.CommentEntry;
import uzuzjmd.java.collections.SortedList;

public class CompetenceLinksView implements Comparable {
	private String abstractLinkId;
	private String evidenceTitel;
	private String evidenceUrl;
	private SortedList<CommentEntry> comments;
	private Boolean validated;

	public CompetenceLinksView(String abstractLinkId, String evidenceTitel, String evidenceUrl, List<CommentEntry> comments, Boolean validated) {
		this.abstractLinkId = abstractLinkId;
		this.evidenceTitel = evidenceTitel;
		this.evidenceUrl = evidenceUrl;
		this.comments = new SortedList<CommentEntry>(new CommentEntryComparator());
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

	public List<CommentEntry> getComments() {
		return comments;
	}

	public void setComments(SortedList<CommentEntry> comments) {
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

	@Override
	public int compareTo(Object arg0) {
		CompetenceLinksView toCompare = (CompetenceLinksView) arg0;
		if (toCompare.getEvidenceTitel().equals(this.getEvidenceTitel())) {
			return 0;
		}
		return toCompare.getEvidenceTitel().hashCode() > this.getEvidenceTitel().hashCode() ? 1 : -1;
	}

}
