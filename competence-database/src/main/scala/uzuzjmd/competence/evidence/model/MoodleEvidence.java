package uzuzjmd.competence.evidence.model;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso(uzuzjmd.competence.evidence.model.Evidence.class)
public class MoodleEvidence extends Evidence {

	private String course;
	private String activityTyp;

	public MoodleEvidence() {
		// TODO Auto-generated constructor stub
	}

	public MoodleEvidence(String shortname, String string, String userId,
			String course, String activityTyp) {
		super(shortname, string, userId);
		this.course = course;
		this.activityTyp = activityTyp;
	}

	public String getActivityTyp() {
		return activityTyp;
	}

	public void setActivityTyp(String activityTyp) {
		this.activityTyp = activityTyp;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
}
