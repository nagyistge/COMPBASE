package uzuzjmd.competence.service.rest.dto;

/**
 * A simple Wrapper class for a user, his courseContext and his role
 */
public class UserData {
	private String user;
	private String courseContext;
	private String role;

	public UserData(String user, String courseContext, String role) {
		super();
		this.user = user;
		this.courseContext = courseContext;
		this.role = role;
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
}
