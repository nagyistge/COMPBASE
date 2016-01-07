package uzuzjmd.competence.service.rest.dto;

public class LearningTemplateData {
	private String userName;
	private String groupId;
	private String selectedTemplate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getSelectedTemplate() {
		return selectedTemplate;
	}

	public void setSelectedTemplate(String selectedTemplate) {
		this.selectedTemplate = selectedTemplate;
	}

	public LearningTemplateData(String userName, String groupId, String selectedTemplate) {
		super();
		this.userName = userName;
		this.groupId = groupId;
		this.selectedTemplate = selectedTemplate;
	}
}
