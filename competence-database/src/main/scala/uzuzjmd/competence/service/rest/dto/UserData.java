package uzuzjmd.competence.service.rest.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A simple Wrapper class for a user, his courseContext and his role
 */
@XmlRootElement
public class UserData {
	private String user;
	private String courseContext;
	private String role;
	private String printableName;
	// the lms systems the user is enrolled in can be inclusive "moodle" < db < "mobile"
	private String lmsSystems;

	public UserData() {
	}

	public UserData(String user, String printableName, String courseContext, String role, String lmsSystems) {
		super();
		this.user = user;
		this.courseContext = courseContext;
		this.role = role;
		this.printableName = printableName;
		this.lmsSystems = lmsSystems;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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
