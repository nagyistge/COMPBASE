package uzuzjmd.competence.service.rest.model.dto;

public class LinkValidationData {

	private String linkId;
	private Boolean isvalid;
	
	public LinkValidationData(String linkId, Boolean isvalid) {
		this.setLinkId(linkId);
		this.setIsvalid(isvalid);
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public Boolean getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}
}
