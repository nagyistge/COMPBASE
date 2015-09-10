package de.unipotsdam.anh.reflexion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import uzuzjmd.competence.shared.DESCRIPTORType;

@ManagedBean(name = "templateCompetenceView")
@ViewScoped
public class TemplateCompetenceView implements Serializable{

	private static final long serialVersionUID = 1L;
	private String descriptorsetName;
	
	private List<DESCRIPTORType> descriptorTypes;
	
	private List<String> competenceHeaders;
	
	@PostConstruct
	public void init() {
		descriptorsetName = "Programmieren";
		descriptorTypes = new ArrayList<DESCRIPTORType>();
		
		DESCRIPTORType desType1 = new DESCRIPTORType();
		desType1.setNAME("ich kann Funktion schreiben");
		desType1.setCOMPETENCE("pascal");
		desType1.setEVALUATIONS("gar nicht; ausreichend; befriedigend; gut");
		desType1.setGOAL((byte) 1);
		desType1.setLEVEL("niedrig");
		
		DESCRIPTORType desType2 = new DESCRIPTORType();
		desType2.setNAME("Ich kann Klasse schreiben");
		desType2.setCOMPETENCE("java");
		desType2.setEVALUATIONS("gar nicht; ausreichend; befriedigend; gut");
		desType2.setGOAL((byte) 1);
		desType2.setLEVEL("hoch");
		
		descriptorTypes.add(desType1);
		descriptorTypes.add(desType2);
		
		competenceHeaders = new ArrayList<String>();
		for(DESCRIPTORType des : descriptorTypes) {
			if(!competenceHeaders.contains(des.getCOMPETENCE())) {
				competenceHeaders.add(des.getCOMPETENCE());
			}
		}
	}

	public String getDescriptorsetName() {
		return descriptorsetName;
	}

	public void setDescriptorsetName(String descriptorsetName) {
		this.descriptorsetName = descriptorsetName;
	}

	public List<DESCRIPTORType> getDescriptorTypes() {
		return descriptorTypes;
	}

	public void setDescriptorTypes(List<DESCRIPTORType> descriptorTypes) {
		this.descriptorTypes = descriptorTypes;
	}

	public List<String> getCompetenceHeaders() {
		return competenceHeaders;
	}

	public void setCompetenceHeaders(List<String> competenceHeaders) {
		this.competenceHeaders = competenceHeaders;
	}
}
