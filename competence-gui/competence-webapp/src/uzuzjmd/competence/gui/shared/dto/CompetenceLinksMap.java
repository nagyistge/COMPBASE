package uzuzjmd.competence.gui.shared.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompetenceLinksMap {

	public CompetenceLinksMap() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * maps linked competence to the view
	 */
	private Map<String, List<CompetenceLinksView>> mapUserCompetenceLinks = new HashMap<String, List<CompetenceLinksView>>();

	public Map<String, List<CompetenceLinksView>> getMapUserCompetenceLinks() {
		return mapUserCompetenceLinks;
	}

	public void setMapUserCompetenceLinks(
			Map<String, List<CompetenceLinksView>> mapUserCompetenceLinks) {
		this.mapUserCompetenceLinks = mapUserCompetenceLinks;
	}

	public CompetenceLinksMap(
			Map<String, List<CompetenceLinksView>> mapUserCompetenceLinks) {
		super();
		this.mapUserCompetenceLinks = mapUserCompetenceLinks;
	}

}
