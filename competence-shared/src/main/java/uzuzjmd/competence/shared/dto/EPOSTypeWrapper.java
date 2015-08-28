package uzuzjmd.competence.shared.dto;

import javax.xml.bind.annotation.XmlRootElement;

import uzuzjmd.competence.shared.DESCRIPTORSETType;

@XmlRootElement
public class EPOSTypeWrapper {

	public EPOSTypeWrapper() {
		// TODO Auto-generated constructor stub
	}

	private DESCRIPTORSETType[] eposCompetences;

	public DESCRIPTORSETType[] getEposCompetences() {
		return eposCompetences;
	}

	public void setEposCompetences(DESCRIPTORSETType[] eposCompetences) {
		this.eposCompetences = eposCompetences;
	}
}
