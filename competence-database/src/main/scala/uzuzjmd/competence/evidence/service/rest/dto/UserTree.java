package uzuzjmd.competence.evidence.service.rest.dto;

import java.beans.Transient;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import uzuzjmd.competence.view.xml.AbstractTreeEntry;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * XMl-mappbare DTOs für den eigenen REST-Service (Evidenzen)
 * 
 * @author julian
 * 
 */
@XmlRootElement(name = "activity")
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

	@XmlElement(name = "activity")
	public List<ActivityTyp> getActivityTypes() {
		return activityTypes;
	}

	@Transient
	@JsonIgnore
	@XmlTransient
	public List<ActivityTyp> getActivityTypez() {
		return activityTypes;
	}

	public void setActivityTypes(List<ActivityTyp> activityTypes) {
		this.activityTypes = activityTypes;
	}

}
