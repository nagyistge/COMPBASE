package uzuzjmd.competence.service.rest.dto;

import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompetenceLinksMap {
	/*
	 * maps linked competence to the view
	 */
	private HashMap<String, List<CompetenceLinksView>> mapUserCompetenceLinks = new HashMap<String, List<CompetenceLinksView>>();

	public HashMap<String, List<CompetenceLinksView>> getMapUserCompetenceLinks() {
		return mapUserCompetenceLinks;
	}

	public void setMapUserCompetenceLinks(HashMap<String, List<CompetenceLinksView>> mapUserCompetenceLinks) {
		this.mapUserCompetenceLinks = mapUserCompetenceLinks;
	}

	public CompetenceLinksMap(HashMap<String, List<CompetenceLinksView>> mapUserCompetenceLinks) {
		super();
		this.mapUserCompetenceLinks = mapUserCompetenceLinks;
	}

}
