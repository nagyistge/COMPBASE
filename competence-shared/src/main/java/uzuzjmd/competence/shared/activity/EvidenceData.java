package uzuzjmd.competence.shared.activity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class EvidenceData {

	private String creator;
	private String role;
	private String linkedUser;
	private List<String> competences;
	private List<Evidence> evidences;

	public EvidenceData() {
		printableUserName = null;
	}

	public String getPrintableUserName() {
		return printableUserName;
	}

	private final String printableUserName;

	/**
	 * This class provides a wrapper for the service
	 * to exchange data necessary to like a competence to a user who has performed it.
	 *
	 * @param course      (the context of the acquirement)
	 * @param creator     the user who created the link
	 * @param role        the role of the user who created the link (can be either
	 *                    "teacher" or "student")
	 * @param linkedUser  the user who has acquired the competences
	 * @param competences the competences acquired
	 * @param evidences   the activities that stand as evidences in the form [url,
	 *                    speakingname]
	 *
	 */
	public EvidenceData(String course, String creator, String role, String linkedUser, List<String> competences, List<Evidence> evidences, String printableUserName) {
		super();
		this.courseId = course;
		this.creator = creator;
		this.role = role;
		this.linkedUser = linkedUser;
		this.competences = competences;
		this.evidences = evidences;
		this.printableUserName = printableUserName;
	}


	public static EvidenceData instance(String course, String creator, String role, String linkedUser, List<String> competences, List<Evidence> evidences, String printableUserName) {
		return new EvidenceData(course, creator, role, linkedUser, competences, evidences, printableUserName);
	}

	private String courseId;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLinkedUser() {
		return linkedUser;
	}

	public void setLinkedUser(String linkedUser) {
		this.linkedUser = linkedUser;
	}

	public List<String> getCompetences() {
		return competences;
	}

	public void setCompetences(List<String> competences) {
		this.competences = competences;
	}


	public List<Evidence> getEvidences() {
		return evidences;
	}

	public void setEvidences(List<Evidence> evidences) {
		this.evidences = evidences;
	}
}
