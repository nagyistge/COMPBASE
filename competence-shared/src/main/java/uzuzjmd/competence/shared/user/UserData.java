package uzuzjmd.competence.shared.user;

import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A simple Wrapper class for a userId, his courseContext and his role
 */
@XmlRootElement
public class UserData {
	@ApiModelProperty(value = "the id (email) of the learner the who has evidenced the competence", required = true)
	private String userId;
	private String courseContext;
	@ApiModelProperty(value = "role can either be 'teacher' or 'student'", required = true)
	private String role;

	private String printableName;
	// the lms systems the userId is enrolled in can be inclusive "moodle" < db < "mobile"
	private String lmsSystems;

	public UserData() {
	}

	public UserData(String user, String printableName, String courseContext, String role, String lmsSystems) {
		super();
		this.userId = user;
		this.courseContext = courseContext;
		this.role = role;
		this.printableName = printableName;
		this.lmsSystems = lmsSystems;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getPrintableName() {
		return printableName;
	}


	public String getLmsSystems() {
		return lmsSystems;
	}

	public void setLmsSystems(String lmsSystems) {
		this.lmsSystems = lmsSystems;
	}
}
