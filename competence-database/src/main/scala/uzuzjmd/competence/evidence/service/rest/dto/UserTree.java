package uzuzjmd.competence.evidence.service.rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(AbstractTreeEntry.class)
public class UserTree extends AbstractTreeEntry {

	private List<ActivityTyp> activities;

	public UserTree() {
	}

	public UserTree(List<ActivityTyp> activityTypes) {
		super();
		this.activities = activityTypes;
	}

	public UserTree(String name, String qtip, String icon,
			List<ActivityTyp> activityTypes) {
		super(name, qtip, icon);
		this.activities = activityTypes;
	}

	public List<ActivityTyp> getActivities() {
		return activities;
	}

	public void setActivities(List<ActivityTyp> activities) {
		this.activities = activities;
	}
}
