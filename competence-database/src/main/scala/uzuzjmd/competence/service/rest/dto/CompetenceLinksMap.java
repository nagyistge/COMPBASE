package uzuzjmd.competence.service.rest.dto;

import java.util.List;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompetenceLinksMap {
	/*
	 * maps linked competence to the view
	 */
	private TreeMap<String, List<CompetenceLinksView>> mapUserCompetenceLinks = new TreeMap<String, List<CompetenceLinksView>>();

	public TreeMap<String, List<CompetenceLinksView>> getMapUserCompetenceLinks() {
		return mapUserCompetenceLinks;
	}

	public void setMapUserCompetenceLinks(TreeMap<String, List<CompetenceLinksView>> mapUserCompetenceLinks) {
		this.mapUserCompetenceLinks = mapUserCompetenceLinks;
	}

	public CompetenceLinksMap(TreeMap<String, List<CompetenceLinksView>> mapUserCompetenceLinks) {
		super();
		this.mapUserCompetenceLinks = mapUserCompetenceLinks;
	}

}
