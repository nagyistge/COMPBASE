package uzuzjmd.competence.service.rest.dto;

import java.util.SortedSet;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompetenceLinksMap {
	/*
	 * maps linked competence to the view
	 */
	private TreeMap<String, SortedSet<CompetenceLinksView>> mapUserCompetenceLinks = new TreeMap<String, SortedSet<CompetenceLinksView>>();

	public TreeMap<String, SortedSet<CompetenceLinksView>> getMapUserCompetenceLinks() {
		return mapUserCompetenceLinks;
	}

	public void setMapUserCompetenceLinks(TreeMap<String, SortedSet<CompetenceLinksView>> mapUserCompetenceLinks) {
		this.mapUserCompetenceLinks = mapUserCompetenceLinks;
	}

	public CompetenceLinksMap(TreeMap<String, SortedSet<CompetenceLinksView>> mapUserCompetenceLinks) {
		super();
		this.mapUserCompetenceLinks = mapUserCompetenceLinks;
	}

}
