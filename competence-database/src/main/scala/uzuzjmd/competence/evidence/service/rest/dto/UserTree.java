package uzuzjmd.competence.evidence.service.rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name="activity")
@XmlSeeAlso(AbstractTreeEntry.class)
public class UserTree extends AbstractTreeEntry {

	private List<ActivityTyp> activityTypes;

	public UserTree() {
	}

	public UserTree(List<ActivityTyp> activityTypes) {
		super();
		this.setActivityTypes(activityTypes);
	}

	public UserTree(String name, String qtip, String icon,
			List<ActivityTyp> activityTypes) {
		super(name, qtip, icon);
		this.setActivityTypes(activityTypes);
	}

	@XmlElement(name="activity")
	public List<ActivityTyp> getActivityTypes() {
		return activityTypes;
	}

	public void setActivityTypes(List<ActivityTyp> activityTypes) {
		this.activityTypes = activityTypes;
	}

}
