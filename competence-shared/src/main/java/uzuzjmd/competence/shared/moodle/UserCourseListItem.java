package uzuzjmd.competence.shared.moodle;

public class UserCourseListItem {
	private Long courseid;
	private String name;

	public UserCourseListItem() {
	}

	public UserCourseListItem(Long courseid, String name) {
		this.courseid = courseid;
		this.name = name;
	}

	public Long getCourseid() {
		return courseid;
	}

	public void setCourseid(Long courseid) {
		this.courseid = courseid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
