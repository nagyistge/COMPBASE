package uzuzjmd.competence.shared.learningtemplate;


import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LearningTemplateData")
public class LearningTemplateData {
	@ApiModelProperty(value = "the user id (email)", required = true)
	private String userName;
	@ApiModelProperty(value = "course context (in liferay group id)", required = true)
	private String groupId;
	@ApiModelProperty(value = "the name of the template", required = false)
	private String selectedTemplate;


	public LearningTemplateData() {
	}

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
